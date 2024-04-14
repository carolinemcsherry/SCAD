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

        // user report 1 Continent
        ArrayList<User_report_1_Continent.PopulationReport> ReportArray1a = User_report_1_Continent.getPopulationByRegion(con);

        User_report_1_Continent.printPopulationReport(ReportArray1a);

        // user report 1 Country
        ArrayList<User_report_1_Country.PopulationReport> ReportArray1b = User_report_1_Country.getPopulationByRegion(con);

        User_report_1_Country.printPopulationReport(ReportArray1b);
        // user report 1 Region
        ArrayList<User_report_1_Region.PopulationReport> ReportArray1c = User_report_1_Region.getPopulationByRegion(con);

        User_report_1_Region.printPopulationReport(ReportArray1c);
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

        // user report 10
        ArrayList<User_report_10.ContinentPopulationReport> ReportArray8 = User_report_10.getAllContinentsPopulation(con);

        User_report_10.printAllContinentsPopulation(ReportArray8);
        // user report 11
        ArrayList<User_report_11.WorldPopulationReport> ReportArray9 = User_report_11.getWorldPopulationList(con);

        User_report_11.printWorldPopulationReport(ReportArray9);

        // user report 12
        ArrayList<User_report_12.PopulationbyRegionReport> ReportArray10 = User_report_12.getPopulationbyRegionReport(con);

        User_report_12.printRegionPopulation(ReportArray10);
        // user report 13
        ArrayList<User_report_13.TopCapitalCitiesInWorld> ReportArray11 = User_report_13.getTopPopulatedCapitalCitiesInWorld(con);

        User_report_13.printTopCapitalCitiesInWorld(ReportArray11);

        // user report 14
        ArrayList<User_report_14.PopulationbyRegionReport> ReportArray12 = User_report_14.getPopulationbyRegionReport(con);

        User_report_14.printRegionPopulation(ReportArray12);

        // user report 15
        ArrayList<User_report_15.PopulationbyContinentReport> ReportArray13 = User_report_15.getPopulationbyRegionReport(con);

        User_report_15.printRegionPopulation(ReportArray13);

        // user report 16
        ArrayList<User_report_16.CapitalCitiesWorld> ReportArray14 = User_report_16.getCapitalCitiesWorld(con);

        User_report_16.printCapitalCitiesWorld(ReportArray14);
        // user report 17
        ArrayList<User_report_17.RegionPopulationReport> ReportArray15 = User_report_17.getRegionPopulation(con);

        User_report_17.printRegionPopulation(ReportArray15);

        // user report 18
        ArrayList<User_report_18.TopCitiesInCountry> ReportArray16 = User_report_18.getTopPopulatedCitiesInCountry(con);

        User_report_18.printTopPopulatedCitiesInCountry(ReportArray16);

        // user report 19
        ArrayList<User_report_19.CapitalCityDataInContinent> ReportArray19 = User_report_19.getTopPopulatedCapitalCitiesInContinent(con);
        User_report_19.printTopPopulatedCapitalCitiesInContinent(ReportArray19);
// user report 20
        ArrayList<User_report_20.TopCitiesInWorld> ReportArray20 = User_report_20.getTopPopulatedCitiesInWorld(con);
        User_report_20.printTopPopulatedCitiesInWorld(ReportArray20);
// user report 21
        ArrayList<User_report_21.CityReport> ReportArray21 = User_report_21.getCityReportByDistrict(con);
        User_report_21.printCityReport(ReportArray21);
// user report 22
        ArrayList<User_report_22.CityReport> ReportArray22 = User_report_22.getCityReport(con);
        User_report_22.printCityReport(ReportArray22);
// user report 23
        ArrayList<User_report_23.CityReport> ReportArray23 = User_report_23.getCitiesByRegion(con);
// user report 24
        ArrayList<User_report_24.CityReport> ReportArray24 = User_report_24.getCitiesByContinent(con);
        User_report_24.printCityReport(ReportArray24);
// user report 25
        ArrayList<User_report_25.CityReport> ReportArray25 = User_report_25.getCitiesInWorld(con);
        User_report_25.printCityReport(ReportArray25);
// user report 26
        ArrayList<User_report_26.CountryData> ReportArray26 = User_report_26.getTopPopulatedCountries(con);
        User_report_26.printTopPopulatedCountries(ReportArray26);
// user report 27
        ArrayList<User_report_27.TopCountriesInContinent> ReportArray27 = User_report_27.getTopPopulatedCountriesInContinent(con);
        User_report_27.printTopPopulatedCountriesInContinent(ReportArray27);
// user report 28
        ArrayList<User_report_28.Population> ReportArray28 = User_report_28.getPopulationReport(con);
        User_report_28.printPopulationReport(ReportArray28);
// user report 29
        ArrayList<User_report_29.ContinentPopulation> ReportArray29 = User_report_29.getPopulationByContinent(con);
        User_report_29.printPopulationByContinent(ReportArray29);
// user report 30
        ArrayList<User_report_30.CountryInRegion> ReportArray30 = User_report_30.getCountriesByRegion(con);
        User_report_30.printCountriesByRegion(ReportArray30);
// user report 31
        ArrayList<User_report_31.CountryInContinent> ReportArray31 = User_report_31.getCountriesByContinent(con);
        User_report_31.printCountriesByContinent(ReportArray31);
// user report 32
        ArrayList<User_report_32.CountryData> ReportArray32 = User_report_32.getCountriesByPopulation(con);
        User_report_32.printCountriesByPopulation(ReportArray32);
// user report 33
        ArrayList<User_report_33.TopCountriesInContinent> ReportArray33 = User_report_33.getTopPopulatedCountriesInContinent(con);
        User_report_33.printTopPopulatedCountriesInContinent(ReportArray33);
// user report 34
        ArrayList<User_report_34.TopCitiesInContinent> ReportArray34 = User_report_34.getTopPopulatedCitiesInContinent(con);
        User_report_34.printTopPopulatedCitiesInContinent(ReportArray34);
        ArrayList<User_Report_35.PopulationbyRegionReport> ReportArray35 = User_Report_35.getPopulationbyRegionReport(con);
        User_Report_35.printRegionPopulation(ReportArray35);

        ArrayList<User_report_36.ContinentPopulation> ReportArray36 = User_report_36.getPopulationByContinent(con);
        User_report_36.printPopulationByContinent(ReportArray36);

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