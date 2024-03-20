package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
        private int Total_Region_Population;
        private int Total_City_Population;
        private String City_Population_Percentage;
        private int Total_Not_City_Population;
        private String None_City_Population_Percentage;

        // Constructor for the RegionPopulationReport class
        public RegionPopulationReport(String Region, int Total_Region_Population, int Total_City_Population, String City_Population_Percentage, int Total_Not_City_Population, String None_City_Population_Percentage) {
            this.Region = Region;
            this.Total_Region_Population = Total_Region_Population;
            this.Total_City_Population = Total_City_Population;
            this.City_Population_Percentage = City_Population_Percentage;
            this.Total_Not_City_Population = Total_Not_City_Population;
            this.None_City_Population_Percentage = None_City_Population_Percentage;
        }

        // Method to represent the object as a string
        public String toString() {
            return  "Region: " + Region + ", " +
                    "Total Region Population: " + Total_Region_Population + ", " +
                    "Total City Population: " + Total_City_Population + ", " +
                    "Total Not City Population: " + Total_Not_City_Population + ", " +
                    "City Population Percentage: " + City_Population_Percentage + ", " +
                    "None City Population Percentage: " + None_City_Population_Percentage;
        }
    }

    // Method to retrieve population data for all regions
    public static ArrayList<RegionPopulationReport> getAllRegionsPopulation(Connection con) {
        try {
            Statement stmt = con.createStatement();

            // SQL query to retrieve region-wise population data
            String strSelect = "SELECT country.Region, " +
                    "SUM(country.Population) AS Total_Region_Population, " +
                    "SUM(city.Population) AS Total_City_Population, " +
                    "CONCAT(ROUND(SUM(city.Population) / SUM(country.Population) * 100, 2),'%') AS City_Population_Percentage, " +
                    "SUM(country.Population) - SUM(city.Population) AS Total_Not_City_Population, " +
                    "CONCAT(100 - ROUND(SUM(city.Population) / SUM(country.Population) * 100, 2) ,'%') AS None_City_Population_Percentage " +
                    "FROM country " +
                    "JOIN city ON country.Code = city.CountryCode " +
                    "GROUP BY country.Region";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<RegionPopulationReport> regionReports = new ArrayList<>();

            // Iterate through the result set and create RegionPopulationReport objects
            while (rset.next()) {
                String Region = rset.getString("Region");
                int Total_Region_Population = rset.getInt("Total_Region_Population");
                int Total_City_Population = rset.getInt("Total_City_Population");
                int Total_Not_City_Population = rset.getInt("Total_Not_City_Population");
                String City_Population_Percentage = rset.getString("City_Population_Percentage");
                String None_City_Population_Percentage = rset.getString("None_City_Population_Percentage");

                // Create a RegionPopulationReport object and add it to the list
                RegionPopulationReport region = new RegionPopulationReport(Region, Total_Region_Population, Total_City_Population, City_Population_Percentage, Total_Not_City_Population, None_City_Population_Percentage);
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
        System.out.println("Region Population Report");
        // Iterate through the list of RegionPopulationReport objects and print each one
        for (RegionPopulationReport region : regions) {
            System.out.println(region);
        }
    }
}
