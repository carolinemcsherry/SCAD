package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
// The top N populated countries in a continent where N is provided by the user.
public class User_report_27{

    // Inner class to represent the top N populated countries in a continent report
    public static class TopCountriesInContinent {
        private int population;
        private String countryName;
        private String continent;

        // Constructor for TopCountriesInContinent class
        public TopCountriesInContinent(int population, String countryName, String continent) {
            this.population = population;
            this.countryName = countryName;
            this.continent = continent;
        }

        // Method to represent the object as a string
        public String toString() {
            return "Population: " + population + ", " +
                    "Country Name: " + countryName + ", " +
                    "Continent: " + continent;
        }
    }

    // Method to retrieve top N populated countries in a continent
    public static ArrayList<TopCountriesInContinent> getTopPopulatedCountriesInContinent(Connection con, String continent, int limit) {
        try {
            Statement stmt = con.createStatement();

            // SQL query to retrieve top N populated countries in a continent
            String strSelect = "SELECT Population, Name AS country, Continent " +
                    "FROM country " +
                    "WHERE Continent = '" + continent + "' " +
                    "ORDER BY Population DESC " +
                    "LIMIT " + limit;

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<TopCountriesInContinent> topCountriesList = new ArrayList<>();

            // Iterate through the result set and create TopCountriesInContinent objects
            while (rset.next()) {
                int population = rset.getInt("Population");
                String countryName = rset.getString("country");
                String continentName = rset.getString("Continent");

                // Create a TopCountriesInContinent object and add it to the list
                TopCountriesInContinent topCountry = new TopCountriesInContinent(population, countryName, continentName);
                topCountriesList.add(topCountry);
            }
            return topCountriesList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get top populated countries in the continent");
            return null;
        }
    }

    // Method to print top N populated countries in a continent
    public static void printTopPopulatedCountriesInContinent(ArrayList<TopCountriesInContinent> topCountriesList) {
        System.out.println("Top Populated Countries in the Continent Report:");
        for (TopCountriesInContinent topCountry : topCountriesList) {
            System.out.println(topCountry);
        }
    }
}
