package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
    private static String dbhost = "jdbc:postgresql://localhost:5433/postgres";
    private static String username = "postgres";
    private static String password = "Squeaky01";
    private static Connection conn;

    @SuppressWarnings("finally")
    public static Connection createNewDBconnection() {
        try  {
            conn = DriverManager.getConnection(
                    dbhost, username, password);
        } catch (SQLException e) {
            System.out.println("Cannot create database connection");
            e.printStackTrace();
        } finally {
            return conn;
        }
    }

}
