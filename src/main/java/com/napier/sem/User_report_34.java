package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


// The top N populated countries in a continent where N is provided by the user.
public class User_report_34 {

    // Inner class to represent the top N populated cities in a continent report
    public static class TopCitiesInContinent {
        private String cityName;
        private int population;
        private String continent;

        // Constructor for TopCitiesInContinent class
        public TopCitiesInContinent(String cityName, int population, String continent) {
            this.cityName = cityName;
            this.population = population;
            this.continent = continent;
        }

        // Method to represent the object as a string
        public String toString() {
            return cityName
                    + population
                    + continent;
        }
    }

    // Method to retrieve top N populated cities in a continent
    public static ArrayList<TopCitiesInContinent> getTopPopulatedCitiesInContinent(Connection con, String continent, int limit) {
        try {
            Statement stmt = con.createStatement();

            // SQL query to retrieve top N populated cities in a continent
            String strSelect = "SELECT city.Name AS cityName, city.Population, country.Continent " +
                    "FROM city " +
                    "JOIN country ON city.CountryCode = country.Code " +
                    "WHERE country.Continent = '" + continent + "' " +
                    "ORDER BY city.Population DESC " +
                    "LIMIT " + limit;

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<TopCitiesInContinent> topCitiesList = new ArrayList<>();

            // Iterate through the result set and create TopCitiesInContinent objects
            while (rset.next()) {
                String cityName = rset.getString("cityName");
                int population = rset.getInt("Population");
                String continentName = rset.getString("Continent");

                // Create a TopCitiesInContinent object and add it to the list
                TopCitiesInContinent topCity = new TopCitiesInContinent(cityName, population, continentName);
                topCitiesList.add(topCity);
            }
            return topCitiesList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get top populated cities in the continent");
            return null;
        }
    }

    // Method to print top N populated cities in a continent
    public static void printTopPopulatedCitiesInContinent(ArrayList<TopCitiesInContinent> topCitiesList) {
        // Check Array List  is not null
        if (topCitiesList == null) {
            System.out.println("No topCitiesList");
            return;
        }

        System.out.println(String.format("%-25s %-25s %-25s", "cityName", "population", "continentName"));

        for (TopCitiesInContinent city : topCitiesList) {
            // Check if the city object is not null
            if (city != null) {
                //Prints table values in columns
                String tableString =
                        String.format("%-25s %-25s %-25s",
                                city.cityName, city.population, city.continent);
                System.out.println(tableString);
            }
        }
    }
}
