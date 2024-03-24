package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//The population of people, people living in cities, and people not living in cities in each continent.

public class User_report_29 {

    // Inner class to represent the population data for each continent
    public static class ContinentPopulation {
        private String continentName;
        private int totalPopulation;
        private int populationInCities;
        private int populationNotInCities;

        // Constructor for ContinentPopulation class
        public ContinentPopulation(String continentName, int totalPopulation, int populationInCities, int populationNotInCities) {
            this.continentName = continentName;
            this.totalPopulation = totalPopulation;
            this.populationInCities = populationInCities;
            this.populationNotInCities = populationNotInCities;
        }

        // Method to represent the object as a string
        public String toString() {
            return "Continent: " + continentName + ", " +
                    "Total Population: " + totalPopulation + ", " +
                    "Population in Cities: " + populationInCities + ", " +
                    "Population Not in Cities: " + populationNotInCities;
        }
    }

    // Method to retrieve population data for each continent
    public static ArrayList<ContinentPopulation> getPopulationByContinent(Connection con) {
        try {
            Statement stmt = con.createStatement();

            // SQL query to retrieve population data for each continent
            String strSelect = "SELECT country.Continent, " +
                    "SUM(country.Population) AS TotalPopulation, " +
                    "SUM(city.Population) AS PopulationInCities, " +
                    "(SUM(country.Population) - SUM(city.Population)) AS PopulationNotInCities " +
                    "FROM country " +
                    "LEFT JOIN city ON country.Code = city.CountryCode " +
                    "GROUP BY country.Continent";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<ContinentPopulation> continentPopulationList = new ArrayList<>();

            // Iterate through the result set and create ContinentPopulation objects
            while (rset.next()) {
                String continentName = rset.getString("Continent");
                int totalPopulation = rset.getInt("TotalPopulation");
                int populationInCities = rset.getInt("PopulationInCities");
                int populationNotInCities = rset.getInt("PopulationNotInCities");

                // Create a ContinentPopulation object and add it to the list
                ContinentPopulation continentPopulation = new ContinentPopulation(continentName, totalPopulation, populationInCities, populationNotInCities);
                continentPopulationList.add(continentPopulation);
            }
            return continentPopulationList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population data by continent");
            return null;
        }
    }

    // Method to print population data for each continent
    public static void printPopulationByContinent(ArrayList<ContinentPopulation> continentPopulationList) {
        System.out.println("Population by Continent Report:");
        for (ContinentPopulation continentPopulation : continentPopulationList) {
            System.out.println(continentPopulation);
        }
    }
}
