package com.napier.sem;
import javax.swing.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

/*
* Main appliacation to run reports
* */

public class App {

    public static void main(String[] args) {
        App a = new App();
        a.connect("172.19.0.3:3306", 30000);
        con = App.con;
        /*
        // user report 1
        ArrayList<User_report_1.PopulationReport> ReportArrayFirst = User_report_1.getPopulationByRegion(con);

       // User_report_1.printPopulationReport(ReportArrayFirst);
        user report 2
       ArrayList<User_report_2.CapitalCityReport> ReportArray = User_report_2.getAllCapitalCities(con);

        User_report_2.printCapitalCityReport(ReportArray);

// user report 3
       ArrayList<User_report_3.CityReport> ReportArray1 = User_report_3.getCityReport(con);

       User_report_3.printCityReport(ReportArray1);
// user report 4
        ArrayList<User_report_4.CountryReport> ReportArray2 = User_report_4.getAllCountries(con);

         User_report_4.printCountryReport(ReportArray2);
// user report 5
        ArrayList<User_report_5.LanguageStats> ReportArray3 = User_report_5.getLanguageStatistics(con);

        User_report_5.printLanguageStatistics(ReportArray3);

// user report 6
        ArrayList<User_report_6.CityReport> ReportArray4 = User_report_6.getAllCities(con);

        User_report_6.printCityReport(ReportArray4);

// user report 7
        ArrayList<User_report_7.RegionPopulationReport> ReportArray5 = User_report_7.getRegionPopulation(con);

        User_report_7.printRegionPopulation(ReportArray5);
// user report 8
        ArrayList<User_report_8.CountryPopulation> ReportArray6 = User_report_8.getCountryPopulation(con);

        User_report_8.printCountryPopulation(ReportArray6);
// user report 9
        ArrayList<User_report_9.RegionPopulationReport> ReportArray7 = User_report_9.getAllRegionsPopulation(con);

        User_report_9.printAllRegionsPopulation(ReportArray7);

        // user report 10
        ArrayList<User_report_10.ContinentPopulationReport> ReportArray8 = User_report_10.getAllContinentsPopulation(con);

        User_report_10.printAllContinentsPopulation(ReportArray8);
*/
        // Disconnect from database


        disconnect();
        System.out.println("Database has successfully disconnected");

    }


    static Connection con = null;

    public void connect(String location, int delay) {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        boolean shouldWait = false;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                if (shouldWait) {
                    // Wait a bit for db to start
                    Thread.sleep(delay);
                }

                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://" + location
                                + "/world?useSSL=false",
                        "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(sqle.getMessage());

                // Let's wait before attempting to reconnect
                shouldWait = true;
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }


    public static void disconnect() {
        if (con != null) {
            try {
                // Close connection
                con.close();
                System.out.println("Disconnected from db");
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }
}

