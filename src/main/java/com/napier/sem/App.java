package com.napier.sem;
import javax.swing.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import com.napier.sem.City;

/*
* Main appliacation to run reports
* */

public class App {

   /* public static void main(String[] args) {
        App a = new App();
        int i = 0;
        a.log(++i);
        // Connect to database
        a.connect();
        a.log(++i);
        System.out.println("here");
        a.log(++i);
        con = App.con;
        a.log(++i);
        // Call getCity method from City class
        City cityInstance = new City();
        City oneCity = cityInstance.getCity(40);
        a.log(++i);
        a.disconnect();
        System.out.println("Database has successfully disconnected");


    }*/

    public static void main(String[] args) {
        App a = new App();
        int i = 0;
        a.log(++i);
        // Connect to database
        a.connect();
        a.log(++i);
        System.out.println("here");
        a.log(++i);
        con = App.con;
        a.log(++i);
        // Call getCity method from City class
        City cityInstance = new City();
        City oneCity = cityInstance.getCity(30);
        a.log(++i);

        // Check if a city was returned
        if (oneCity != null) {
            // Display city information
            cityInstance.displayCity(oneCity);
        } else {
            System.out.println("City not found.");
        }

        a.disconnect();
        System.out.println("Database has successfully disconnected");
    }



    static Connection con = null;

    public void connect()
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(10000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect()
    {
        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }
    public void log(int i){

        System.out.println("testing "+ i);

    }

}

