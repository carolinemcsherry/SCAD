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
public class User_report_34 {

    // Inner class to represent the top N populated cities in a continent report
    public static class TopCitiesInContinent {
        private String Continent;
        private String cityName;
        private long population;


        // Constructor for TopCitiesInContinent class
        public TopCitiesInContinent(String Continent, String cityName, long population) {
            this.Continent = Continent;
            this.cityName = cityName;
            this.population = population;
                    }

        // Method to represent the object as a string
        public String toString() {
            return Continent + cityName
                    + population;

        }
    }

    // Method to retrieve top N populated cities in a continent
    public static ArrayList<TopCitiesInContinent> getTopPopulatedCitiesInContinent(Connection con) {
        // set up vars for input
        int input = 0;
        //open scanner
        Scanner scanner = new Scanner(System.in);
        // ask user what they want
        System.out.print("Enter the name of the Continent or leave blank for all Continent's: ");
        // check input
        String Stringinput = scanner.nextLine();
        if (Stringinput.isEmpty() == true) {
            //set the wild card to return all records
            Stringinput = "%";

        }
        try {
            System.out.print("Enter the number of records you want to retrieve: ");
            input = scanner.nextInt(); // Try to read an integer
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter a valid number.");

            scanner.nextInt(); // Clear the input buffer
        }

        try {
            Statement stmt = con.createStatement();

            // SQL query to retrieve top N populated cities in a continent
            String strSelect = "SELECT country.Continent, city.Name AS cityName, city.Population  " +
                    "FROM city " +
                    "JOIN country ON city.CountryCode = country.Code " +
                    "WHERE country.Continent like '" + Stringinput + "' " +
                    "ORDER BY country.Continent, city.Population DESC " +
                    "LIMIT " + input ;

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<TopCitiesInContinent> topCitiesList = new ArrayList<>();

            // Iterate through the result set and create TopCitiesInContinent objects
            while (rset.next()) {
                String Continent = rset.getString("Continent");
                String cityName = rset.getString("cityName");
                long population = rset.getLong("Population");

                // Create a TopCitiesInContinent object and add it to the list
                TopCitiesInContinent topCity = new TopCitiesInContinent(Continent, cityName, population );
                topCitiesList.add(topCity);
            }
            return topCitiesList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get top populated cities in the continent");
            return null;
        }
    }

    // Method to print top N populated cities in a continent
    public static void printTopPopulatedCitiesInContinent(ArrayList<TopCitiesInContinent> topCitiesList) {
        // Check Array List  is not null
        if (topCitiesList == null) {
            System.out.println("No topCitiesList");
            return;
        }

        System.out.println(String.format("%-35s %-35s %-25s", "continentName","cityName", "population" ));

        for (TopCitiesInContinent city : topCitiesList) {
            // Check if the city object is not null
            if (city != null) {
                //Prints table values in columns
                String tableString =
                        String.format("%-35s %-35s %-25s",
                                city.Continent, city.cityName, city.population );
                System.out.println(tableString);
            }
        }
    }
}
