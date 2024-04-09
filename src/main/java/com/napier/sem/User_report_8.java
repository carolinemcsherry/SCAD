package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


// The population of a country. For the population reports, the following information is requested:
//•	The name of the continent/region/country.
//•	The total population of the continent/region/country.
//•	The total population of the continent/region/country living in cities (including a %).
//•	The total population of the continent/region/country not living in cities (including a %).

public class User_report_8 {

    // Inner class to represent the population report of a region
    public static class CountryPopulation{
        private String name;
        private long totalPopulation;
        private long populationInCities;
        private String populationInCitiesPercentage;
        private long populationNotInCities;
        private String populationNotInCitiesPercentage;

        // Constructor for CountryPopulation class
        public CountryPopulation(String name, long totalPopulation, long populationInCities, String populationInCitiesPercentage, long populationNotInCities, String populationNotInCitiesPercentage) {
            this.name = name;
            this.totalPopulation = totalPopulation;
            this.populationInCities = populationInCities;
            this.populationInCitiesPercentage = populationInCitiesPercentage;
            this.populationNotInCities = populationNotInCities;
            this.populationNotInCitiesPercentage = populationNotInCitiesPercentage;
        }

        // Method to represent the object as a string
        public String toString() {
            return  name + ", " +
                     totalPopulation +
                     populationInCities +
                     populationInCitiesPercentage +
                     populationNotInCities +
                     populationNotInCitiesPercentage;
        }
    }

    // Method to retrieve country population data
    public static ArrayList<CountryPopulation> getCountryPopulation(Connection con) {
        try {
            Statement stmt = con.createStatement();

            // SQL query to retrieve country population data
            String strSelect = "SELECT country.Name, " +
                    "SUM(country.Population) AS TotalPopulation, " +
                    "SUM(city.Population) AS PopulationInCities, " +
                    "CONCAT(ROUND(SUM(city.Population) / SUM(country.Population) * 100, 2), '%') AS PopulationInCitiesPercentage, " +
                    "(SUM(country.Population) - SUM(city.Population)) AS PopulationNotInCities, " +
                    "CONCAT(ROUND((SUM(country.Population) - SUM(city.Population)) / SUM(country.Population) * 100, 2), '%') AS PopulationNotInCitiesPercentage " +
                    "FROM country " +
                    "LEFT JOIN city ON country.Code = city.CountryCode " +
                    "GROUP BY country.Name";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<CountryPopulation> countryPopulationList = new ArrayList<>();

            // Iterate through the result set and create CountryPopulation objects
            while (rset.next()) {
                String name = rset.getString("Name");
                long totalPopulation = rset.getLong("TotalPopulation");
                long populationInCities = rset.getLong("PopulationInCities");
                String populationInCitiesPercentage = rset.getString("PopulationInCitiesPercentage");
                long populationNotInCities = rset.getLong("PopulationNotInCities");
                String populationNotInCitiesPercentage = rset.getString("PopulationNotInCitiesPercentage");

                // Create a CountryPopulation object and add it to the list
                CountryPopulation countryPopulation = new CountryPopulation(name, totalPopulation, populationInCities, populationInCitiesPercentage, populationNotInCities, populationNotInCitiesPercentage);
                countryPopulationList.add(countryPopulation);
            }
            return countryPopulationList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country population data");
            return null;
        }
    }

    // Method to print country population data
    public static void printCountryPopulation(ArrayList<CountryPopulation> countryPopulationList) {
        // Check Array List  is not null
        if (countryPopulationList == null)
        {
            System.out.println("No country Population List");
            return;
        }
        //print report name
        System.out.println("Country Population Report:");
        //format and print header
        System.out.println(String.format("%-20s %-20s %-20s %-20s %-20s %-20s", "Name", "TotalPopulation", "PopulationInCities", "PopulationInCitiesPercentage", "PopulationNotInCities", "PopulationNotInCitiesPercentage"));

        for (CountryPopulation countryPopulation : countryPopulationList) {
            //If an atrabute value is null the job will continue
            if (countryPopulation == null)
                continue;
            //Prints table values in columbs
            String Table_string =
                    String.format("%-20s %-20s %-20s %-20s %-20s %-20s",
                            countryPopulation.name,countryPopulation.totalPopulation,countryPopulation.populationInCities, countryPopulation.populationInCitiesPercentage, countryPopulation.populationNotInCities, countryPopulation.populationNotInCitiesPercentage);
            System.out.println(Table_string);
        }
    }
}