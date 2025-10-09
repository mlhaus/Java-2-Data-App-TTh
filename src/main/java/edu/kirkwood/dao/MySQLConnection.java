package edu.kirkwood.dao;

import java.sql.Connection;

public class MySQLConnection {
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Can't find MySQL driver.", e);
        }
        return null;
    }
}
