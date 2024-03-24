package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class User_report_14 {

    public static class CityByRegion{
        private String cityName;
        private String countryName;
        private String region;
        private int population;

        // Constructor for CityByRegion class
        public CityByRegion(String cityName, String countryName, String region, int population) {
            this.cityName = cityName;
            this.countryName = countryName;
            this.region = region;
            this.population = population;
        }

        // Method to represent the object as a string
        public String toString() {
            return "City Name: " + cityName + ", " +
                    "Country: " + countryName + ", " +
                    "Region: " + region + ", " +
                    "Population: " + population;
        }
    }

    // Method to retrieve cities by region
    public static ArrayList<CityByRegion> getCitiesByRegion(Connection con) {
        try {
            Statement stmt = con.createStatement();

            // SQL query to retrieve cities by region
            String strSelect = "SELECT city.Name AS CityName, country.Name AS CountryName, country.Region, city.Population " +
                    "FROM city A " +
                    "LEFT JOIN country B ON A.CountryCode = B.Code " +
                    "ORDER BY city.Population DESC";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<CityByRegion> cityByRegionList = new ArrayList<>();

            // Iterate through the result set and create CityByRegion objects
            while (rset.next()) {
                String cityName = rset.getString("CityName");
                String countryName = rset.getString("CountryName");
                String region = rset.getString("Region");
                int population = rset.getInt("Population");

                // Create a CityByRegion object and add it to the list
                CityByRegion cityByRegion = new CityByRegion(cityName, countryName, region, population);
                cityByRegionList.add(cityByRegion);
            }
            return cityByRegionList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get cities by region");
            return null;
        }
    }

    // Method to print cities by region
    public static void printCitiesByRegion(ArrayList<CityByRegion> cityByRegionList) {

        if (cityByRegionList == null)
        {
            System.out.println("No city By Region List");
            return;
        }
        System.out.println("Cities By Region Report:");
        for (CityByRegion cityByRegion : cityByRegionList) {
            System.out.println(cityByRegion);
        }
    }
}
