package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
public class User_report_2{
    // User Report 2 - Capital City Report
/*A Capital City Report
Name
Country
Population
.*/
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

        // toString method to represent the object as a string
        public String toString() {
            return  cityName +
                    countryName +
                    population;
        }
    }

    // Method to retrieve capital city data for the report.
    public static ArrayList<CapitalCityReport> getAllCapitalCities(Connection con) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            // SQL query to retrieve capital city data for the report
            String strSelect = "SELECT city.Name AS CityName, country.Name AS CountryName, city.Population " +
                    "FROM city " +
                    "JOIN country ON country.Capital = city.ID ";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<CapitalCityReport> capitalCities = new ArrayList<>();

            // Iterate through the result set and create CapitalCityReport objects
            while (rset.next()) {
                String cityName = rset.getString("CityName");
                String countryName = rset.getString("CountryName");
                int population = rset.getInt("Population");

                // Create a CapitalCityReport object and add it to the list
                CapitalCityReport city = new CapitalCityReport(cityName, countryName, population);
                capitalCities.add(city);
            }
            return capitalCities;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get capital city details for the report");
            return null;
        }
    }

    // Method to print capital city report
    public static void printCapitalCityReport(ArrayList<CapitalCityReport> cities) {
        // Check Array List  is not null
        if (cities == null)
        {
            System.out.println("No cities");
            return;
        }
        //print report name
        System.out.println("Capital City Report:");
        //format and print header
        System.out.println(String.format("%-30s %-15s %-20s", "Capital City Name", "Country Name", "Population"));

        for (CapitalCityReport city : cities) {
            //If an attribute value is null the job will continue
            if (city == null)
                continue;
            //Prints table values in columns
            String Table_string =
                    String.format("%-10s %-15s %-20s",
                            city.cityName, city.countryName, city.population);
            System.out.println(Table_string);




        }
    }
}

//End
