package com.napier.sem;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;


// The population of people, people living in cities, and people not living in cities in each region.

public class User_report_7 {

    // Inner class to represent the population report of a region
    public static class RegionPopulationReport {
        private String district;
        private long population;


        // Constructor for the RegionPopulationReport class
        public RegionPopulationReport(String district, long population) {
            this.district = district;
            this.population = population;

        }

        // Method to represent the object as a string
        public String toString() {
            return   district +
                    population ;
        }
    }

    // Method to retrieve population data for each region
    public static ArrayList<RegionPopulationReport> getRegionPopulation(Connection con) {
        ArrayList<RegionPopulationReport> regionReports = new ArrayList<>();
        try {
            Statement stmt = con.createStatement();

            // SQL query to retrieve population data for each region
            String strSelect = "select district, population from city ";

            ResultSet rset = stmt.executeQuery(strSelect);

            // Iterate through the result set and create RegionPopulationReport objects
            while (rset.next()) {
                String district = rset.getString("district");
                long population = rset.getLong("population");

                // Create a RegionPopulationReport object and add it to the list
                RegionPopulationReport region = new RegionPopulationReport(district, population);
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
        System.out.println(String.format("%-25s %-25s", "District", "population"));
        // Iterate through the list of RegionPopulationReport objects and print each one
        for (RegionPopulationReport region : regions) {
            //If an attribute value is null the job will continue
            if (region == null)
                continue;
            //Prints table values in columns
            String Table_string =
                    String.format("%-25s %-25s",
                            region.district, region.population);
            System.out.println(Table_string);
        }
    }
    }
