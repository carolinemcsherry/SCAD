package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

// All the countries in a region organised by largest population to smallest.
public class User_report_30{

    // Inner class to represent country data within a region
    public static class CountryInRegion {
        private String countryName;
        private int population;

        // Constructor for CountryInRegion class
        public CountryInRegion(String countryName, int population) {
            this.countryName = countryName;
            this.population = population;
        }

        // Method to represent the object as a string
        public String toString() {
            return countryName
                    + population;
        }
    }

    // Method to retrieve countries in a region organized by largest population to smallest
    public static ArrayList<CountryInRegion> getCountriesByRegion(Connection con, String region) {
        try {
            Statement stmt = con.createStatement();

            // SQL query to retrieve countries in a region organized by largest population to smallest
            String strSelect = "SELECT Name AS CountryName, Population " +
                    "FROM country " +
                    "WHERE Region = '" + region + "' " +
                    "ORDER BY Population DESC";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<CountryInRegion> countriesList = new ArrayList<>();

            // Iterate through the result set and create CountryInRegion objects
            while (rset.next()) {
                String countryName = rset.getString("CountryName");
                int population = rset.getInt("Population");

                // Create a CountryInRegion object and add it to the list
                CountryInRegion country = new CountryInRegion(countryName, population);
                countriesList.add(country);
            }
            return countriesList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get countries by region");
            return null;
        }
    }

    // Method to print countries in a region organized by largest population to smallest
    public static void printCountriesByRegion(ArrayList<CountryInRegion> countriesList) {
        // Check Array List  is not null
        if (countriesList == null)
        {
            System.out.println("No countriesList");
            return;
        }
        System.out.println(String.format("%-25s %-25s %-25s %-25s","countryName","population"));


        for (CountryInRegion countries : countriesList) {
            if (countriesList == null)
                continue;
            //Prints table values in columbs
            String Table_string =
                    String.format("%-25s %-25s %-25s %-25s",
                            countries.countryName, countries.population);
            System.out.println(Table_string);
        }
    }
}
