package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
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

                //open scanner
        Scanner scanner = new Scanner(System.in);
                int input = 10; // Default value
        try {

            System.out.print("Enter the number of records you want to retrieve: ");
            input = scanner.nextInt();
        } catch (NoSuchElementException e) {
            // No user input, continue with default value
        }
        try {
            Statement stmt = con.createStatement();

            // SQL query to retrieve top N populated countries in a continent
            String strSelect = "SELECT Name AS countryName, Continent, Population " +
                    "FROM country " +
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