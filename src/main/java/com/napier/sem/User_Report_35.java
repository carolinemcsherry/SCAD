package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.swing.JOptionPane;

// The population of the world.
public class User_Report_35 {

    public static class PopulationbyRegionReport {
        private String Region;
        private String City;
        private int Population;

        public PopulationbyRegionReport(String Region, String City, int Population) {
            this.Region = Region;
            this.City = City;
            this.Population = Population;
        }

        public String toString() {
            return  Region +
                    City +
                    + Population;
        }
    }

    public static ArrayList<PopulationbyRegionReport> getPopulationbyRegionReport(Connection con) {

//Var set up for methord
        ArrayList<PopulationbyRegionReport> populationReports = new ArrayList<>();

        //Open scanner
        Scanner scanner = new Scanner(System.in);
        // ask user what they want
                int input = 10; // Default value
        try {

            System.out.print("Enter the number of records you want to retrieve: ");
            input = scanner.nextInt();
        } catch (NoSuchElementException e) {
            // No user input, continue with default value
        }
// Handeling null value in string to get full range

        try {
            Statement stmt = con.createStatement();

            // SQL query to retrieve top N populated capital cities in each region
            String strSelect = "SELECT country.Region, city.Name AS City, city.Population " +
                    "FROM city " +
                    "JOIN country ON city.CountryCode = country.Code " +
                  " ORDER BY city.Population DESC " +
                    "LIMIT " + input ;

            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next()) {
                String Region = rset.getString("Region");
                String City = rset.getString("City");
                int Population = rset.getInt("Population");

                PopulationbyRegionReport report = new PopulationbyRegionReport(Region, City, Population);
                populationReports.add(report);
            }

            rset.close();
            stmt.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population report details");
        }

        return populationReports;
    }

    //Print section
    public static void printRegionPopulation(ArrayList<PopulationbyRegionReport> regions) {
        if (regions == null) {
            System.out.println("No regions or empty list");
            return;
        }
        System.out.println("Region Population Report");
        //print header
        System.out.println(String.format("%-25s %-20s %-20s", "Region", "City", "Population"));

        for (PopulationbyRegionReport region : regions) {
            if (region == null) {
                System.out.println("Encountered a null region");
                continue;
            }
            String emp_string =
                    String.format("%-25s %-20s %-20s",
                            region.Region, region.City, region.Population);
            System.out.println(emp_string);
            //    System.out.println(continent);
        }}
}



