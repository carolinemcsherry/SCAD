package com.napier.sem;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


// The population of people, people living in cities, and people not living in cities in each region.

public class User_report_18 {

    // Inner class to represent the population report of a region
    public static class TopCitiesInCountry {
        private String countryName;
        private String cityName;
        private long population;


        // Constructor for the TopCitiesInCountry class
        public TopCitiesInCountry(String countryName, String cityName, long population) {
            this.countryName = countryName;
            this.cityName = cityName;
            this.population = population;

        }

        // Method to represent the object as a string
        public String toString() {
            return   countryName + cityName +
                    population ;
        }
    }

    // Method to retrieve population data for each region
    public static ArrayList<TopCitiesInCountry> getTopPopulatedCitiesInCountry(Connection con)  {
        String input = "";
        boolean myBool = true;
        int i = 1;
        // check to see if user entered a number
        while (myBool == true & i <5) {
            input = JOptionPane.showInputDialog("Enter the Number of top Populated district's you would like");
            try
            {
                Integer.parseInt(input);
                myBool = false;
                break;
            }
            catch (NumberFormatException e)
            {
                //user gets 5 turns before stops
                System.out.println("Attempt "+i +" of 5");
                System.out.println(input + " is not a valid number!");
                myBool = true;
                i++;
            } }



        String Stringinput = JOptionPane.showInputDialog("Enter the name of the district or leave blank for all district's");

        if (Stringinput.isEmpty() == true) {
            Stringinput = "%";
            System.out.println("You picked all!");
        }

        try {
            Statement stmt = con.createStatement();

            // SQL query to retrieve population data for each region
            String strSelect = "SELECT country.Name as countryName, city.Name as cityName, city.Population from city join country on city.CountryCode = country.Code where country.Name like '" + Stringinput +"' order by city.Population DESC LIMIT " + input;

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<TopCitiesInCountry> regionReports = new ArrayList<>();

            // Iterate through the result set and create TopCitiesInCountry objects
            while (rset.next()) {
                String countryName = rset.getString("countryName");
                String cityName = rset.getString("cityName");
                long population = rset.getLong("population");

                // Create a TopCitiesInCountry object and add it to the list
                TopCitiesInCountry region = new TopCitiesInCountry(countryName,cityName, population);
                regionReports.add(region);
            }
            return regionReports;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country city population details");
            return null;
        }
    }

    // Method to print population data for each region
    public static void printTopPopulatedCitiesInCountry(ArrayList<TopCitiesInCountry> topCitiesList) {
        // Check Array List  is not null
        if (topCitiesList == null)
        {
            System.out.println("No regions");
            return;
        }
        //print report name
        System.out.println("Region Population Report");
        //format and print header
        System.out.println(String.format("%-25s %-25s %-25s", "countryName", "cityName", "population"));
        // Iterate through the list of RegionPopulationReport objects and print each one
        for (TopCitiesInCountry region : topCitiesList) {
            //If an atrabute value is null the job will continue
            if (region == null)
                continue;
            //Prints table values in columbs
            String Table_string =
                    String.format("%-25s %-25s%-25s",
                            region.countryName, region.cityName,  region.population);
            System.out.println(Table_string);
        }
    }
}
