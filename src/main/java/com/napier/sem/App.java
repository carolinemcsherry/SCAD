package com.napier.sem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        // Database connection variables
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Successfully loaded jdbc.driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        // Connection to the database
        System.out.println("Connecting to database...");
        int retries = 100;
        for (int i = 0; i < retries; ++i) {
            try {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                System.out.println("Attempting connection to jdbc:mysql://db:3306/world?useSSL=false");
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");

                // Exit for loop
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + (i + 1)); // Increment i for attempt count
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }

        if (con != null) {
            try {
                // Create statement
                stmt = con.createStatement();
                // Execute query
                String query = "SELECT Name FROM country"; // Corrected table name to lowercase
                rs = stmt.executeQuery(query);
                // Process result set
                System.out.println("Country Names:");
                while (rs.next()) {
                    String name = rs.getString("Name");
                    System.out.println(name);
                }
            } catch (SQLException e) {
                System.out.println("Error executing query: " + e.getMessage());
            } finally {
                try {
                    // Close result set
                    if (rs != null) rs.close();
                    // Close statement
                    if (stmt != null) stmt.close();
                    // Close connection
                    if (con != null) con.close();
                    System.out.println("Connection closed successfully");
                } catch (SQLException e) {
                    System.out.println("Error closing resources: " + e.getMessage());
                }
            }
        }
    }
}
