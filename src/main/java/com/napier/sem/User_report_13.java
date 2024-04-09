package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


//The top N populated capital cities in the world where N is provided by the user.
public class User_report_13 {

    // Inner class to represent the top N populated capital cities in the world report
    public static class TopCapitalCitiesInWorld {
        private int population;
        private String capitalCityName;
        private String continent;

        // Constructor for TopCapitalCitiesInWorld class
        public TopCapitalCitiesInWorld(int population, String capitalCityName, String continent) {
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

    // Method to retrieve top N populated capital cities in the world
    public static ArrayList<TopCapitalCitiesInWorld> getTopPopulatedCapitalCitiesInWorld(Connection con) {
        try {
            Statement stmt = con.createStatement();

            // SQL query to retrieve top N populated capital cities in the world
            String strSelect = "SELECT country.Population, city.Name AS CapitalCity, country.Continent " +
                    "FROM city " +
                    "JOIN country ON city.ID = country.Capital " +
                    "ORDER BY country.Population DESC " +
                    "LIMIT " + "";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<TopCapitalCitiesInWorld> topCapitalCitiesList = new ArrayList<>();

            // Iterate through the result set and create TopCapitalCitiesInWorld objects
            while (rset.next()) {
                int population = rset.getInt("Population");
                String capitalCityName = rset.getString("CapitalCity");
                String continentName = rset.getString("Continent");

                // Create a TopCapitalCitiesInWorld object and add it to the list
                TopCapitalCitiesInWorld topCapitalCity = new TopCapitalCitiesInWorld(population, capitalCityName, continentName);
                topCapitalCitiesList.add(topCapitalCity);
            }
            return topCapitalCitiesList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get top populated capital cities in the world");
            return null;
        }
    }

    // Method to print top N populated capital cities in the world
    public static void printTopCapitalCitiesInWorld(ArrayList<TopCapitalCitiesInWorld> TopCapitalCitiesInWorld) {
        // Check if ArrayList is not null
        if (TopCapitalCitiesInWorld == null || TopCapitalCitiesInWorld.isEmpty()) {
            System.out.println("No Top Capital Cities in the World to display");
            return;
        }
        System.out.println("Top Capital Cities in the World Report:");
        for (TopCapitalCitiesInWorld city : TopCapitalCitiesInWorld) {
            System.out.println(city);
        }
    }

}




