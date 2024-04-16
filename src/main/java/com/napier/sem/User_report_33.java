package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.swing.JOptionPane;
// The top N populated countries in a continent where N is provided by the user.
public class User_report_33{

    // Inner class to represent the top N populated countries in a continent report
    public static class TopCountriesInContinent {
        private String region;
        private String countryName;
        private Long population;

        // Constructor for TopCountriesInContinent class
        public TopCountriesInContinent(String region, String countryName,  Long population) {
            this.region = region;
            this.countryName = countryName;
            this.population = population;
        }

        // Method to represent the object as a string
        public String toString() {
            return  region + countryName +
                     population;
        }
    }

    // Method to retrieve top N populated countries in a continent
    public static ArrayList<TopCountriesInContinent> getTopPopulatedCountriesInContinent(Connection con) {
        // set up vars for input
        int input = 0;
        //open scanner
        Scanner scanner = new Scanner(System.in);
        // ask user what they want
        System.out.print("Enter the name of the region or leave blank for all region 's: ");
        // check input
        String Stringinput = scanner.nextLine();
        if (Stringinput.isEmpty() == true) {
            //set the wild card to return all records
            Stringinput = "%";

        }
        try {
            System.out.print("Enter the number of records you want to retrieve: ");
            input = scanner.nextInt(); // Try to read an integer
            if(input == 0){

                input = 5;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter a valid number.");

            scanner.nextInt(); // Clear the input buffer
        }

// handeling null value in string to get full range
        if (Stringinput.isEmpty() == true) {
            Stringinput = "%";
        }
        try {
            Statement stmt = con.createStatement();

            // SQL query to retrieve top N populated countries in a continent
            String strSelect = "SELECT region, Name AS countryName,  Population " +
                    "FROM country " +
                    "WHERE region like '" + Stringinput + "'"+
                    "ORDER BY Population DESC " +
                    "LIMIT " + input ;


            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<TopCountriesInContinent> topCountriesList = new ArrayList<>();

            // Iterate through the result set and create TopCountriesInContinent objects
            while (rset.next()) {
                String region = rset.getString("region");
                String countryName = rset.getString("countryName");
                Long population = rset.getLong("Population");

                // Create a TopCountriesInContinent object and add it to the list
                TopCountriesInContinent topCountry = new TopCountriesInContinent(region, countryName, population);
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
        System.out.println(String.format("%-30s %-30s %-20s",  "Region", "Country", "Population"));
        for (TopCountriesInContinent topCountry : topCountriesList) {
            if (topCountry == null) {
                System.out.println("Null country");
                continue;
            }
            System.out.println(String.format("%-30s %-30s %-20s",
                    topCountry.region, topCountry.countryName, topCountry.population));
        }
    }}