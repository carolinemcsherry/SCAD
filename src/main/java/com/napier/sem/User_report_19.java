package com.napier.sem;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class User_report_19 {

    public static class CapitalCityDataInContinent {
        private String countryContinent;
        private String CityName;
        private Long population;

        public CapitalCityDataInContinent(String countryContinent, String cityName, Long population) {
           this.countryContinent = countryContinent;
            this.CityName = cityName;
            this.population = population;
        }

        public String toString() {
            return countryContinent + CityName +  population;
        }
    }

    public static ArrayList<CapitalCityDataInContinent> getTopPopulatedCapitalCitiesInContinent(Connection con) {

        //Open scanner
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

            String strSelect = "SELECT country.Continent as countryContinent, city.Name AS CityName, city.Population " +
                    "FROM city " +
                    "JOIN country ON city.ID = country.Capital " +
                    "ORDER BY city.Population DESC " +
                    "LIMIT " + input;

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<CapitalCityDataInContinent> citiesList = new ArrayList<>();

            while (rset.next()) {
                String countryContinent = rset.getString("countryContinent");
                String CityName = rset.getString("CityName");
                Long population = rset.getLong("Population");

                CapitalCityDataInContinent city = new CapitalCityDataInContinent(countryContinent, CityName, population);
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

        System.out.println(String.format("%-25s %-25s %-25s","Continent" ,"Capital City", "Population"));
        for (CapitalCityDataInContinent city : citiesList) {
            if (city == null)
                continue;
            String Table_string =
                    String.format("%-25s %-25s%-25s",
                            city.countryContinent, city.CityName, city.population);
            System.out.println(Table_string);
        }
    }
}
