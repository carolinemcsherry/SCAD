package com.napier.sem;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//Country Report
/*A country report requires the following columns:

Code
Name
Continent
Region
Population
Capital
City Report

 */
public class User_report_4 {

    public static class CountryReport {
        private String code;
        private String name;
        private String continent;
        private String region;
        private int population;
        private String capital;

        // Constructor
        public CountryReport(String code, String name, String continent, String region, int population, String capital) {
            this.code = code;
            this.name = name;
            this.continent = continent;
            this.region = region;
            this.population = population;
            this.capital = capital;
        }

        // toString method to represent the object as a string
        public String toString() {
            return  code +
                    name +
                   continent +
                   region +
                    population +
                     capital;
        }
    }

    // Method to retrieve country data for the report
    public static ArrayList<CountryReport> getAllCountries(Connection con) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            // SQL query to retrieve country data for the report
            String strSelect = "SELECT country.Code, country.Name, country.Continent, Region, country.Population, city.Name AS Capital " +
                    "FROM country " +
                    "LEFT JOIN city ON country.Capital = city.ID";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<CountryReport> countryReports = new ArrayList<>();

            // Iterate through the result set and create CountryReport objects
            while (rset.next()) {
                String code = rset.getString("Code");
                String name = rset.getString("Name");
                String continent = rset.getString("Continent");
                String region = rset.getString("Region");
                int population = rset.getInt("Population");
                String capital = rset.getString("Capital");

                // Create a CountryReport object and add it to the list
                CountryReport country = new CountryReport(code, name, continent, region, population, capital);
                countryReports.add(country);
            }
            return countryReports;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details for the report");
            return null;
        }
    }

    // Method to print country report
    public static void printCountryReport(ArrayList<CountryReport> countries) {
        // Check Array List  is not null
        if (countries == null)
        {
            System.out.println("No countries");
            return;
        }
        //print report name
        System.out.println("Country Report");
        //format and print header
        System.out.println(String.format("%-5s %-20s %-20s %-20s %-20s%-20s", "Code", "Name", "Continent", "Region","Population","Capital"));
        // Iterate through the list of CityReport objects and print each one
        for (CountryReport country : countries) {
            //If an attribute value is null the job will continue
            if (country == null)
                continue;
            //Prints table values in columns
            String Table_string =
                    String.format("%-5s %-20s %-20s %-20s %-20s%-20s",
                            country.code, country.name, country.continent, country.region, country.population, country.capital);
            System.out.println(Table_string);

        }
    }
}
// The End
