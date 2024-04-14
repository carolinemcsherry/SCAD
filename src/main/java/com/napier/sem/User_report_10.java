package com.napier.sem;



import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

/*Additionally, the following information should be accessible to the organisation: The population of a continent.*/



public class User_report_10{

    public static class ContinentPopulationReport {

        // Inner class to represent the population report of a continent

        private String Continent;
        private long Total_Continent_Population;


        // Constructor for the ContinentPopulationReport class
        public ContinentPopulationReport(String Continent, long Total_Continent_Population) {
            this.Continent = Continent;
            this.Total_Continent_Population = Total_Continent_Population;

        }

        // Method to represent the object as a string
        public String toString() {
            return   Continent +
                     Total_Continent_Population ;
        }
    }

    // Method to retrieve population data for all continents
    public static ArrayList<ContinentPopulationReport> getAllContinentsPopulation(Connection con) {

        //Regex to check names
        String Regex = "^[A-Za-z\\s%]+(?:[ '-][A-Za-z\\s%]+)*$";
        //open scanner
        Scanner scanner = new Scanner(System.in);
        // ask user what they want
        System.out.print("Enter the name of the Continent or leave blank for all Continent's");
        // check input
        String input = scanner.nextLine();
        if (input.matches(Regex)||input.isEmpty() == true) {
            //set the wild card to return all records
            input = "%";
            System.out.println(input);

        } else {
            //tell user that is not a valid city name
            System.out.println("Invalid city name no records will show!");
        }

        // close the scanner
        scanner.close();


        try {
            Statement stmt = con.createStatement();

            // SQL query to retrieve continent-wise population data
            String strSelect = "SELECT country.Continent, " +
                    "SUM(country.Population) AS Total_Continent_Population " +
                     "FROM country where country.Continent like '" +  input +
                    "' GROUP BY country.Continent";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<ContinentPopulationReport> continentReports = new ArrayList<>();

            // Iterate through the result set and create ContinentPopulationReport objects
            while (rset.next()) {
                String Continent = rset.getString("Continent");
                long Total_Continent_Population = rset.getLong("Total_Continent_Population");


                // Create a ContinentPopulationReport object and add it to the list
                ContinentPopulationReport continent = new ContinentPopulationReport(Continent, Total_Continent_Population);
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
        System.out.println(String.format("%-25s %-15s ", "Continent", "Total_Continent_Population"));

        // Iterate through the list of ContinentPopulationReport objects and print each one
        for (ContinentPopulationReport continent : continents) {
            if (continent == null)
                continue;
            String emp_string =
                    String.format("%-25s %-15s ",
                            continent.Continent, continent.Total_Continent_Population);
            System.out.println(emp_string);
        //    System.out.println(continent);
        }
    }
}
