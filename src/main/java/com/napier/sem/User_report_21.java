
package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

//all cities within my district, sorted by population from largest to smallest

public class User_report_21 {

    // Inner class to represent the city report
    public static class CityReport {
        private String CityName;
        private String CountryName;
        private String District;
        private int Population;

        // Constructor for the CityReport class
        public CityReport(String CityName, String CountryName, String District, int Population) {
            this.CityName = CityName;
            this.CountryName = CountryName;
            this.District = District;
            this.Population = Population;
        }

        // Method to represent the object as a string
        public String toString() {
            return "City Name: " + CityName + ", " +
                    "Country Name: " + CountryName + ", " +
                    "District: " + District + ", " +
                    "Population: " + Population;
        }
    }

    // Method to retrieve city report data for a specific district sorted by population
    public static ArrayList<CityReport> getCityReportByDistrict(Connection con, String district) {
        try {
            Statement stmt = con.createStatement();

            // SQL query to retrieve city report data for a specific district
            String strSelect = "SELECT A.Name AS CityName, B.Name AS CountryName, A.District, A.Population " +
                    "FROM city A " +
                    "LEFT JOIN country B ON A.CountryCode = B.Code " +
                    "WHERE A.District = '" + district + "' " +
                    "ORDER BY A.Population DESC";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<CityReport> cityReports = new ArrayList<>();

            // Iterate through the result set and create CityReport objects
            while (rset.next()) {
                String CityName = rset.getString("CityName");
                String CountryName = rset.getString("CountryName");
                String District = rset.getString("District");
                int Population = rset.getInt("Population");

                // Create a CityReport object and add it to the list
                CityReport city = new CityReport(CityName, CountryName, District, Population);
                cityReports.add(city);
            }

            // Close the result set and statement
            rset.close();
            stmt.close();

            return cityReports;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city report details");
            return null;
        }
    }

    // Method to print city report data
    public static void printCityReport(ArrayList<CityReport> cities) {
        System.out.println("City Report");
        // Iterate through the list of CityReport objects and print each one
        for (CityReport city : cities) {
            System.out.println(city);
        }
    }
}
