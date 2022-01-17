package com.example.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    private static Connection conn = null;
    private static final String URL = "jdbc:mysql://localhost/";
    private static final String DB_NAME = "crudwebapp";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    public static Connection getConnect() throws ClassNotFoundException, SQLException {
        if (conn == null) {

            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL + DB_NAME, USERNAME, PASSWORD);

          
        }
          return conn;

    }
}
