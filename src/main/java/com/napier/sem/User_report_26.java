package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
// The top N populated countries in the world where N is provided by the user.
public class User_report_26{

    // Inner class to represent country data
    public static class CountryData {
        private String countryName;
        private Long population;

        // Constructor for CountryData class
        public CountryData(String countryName, Long population) {
            this.countryName = countryName;
            this.population = population;
        }

        // Method to represent the object as a string
        public String toString() {
            return countryName +
                    population;
        }
    }

    // Method to retrieve the top N populated countries in the world
    public static ArrayList<CountryData> getTopPopulatedCountries(Connection con, Long N) {
        try {
            Statement stmt = con.createStatement();

            // SQL query to retrieve the top N populated countries in the world
            String strSelect = "SELECT Name AS CountryName, Population " +
                    "FROM country " +
                    "ORDER BY Population DESC " +
                    "LIMIT " + N;

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<CountryData> countriesList = new ArrayList<>();

            // Iterate through the result set and create CountryData objects
            while (rset.next()) {
                String countryName = rset.getString("CountryName");
                Long population = rset.getLong("Population");

                // Create a CountryData object and add it to the list
                CountryData country = new CountryData(countryName, population);
                countriesList.add(country);
            }
            return countriesList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get top populated countries");
            return null;
        }
    }

    // Method to print the top N populated countries in the world
    public static void printTopPopulatedCountries(ArrayList<CountryData> countriesList) {
        System.out.println("Top Populated Countries Report:");
        for (CountryData country : countriesList) {
            System.out.println(country);
        }
    }
}
