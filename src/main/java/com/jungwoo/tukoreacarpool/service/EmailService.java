package com.jungwoo.tukoreacarpool.service;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.transactional.SendContact;
import com.mailjet.client.transactional.SendEmailsRequest;
import com.mailjet.client.transactional.TrackOpens;
import com.mailjet.client.transactional.TransactionalEmail;
import com.mailjet.client.transactional.response.SendEmailsResponse;

import java.util.UUID;

public class EmailService {
    public EmailService() {
        super();
    }

    public String sendVerificationEmail(String email) {
        ClientOptions options = ClientOptions.builder()
                .apiKey("2a86737107a6258ae809aaefc9c16509")
                .apiSecretKey("0a9e2c735a421acfabe1cc0d235f233e")
                .build();

        MailjetClient client = new MailjetClient(options);

        String token = UUID.randomUUID().toString();
        String verificationLink = "http://127.0.0.1:8080/verify-email?token=" + token;

        TransactionalEmail msg = TransactionalEmail
                .builder()
                .to(new SendContact(email))
                .from(new SendContact("carpool@tukorea.xyz"))
                .textPart(verificationLink)
                .subject("이메일 인증")
                .build();

        SendEmailsRequest request = SendEmailsRequest
                .builder()
                .message(msg)
                .build();

        try {
            SendEmailsResponse response = request.sendWith(client);
        } catch (MailjetException e) {
            throw new RuntimeException(e);
        }

        return token;
    }
}
