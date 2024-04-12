package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

// The population of the world.
public class User_report_15 {

    public static class PopulationbyContinentReport {
        private String Continent;
        private String City;
        private long Population;

        public PopulationbyContinentReport(String Continent, String City, long Population) {
            this.Continent = Continent;
            this.City = City;
            this.Population = Population;
        }

        public String toString() {
            return  Continent +
                    City +
                    + Population;
        }
    }

    public static ArrayList<PopulationbyContinentReport> getPopulationbyRegionReport(Connection con) {

//Var set up for methord
        ArrayList<PopulationbyContinentReport> populationContinent = new ArrayList<>();

//get string from user
        String Stringinput = JOptionPane.showInputDialog("Enter the name of the Continent or leave blank for all Continent's");
// handeling null value in string to get full range
        if (Stringinput.isEmpty() == true) {
            Stringinput = "%";
        }
        try {
            Statement stmt = con.createStatement();

            // SQL query to retrieve top N populated capital cities in each region
            String strSelect = "SELECT country.Continent, city.Name AS City, city.Population " +
                    "FROM city " +
                    "JOIN country ON city.CountryCode = country.Code " +
                    "WHERE city.ID = country.Capital  and country.Continent like '" + Stringinput +
                    "' ORDER BY country.Continent, city.Population DESC " ;

            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next()) {
                String Continent = rset.getString("Continent");
                String City = rset.getString("City");
                long Population = rset.getLong("Population");

                PopulationbyContinentReport report = new PopulationbyContinentReport(Continent, City, Population);
                populationContinent.add(report);
            }

            rset.close();
            stmt.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population report details");
        }

        return populationContinent;
    }

    //print section
    public static void printRegionPopulation(ArrayList<PopulationbyContinentReport> Continents) {
        if (Continents == null) {
            System.out.println("No regions or empty list");
            return;
        }
        System.out.println("Region Population Report");
        //print header
        System.out.println(String.format("%-25s %-20s %-20s", "Continent", "Capatial City", "Population"));

        for (PopulationbyContinentReport Continent : Continents) {
            if (Continent == null) {
                System.out.println("Encountered a null region");
                continue;
            }
            String emp_string =
                    String.format("%-25s %-20s %-20s",
                            Continent.Continent, Continent.City, Continent.Population);
            System.out.println(emp_string);
            //    System.out.println(continent);
        }}
}


