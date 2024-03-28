package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class User_report_6{
/*City Report
A city report requires the following columns:

Name
Country
District
//Population.

    public static class CityReport {
        private String cityName;
        private String countryName;
        private String district;
        private int population;

        // Constructor
        public CityReport(String cityName, String countryName, String district, int population) {
            this.cityName = cityName;
            this.countryName = countryName;
            this.district = district;
            this.population = population;
        }

        // toString method to represent the object as a string
        public String toString() {
            return "City Name: " + cityName + ", " +
                    "Country: " + countryName + ", " +
                    "District: " + district + ", " +
                    "Population: " + population;
        }
    }

    // Method to retrieve city data for the report
    public static ArrayList<CityReport> getAllCities(Connection con) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            // SQL query to retrieve city data for the report
            String strSelect = "SELECT city.Name AS CityName, country.Name AS CountryName, city.District, city.Population " +
                    "FROM city " +
                    "JOIN country ON country.Code = city.CountryCode";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<CityReport> cityReports = new ArrayList<>();

            // Iterate through the result set and create CityReport objects
            while (rset.next()) {
                String cityName = rset.getString("CityName");
                String countryName = rset.getString("CountryName");
                String district = rset.getString("District");
                int population = rset.getInt("Population");

                // Create a CityReport object and add it to the list
                CityReport city = new CityReport(cityName, countryName, district, population);
                cityReports.add(city);
            }
            return cityReports;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details for the report");
            return null;
        }
    }

    // Method to print city report
    public static void printCityReport(ArrayList<CityReport> cities) {
        System.out.println("City Report:");
        for (CityReport city : cities) {
            System.out.println(city);
        }
    }
}
