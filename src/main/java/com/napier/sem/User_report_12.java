package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

// The population of the world.
public class User_report_12 {

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


        ArrayList<PopulationbyRegionReport> populationReports = new ArrayList<>();
        String input = "";

        boolean myBool = true;

        int i = 1;
        while (myBool == true & i <5) {

            input = JOptionPane.showInputDialog("Enter the Number of top Populated Region's");

        try
        {
            Integer.parseInt(input);
            myBool = false;
            break;
        }
        catch (NumberFormatException e)
        {

            System.out.println("Attempt "+i);
            System.out.println(input + " is not a valid number!");
            myBool = true;
            i++;

        } }

        String Stringinput = JOptionPane.showInputDialog("Enter the name of the Region or leave blank for all Region's");

        if (Stringinput.isEmpty() == true) {
            Stringinput = "%";

        }


        try {
                        Statement stmt = con.createStatement();

            // SQL query to retrieve top N populated capital cities in each region
            String strSelect = "SELECT country.Region, city.Name AS City, city.Population " +
                    "FROM city " +
                    "JOIN country ON city.CountryCode = country.Code " +
                    "WHERE city.ID = country.Capital  and country.Region like '" + Stringinput +
                    "' ORDER BY city.Population DESC " +
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

    public static void printRegionPopulation(ArrayList<PopulationbyRegionReport> regions) {
        if (regions == null || regions.isEmpty()) {
            System.out.println("No regions or empty list");
            return;
        }

        System.out.println("Region Population Report");
        //print header
        System.out.println(String.format("%-25s %-20s %-20s", "Region", "Capatial City", "Population"));

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


