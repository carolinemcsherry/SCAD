
package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//The population of a country.
//For the population reports, the following information is requested:
//
//The name of the continent/region/country.
//The total population of the continent/region/country.
//The total population of the continent/region/country living in cities (including a %).
//The total population of the continent/region/country not living in cities (including a %).
public class User_report_1 {



                public static class CountryPopulationReport {
                    private String countryName;
                    private int totalCountryPopulation;
                    private int totalCityPopulation;
                    private String cityPopulationPercentage;
                    private int totalNotCityPopulation;
                    private String noneCityPopulationPercentage;

                    // Constructor
                    public CountryPopulationReport(String countryName, int totalCountryPopulation, int totalCityPopulation,
                                                   String cityPopulationPercentage, int totalNotCityPopulation,
                                                   String noneCityPopulationPercentage) {
                        this.countryName = countryName;
                        this.totalCountryPopulation = totalCountryPopulation;
                        this.totalCityPopulation = totalCityPopulation;
                        this.cityPopulationPercentage = cityPopulationPercentage;
                        this.totalNotCityPopulation = totalNotCityPopulation;
                        this.noneCityPopulationPercentage = noneCityPopulationPercentage;
                    }

                    // toString method to represent the object as a string
                    public String toString() {
                        return "Country: " + countryName + ", " +
                                "Total Country Population: " + totalCountryPopulation + ", " +
                                "Total City Population: " + totalCityPopulation + ", " +
                                "City Population Percentage: " + cityPopulationPercentage + ", " +
                                "Total Not City Population: " + totalNotCityPopulation + ", " +
                                "None City Population Percentage: " + noneCityPopulationPercentage;
                    }
                }

                // Method to retrieve population data for all countries
                public static ArrayList<CountryPopulationReport> getAllCountriesPopulation(Connection con) {
                    try {
                        // Create an SQL statement
                        Statement stmt = con.createStatement();

                        // SQL query to retrieve country population data
                        String strSelect = "SELECT country.name AS Country, " +
                                "SUM(country.Population) AS Total_Country_Population, " +
                                "SUM(city.Population) AS Total_City_Population, " +
                                "CONCAT(ROUND(SUM(city.Population) / SUM(country.Population) * 100, 2),'%') AS City_Population_Percentage, " +
                                "SUM(country.Population) - SUM(city.Population) AS Total_Not_City_Population, " +
                                "CONCAT(100 - ROUND(SUM(city.Population) / SUM(country.Population) * 100, 2) ,'%') AS None_City_Population_Percentage " +
                                "FROM country " +
                                "JOIN city ON country.Code = city.CountryCode " +
                                "GROUP BY country.name";

                        // Execute SQL statement
                        ResultSet rset = stmt.executeQuery(strSelect);

                        ArrayList<CountryPopulationReport> countryReports = new ArrayList<>();

                        // Iterate through the result set and create CountryPopulationReport objects
                        while (rset.next()) {
                            String countryName = rset.getString("Country");
                            int totalCountryPopulation = rset.getInt("Total_Country_Population");
                            int totalCityPopulation = rset.getInt("Total_City_Population");
                            String cityPopulationPercentage = rset.getString("City_Population_Percentage");
                            int totalNotCityPopulation = rset.getInt("Total_Not_City_Population");
                            String noneCityPopulationPercentage = rset.getString("None_City_Population_Percentage");

                            // Create a CountryPopulationReport object and add it to the list
                            CountryPopulationReport country = new CountryPopulationReport(countryName, totalCountryPopulation,
                                    totalCityPopulation, cityPopulationPercentage, totalNotCityPopulation,
                                    noneCityPopulationPercentage);
                            countryReports.add(country);
                        }
                        return countryReports;
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                        System.out.println("Failed to get country population details");
                        return null;
                    }
                }

                // Method to print country population data
                public static void printAllCountriesPopulation(ArrayList<CountryPopulationReport> countries) {
                    System.out.println("Country Population Report");
                    for (CountryPopulationReport country : countries) {
                        System.out.println(country);
                    }
                }
            }
