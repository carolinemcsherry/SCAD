package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//All the cities in a continent organised by largest population to smallest.
public class User_report_25 {

    // Inner class to represent the city report
    public static class CityReport {
        private String CityName;
        private String CountryName;
        private Long Population;

        // Constructor for the CityReport class
        public CityReport(String CityName, String CountryName, Long Population) {
            this.CityName = CityName;
            this.CountryName = CountryName;
            this.Population = Population;
        }

        // Method to represent the object as a string
        public String toString() {
            return CityName +
                    CountryName +
                    Population;
        }
    }

    // Method to retrieve city report data for all cities in the world sorted by population
    public static ArrayList<CityReport> getCitiesInWorld(Connection con) {
        try {
            Statement stmt = con.createStatement();

            // SQL query to retrieve city report data for all cities in the world
            String strSelect = "SELECT A.Name AS CityName, B.Name AS CountryName, A.Population " +
                    "FROM city A " +
                    "JOIN country B ON A.CountryCode = B.Code " +
                    "ORDER BY A.Population DESC";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<CityReport> cityReports = new ArrayList<>();

            // Iterate through the result set and create CityReport objects
            while (rset.next()) {
                String CityName = rset.getString("CityName");
                String CountryName = rset.getString("CountryName");
                Long Population = rset.getLong("Population");

                // Create a CityReport object and add it to the list
                CityReport city = new CityReport(CityName, CountryName, Population);
                cityReports.add(city);
            }

            // Close the result set and statement
            rset.close();
            stmt.close();

            return cityReports;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city report details");
            return null;
        }
    }

    // Method to print city report data
    public static void printCityReport(ArrayList<CityReport> cities) {
        // Check Array List  is not null
        if (cities == null)
        {
            System.out.println("No cities");
            return;
        }

        System.out.println("All Cities in the world organised by largest population to smallest");
        //format and print header
        System.out.println(String.format("%-25s %-35s %-25s ", "City Name", "Country Name", "Population"));
        // Iterate through the list of CityReport objects and print each one
        for (CityReport city : cities) {
            if (city == null)
                continue;
            //Prints table values in columbs
            String Table_string =
                    String.format("%-25s %-35s %-25s",
                            city.CityName, city.CountryName,  city.Population);
            System.out.println(Table_string);
        }
    }
}