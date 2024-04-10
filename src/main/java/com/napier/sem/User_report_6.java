package com.napier.sem;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class User_report_6{
//  City Report
//A city report requires the following columns:
//
//Name.
//Country.
//District.
//Population.

    public static class CityReport {
        private String cityName;
        private String countryName;
        private String district;
        private long population;

        // Constructor
        public CityReport(String cityName, String countryName, String district, long population) {
            this.cityName = cityName;
            this.countryName = countryName;
            this.district = district;
            this.population = population;
        }

        // toString method to represent the object as a string
        public String toString() {
            return  cityName +
                     countryName +
                    district +
                     population;
        }
    }

    // Method to retrieve city data for the report
    public static ArrayList<CityReport> getAllCities(Connection con) {

        String input = JOptionPane.showInputDialog("Enter the name of the city or leave blank for all citys");

        if (input.isEmpty() == true) {
            input = "%";
            System.out.println(input);
        }

        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            // SQL query to retrieve city data for the report
            String strSelect = "SELECT city.Name AS CityName, country.Name AS CountryName, city.District, city.Population FROM city JOIN country ON country.Code = city.CountryCode where city.Name like '" + input +"'";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<CityReport> cityReports = new ArrayList<>();

            // Iterate through the result set and create CityReport objects
            while (rset.next()) {
                String cityName = rset.getString("CityName");
                String countryName = rset.getString("CountryName");
                String district = rset.getString("District");
                long population = rset.getLong("Population");

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
        // Check Array List  is not null
        if (cities == null)
        {
            System.out.println("No cities");
            return;
        }
        System.out.println("Capital City Report:");
        System.out.println("City Report:");
        //format and print header
        System.out.println(String.format("%-25s %-25s %-25s %-25s", "CityName", "CountryName","District", "Population"));
        for (CityReport city : cities) {
            if (city == null)
                continue;
            //Prints table values in columbs
            String Table_string =
                    String.format("%-25s %-25s %-25s %-25s",
                            city.cityName, city.countryName, city.district , city.population);
            System.out.println(Table_string);
        }
    }
}
