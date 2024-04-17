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

public class User_report_20 {

    public static class TopCitiesInWorld {
        private String cityName;
        private Long population;

        public TopCitiesInWorld(String cityName, Long population) {
            this.cityName = cityName;
            this.population = population;
        }

        public String toString() {
            return cityName + " " + population;
        }
    }

    public static ArrayList<TopCitiesInWorld> getTopPopulatedCitiesInWorld(Connection con) {
        //Open scanner
        Scanner scanner = new Scanner(System.in);
        // ask user what they want
        int input = 10; // Default value
        try {

            System.out.print("Enter the number of records you want to retrieve: ");
            input = scanner.nextInt();
        } catch (NoSuchElementException e) {
            // No user input, continue with default value
        }

        try {
            Statement stmt = con.createStatement();

            String strSelect = "SELECT Name AS CityName, Population " +
                    "FROM city " +
                    "ORDER BY Population DESC " +
                    "LIMIT " + input;

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<TopCitiesInWorld> topCitiesList = new ArrayList<>();

            while (rset.next()) {
                String cityName = rset.getString("CityName");
                Long population = rset.getLong("Population");

                TopCitiesInWorld topCity = new TopCitiesInWorld(cityName, population);
                topCitiesList.add(topCity);
            }
            return topCitiesList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get top populated cities in the world");
            return null;
        }
    }

    public static void printTopPopulatedCitiesInWorld(ArrayList<TopCitiesInWorld> topCitiesList) {
        if (topCitiesList == null) {
            System.out.println("No cities");
            return;
        }

        System.out.println("Top Populated Cities in the World Report:");
        System.out.println("City Report:");
        System.out.println(String.format("%-25s %-25s", "CityName", "Population"));
        for (TopCitiesInWorld topCity : topCitiesList) {
            if (topCity == null)
                continue;
            String Table_string =
                    String.format("%-25s %-25s",
                            topCity.cityName, topCity.population);
            System.out.println(Table_string);
        }
    }
}
