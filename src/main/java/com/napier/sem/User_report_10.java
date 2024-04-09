package com.napier.sem;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

// The population of a continent.
//For the population reports, the following information is requested:
// The name of the continent/region/country.
// The total population of the continent/region/country.
// The total population of the continent/region/country living in cities (including a %).
// The total population of the continent/region/country not living in cities (including a %).



public class User_report_10{

    public static class ContinentPopulationReport {

        // Inner class to represent the population report of a continent

        private String Continent;
        private long Total_Continent_Population;
        private long Total_City_Population;
        private String City_Population_Percentage;
        private long Total_Not_City_Population;
        private String None_City_Population_Percentage;

        // Constructor for the ContinentPopulationReport class
        public ContinentPopulationReport(String Continent, long Total_Continent_Population, long Total_City_Population, String City_Population_Percentage, long Total_Not_City_Population, String None_City_Population_Percentage) {
            this.Continent = Continent;
            this.Total_Continent_Population = Total_Continent_Population;
            this.Total_City_Population = Total_City_Population;
            this.City_Population_Percentage = City_Population_Percentage;
            this.Total_Not_City_Population = Total_Not_City_Population;
            this.None_City_Population_Percentage = None_City_Population_Percentage;
        }

        // Method to represent the object as a string
        public String toString() {
            return   Continent +
                     Total_Continent_Population +
                     Total_City_Population +
                     Total_Not_City_Population +
                     City_Population_Percentage +
                     None_City_Population_Percentage;
        }
    }

    // Method to retrieve population data for all continents
    public static ArrayList<ContinentPopulationReport> getAllContinentsPopulation(Connection con) {
        try {
            Statement stmt = con.createStatement();

            // SQL query to retrieve continent-wise population data
            String strSelect = "SELECT country.Continent, " +
                    "SUM(country.Population) AS Total_Continent_Population, " +
                    "SUM(city.Population) AS Total_City_Population, " +
                    "CONCAT(ROUND(SUM(city.Population) / SUM(country.Population) * 100, 2),'%') AS City_Population_Percentage, " +
                    "SUM(country.Population) - SUM(city.Population) AS Total_Not_City_Population, " +
                    "CONCAT(100 - ROUND(SUM(city.Population) / SUM(country.Population) * 100, 2) ,'%') AS None_City_Population_Percentage " +
                    "FROM country " +
                    "JOIN city ON country.Code = city.CountryCode " +
                    "GROUP BY country.Continent";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<ContinentPopulationReport> continentReports = new ArrayList<>();

            // Iterate through the result set and create ContinentPopulationReport objects
            while (rset.next()) {
                String Continent = rset.getString("Continent");
                long Total_Continent_Population = rset.getLong("Total_Continent_Population");
                long Total_City_Population = rset.getLong("Total_City_Population");
                long Total_Not_City_Population = rset.getLong("Total_Not_City_Population");
                String City_Population_Percentage = rset.getString("City_Population_Percentage");
                String None_City_Population_Percentage = rset.getString("None_City_Population_Percentage");

                // Create a ContinentPopulationReport object and add it to the list
                ContinentPopulationReport continent = new ContinentPopulationReport(Continent, Total_Continent_Population, Total_City_Population, City_Population_Percentage, Total_Not_City_Population, None_City_Population_Percentage);
                continentReports.add(continent);
            }
            return continentReports;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get continent population details");
            return null;
        }
    }

    // Method to print population data for all continents
    public static void printAllContinentsPopulation(ArrayList<ContinentPopulationReport> continents) {
        if (continents == null)
        {
            System.out.println("No continents");
            return;
        }

        System.out.println("Continent Population Report");
        //print header
        System.out.println(String.format("%-25s %-15s %-20s %-15s %-15s ", "Continent", "Total_Continent_Population", "Total_Not_City_Population", "City_Population_Percentage", "None_City_Population_Percentage"));

        // Iterate through the list of ContinentPopulationReport objects and print each one
        for (ContinentPopulationReport continent : continents) {
            if (continent == null)
                continue;
            String emp_string =
                    String.format("%-25s %-15s %-20s %-15s %-15s",
                            continent.Continent, continent.Total_Continent_Population, continent.Total_Not_City_Population, continent.City_Population_Percentage, continent.None_City_Population_Percentage);
            System.out.println(emp_string);
        //    System.out.println(continent);
        }
    }
}
