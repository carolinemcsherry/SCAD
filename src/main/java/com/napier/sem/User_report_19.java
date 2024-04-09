package com.napier.sem;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class User_report_19 {

    public static class CapitalCityDataInContinent {
        private String cityName;
        private Long population;

        public CapitalCityDataInContinent(String cityName, Long population) {
            this.cityName = cityName;
            this.population = population;
        }

        public String toString() {
            return cityName + " " + population;
        }
    }

    public static ArrayList<CapitalCityDataInContinent> getTopPopulatedCapitalCitiesInContinent(Connection con) {
        try {
            String input = JOptionPane.showInputDialog("Enter the Number of top Populated Cities");

            Statement stmt = con.createStatement();

            String strSelect = "SELECT city.Name AS CityName, city.Population " +
                    "FROM city " +
                    "JOIN country ON city.ID = country.Capital " +
                    "WHERE country.Continent = 'continent' " +  // Corrected the missing single quote
                    "ORDER BY city.Population DESC " +
                    "LIMIT " + input;

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<CapitalCityDataInContinent> citiesList = new ArrayList<>();

            while (rset.next()) {
                String cityName = rset.getString("CityName");
                Long population = rset.getLong("Population");

                CapitalCityDataInContinent city = new CapitalCityDataInContinent(cityName, population);
                citiesList.add(city);
            }
            return citiesList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get top populated capital cities in continent");
            return null;
        }
    }

    public static void printTopPopulatedCapitalCitiesInContinent(ArrayList<CapitalCityDataInContinent> citiesList) {
        if (citiesList == null) {
            System.out.println("No cities");
            return;
        }

        System.out.println("Top Populated Capital Cities in Continent Report:");
        System.out.println("City Report:");
        System.out.println(String.format("%-25s %-25s", "CityName", "Population"));
        for (CapitalCityDataInContinent city : citiesList) {
            if (city == null)
                continue;
            String Table_string =
                    String.format("%-25s %-25s",
                            city.cityName, city.population);
            System.out.println(Table_string);
        }
    }
}