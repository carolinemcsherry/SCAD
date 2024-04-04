package com.napier.sem;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

// The top N populated cities within a district, where N is determined based on planning requirement
public class User_report_17 {

    // Inner class to represent the top N populated cities in a district report
    public static class TopCitiesInDistrict {
        private String cityName;
        private String districtName;
        private int population;

        // Constructor for TopCitiesInDistrict class
        public TopCitiesInDistrict(String cityName, String districtName, int population) {
            this.cityName = cityName;
            this.districtName = districtName;
            this.population = population;
        }

        // Method to represent the object as a string
        public String toString() {
            return "City Name: " + cityName + ", " +
                    "District Name: " + districtName + ", " +
                    "Population: " + population;
        }
    }

    // Method to retrieve top N populated cities in a district
    public static ArrayList<TopCitiesInDistrict> getTopCitiesInDistrict(Connection con) {
        try {
            Statement stmt = con.createStatement();

            // SQL query to retrieve top N populated cities in a district
            String strSelect = "SELECT Name AS CityName, District, Population " +
                    "FROM city " +
                    "WHERE District = '" + "district" + "' " +
                    "ORDER BY Population DESC " +
                    "LIMIT " + "limit";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<TopCitiesInDistrict> topCitiesList = new ArrayList<>();

            // Iterate through the result set and create TopCitiesInDistrict objects
            while (rset.next()) {
                String cityName = rset.getString("CityName");
                String districtName = rset.getString("District");
                int population = rset.getInt("Population");

                // Create a TopCitiesInDistrict object and add it to the list
                TopCitiesInDistrict topCity = new TopCitiesInDistrict(cityName, districtName, population);
                topCitiesList.add(topCity);
            }
            return topCitiesList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get top populated cities in the district");
            return null;
        }
    }

    // Method to print top N populated cities in a district
    public static void printTopCitiesInDistrict(ArrayList<TopCitiesInDistrict> topCitiesInDistrictArrayList) {
        if (topCitiesInDistrictArrayList == null || topCitiesInDistrictArrayList.isEmpty()) {
            System.out.println("No top populated cities in the district to display");
            return;
        }
        System.out.println("Top Populated Cities in the District Report:");
        for (TopCitiesInDistrict topCity : topCitiesInDistrictArrayList) {
            System.out.println(topCity);
        }
    }
}
