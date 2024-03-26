package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


// The population of people, people living in cities, and people not living in cities in each region.

public class User_report_7 {

    // Inner class to represent the population report of a region
    public static class RegionPopulationReport {
        private String Region;
        private long Total_Population;
        private long City_Population;
        private long Non_City_Population;

        // Constructor for the RegionPopulationReport class
        public RegionPopulationReport(String Region, long Total_Population, long City_Population, long Non_City_Population) {
            this.Region = Region;
            this.Total_Population = Total_Population;
            this.City_Population = City_Population;
            this.Non_City_Population = Non_City_Population;
        }

        // Method to represent the object as a string
        public String toString() {
            return   Region +
                    Total_Population +
                     City_Population +
                     Non_City_Population;
        }
    }

    // Method to retrieve population data for each region
    public static ArrayList<RegionPopulationReport> getRegionPopulation(Connection con) {
        try {
            Statement stmt = con.createStatement();

            // SQL query to retrieve population data for each region
            String strSelect = "SELECT country.Region, " +
                    "SUM(country.Population) AS Total_Population, " +
                    "SUM(city.Population) AS City_Population, " +
                    "(SUM(country.Population) - SUM(city.Population)) AS Non_City_Population " +
                    "FROM country " +
                    "LEFT JOIN city ON country.Code = city.CountryCode " +
                    "GROUP BY country.Region";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<RegionPopulationReport> regionReports = new ArrayList<>();

            // Iterate through the result set and create RegionPopulationReport objects
            while (rset.next()) {
                String Region = rset.getString("Region");
                long Total_Population = rset.getLong("Total_Population");
                long City_Population = rset.getLong("City_Population");
                long Non_City_Population = rset.getLong("Non_City_Population");

                // Create a RegionPopulationReport object and add it to the list
                RegionPopulationReport region = new RegionPopulationReport(Region, Total_Population, City_Population, Non_City_Population);
                regionReports.add(region);
            }
            return regionReports;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get region population details");
            return null;
        }
    }

    // Method to print population data for each region
    public static void printRegionPopulation(ArrayList<RegionPopulationReport> regions) {
        // Check Array List  is not null
        if (regions == null)
        {
            System.out.println("No regions");
            return;
        }
        //print report name
        System.out.println("Region Population Report");
        //format and print header
        System.out.println(String.format("%-25s%-25s%-25s%-25s%-25s", "Region", "Total_Population", "City_Population", "Non_City_Population"));
        // Iterate through the list of RegionPopulationReport objects and print each one
        for (RegionPopulationReport region : regions) {
            //If an atrabute value is null the job will continue
            if (region == null)
                continue;
            //Prints table values in columbs
            String Table_string =
                    String.format("%-25s%-25s%-25s%-25s%-25s",
                            region.Region, region.Total_Population, region.City_Population,region.Non_City_Population);
            System.out.println(Table_string);
        }
    }
    }
