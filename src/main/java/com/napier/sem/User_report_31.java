package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//All the countries in a continent organised by largest population to smallest.
public class User_report_31 {

    // Inner class to represent country data within a continent
    public static class CountryInContinent {
        private String countryName;
        private int population;

        // Constructor for CountryInContinent class
        public CountryInContinent(String countryName, int population) {
            this.countryName = countryName;
            this.population = population;
        }

        // Method to represent the object as a string
        public String toString() {
            return  countryName
                    + population;
        }
    }

    // Method to retrieve countries in a continent organized by largest population to smallest
    public static ArrayList<CountryInContinent> getCountriesByContinent(Connection con) {

        try {
            Statement stmt = con.createStatement();

            // SQL query to retrieve countries in a continent organized by largest population to smallest
            String strSelect = "SELECT Name AS CountryName, Population " +
                    "FROM country " +
                    "WHERE Continent = '" + "continent" + "' " +
                    "ORDER BY Population DESC";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<CountryInContinent> countriesList = new ArrayList<>();

            // Iterate through the result set and create CountryInContinent objects
            while (rset.next()) {
                String countryName = rset.getString("CountryName");
                int population = rset.getInt("Population");

                // Create a CountryInContinent object and add it to the list
                CountryInContinent country = new CountryInContinent(countryName, population);
                countriesList.add(country);
            }
            return countriesList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get countries by continent");
            return null;
        }
    }

    // Method to print countries in a continent organized by largest population to smallest
    public static void printCountriesByContinent(ArrayList<CountryInContinent> countriesList) {
        if (countriesList == null) {
            System.out.println("No countriesList");
            return;
        }

        System.out.println(String.format("%-25s %-25s", "countryName", "population"));

        for (CountryInContinent country : countriesList) {
            if (country != null) {
                String tableString = String.format("%-25s %-25s", country.countryName, country.population);
                System.out.println(tableString);
            }
        }
    }
}
