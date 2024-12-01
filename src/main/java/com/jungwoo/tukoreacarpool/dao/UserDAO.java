package com.jungwoo.tukoreacarpool.dao;

import com.jungwoo.tukoreacarpool.dataobject.UserDO;

import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;

public class UserDAO extends BaseDAO {
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

    public boolean validateUser(String email, String userPassword) {
        connect();
        String sql = "SELECT password FROM users WHERE email = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String password = rs.getString("password");

                return BCrypt.checkpw(userPassword, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            disconnect();
        }
        return false;
    }

    public UserDO getUserByEmail(String email) {
        connect();
        String sql = "SELECT id, username, email, verified FROM users WHERE email = ?";
        UserDO user = new UserDO();
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();

            while(rs.next()) {
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setVerified(rs.getBoolean("verified"));
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }
        return user;
    }

    public void updateUserVerified(String email) {
        connect();
        String sql = "UPDATE users SET verified = ? WHERE email = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setBoolean(1, true);
            pst.setString(2, email);
            pst.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
