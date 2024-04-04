package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


//To explore capital cities within a specific continent, sorted by population from largest to smallest
public class User_report_15 {

    // Inner class to represent capital cities by continent report
    public static class CapitalCitiesByContinent {
        private String cityName;
        private String countryName;
        private String continent;
        private int population;

        // Constructor for CapitalCitiesByContinent class
        public CapitalCitiesByContinent(String cityName, String countryName, String continent, int population) {
            this.cityName = cityName;
            this.countryName = countryName;
            this.continent = continent;
            this.population = population;
        }

        // Method to represent the object as a string
        public String toString() {
            return "City Name: " + cityName + ", " +
                    "Country: " + countryName + ", " +
                    "Continent: " + continent + ", " +
                    "Population: " + population;
        }
    }

    // Method to retrieve capital cities by continent
    public static ArrayList<CapitalCitiesByContinent> getCapitalCitiesByContinent(Connection con) {
        try {
            Statement stmt = con.createStatement();

            // SQL query to retrieve capital cities by continent
            String strSelect = "SELECT city.Name AS CityName, country.Name AS CountryName, country.Continent, city.Population " +
                    "FROM city " +
                    "JOIN country ON city.ID = country.Capital " +
                    "WHERE country.Continent = '" + "" + "' " +
                    "ORDER BY city.Population DESC";


            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<CapitalCitiesByContinent> capitalCitiesByContinentList = new ArrayList<>();

            // Iterate through the result set and create CapitalCitiesByContinent objects
            while (rset.next()) {
                String cityName = rset.getString("CityName");
                String countryName = rset.getString("CountryName");
                String continentName = rset.getString("Continent");
                int population = rset.getInt("Population");

                // Create a CapitalCitiesByContinent object and add it to the list
                CapitalCitiesByContinent capitalCity = new CapitalCitiesByContinent(cityName, countryName, continentName, population);
                capitalCitiesByContinentList.add(capitalCity);
            }
            return capitalCitiesByContinentList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get capital cities by continent");
            return null;
        }
    }

    // Method to print capital cities by continent
    public static void printCapitalCitiesByContinent(ArrayList<CapitalCitiesByContinent> capitalCitiesByContinentList) {
        if (capitalCitiesByContinentList == null || capitalCitiesByContinentList.isEmpty()) {
            System.out.println("No capital cities by continent to display");
            return;
        }
        System.out.println("Capital Cities By Continent Report:");
        for (CapitalCitiesByContinent capitalCity : capitalCitiesByContinentList) {
            System.out.println(capitalCity);
        }
    }
}