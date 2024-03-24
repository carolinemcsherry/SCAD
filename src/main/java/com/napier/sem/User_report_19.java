package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

// The top N populated capital cities in a continent where N is provided by the user
public class User_report_19 {

    // Inner class to represent capital city data in a continent
    public static class CapitalCityDataInContinent {
        private String cityName;
        private int population;

        // Constructor for CapitalCityDataInContinent class
        public CapitalCityDataInContinent(String cityName, int population) {
            this.cityName = cityName;
            this.population = population;
        }

        // Method to represent the object as a string
        public String toString() {
            return "City Name: " + cityName + ", " +
                    "Population: " + population;
        }
    }

    // Method to retrieve the top N populated capital cities in a continent
    public static ArrayList<CapitalCityDataInContinent> getTopPopulatedCapitalCitiesInContinent(Connection con, String continent, int N) {
        try {
            Statement stmt = con.createStatement();

            // SQL query to retrieve the top N populated capital cities in a continent
            String strSelect = "SELECT city.Name AS CityName, city.Population " +
                    "FROM city " +
                    "JOIN country ON city.ID = country.Capital " +
                    "WHERE country.Continent = '" + continent + "' " +
                    "ORDER BY city.Population DESC " +
                    "LIMIT " + N;

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<CapitalCityDataInContinent> citiesList = new ArrayList<>();

            // Iterate through the result set and create CapitalCityDataInContinent objects
            while (rset.next()) {
                String cityName = rset.getString("CityName");
                int population = rset.getInt("Population");

                // Create a CapitalCityDataInContinent object and add it to the list
                CapitalCityDataInContinent city = new CapitalCityDataInContinent(cityName, population);
                citiesList.add(city);
            }
            return citiesList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get top populated capital cities in continent");
            return null;
        }
    }

    // Method to print the top N populated capital cities in a continent
    public static void printTopPopulatedCapitalCitiesInContinent(ArrayList<CapitalCityDataInContinent> citiesList) {
        System.out.println("Top Populated Capital Cities in Continent Report:");
        for (CapitalCityDataInContinent city : citiesList) {
            System.out.println(city);
        }
    }
}
