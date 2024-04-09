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

    public static void main(String[] args) {
        App a = new App();
        System.out.println("going in to connect");
        if (args.length < 1) {
            a.connect("localhost:33060", 10000);
        } else {
            a.connect(args[0], Integer.parseInt(args[1]));
        }


        con = App.con;

        // Call getCity method from City class
        // user report 1
        // user report 2



// user report 28
        ArrayList<User_report_28.Population> ReportArray27 = User_report_28.getPopulationReport(con);
        User_report_28.printPopulationReport(ReportArray27);
// user report 29
        ArrayList<User_report_29.ContinentPopulation> ReportArray28 = User_report_29.getPopulationByContinent(con);
        User_report_29.printPopulationByContinent(ReportArray28);
// user report 30
        ArrayList<User_report_30.CountryInRegion> ReportArray29 = User_report_30.getCountriesByRegion(con);
        User_report_30.printCountriesByRegion(ReportArray29);
// user report 31
        ArrayList<User_report_31.CountryInContinent> ReportArray30 = User_report_31.getCountriesByContinent(con);
        User_report_31.printCountriesByContinent(ReportArray30);
// user report 32
        ArrayList<User_report_32.CountryData> ReportArray31 = User_report_32.getCountriesByPopulation(con);
        User_report_32.printCountriesByPopulation(ReportArray31);
// user report 33
        ArrayList<User_report_33.CountryDataInRegion> ReportArray32 = User_report_33.getTopPopulatedCountriesInRegion(con);
        User_report_33.printTopPopulatedCountriesInRegion(ReportArray32);
// user report 34
        ArrayList<User_report_34.TopCitiesInContinent> ReportArray33 = User_report_34.getTopPopulatedCitiesInContinent(con);
        User_report_34.printTopPopulatedCitiesInContinent(ReportArray33);



        a.disconnect();
        System.out.println("Database has successfully disconnected");
    }






    /**
     * connect from the MySQL database.
     */

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
                System.out.println("Going in to  connect");
                con = DriverManager.getConnection("jdbc:mysql://" + location
                                + "/world?allowPublicKeyRetrieval=true&useSSL=false",
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

    /*
    disconnect from dev branch
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

