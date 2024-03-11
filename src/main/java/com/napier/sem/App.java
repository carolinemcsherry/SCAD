package com.napier.sem;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Successfuly loaded jdbc.driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        // Connection to the database
        Connection con = null;
        System.out.println("Connection is null");
        int retries = 100;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                System.out.println("waiting 30 seconds");
                Thread.sleep(30000);
                System.out.println("30 seconds is now over");
                // Connect to database
                System.out.println("Attempting connection to jdbc:mysql://db:3306/world?useSSL=false");
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");


                System.out.println("Successfully connected");
                // Wait a bit

                Thread.sleep(10000);

                // Exit for loop
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }

        if (con != null) {
            try {
                // Close connection
                con.close();
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }
}

