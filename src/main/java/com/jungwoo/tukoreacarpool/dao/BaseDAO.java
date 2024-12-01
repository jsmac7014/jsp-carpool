package com.jungwoo.tukoreacarpool.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class BaseDAO {
    protected Connection conn = null;
    protected PreparedStatement pst = null;

    String jdbcDriver = "com.mysql.cj.jdbc.Driver";
    //    String jdbc_url = "jdbc:mysql://localhost:3307/carpool?allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC";
    String jdbcUrl = "jdbc:mysql://junction.proxy.rlwy.net:56462/railway";
    String username = "root";
    String password = "EOxtPoWWVrWnTEMkxTGTrspRibSQqJVX";

    protected void connect() {
        try {
            Class.forName(jdbcDriver);
            conn = DriverManager.getConnection(jdbcUrl, username, password);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to the database", e);
        }
    }

    protected void disconnect() {
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (pst != null) {
            try {
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
