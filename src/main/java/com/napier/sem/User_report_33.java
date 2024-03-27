package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//The top N populated countries in a region where N is provided by the user.
public class User_report_33 {

    // Inner class to represent country data in a region
    public static class CountryDataInRegion {
        private String countryName;
        private int population;

        // Constructor for CountryDataInRegion class
        public CountryDataInRegion(String countryName, int population) {
            this.countryName = countryName;
            this.population = population;
        }

        // Method to represent the object as a string
        public String toString() {
            return countryName
                    + population;
        }
    }

    // Method to retrieve the top N populated countries in a region
    public static ArrayList<CountryDataInRegion> getTopPopulatedCountriesInRegion(Connection con, String region, int N) {
        try {
            Statement stmt = con.createStatement();

            // SQL query to retrieve the top N populated countries in a region
            String strSelect = "SELECT Name AS CountryName, Population " +
                    "FROM country " +
                    "WHERE Region = '" + region + "' " +
                    "ORDER BY Population DESC " +
                    "LIMIT " + N;

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<CountryDataInRegion> countriesList = new ArrayList<>();

            // Iterate through the result set and create CountryDataInRegion objects
            while (rset.next()) {
                String countryName = rset.getString("CountryName");
                int population = rset.getInt("Population");

                // Create a CountryDataInRegion object and add it to the list
                CountryDataInRegion country = new CountryDataInRegion(countryName, population);
                countriesList.add(country);
            }
            return countriesList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get top populated countries in region");
            return null;
        }
    }

    // Method to print the top N populated countries in a region
    public static void printTopPopulatedCountriesInRegion(ArrayList<CountryDataInRegion> countriesList) {
        // Check Array List  is not null
        if (countriesList == null)
        {
            System.out.println("No countriesList");
            return;
        }

        System.out.println(String.format("%-25s %-25s %-25s %-25s","countryName","population"));


        System.out.println("Top Populated Countries in Region Report:");
        for (CountryDataInRegion country : countriesList) {
            System.out.println(country);
        }
    }
}
