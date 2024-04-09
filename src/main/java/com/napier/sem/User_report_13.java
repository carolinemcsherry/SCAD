package com.napier.sem;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;


//The top N populated capital cities in the world where N is provided by the user.
public class User_report_13 {

    // Inner class to represent the top N populated capital cities in the world report
    public static class TopCapitalCitiesInWorld {
        private int population;
        private String capitalCityName;
        private String continent;

        // Constructor for TopCapitalCitiesInWorld class
        public TopCapitalCitiesInWorld(int population, String capitalCityName, String continent) {
            this.population = population;
            this.capitalCityName = capitalCityName;
            this.continent = continent;
        }

        // Method to represent the object as a string
        public String toString() {
            return  population +
                    capitalCityName +
                     continent;
        }
    }

    // Method to retrieve top N populated capital cities in the world
    public static ArrayList<TopCapitalCitiesInWorld> getTopPopulatedCapitalCitiesInWorld(Connection con) {

        String input = "";
        input = JOptionPane.showInputDialog("Enter the Number of top Populated Region's");
        try {
            Statement stmt = con.createStatement();

            // SQL query to retrieve top N populated capital cities in the world
            String strSelect = "SELECT country.Population, city.Name AS CapitalCity, country.Continent " +
                    "FROM city " +
                    "JOIN country ON city.ID = country.Capital " +
                    "ORDER BY country.Population DESC " +
                    "LIMIT " + input;

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<TopCapitalCitiesInWorld> topCapitalCitiesList = new ArrayList<>();

            // Iterate through the result set and create TopCapitalCitiesInWorld objects
            while (rset.next()) {
                int population = rset.getInt("Population");
                String capitalCityName = rset.getString("CapitalCity");
                String continentName = rset.getString("Continent");

                // Create a TopCapitalCitiesInWorld object and add it to the list
                TopCapitalCitiesInWorld topCapitalCity = new TopCapitalCitiesInWorld(population, capitalCityName, continentName);
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
    public static void printTopCapitalCitiesInWorld(ArrayList<TopCapitalCitiesInWorld> Cities) {
        // Check if ArrayList is not null
        if (Cities == null ) {
            System.out.println("No Top Capital Cities in the World to display");
            return;
        }
        System.out.println("Top Capital Cities in the World Report:");
        //print header
        System.out.println(String.format("%-25s %-20s %-20s", "Population", "CapitalCity", "continent"));

        // Iterate through the list of ContinentPopulationReport objects and print each one
        for (TopCapitalCitiesInWorld city: Cities) {
            if (Cities == null)
                continue;
            String emp_string =
                    String.format("%-25s %-20s %-20s",
                            city.population, city.capitalCityName, city.continent   );
            System.out.println(emp_string);
            //    System.out.println(continent);
        }}

}




