package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

// A city report requires the following columns:
/*
Name.
Country.
District.
Population.
Capital City Report*/
public class User_report_3 {

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
            return  CityName +
                    CountryName +
                    District +
                    Population;
        }
    }

    // Method to retrieve city report data
    public static ArrayList<CityReport> getCityReport(Connection con) {
        try {
            Statement stmt = con.createStatement();

            // SQL query to retrieve city report data
            String strSelect = "SELECT A.Name AS CityName, B.Name AS CountryName, A.District, A.Population " +
                    "FROM city A " +
                    "LEFT JOIN country B ON A.CountryCode = B.Code";

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
            return cityReports;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city report details");
            return null;
        }
    }

    // Method to print city report data
    public static void printCityReport(ArrayList<CityReport> cities) {
        //test for nullin arraylist
        if (cities == null)
        {
            System.out.println("No cities");
            return;
        }
        //print report name
        System.out.println("City Report");
        //format and print header
        System.out.println(String.format("%-20s %-20s %-25s %-20s" , "CityName", "CountryName", "District", "Population"));
        // Iterate through the list of CityReport objects and print each one
        for (CityReport city : cities) {
            //If an attribute value is null the job will continue
            if (city == null)
                continue;
            //Prints table values in columns
            String Table_string =
                    String.format("%-20s %-20s %-25s %-20s",
                            city.CityName, city.CountryName, city.District, city.Population);
            System.out.println(Table_string);

        }
    }
}
