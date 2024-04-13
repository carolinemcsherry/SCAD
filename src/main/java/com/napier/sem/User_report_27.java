package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
// The top N populated countries in a continent where N is provided by the user.
public class User_report_27{

    // Inner class to represent the top N populated countries in a continent report
    public static class TopCountriesInContinent {
        private String countryName;
        private String continent;
        private Long population;

        // Constructor for TopCountriesInContinent class
        public TopCountriesInContinent(String countryName, String continent, Long population) {
            this.countryName = countryName;
            this.continent = continent;
            this.population = population;

        }

        // Method to represent the object as a string
        public String toString() {
            return   countryName +
                    continent + population;
        }
    }

    // Method to retrieve top N populated countries in a continent
    public static ArrayList<TopCountriesInContinent> getTopPopulatedCountriesInContinent(Connection con) {
        // set up vars for input
        String input = "";
        boolean myBool = true;
        int i = 1;
        // check to see if user entered a number
        while (myBool == true & i <5) {
            input = JOptionPane.showInputDialog("Enter the Number of top Populated Capital City's in \r\n a continent you would like to retreive");
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
//get string from user
        String Stringinput = JOptionPane.showInputDialog("Enter the name of the Continent or leave blank for all Continent's");
// handeling null value in string to get full range
        if (Stringinput.isEmpty() == true) {
            Stringinput = "%";
        }
        try {
            Statement stmt = con.createStatement();

            // SQL query to retrieve top N populated countries in a continent
            String strSelect = "SELECT Name AS countryName, Continent, Population " +
                    "FROM country " +
                    "WHERE Continent like '" + Stringinput + "'"+
                    "ORDER BY Population DESC " +
                    "LIMIT " + input;

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<TopCountriesInContinent> topCountriesList = new ArrayList<>();

            // Iterate through the result set and create TopCountriesInContinent objects
            while (rset.next()) {
                String countryName = rset.getString("countryName");
                String continent = rset.getString("Continent");
                Long population = rset.getLong("Population");

                // Create a TopCountriesInContinent object and add it to the list
                TopCountriesInContinent topCountry = new TopCountriesInContinent(countryName, continent, population);
                topCountriesList.add(topCountry);
            }
            return topCountriesList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get top populated countries in the continent");
            return null;
        }
    }

    // Method to print top N populated countries in a continent
    public static void printTopPopulatedCountriesInContinent(ArrayList<TopCountriesInContinent> topCountriesList) {
        if (topCountriesList == null) {
            System.out.println("No countries");
            return;
        }
        System.out.println("Top Populated Countries in the Continent Report:");
        System.out.println(String.format("%-30s %-30s %-20s",  "Country", "Continent", "Population"));
        for (TopCountriesInContinent topCountry : topCountriesList) {
            if (topCountry == null) {
                System.out.println("Null country");
                continue;
            }
            System.out.println(String.format("%-30s %-30s %-20s",
                     topCountry.countryName, topCountry.continent, topCountry.population));
        }
    }}