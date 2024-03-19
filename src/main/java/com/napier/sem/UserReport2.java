package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserReport2 {
    public static class CapitalCityReport {
        private String cityName;
        private int cityPopulation;
        private String countryName;
        private int countryPopulation;

        // Constructor  change to atrabutes from the SQL.
        public CapitalCityReport(String cityName, int cityPopulation, String countryName, int countryPopulation) {
            this.cityName = cityName;
            this.cityPopulation = cityPopulation;
            this.countryName = countryName;
            this.countryPopulation = countryPopulation;

        }

        public String toString() {
            return  cityName + '\'' +
                    cityPopulation + '\'' +
                    countryName + '\'' +
                    countryPopulation ;
        }
    }

    // Name your arryList
    public static ArrayList<CityPopulationReport> getAllCitiesPopulations(Connection con) {
        try {
            System.out.println("in getAllCitiesPopulations.");
            // Create an SQL statement
            Statement stmt = con.createStatement();

            System.out.println("Ot of Statement stmt = con.createStatement.");

            // PUT SQL here
            String strSelect = "SELECT A.Name AS cityname," +
                    "A.Population AS city_population," +
                    "B.Name AS countryname," +
                    "B.Population AS country_population" +
                    "FROM city A" +
                    "LEFT JOIN country B ON A.CountryCode = B.Code";

            System.out.println("made String strSelect.");
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            //log message
            System.out.println("out of exsacute.");

            // Extract information from DB. Name array list same above. New name for var change capitalCities
            ArrayList<CityPopulationReport> AllCitiesPopulationS = new ArrayList<>();
            //log message
            System.out.println("built ArrayList.");

            // change the data types and the atrabute name and the var in code above to  capitalCities.add(city);
            while (rset.next()) {
                String cityName = rset.getString("Capital");
                int cityPopulation = rset.getInt("cityPopulation");
                String countryName = rset.getString("Country");
                int countryPopulation = rset.getInt("countryPopulation");
                CityPopulationReport city = new CityPopulationReport(cityName, cityPopulation, countryName, countryPopulation);
                AllCitiesPopulation.add(city);
                System.out.println("out of while.");
            }
            System.out.println("return.");
            return AllCitiesPopulationS;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }

    // Method to print change to var above
    public static void printAllCities(ArrayList<CityPopulationReport> cities) {
        System.out.println("Capital Cities Report:");
        for (CityPopulationReport city : cities) {
            System.out.println(city);
        }
    }
}
