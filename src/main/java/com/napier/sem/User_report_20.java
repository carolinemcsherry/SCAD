package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//The top N populated cities in the world where N is provided by the user.
public class User_report_20 {

    // Inner class to represent the top N populated cities in the world report
    public static class TopCitiesInWorld {
        private String cityName;
        private Long population;

        // Constructor for TopCitiesInWorld class
        public TopCitiesInWorld(String cityName, Long population) {
            this.cityName = cityName;
            this.population = population;
        }

        // Method to represent the object as a string
        public String toString() {
            return  cityName +
                    population;
        }
    }

    // Method to retrieve top N populated cities in the world
    public static ArrayList<TopCitiesInWorld> getTopPopulatedCitiesInWorld(Connection con, Long limit) {
        try {
            Statement stmt = con.createStatement();

            // SQL query to retrieve top N populated cities in the world
            String strSelect = "SELECT Name AS CityName, Population " +
                    "FROM city " +
                    "ORDER BY Population DESC " +
                    "LIMIT " + limit;

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<TopCitiesInWorld> topCitiesList = new ArrayList<>();

            // Iterate through the result set and create TopCitiesInWorld objects
            while (rset.next()) {
                String cityName = rset.getString("CityName");
                Long population = rset.getLong("Population");

                // Create a TopCitiesInWorld object and add it to the list
                TopCitiesInWorld topCity = new TopCitiesInWorld(cityName, population);
                topCitiesList.add(topCity);
            }
            return topCitiesList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get top populated cities in the world");
            return null;
        }
    }

    // Method to print top N populated cities in the world
    public static void printTopPopulatedCitiesInWorld(ArrayList<TopCitiesInWorld> topCitiesList) {
        // Check Array List  is not null
        if (topCitiesList == null)
        {
            System.out.println("No cities");
            return;
        }

        System.out.println("Top Populated Cities in the World Report:");
        System.out.println("City Report:");
                // format and print header
        System.out.println(String.format("%-25s %-25s %-25s %-25s", "CityName", "Population"));
        for (TopCitiesInWorld topCity : topCitiesList) {
            if (topCitiesList == null)
                continue;
            //Prints table values in columbs
            String Table_string =
                    String.format("%-25s %-25s %-25s %-25s",
                            topCity.cityName, topCity.population);
            System.out.println(Table_string);
        }
    }
}
