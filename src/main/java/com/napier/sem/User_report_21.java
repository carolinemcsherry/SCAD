
package com.napier.sem;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


//all cities within my district, sorted by population from largest to smallest

public class User_report_21 {

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

    // Method to retrieve city report data for a specific district sorted by population
    public static ArrayList<CityReport> getCityReportByDistrict(Connection con) {
        //get string from user
        String Stringinput = JOptionPane.showInputDialog("Enter the name of the district or leave blank for all district's");
// handeling null value in string to get full range
        if (Stringinput.isEmpty() == true) {
            Stringinput = "%";
        }

        try {
            Statement stmt = con.createStatement();

            // SQL query to retrieve city report data for a specific district
            String strSelect = "SELECT city.Name AS CityName, country.Name AS CountryName, city.District, city.Population " +
                    "FROM city " +
                    "LEFT JOIN country ON city.CountryCode = country.Code " +
                    "WHERE city.District Like '" + Stringinput + "' "+
                    "ORDER BY city.Population DESC";

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

        System.out.println("All City's In a District Report:");
        //format and print header
        System.out.println(String.format("%-25s %-35s %-25s %-25s", "CityName", "CountryName","District", "Population"));
        // Iterate through the list of CityReport objects and print each one
        for (CityReport city : cities)
        {
            if (city == null)
                continue;
            //Prints table values in columbs
            String Table_string =
                    String.format("%-25s %-35s %-25s %-25s",
                            city.CityName, city.CountryName, city.District , city.Population);
            System.out.println(Table_string);
        }
    }
}