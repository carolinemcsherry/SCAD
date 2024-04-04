package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
            return "Region: " + Region + ", " +
                    "City: " + City + ", " +
                    "Population: " + Population;
        }
    }

    public static ArrayList<PopulationbyRegionReport> getPopulationbyRegionReport(Connection con) {
        ArrayList<PopulationbyRegionReport> populationReports = new ArrayList<>();

        try {
            Statement stmt = con.createStatement();

            // SQL query to retrieve top N populated capital cities in each region
            String strSelect = "SELECT R.Name AS Region, city.Name AS City, city.Population " +
                    "FROM city " +
                    "JOIN country ON city.CountryCode = country.Code " +
                    "JOIN region R ON country.Region = R.Code " +
                    "WHERE city.ID = country.Capital " +
                    "ORDER BY city.Population DESC " +
                    "LIMIT " + "";

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
        if (regions == null) {
            System.out.println("No regions");
            return;
        }
        System.out.println("Region Population Report");
        for (PopulationbyRegionReport region : regions) {
            System.out.println(region);
        }
    }
}


