

    package com.napier.sem;

    import java.sql.Connection;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.sql.Statement;
    import java.util.ArrayList;

    // capital cities in the world, sorted by population from largest to smallest

    public class User_report_16 {
        public static class CapitalCitiesWorld {
        private String cityName;
        private int population;

        // Constructor for CapitalCitiesWorld class
        public CapitalCitiesWorld(String cityName, int population) {
            this.cityName = cityName;
            this.population = population;
        }

        // Method to represent the object as a string
        public String toString() {
            return "City Name: " + cityName + ", " +
                    "Population: " + population;
        }
    }

    // Method to retrieve capital cities in the world
    public static ArrayList<CapitalCitiesWorld> getCapitalCitiesWorld(Connection con) {
        try {
            Statement stmt = con.createStatement();

            // SQL query to retrieve capital cities in the world
            String strSelect = "SELECT Name, Population " +
                    "FROM city " +
                    "ORDER BY Population DESC";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<CapitalCitiesWorld> capitalCitiesWorldList = new ArrayList<>();

            // Iterate through the result set and create CapitalCitiesWorld objects
            while (rset.next()) {
                String cityName = rset.getString("Name");
                int population = rset.getInt("Population");

                // Create a CapitalCitiesWorld object and add it to the list
                CapitalCitiesWorld capitalCity = new CapitalCitiesWorld(cityName, population);
                capitalCitiesWorldList.add(capitalCity);
            }
            return capitalCitiesWorldList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get capital cities in the world");
            return null;
        }
    }

    // Method to print capital cities in the world
    public static void printCapitalCitiesWorld(ArrayList<CapitalCitiesWorld> capitalCitiesWorldList) {
        if (capitalCitiesWorldList == null || capitalCitiesWorldList.isEmpty()) {
            System.out.println("No capital cities in the world to display");
            return;
        }
        System.out.println("Capital Cities in the World Report:");
        for (CapitalCitiesWorld capitalCity : capitalCitiesWorldList) {
            System.out.println(capitalCity);
        }
    }

    }

