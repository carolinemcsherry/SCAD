package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//•	11.The population of the world.•
public class User_report_11 {

    // Inner class to represent the population report of the world
    public static class WorldPopulationReport {
        private int Total_World_Population;

        // Constructor for the WorldPopulationReport class
        public WorldPopulationReport(int Total_World_Population) {
            this.Total_World_Population = Total_World_Population;
        }

        // Method to represent the object as a string
        public String toString() {
            return "Total World Population: " + Total_World_Population;
        }
    }

    // Method to retrieve population data for the entire world
    public static WorldPopulationReport getWorldPopulation(Connection con) {
        try {
            Statement stmt = con.createStatement();

            // SQL query to retrieve world population data
            String strSelect = "SELECT SUM(Population) AS Total_World_Population FROM country";

            ResultSet rset = stmt.executeQuery(strSelect);

            int totalWorldPopulation = 0;

            // Extract total world population from the result set
            if (rset.next()) {
                totalWorldPopulation = rset.getInt("Total_World_Population");
            }

            return new WorldPopulationReport(totalWorldPopulation);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get world population details");
            return null;
        }
    }

    // Method to print population data for the entire world
    public static void printWorldPopulation(WorldPopulationReport worldPopulation) {
        System.out.println("World Population Report");
        System.out.println(worldPopulation);
    }
}

