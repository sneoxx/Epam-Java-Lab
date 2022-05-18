package com.zaraev.epam.javacourses.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBWorker {
    private static final String HOST = "jdbc:postgresql://localhost:5432/epamcourses?currentSchema=zaraev";
    private static final String USERNAME = "usr";
    private static final String PASSWORD = "pass";

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public DBWorker() {
        try {
            connection = DriverManager.getConnection(HOST,USERNAME,PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
