package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


/*The population of people, people living in cities, and people not living in cities in each region. */

public class User_report_36 {

    // Inner class to represent the population data for each continent
    public static class ContinentPopulation {
        private String regionName;
        private long populationInCities;
        private long populationNotInCities;

        // Constructor for ContinentPopulation class
        public ContinentPopulation(String regionName,long populationInCities,long populationNotInCities) {
            this.regionName = regionName;
            this.populationInCities = populationInCities;
            this.populationNotInCities = populationNotInCities;
        }

        // Method to represent the object as a string
        public String toString() {
            return regionName
                    + populationInCities
                    + populationNotInCities;
        }
    }

    // Method to retrieve population data for each continent
    public static ArrayList<ContinentPopulation> getPopulationByContinent(Connection con) {

        try {
            Statement stmt = con.createStatement();

            // SQL query to retrieve population data for each continent
            String strSelect = "SELECT country.region as regionName, " +
                                        "SUM(city.Population) AS PopulationInCities, " +
                    "(country.Population - SUM(city.Population)) AS PopulationNotInCities " +
                    "FROM country " +
                    "LEFT JOIN city ON country.Code = city.CountryCode " +
                    "GROUP BY country.region, country.Population";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<ContinentPopulation> continentPopulationList = new ArrayList<>();

            // Iterate through the result set and create ContinentPopulation objects
            while (rset.next()) {
                String regionName = rset.getString("regionName");
                long populationInCities = rset.getLong("PopulationInCities");
                long populationNotInCities = rset.getLong("PopulationNotInCities");

                // Create a ContinentPopulation object and add it to the list
                ContinentPopulation continentPopulation = new ContinentPopulation(regionName, populationInCities, populationNotInCities);
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
        if (continentPopulationList == null) {
            System.out.println("No continentPopulationList");
            return;
        }

        System.out.println(String.format("%-35s %-25s %-25s", "Region Name", "Population In Cities", "Population Not In Cities"));

        for (ContinentPopulation continentPopulation : continentPopulationList) {
            if (continentPopulation != null) {
                String tableString = String.format("%-35s %-25s %-25s",
                        continentPopulation.regionName,  continentPopulation.populationInCities, continentPopulation.populationNotInCities);
                System.out.println(tableString);
            }
        }
    }
}

