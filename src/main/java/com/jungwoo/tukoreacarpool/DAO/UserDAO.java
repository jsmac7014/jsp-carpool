package com.jungwoo.tukoreacarpool.DAO;

import com.jungwoo.tukoreacarpool.DO.UserDO;

import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;

public class UserDAO {
    Connection conn = null;
    PreparedStatement pst = null;

    String jdbc_driver = "com.mysql.cj.jdbc.Driver";
    String jdbc_url = "jdbc:mysql://localhost:3307/carpool?allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC";
    String username = "root";
    String password = "";

    void connect() {
        try {
            Class.forName(jdbc_driver);
            conn = DriverManager.getConnection(jdbc_url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void disconnect() {
        if (pst != null) {
            try {
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean createUser(UserDO user) {
        connect();
        String sql = "INSERT INTO users (username, password, email, verified) VALUES (?, ?, ?, ?)";

        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, user.getUsername());
            pst.setString(2, hashedPassword);
            pst.setString(3, user.getEmail());
            pst.setBoolean(4, user.getVerified());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            disconnect();
        }
        return true;
    }

    public boolean validateUser(UserDO user) {
        connect();
        String sql = "SELECT password FROM users WHERE username = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, user.getUsername());
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String password = rs.getString("password");

                return BCrypt.checkpw(user.getPassword(), password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            disconnect();
        }
        return false;
    }
}
