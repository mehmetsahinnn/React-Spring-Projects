package com.humanresources.hr.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection connection;

    public static void connect(String url, String username, String password) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            url = "jdbc:mysql://localhost:3306/hrmanagement?useSSL=false&serverTimezone=UTC";
            username = "root";
            password = "1234";
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        connect("jdbc:mysql://localhost:3306/hrmanagement?useSSL=false&serverTimezone=UTC", "root", "1234");
        return connection;
    }
}
