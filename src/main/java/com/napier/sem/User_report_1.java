
package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//The population of a country.
//For the population reports, the following information is requested:
//
//The name of the continent/region/country.
//The total population of the continent/region/country.
//The total population of the continent/region/country living in cities (including a %).
//The total population of the continent/region/country not living in cities (including a %).
public class User_report_1 {

    public static class CountryNameReport {
        private String CountryName;
        private int Total_Country_Population;
        private int Total_City_Population;
        private String City_Population_Percentage;
        private int Total_Not_City_Population;
        private String None_City_Population_Percentage;



        public CountryNameReport(String CountryName, int Total_Country_Population, int Total_City_Population, String City_Population_Percentage, int Total_Not_City_Population, String None_City_Population_Percentage) {
            this.CountryName = CountryName;
            this.Total_Country_Population = Total_Country_Population;
            this.Total_City_Population = Total_City_Population;
            this.Total_Not_City_Population = Total_Not_City_Population;
            this.City_Population_Percentage = City_Population_Percentage;
            this.None_City_Population_Percentage = None_City_Population_Percentage;
        }

        public String toString() {
            return  "Country Name: " + CountryName + ", " +
                    "Total Country Population: " + Total_Country_Population + ", " +
                    "Total City Population: " + Total_City_Population + ", " +
                    "Total Not City Population: " + Total_Not_City_Population + ", " +
                    "City Population Percentage: " + City_Population_Percentage + ", " +
                    "None City Population Percentage: " + None_City_Population_Percentage;
        }
    }

    // Name your arrayList
    public static ArrayList<CountryNameReport> getAllCountries(Connection con) {
        try {
            System.out.println("in getAllCountries.");
            // Create an SQL statement
            Statement stmt = con.createStatement();

            System.out.println("Out of Statement stmt = con.createStatement.");

            String strSelect = "SELECT country.name AS Country, " +
                    "SUM(country.Population) AS Total_Country_Population, " +
                    "SUM(city.Population) AS Total_City_Population, " +
                    "CONCAT(ROUND(SUM(city.Population) / SUM(country.Population) * 100, 2),'%') AS City_Population_Percentage, " +
                    "SUM(country.Population) - SUM(city.Population) AS Total_Not_City_Population, " +
                    "CONCAT(100 - ROUND(SUM(city.Population) / SUM(country.Population) * 100, 2) ,'%') AS None_City_Population_Percentage " +
                    "FROM country " +
                    "JOIN city ON country.Code = city.CountryCode " +
                    "GROUP BY country.name";

            System.out.println("made String strSelect.");
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            //log message
            System.out.println("out of execute.");


            ArrayList<CountryNameReport> countryReports = new ArrayList<>();

            System.out.println("built ArrayList.");


            while (rset.next()) {

                String CountryName = rset.getString("Country");
                int Total_Country_Population = rset.getInt("Total_Country_Population");
                int Total_City_Population = rset.getInt("Total_City_Population");
                int Total_Not_City_Population = rset.getInt("Total_Not_City_Population");
                String City_Population_Percentage = rset.getString("City_Population_Percentage");
                String None_City_Population_Percentage = rset.getString("None_City_Population_Percentage");

                CountryNameReport country = new CountryNameReport(CountryName, Total_Country_Population, Total_City_Population, City_Population_Percentage, Total_Not_City_Population, None_City_Population_Percentage);
                countryReports.add(country);
                System.out.println("out of while.");
            }
            System.out.println("return.");
            return countryReports;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }

    // Method to print change to variable above
    public static void printAllCountries(ArrayList<CountryNameReport> countries) {
        System.out.println("Country Name Report");
        for (CountryNameReport country : countries) {
            System.out.println(country);
        }
    }
}


