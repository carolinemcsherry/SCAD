package com.napier.sem;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

// the population of people, people living in cities, and people not living in cities in each country
public class User_report_28{

    // Inner class to represent population report
    public static class Population {
        private String cityName;
        private int cityPopulation;
        private String countryName;
        private int countryPopulation;

        // Constructor for Population class
        public Population(String cityName, int cityPopulation, String countryName, int countryPopulation) {
            this.cityName = cityName;
            this.cityPopulation = cityPopulation;
            this.countryName = countryName;
            this.countryPopulation = countryPopulation;
        }

        // Method to represent the object as a string
        public String toString() {
            return cityName +
                    + cityPopulation
                    + countryName
                   + countryPopulation;
        }
    }

    // Method to retrieve population data
    public static ArrayList<Population> getPopulationReport(Connection con) {
        try {
            Statement stmt = con.createStatement();

            // SQL query to retrieve population data
            String strSelect = "SELECT A.Name AS cityname, A.Population AS city_population, " +
                    "B.Name AS countryname, B.Population AS country_population " +
                    "FROM city A " +
                    "LEFT JOIN country B ON A.CountryCode = B.Code";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Population> populationList = new ArrayList<>();

            // Iterate through the result set and create Population objects
            while (rset.next()) {
                String cityName = rset.getString("cityname");
                int cityPopulation = rset.getInt("city_population");
                String countryName = rset.getString("countryname");
                int countryPopulation = rset.getInt("country_population");

                // Create a Population object and add it to the list
                Population population = new Population(cityName, cityPopulation, countryName, countryPopulation);
                populationList.add(population);
            }
            return populationList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population report");
            return null;
        }
    }

    // Method to print population data
    public static void printPopulationReport(ArrayList<Population> populationList) {
        // Check Array List  is not null
        if (populationList == null)
        {
            System.out.println("No populationList");
            return;
        }


        System.out.println("Population Report:");
        for (Population population : populationList) {
            System.out.println(population);
        }
    }
}
