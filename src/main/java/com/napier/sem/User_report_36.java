package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


// The population of a dISTRICT
//For the population reports, the following information is requested:
//
//The name of the continent/region/country.
//The total population of the continent/region/country.
//The total population of the continent/region/country living in cities (including a %).
//The total population of the continent/region/country not living in cities (including a %).
public class User_report_36 {
    public static class DistrictPopulationReport {
        private String DistrictName;
        private int Total_District_Population;

        // Constructor for the DistrictPopulationReport class
        public DistrictPopulationReport(String DistrictName, int Total_District_Population) {
            this.DistrictName = DistrictName;
            this.Total_District_Population = Total_District_Population;
        }

        // Method to represent the object as a string
        public String toString() {
            return "District: " + DistrictName + ", " +
                    "Total District Population: " + Total_District_Population;
        }
    }

    // Method to retrieve population data for all districts
    public static ArrayList<DistrictPopulationReport> getAllDistrictsPopulation(Connection con) {
        try {
            Statement stmt = con.createStatement();

            // SQL query to retrieve district-wise population data
            String strSelect = "SELECT district.Name AS DistrictName, " +
                    "SUM(city.Population) AS Total_District_Population " +
                    "FROM city " +
                    "JOIN country ON city.CountryCode = country.Code " +
                    "JOIN district ON city.District = district.Name " +
                    "GROUP BY district.Name";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<DistrictPopulationReport> districtReports = new ArrayList<>();

            // Iterate through the result set and create DistrictPopulationReport objects
            while (rset.next()) {
                String DistrictName = rset.getString("DistrictName");
                int Total_District_Population = rset.getInt("Total_District_Population");

                // Create a DistrictPopulationReport object and add it to the list
                DistrictPopulationReport district = new DistrictPopulationReport(DistrictName, Total_District_Population);
                districtReports.add(district);
            }
            return districtReports;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get district population details");
            return null;
        }
    }

    // Method to print population data for all districts
    public static void printAllDistrictsPopulation(ArrayList<DistrictPopulationReport> districts) {

        if (districts == null)
        {
            System.out.println("No districts");
            return;
        }
        System.out.println("District Population Report");
        // Iterate through the list of DistrictPopulationReport objects and print each one
        for (DistrictPopulationReport district : districts) {
            System.out.println(district);
        }
    }
}