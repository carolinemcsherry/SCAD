package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

// All the countries in the world organised by largest population to smallest.

public class User_report_32{

    // Inner class to represent country data
    public static class CountryData {
        private String countryName;
        private int population;

        // Constructor for CountryData class
        public CountryData(String countryName, int population) {
            this.countryName = countryName;
            this.population = population;
        }

        // Method to represent the object as a string
        public String toString() {
            return countryName
                    + population;
        }
    }

    // Method to retrieve all countries in the world organized by largest population to smallest
    public static ArrayList<CountryData> getCountriesByPopulation(Connection con) {
        try {
            Statement stmt = con.createStatement();

            // SQL query to retrieve all countries in the world organized by largest population to smallest
            String strSelect = "SELECT Name AS CountryName, Population " +
                    "FROM country " +
                    "ORDER BY Population DESC";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<CountryData> countriesList = new ArrayList<>();

            // Iterate through the result set and create CountryData objects
            while (rset.next()) {
                String countryName = rset.getString("CountryName");
                int population = rset.getInt("Population");

                // Create a CountryData object and add it to the list
                CountryData country = new CountryData(countryName, population);
                countriesList.add(country);
            }
            return countriesList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get countries by population");
            return null;
        }
    }

    // Method to print all countries in the world organized by largest population to smallest
    public static void printCountriesByPopulation(ArrayList<CountryData> countriesList) {
        // Check Array List  is not null
        if (countriesList == null)
        {
            System.out.println("No countriesList");
            return;
        }
        System.out.println(String.format("%-25s %-25s %-25s %-25s","countryName","population"));


    }
}
