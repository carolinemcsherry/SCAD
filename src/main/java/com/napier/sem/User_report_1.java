package com.napier.sem;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

// 1The population of people, people living in cities, and people not living in cities in each region.

public class User_report_1 {

    // Inner class to represent the population report for a region
    public static class PopulationReport {
        private String Region;
        private long TotalPopulation;
        private long PopulationInCities;
        private long PopulationNotInCities;

        // Constructor for the PopulationReport class
        public PopulationReport(String Region, long TotalPopulation, long PopulationInCities, long PopulationNotInCities) {
            this.Region = Region;
            this.TotalPopulation = TotalPopulation;
            this.PopulationInCities = PopulationInCities;
            this.PopulationNotInCities = PopulationNotInCities;
        }

        // Method to represent the object as a string
        public String toString() {
            return  Region +
                    TotalPopulation +
                     PopulationInCities +
                    PopulationNotInCities;
        }
    }

    // Method to retrieve population report data for each region
    public static ArrayList<PopulationReport> getPopulationByRegion(Connection con) {
        ArrayList<PopulationReport> populationReports = new ArrayList<>();

        try {
            Statement stmt = con.createStatement();

            // SQL query to retrieve population data for each region
            String strSelect = "SELECT R.Name AS Region, " +
                    "SUM(Population) AS TotalPopulation, " +
                    "SUM(CASE WHEN ID = Capital THEN Population ELSE 0 END) AS PopulationInCities, " +
                    "SUM(CASE WHEN ID != Capital THEN Population ELSE 0 END) AS PopulationNotInCities " +
                    "FROM city " +
                    "JOIN country ON city.CountryCode = country.Code " +
                    "JOIN countrylanguage ON city.CountryCode = countrylanguage.CountryCode " +
                    "JOIN region R ON country.Region = R.Code " +
                    "GROUP BY R.Name";

            ResultSet rset = stmt.executeQuery(strSelect);

            // Iterate through the result set and create PopulationReport objects
            while (rset.next()) {
                String Region = rset.getString("Region");
                long TotalPopulation = rset.getLong("TotalPopulation");
                long PopulationInCities = rset.getLong("PopulationInCities");
                long PopulationNotInCities = rset.getLong("PopulationNotInCities");

                // Create a PopulationReport object and add it to the list
                PopulationReport report = new PopulationReport(Region, TotalPopulation, PopulationInCities, PopulationNotInCities);
                populationReports.add(report);
            }

            // Close the result set and statement
            rset.close();
            stmt.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population report details");
        }

        return populationReports;
    }

    // Method to print population report data for each region
    public static void printPopulationReport(ArrayList<PopulationReport> reports) {
        if (reports == null)
        {
            System.out.println("No reports");
            return;
        }
        //print report name
        System.out.println("Population Report");
        //format and print header
        System.out.println(String.format("%-20s %-15s %-15s %-15s", "Region", "TotalPopulation", "PopulationInCities", "PopulationNotInCities"));
        // Iterate through the list of PopulationReport objects and print each one
        for (PopulationReport report : reports) {
            if (report == null)
                continue;
            String Table_string =
                    String.format("%-20s %-15s %-15s %-15s",
                            report.Region, report.TotalPopulation, report.PopulationInCities, report.PopulationNotInCities);
            System.out.println(Table_string);

        }
    }
}

