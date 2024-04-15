package com.napier.sem;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
//The population of a region.
//
//For the population reports, the following information is requested:
//
//The name of the continent/region/country.
//The total population of the continent/region/country.
//The total population of the continent/region/country living in cities (including a %).
//The total population of the continent/region/country not living in cities (including a %).

public class User_report_9{

    public static class RegionPopulationReport {

        // Inner class to represent the population report of a region

        private String Region;
        private long Total_Region_Population;


        // Constructor for the RegionPopulationReport class
        public RegionPopulationReport(String Region, long Total_Region_Population) {
            this.Region = Region;
            this.Total_Region_Population = Total_Region_Population;

        }

        // Method to represent the object as a string
        public String toString() {
            return   Region +
                    Total_Region_Population ;
        }
    }

    // Method to retrieve population data for all regions
    public static ArrayList<RegionPopulationReport> getAllRegionsPopulation(Connection con) {
        try {
            Statement stmt = con.createStatement();

            // SQL query to retrieve region-wise population data
            String strSelect = "SELECT country.Region, " +
                    "SUM(country.Population) AS Total_Region_Population " +
                    "FROM country  GROUP BY country.Region";


            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<RegionPopulationReport> regionReports = new ArrayList<>();

            // Iterate through the result set and create RegionPopulationReport objects
            while (rset.next()) {
                String Region = rset.getString("Region");
                long Total_Region_Population = rset.getLong("Total_Region_Population");


                // Create a RegionPopulationReport object and add it to the list
                RegionPopulationReport region = new RegionPopulationReport(Region, Total_Region_Population);
                regionReports.add(region);
            }
            return regionReports;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get region population details");
            return null;
        }
    }

    // Method to print population data for all regions
    public static void printAllRegionsPopulation(ArrayList<RegionPopulationReport> regions) {

        // Check Array List  is not null
        if (regions == null)
        {
            System.out.println("No regions");
            return;
        }
        //print report name
        System.out.println("Region Population Report");
        //format and print header
        System.out.println(String.format("%-35s %-20s ", "Region", "Total_Region_Population"));


        // Iterate through the list of RegionPopulationReport objects and print each one
        for (RegionPopulationReport region : regions) {
            //If an atrabute value is null the job will continue
            if (region == null)
                continue;
            //Prints table values in columbs
            String Table_string =
                    String.format("%-35s %-20s ",
                            region.Region, region.Total_Region_Population);
            System.out.println(Table_string);
        }

    }
}

