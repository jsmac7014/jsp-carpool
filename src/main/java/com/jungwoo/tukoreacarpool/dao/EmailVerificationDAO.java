package com.jungwoo.tukoreacarpool.dao;

import com.jungwoo.tukoreacarpool.dataobject.EmailVerificationDO;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class EmailVerificationDAO extends BaseDAO {
    public void createEmailVerification(String email, String token) {
        connect();
        String sql = "INSERT INTO email_verifications (email, token, expires_at, verified) VALUES (?, ?, ?, ?)";

        // Calculate expiration time (+1 hour)
        LocalDateTime expirationTime = LocalDateTime.now().plusHours(1);
        Timestamp expiresAt = Timestamp.valueOf(expirationTime);

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, token);
            pst.setTimestamp(3, expiresAt);
            pst.setBoolean(4, false);
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }
    }

    public EmailVerificationDO getByToken(String token) {
        connect();
        String sql = "SELECT * FROM email_verifications WHERE token = ?";
        EmailVerificationDO emailVerificationDO = new EmailVerificationDO();

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, token);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                emailVerificationDO.setEmail(rs.getString("email"));
                emailVerificationDO.setToken(rs.getString("token"));
                emailVerificationDO.setExpires_at(rs.getTimestamp("expires_at"));
                emailVerificationDO.setVerified(rs.getBoolean("verified"));
            }

            return emailVerificationDO;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateTokenVerified(String token) {
        connect();
        String sql = "UPDATE email_verifications SET verified = ? WHERE token = ?";

        try {
            pst = conn.prepareStatement(sql);
            pst.setBoolean(1, true);
            pst.setString(2, token);
            pst.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
