package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//. The top N populated capital cities in a region where N is provided by the user.
public class User_report_12{

    // Inner class to represent the top N populated capital cities in a continent report
    public static class TopCapitalCitiesInContinent {
        private int population;
        private String capitalCityName;
        private String continent;

        // Constructor for TopCapitalCitiesInContinent class
        public TopCapitalCitiesInContinent(int population, String capitalCityName, String continent) {
            this.population = population;
            this.capitalCityName = capitalCityName;
            this.continent = continent;
        }

        // Method to represent the object as a string
        public String toString() {
            return "Population: " + population + ", " +
                    "Capital City Name: " + capitalCityName + ", " +
                    "Continent: " + continent;
        }
    }

    // Method to retrieve top N populated capital cities in a continent
    public static ArrayList<TopCapitalCitiesInContinent> getTopPopulatedCapitalCitiesInContinent(Connection con, String continent, int limit) {
        try {
            Statement stmt = con.createStatement();

            // SQL query to retrieve top N populated capital cities in a continent
            String strSelect = "SELECT country.Population, city.Name AS CapitalCity, country.Continent " +
                    "FROM city " +
                    "JOIN country ON city.ID = country.Capital " +
                    "WHERE country.Continent = '" + continent + "' " +
                    "ORDER BY country.Population DESC " +
                    "LIMIT " + limit;

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<TopCapitalCitiesInContinent> topCapitalCitiesList = new ArrayList<>();

            // Iterate through the result set and create TopCapitalCitiesInContinent objects
            while (rset.next()) {
                int population = rset.getInt("Population");
                String capitalCityName = rset.getString("CapitalCity");
                String continentName = rset.getString("Continent");

                // Create a TopCapitalCitiesInContinent object and add it to the list
                TopCapitalCitiesInContinent topCapitalCity = new TopCapitalCitiesInContinent(population, capitalCityName, continentName);
                topCapitalCitiesList.add(topCapitalCity);
            }
            return topCapitalCitiesList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get top populated capital cities in the continent");
            return null;
        }
    }

    // Method to print top N populated capital cities in a continent
    public static void printTopPopulatedCapitalCitiesInContinent(ArrayList<TopCapitalCitiesInContinent> topCapitalCitiesList) {
        System.out.println("Top Populated Capital Cities in the Continent Report:");
        for (TopCapitalCitiesInContinent topCapitalCity : topCapitalCitiesList) {
            System.out.println(topCapitalCity);
        }
    }
}
