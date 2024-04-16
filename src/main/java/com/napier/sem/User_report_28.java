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
        private String countryName;
        private long people_living_in_cities;

        private long people_not_living_in_cities ;

        // Constructor for Population class
        public Population(String countryName, long people_living_in_cities, long people_not_living_in_cities) {
            this.countryName = countryName;
            this.people_living_in_cities = people_living_in_cities;
            this.people_not_living_in_cities = people_not_living_in_cities;
        }

        // Method to represent the object as a string
        public String toString() {
            return countryName
                    + people_living_in_cities
                    + people_not_living_in_cities;
                        }
    }

    // Method to retrieve population data
    public static ArrayList<Population> getPopulationReport(Connection con) {

        try {
            Statement stmt = con.createStatement();

            // SQL query to retrieve population data
            String strSelect = "SELECT country.Name AS countryName, SUM(city.Population) AS people_living_in_cities, (country.Population - SUM(city.Population)) as people_not_living_in_cities FROM country JOIN city ON country.Code = city.CountryCode where city.CountryCode=country.Code group by country.Name, country.Population ";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Population> populationList = new ArrayList<>();

            // Iterate through the result set and create Population objects
            while (rset.next()) {
                String countryName = rset.getString("countryName");
                long people_living_in_cities = rset.getLong("people_living_in_cities");
                long people_not_living_in_cities = rset.getLong("people_not_living_in_cities");

                // Create a Population object and add it to the list
                Population population = new Population(countryName, people_living_in_cities, people_not_living_in_cities);
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
        if (populationList == null) {
            System.out.println("No populationList");
            return;
        }
        System.out.println(String.format("%-25s %-25s %-25s", "Country Name", "People Living in Cities", "People Not Living in Cities"));
        for (Population population : populationList) {
            if (population != null) {
                String tableString = String.format("%-25s %-25s %-25s ",
                        population.countryName, population.people_living_in_cities, population.people_not_living_in_cities);
                System.out.println(tableString);
            }
        }
    }
}
