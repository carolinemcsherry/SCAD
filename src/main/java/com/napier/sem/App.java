package com.napier.sem;
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
        System.out.println("going in to connect");
        if (args.length < 1) {
            a.connect("localhost:33060", 10000);
        } else {
            a.connect(args[0], Integer.parseInt(args[1]));
        }

        con = App.con;

        // user report 12
        ArrayList<User_report_12.PopulationbyRegionReport> ReportArray10 = User_report_12.getPopulationbyRegionReport(con);

        User_report_12.printRegionPopulation(ReportArray10);
        // user report 13
        ArrayList<User_report_13.TopCapitalCitiesInWorld> ReportArray11 = User_report_13.getTopPopulatedCapitalCitiesInWorld(con);

        User_report_13.printTopCapitalCitiesInWorld(ReportArray11);
        // user report 14
        ArrayList<User_report_14.CityReport> ReportArray12 = User_report_14.getCityReportByRegion(con);

        User_report_14.printCityReport(ReportArray12);

        // user report 15
        ArrayList<User_report_15.CapitalCitiesByContinent> ReportArray13 = User_report_15.getCapitalCitiesByContinent(con);

        User_report_15.printCapitalCitiesByContinent(ReportArray13);

        // user report 16
        ArrayList<User_report_16.CapitalCitiesWorld> ReportArray14 = User_report_16.getCapitalCitiesWorld(con);

        User_report_16.printCapitalCitiesWorld(ReportArray14);
        // user report 17
        ArrayList<User_report_17.TopCitiesInDistrict> ReportArray15 = User_report_17.getTopCitiesInDistrict(con);

        User_report_17.printTopCitiesInDistrict(ReportArray15);

        // user report 18
        ArrayList<User_report_18.TopCitiesInCountry> ReportArray16 = User_report_18.getTopPopulatedCitiesInCountry(con);

        User_report_18.printTopPopulatedCitiesInCountry(ReportArray16);

        // disconect from BD
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
    disconnect from bd
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

