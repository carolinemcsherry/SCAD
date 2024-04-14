package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

// The population of the world.
public class User_report_11 {

    // Inner class to represent the world population report
    public static class WorldPopulationReport {
        private long totalPopulation;

        // Constructor for WorldPopulationReport class
        public WorldPopulationReport(long totalPopulation) {
            this.totalPopulation = totalPopulation;
        }

        // Method to represent the object as a string
        public String toString() {
            return "Total World Population: " + totalPopulation;
        }
    }

    // Method to retrieve world population
    public static WorldPopulationReport getWorldPopulation(Connection con) {
        try {
            Statement stmt = con.createStatement();

            // SQL query to retrieve world population
            String strSelect = "SELECT SUM(Population) AS Total_World_Population FROM country";

            ResultSet rset = stmt.executeQuery(strSelect);

            long totalWorldPopulation = 0;

            // Extract total world population from the result set
            if (rset.next()) {
                totalWorldPopulation = rset.getLong("Total_World_Population");
            }

            return new WorldPopulationReport(totalWorldPopulation);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get world population details");
            return null;
        }
    }

    // Method to print world population
    public static void printWorldPopulation(WorldPopulationReport worldPopulation) {
        if (worldPopulation != null) {
            System.out.println("World Population Report:");
            System.out.println(worldPopulation);
        } else {
            System.out.println("Failed to print world population report.");
        }
    }

    // Method to retrieve and store world population in an ArrayList
    public static ArrayList<WorldPopulationReport> getWorldPopulationList(Connection con) {
        ArrayList<WorldPopulationReport> worldPopulationList = new ArrayList<>();
        WorldPopulationReport worldPopulation = getWorldPopulation(con);
        if (worldPopulation != null) {
            worldPopulationList.add(worldPopulation);
        }
        return worldPopulationList;
    }

    // Method to print capital city report
    public static void printWorldPopulationReport(ArrayList<WorldPopulationReport> worldPopulationList) {
        // Check if ArrayList is not null
        if (worldPopulationList == null) {
            System.out.println("No World Population");
            return;
        }
        System.out.println("World Population Report:");
        for (WorldPopulationReport worldPopulation : worldPopulationList) {
            System.out.println(worldPopulation);
        }
    }

}