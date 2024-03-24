package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

// The top N populated cities in a country where N is provided by the user.

public class User_report_18 {

    // Inner class to represent the top N populated cities in a country report
    public static class TopCitiesInCountry {
        private String cityName;
        private String countryName;
        private int population;

        // Constructor for TopCitiesInCountry class
        public TopCitiesInCountry(String cityName, String countryName, int population) {
            this.cityName = cityName;
            this.countryName = countryName;
            this.population = population;
        }

        // Method to represent the object as a string
        public String toString() {
            return "City Name: " + cityName + ", " +
                    "Country Name: " + countryName + ", " +
                    "Population: " + population;
        }
    }

    // Method to retrieve top N populated cities in a country
    public static ArrayList<TopCitiesInCountry> getTopPopulatedCitiesInCountry(Connection con, String countryCode, int limit) {
        try {
            Statement stmt = con.createStatement();

            // SQL query to retrieve top N populated cities in a country
            String strSelect = "SELECT A.Name AS CityName, B.Name AS CountryName, A.Population " +
                    "FROM city A " +
                    "LEFT JOIN country B ON A.CountryCode = B.Code " +
                    "WHERE A.CountryCode = '" + countryCode + "' " +
                    "ORDER BY A.Population DESC " +
                    "LIMIT " + limit;

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<TopCitiesInCountry> topCitiesList = new ArrayList<>();

            // Iterate through the result set and create TopCitiesInCountry objects
            while (rset.next()) {
                String cityName = rset.getString("CityName");
                String countryName = rset.getString("CountryName");
                int population = rset.getInt("Population");

                // Create a TopCitiesInCountry object and add it to the list
                TopCitiesInCountry topCity = new TopCitiesInCountry(cityName, countryName, population);
                topCitiesList.add(topCity);
            }
            return topCitiesList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get top populated cities in the country");
            return null;
        }
    }

    // Method to print top N populated cities in a country
    public static void printTopPopulatedCitiesInCountry(ArrayList<TopCitiesInCountry> topCitiesList) {
        System.out.println("Top Populated Cities in the Country Report:");
        for (TopCitiesInCountry topCity : topCitiesList) {
            System.out.println(topCity);
        }
    }
}
