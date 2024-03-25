package com.napier.sem;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class User_report_12 {



    public static class TopCapitalCitiesInContinent {
        private int population;
        private String capitalCityName;
        private String continent;

        public TopCapitalCitiesInContinent(int population, String capitalCityName, String continent) {
            this.population = population;
            this.capitalCityName = capitalCityName;
            this.continent = continent;
        }

        public String toString() {
            return "Population: " + population + ", " +
                    "Capital City Name: " + capitalCityName + ", " +
                    "Continent: " + continent;
        }
    }

    public static ArrayList<TopCapitalCitiesInContinent> getTopPopulatedCapitalCitiesInContinent(Connection con, String continent, int limit) {
        try {
            Statement stmt = con.createStatement();
            String strSelect = "SELECT country.Population, city.Name AS CapitalCity, country.Continent " +
                    "FROM city " +
                    "JOIN country ON city.ID = country.Capital " +
                    "WHERE country.Continent = '" + continent + "' " +
                    "ORDER BY country.Population DESC " +
                    "LIMIT " + limit;

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<TopCapitalCitiesInContinent> topCapitalCitiesList = new ArrayList<>();

            while (rset.next()) {
                int population = rset.getInt("Population");
                String capitalCityName = rset.getString("CapitalCity");
                String continentName = rset.getString("Continent");

                TopCapitalCitiesInContinent topCapitalCity = new TopCapitalCitiesInContinent(population, capitalCityName, continentName);
                topCapitalCitiesList.add(topCapitalCity);
            }
            return topCapitalCitiesList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get top populated capital cities in the continent");
            return null;
        }
    }

    public static void printTopCapitalCitiesInContinentReport(ArrayList<TopCapitalCitiesInContinent> topCapitalCitiesInContinent) {
        if (topCapitalCitiesInContinent == null) {
            System.out.println("No TopCapitalCitiesInContinent");
            return;
        }
        System.out.println("TopCapitalCitiesInContinent:");
        for (TopCapitalCitiesInContinent capitalCity : topCapitalCitiesInContinent) {
            System.out.println(capitalCity);
        }
    }
}
