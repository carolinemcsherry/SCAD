package com.napier.sem;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/* User Report 1  - The population of people, people living in cities, and people not living in cities in each region.*/

public class User_report_1 {

    // Inner class to represent the population report for a region
    public static class PopulationReport {
        private String Continent;
        private long Total_Continent_Population;
        private long Total_City_Population;
        private double  City_Population_Percentage;
        private long Total_Not_City_Population;
        private double  None_City_Population_Percentage;

        // Constructor for the PopulationReport class
        public PopulationReport(String Continent, long Total_Continent_Population, long Total_City_Population, double  City_Population_Percentage, long Total_Not_City_Population, double  None_City_Population_Percentage ) {
            this.Continent = Continent;
            this.Total_Continent_Population = Total_Continent_Population;
            this.Total_City_Population = Total_City_Population;
            this.City_Population_Percentage = City_Population_Percentage;
            this.Total_Not_City_Population = Total_Not_City_Population;
            this.None_City_Population_Percentage = None_City_Population_Percentage;
        }

        // Method to represent the object as a string
        public String toString() {
            return Continent +
                    Total_Continent_Population +
                    Total_City_Population +
                    City_Population_Percentage +
                    Total_Not_City_Population +
                    None_City_Population_Percentage;

        }
    }

    // Method to retrieve population report data for each of the regions
    public static ArrayList<PopulationReport> getPopulationByRegion(Connection con) {
        ArrayList<PopulationReport> populationReports = new ArrayList<>();

        try { System.out.println("in to SQL check .");
            Statement stmt = con.createStatement();

            // SQL query to retrieve population data for each region
            String strSelect = "SELECT country.Continent, " +
                    "SUM(country.Population) AS Total_Continent_Population, " +
                    "SUM(city.Population) AS Total_City_Population, " +
                    "ROUND((SUM(city.Population) / SUM(country.Population)) * 100, 2) AS City_Population_Percentage, " +
                    "SUM(country.Population) - SUM(city.Population) AS Total_Not_City_Population, " +
                    "ROUND(((SUM(country.Population) - SUM(city.Population)) / SUM(country.Population)) * 100, 2) AS None_City_Population_Percentage " +
                    "FROM country " +
                    "JOIN city ON country.Code = city.CountryCode " +
                    "GROUP BY country.Continent " ;

            ResultSet rset = stmt.executeQuery(strSelect);

            // Iterate through the result set and create PopulationReport objects
            while (rset.next()) {
                String Continent = rset.getString("Continent");
                long Total_Continent_Population = rset.getLong("Total_Continent_Population");
                long Total_City_Population = rset.getLong("Total_City_Population");
                double  City_Population_Percentage = rset.getDouble("City_Population_Percentage");
                long Total_Not_City_Population = rset.getLong("Total_Not_City_Population");
                double None_City_Population_Percentage = rset.getDouble("None_City_Population_Percentage");


                // Create a PopulationReport object and add it to the list
                PopulationReport report = new PopulationReport(Continent, Total_Continent_Population, Total_City_Population, City_Population_Percentage, Total_Not_City_Population, None_City_Population_Percentage);
                populationReports.add(report);
            }

            // Close the result set and statement
            rset.close();
            stmt.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population report details");
        }

        return populationReports;
    }

    // Method to print population report data for each region.
    public static void printPopulationReport(ArrayList<PopulationReport> reports) {
        // Check Array List  is not null
        if (reports == null)
        {
            System.out.println("No reports");
            return;
        }
        System.out.println("Population Report for Region");
        // Iterate through the list of PopulationReport objects and print each one
        System.out.println(String.format("%-35s %-25s %-25s%-25s %-25s %-25s", "Continent", "Total_Continent_Population", "Total_City_Population", "City_Population_Percentage", "Total_Not_City_Population", "None_City_Population_Percentage"));

        for (PopulationReport Region : reports) {
            //If an atrabute value is null the job will continue
            if (Region == null)
                continue;
            //Prints table values in columbs
            String Table_string =
                    String.format("%-35s %-25s %-25s%-25s %-25s %-25s",
                            Region.Continent, Region.Total_Continent_Population, Region.Total_City_Population, Region.City_Population_Percentage, Region.Total_City_Population, Region.None_City_Population_Percentage);
            System.out.println(Table_string);

        }
    }
}
