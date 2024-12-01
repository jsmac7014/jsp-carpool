package com.jungwoo.tukoreacarpool.controller;

import java.io.*;
import java.util.Date;

import com.jungwoo.tukoreacarpool.dao.EmailVerificationDAO;
import com.jungwoo.tukoreacarpool.dao.UserDAO;
import com.jungwoo.tukoreacarpool.dataobject.EmailVerificationDO;
import com.jungwoo.tukoreacarpool.service.EmailService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONObject;

@WebServlet("/verify-email")
public class EmailVerification extends HttpServlet {
    private EmailVerificationDAO emailVerificationDAO;
    private UserDAO userDAO;

    public void init() {
        emailVerificationDAO = new EmailVerificationDAO();
        userDAO = new UserDAO();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        req.setAttribute("title", "이메일 인증");
        req.setAttribute("content", "/verify.jsp");

        String token = req.getParameter("token");

        // check token is valid
        EmailVerificationDO emailVerificationDO = emailVerificationDAO.getByToken(token);
        if(!emailVerificationDO.getVerified()) {
            emailVerificationDAO.updateTokenVerified(token);
            Date now = new Date();

            System.out.println(token);

            if (emailVerificationDO == null) {
                req.setAttribute("message", "실패하였습니다.");
            }

            if(now.after(emailVerificationDO.getExpires_at())) {
                req.setAttribute("message", "유효기간이 만료되었습니다.");
            }

            userDAO.updateUserVerified(emailVerificationDO.getEmail());
            req.setAttribute("message", "성공적으로 인증되었습니다.");

        } else {
            req.setAttribute("message", "유효하지 않은 토큰입니다.");
        }

        RequestDispatcher rd = req.getRequestDispatcher("/layouts/main_layout.jsp");
        rd.forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        JSONObject json = new JSONObject();

        EmailService emailService = new EmailService();
        HttpSession session = req.getSession(false);
        String email = (String) session.getAttribute("email");
        System.out.println(email);
        String token = emailService.sendVerificationEmail(email);

        emailVerificationDAO.createEmailVerification(email, token);

        json.put("status", "success");
        json.put("message", "성공적으로 발송되었습니다.");
        out.println(json.toString());
    }

    public void destroy() {
    }
}