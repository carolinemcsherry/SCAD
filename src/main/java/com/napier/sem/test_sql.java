package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/*
        This method gets all the countries in the world organised by largest population to smallest.
         */
public class test_sql {

    public static class CapitalCityReport {
        private String cityName;
        private String countryName;
        private int population;

        // Constructor
        public CapitalCityReport(String cityName, String countryName, int population) {
            this.cityName = cityName;
            this.countryName = countryName;
            this.population = population;
        }

        public String toString() {
            return  cityName + '\'' +
                     countryName + '\'' +
                     population ;
        }
    }

    // Method to get all capital cities
    public static ArrayList<CapitalCityReport> getAllCities(Connection con) {
        try {
            System.out.println("in getAllCities.");
            // Create an SQL statement
            Statement stmt = con.createStatement();

            System.out.println("Ot of Statement stmt = con.createStatement.");

            // Create string for SQL statement
            String strSelect = "SELECT city.Name as Capital, " +
                    "country.Name as Country, city.Population " +
                    "FROM city " +
                    "JOIN country ON country.Capital = city.ID where city.id = '5'";

            System.out.println("made String strSelect.");
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            System.out.println("out of exsacute.");

            // Extract city information
            ArrayList<CapitalCityReport> capitalCities = new ArrayList<>();
            System.out.println("built ArrayList.");
            while (rset.next()) {
                String cityName = rset.getString("Capital");
                String countryName = rset.getString("Country");
                int population = rset.getInt("Population");
                CapitalCityReport city = new CapitalCityReport(cityName, countryName, population);
                capitalCities.add(city);
                System.out.println("out of while.");
            }
            System.out.println("return.");
            return capitalCities;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }

    // Method to print all capital cities
    public static void printAllCities(ArrayList<CapitalCityReport> cities) {
        System.out.println("Capital Cities Report:");
        for (CapitalCityReport city : cities) {
            System.out.println(city);
        }
    }
}
