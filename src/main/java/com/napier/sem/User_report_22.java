package com.napier.sem;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//All the cities in a country organised by largest population to smallest.

public class User_report_22 {

    // Inner class to represent the city report
    public static class CityReport {
        private String CityName;
        private String CountryName;
        private String District;
        private Long Population;

        // Constructor for the CityReport class
        public CityReport(String CityName, String CountryName, String District, Long Population) {
            this.CityName = CityName;
            this.CountryName = CountryName;
            this.District = District;
            this.Population = Population;
        }

        // Method to represent the object as a string
        public String toString() {
            return  CityName +
                    CountryName +
                    District +
                    Population;
        }
    }

    // Method to retrieve city report data for a specific country sorted by population
    public static ArrayList<CityReport> getCityReportByCountry(Connection con, String country) {
        try {
            Statement stmt = con.createStatement();

            // SQL query to retrieve city report data for a specific country
            String strSelect = "SELECT A.Name AS CityName, B.Name AS CountryName, A.District, A.Population " +
                    "FROM city A " +
                    "LEFT JOIN country B ON A.CountryCode = B.Code " +
                    "WHERE B.Name = '" + country + "' " +
                    "ORDER BY A.Population DESC";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<CityReport> cityReports = new ArrayList<>();

            // Iterate through the result set and create CityReport objects
            while (rset.next()) {
                String CityName = rset.getString("CityName");
                String CountryName = rset.getString("CountryName");
                String District = rset.getString("District");
                Long Population = rset.getLong("Population");

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
        // Check Array List  is not null
        if (cities == null)
        {
            System.out.println("No cities");
            return;
        }
        System.out.println("Capital City Report:");
        System.out.println("City Report");
        //format and print header
        // Iterate through the list of CityReport objects and print each one
        System.out.println(String.format("%-25s %-25s %-25s %-25s", "CityName", "CountryName","District", "Population"));
        for (CityReport city : cities) {

            if (city == null)
                continue;
            //Prints table values in columbs
            String Table_string =
                    String.format("%-25s %-25s %-25s %-25s",
                            city.CityName, city.CountryName, city.District , city.Population);
            System.out.println(Table_string);
        }
    }
    }

