package com.napier.sem;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//The population of a City
//
//For the population reports, the following information is requested:
//
//The name of the continent/region/country.
//The total population of the continent/region/country.
//The total population of the continent/region/country living in cities (including a %).
//The total population of the continent/region/country not living in cities (including a %).

public class User_report_35 {

    public static class CityPopulationReport {

            private String CityName;
            private int Total_City_Population;
            private String City_Population_Percentage;

            // Constructor for the CityPopulationReport class
            public CityPopulationReport(String CityName, int Total_City_Population, String City_Population_Percentage) {
                this.CityName = CityName;
                this.Total_City_Population = Total_City_Population;
                this.City_Population_Percentage = City_Population_Percentage;
            }

            // Method to represent the object as a string
            public String toString() {
                return  "City Name: " + CityName + ", " +
                        "Total City Population: " + Total_City_Population + ", " +
                        "City Population Percentage: " + City_Population_Percentage;
            }
        }

        // Method to retrieve population data for all cities
        public static ArrayList<CityPopulationReport> getAllCitiesPopulation(Connection con) {
            try {
                Statement stmt = con.createStatement();

                // SQL query to retrieve city-wise population data
                String strSelect = "SELECT city.Name AS CityName, " +
                        "SUM(city.Population) AS Total_City_Population, " +
                        "CONCAT(ROUND(SUM(city.Population) / (SELECT SUM(Population) FROM city) * 100, 2),'%') AS City_Population_Percentage " +
                        "FROM city " +
                        "GROUP BY city.Name";

                ResultSet rset = stmt.executeQuery(strSelect);

                ArrayList<CityPopulationReport> cityReports = new ArrayList<>();

                // Iterate through the result set and create CityPopulationReport objects
                while (rset.next()) {
                    String CityName = rset.getString("CityName");
                    int Total_City_Population = rset.getInt("Total_City_Population");
                    String City_Population_Percentage = rset.getString("City_Population_Percentage");

                    // Create a CityPopulationReport object and add it to the list
                    CityPopulationReport city = new CityPopulationReport(CityName, Total_City_Population, City_Population_Percentage);
                    cityReports.add(city);
                }
                return cityReports;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                System.out.println("Failed to get city population details");
                return null;
            }
        }

        // Method to print population data for all cities
        public static void printAllCitiesPopulation(ArrayList<CityPopulationReport> cities) {
            System.out.println("City Population Report");
            // Iterate through the list of CityPopulationReport objects and print each one
            for (CityPopulationReport city : cities) {
                System.out.println(city);
            }
        }
    }
