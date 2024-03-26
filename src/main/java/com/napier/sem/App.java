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
        // Connect to database
        Connection con = connect();
       // user report 2
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

        //user report 11

        ArrayList<User_report_11.WorldPopulationReport> ReportArray9 = User_report_11.getWorldPopulationList(con);

        User_report_11.printWorldPopulationReport(ReportArray9);

        //user report 12

        ArrayList<User_report_12.PopulationbyRegionReport> ReportArray10 = User_report_12.getPopulationbyRegionReport(con);

        User_report_12.printRegionPopulation(ReportArray10);

        // User report 13

        ArrayList<User_report_13.TopCapitalCitiesInWorld> ReportArray11 = User_report_13.getTopPopulatedCapitalCitiesInWorld(con);

        User_report_13.printTopCapitalCitiesInWorld(ReportArray11);

        // User report 14

        ArrayList<User_report_14.CityReport> ReportArray12 = User_report_14.getCityReportByRegion(con);

        User_report_14.printCityReport(ReportArray12);

        // User report 15


        ArrayList<User_report_15.CapitalCitiesByContinent> ReportArray13 = User_report_15.getCapitalCitiesByContinent(con);

        User_report_15.printCapitalCitiesByContinent(ReportArray13);

        // User report 16

        ArrayList<User_report_16.CapitalCitiesWorld> ReportArray14 = User_report_16.getCapitalCitiesWorld(con);

        User_report_16.printCapitalCitiesWorld(ReportArray14);

        // User report 17

        ArrayList<User_report_17.TopCitiesInDistrict> ReportArray15 = User_report_17.getTopCitiesInDistrict(con);

        User_report_17.printTopCitiesInDistrict(ReportArray15);

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
                Thread.sleep(5000);
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

