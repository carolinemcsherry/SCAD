package com.napier.sem;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;


/*Additionally, the following information should be accessible to the organisation: The population of a country. */

public class User_report_8 {

    // Inner class to represent the population report of a region
    public static class CountryPopulation{
        private String name;
        private long Population;


        // Constructor for CountryPopulation class
        public CountryPopulation(String name, long Population) {
            this.name = name;
            this.Population = Population;

        }

        // Method to represent the object as a string
        public String toString() {
            return  name  +
                     Population ;
        }
    }

    // Method to retrieve country population data
    public static ArrayList<CountryPopulation> getCountryPopulation(Connection con) {

        //Regex to check names
        String Regex = "^[A-Za-z\\s%]+(?:[ '-][A-Za-z\\s%]+)*$";
        //open scanner
        Scanner scanner = new Scanner(System.in);
        // ask user what they want
        System.out.print("Enter the name of the country or leave blank for all country's");
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

            // SQL query to retrieve country population data
            String strSelect = "SELECT name, population FROM country where name like '" + input + "'";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<CountryPopulation> countryPopulationList = new ArrayList<>();

            // Iterate through the result set and create CountryPopulation objects
            while (rset.next()) {
                String name = rset.getString("Name");
                long Population = rset.getLong("Population");


                // Create a CountryPopulation object and add it to the list
                CountryPopulation countryPopulation = new CountryPopulation(name, Population);
                countryPopulationList.add(countryPopulation);
            }
            return countryPopulationList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country population data");
            return null;
        }
    }

    // Method to print country population data
    public static void printCountryPopulation(ArrayList<CountryPopulation> countryPopulationList) {
        // Check Array List  is not null
        if (countryPopulationList == null)
        {
            System.out.println("No country Population List");
            return;
        }
        //print report name
        System.out.println("Country Population Report:");
        //format and print header
        System.out.println(String.format("%-35s %-20s", "Name", "Population"));

        for (CountryPopulation countryPopulation : countryPopulationList) {
            //If an atrabute value is null the job will continue
            if (countryPopulation == null)
                continue;
            //Prints table values in columbs
            String Table_string =
                    String.format("%-35s %-20s ",
                            countryPopulation.name,countryPopulation.Population);
            System.out.println(Table_string);
        }
    }
}