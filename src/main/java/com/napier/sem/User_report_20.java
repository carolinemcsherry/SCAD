package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
        String input = JOptionPane.showInputDialog("Enter the Number of top Populated cities \nyou would like to retrevie!");
        boolean myBool = true;
        int i = 1;
        // check to see if user entered a number
        while (myBool == true & i <5) {
            input = JOptionPane.showInputDialog("Enter the Number of top Populated Region's");
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