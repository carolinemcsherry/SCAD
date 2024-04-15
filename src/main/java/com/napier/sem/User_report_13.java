package com.napier.sem;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


//The top N populated capital cities in the world where N is provided by the user.
public class User_report_13 {

    // Inner class to represent the top N populated capital cities in the world report
    public static class TopCapitalCitiesInWorld {
        private String capitalCityName;
        private int population;



        // Constructor for TopCapitalCitiesInWorld class
        public TopCapitalCitiesInWorld(int population, String capitalCityName) {
            this.capitalCityName = capitalCityName;
            this.population = population;


        }

        // Method to represent the object as a string
        public String toString() {
            return  capitalCityName +
             population;
        }
    }

    // Method to retrieve top N populated capital cities in the world
    public static ArrayList<TopCapitalCitiesInWorld> getTopPopulatedCapitalCitiesInWorld(Connection con) {
        //Var set up for methord
        ArrayList<TopCapitalCitiesInWorld> topCapitalCitiesList = new ArrayList<>();
        int input =0;
        //new  scanner
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter the Number of top Populated Region's: ");
             input = scanner.nextInt(); // Try to read an integer
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter a valid number.");

            scanner.nextInt(); // Clear the input buffer
        }


        try {
            Statement stmt = con.createStatement();

            // SQL query to retrieve top N populated capital cities in the world
            String strSelect = "SELECT  city.Name AS CapitalCity, city.Population" +
                    " FROM city " +
                    "JOIN country ON city.ID = country.Capital " +
                    "ORDER BY city.Population DESC " +
                    "LIMIT " + input;

            ResultSet rset = stmt.executeQuery(strSelect);

            // Iterate through the result set and create TopCapitalCitiesInWorld objects
            while (rset.next()) {
                int population = rset.getInt("Population");
                String capitalCityName = rset.getString("CapitalCity");


                // Create a TopCapitalCitiesInWorld object and add it to the list
                TopCapitalCitiesInWorld topCapitalCity = new TopCapitalCitiesInWorld(population, capitalCityName);
                topCapitalCitiesList.add(topCapitalCity);
            }
            return topCapitalCitiesList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get top populated capital cities in the world");
            return null;
        }
    }

    // Method to print top N populated capital cities in the world
    public static void printTopCapitalCitiesInWorld(ArrayList<TopCapitalCitiesInWorld> TopCapitalCitiesInWorld) {
        // Check if ArrayList is not null
        if (TopCapitalCitiesInWorld == null ) {
            System.out.println("No Top Capital Cities in the World to display");
            return;
        }
        System.out.println("Top Capital Cities in the World Report:");
        //print header
        System.out.println(String.format("%-25s %-20s",  "Capatial City", "Population"));

        for (TopCapitalCitiesInWorld city : TopCapitalCitiesInWorld) {
            if (city == null) {
                System.out.println("Encountered a null region");
                continue;
            }
            String emp_string =
                    String.format("%-25s %-20s ",
                             city.capitalCityName, city.population);
            System.out.println(emp_string);
            //    System.out.println(continent);
        }
        }
    }




