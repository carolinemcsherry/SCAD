package com.napier.sem;
import javax.swing.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;



public class App {

    public static void main(String[] args) {
        // Get user input to pick report. Returns chosen report as string
        //joption


        // Connect to database
      //  Connection con = connect();
        //log post
        System.out.println("out of connect");
       String SelectedReport = User_Input.UserReport();

       //consoul
       // Menu.runMenu(con);
        //Go in to switch to build chosen report passes selected report string and connection
     /*  Switch_Report.ReportArray(SelectedReport, con);

        //log post
        System.out.println("out of ArrayList.");
        System.out.println("Going in to if.");*/
        // Disconnect from database
        disconnect();
        System.out.println("Database has successfully disconnected");

    }


    private static Connection con = null;

     static Connection connect() {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 1; i < retries; ++i) {
            System.out.println("Connecting to database...");
            System.out.println("going in to try");
            try {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                System.out.println("going in to con");
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sql) {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(sql.getMessage());

            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");

            }
        }
        return con;
    }


    public static void disconnect() {
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

