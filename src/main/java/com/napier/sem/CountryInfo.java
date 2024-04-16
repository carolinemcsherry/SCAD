package com.napier.sem;

import java.sql.ResultSet;
import java.sql.Statement;

import static com.napier.sem.App.con;

public class CountryInfo {
    public static void getCountryInfo(String countryCode) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT * FROM country WHERE Code = '" + countryCode+"'";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Check if result set is not null
            if (rset.next()) {
                // Retrieve country information from result set
                String code = rset.getString("Code");
                String name = rset.getString("Name");
                String continent = rset.getString("Continent");
                String region = rset.getString("Region");
                double surfaceArea = rset.getDouble("SurfaceArea");
                int indepYear = rset.getInt("IndepYear");
                int population = rset.getInt("Population");
                double lifeExpectancy = rset.getDouble("LifeExpectancy");
                double gnp = rset.getDouble("GNP");
                double gnpOld = rset.getDouble("GNPOld");
                String localName = rset.getString("LocalName");
                String governmentForm = rset.getString("GovernmentForm");
                String headOfState = rset.getString("HeadOfState");
                int capital = rset.getInt("Capital");
                String code2 = rset.getString("Code2");
                // Print country information
                System.out.println("Country Code: " + code);
                System.out.println("Country Name: " + name);
                System.out.println("Continent: " + continent);
                System.out.println("Region: " + region);
                System.out.println("Surface Area: " + surfaceArea);
                System.out.println("Independence Year: " + indepYear);
                System.out.println("Population: " + population);
                System.out.println("Life Expectancy: " + lifeExpectancy);
                System.out.println("GNP: " + gnp);
                System.out.println("Old GNP: " + gnpOld);
                System.out.println("Local Name: " + localName);
                System.out.println("Government Form: " + governmentForm);
                System.out.println("Head of State: " + headOfState);
                System.out.println("Capital: " + capital);
                System.out.println("Country Code (2): " + code2);
            } else {
                // Print message if country ID not found
                System.out.println("Country with ID " + countryCode + " not found");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country information");
        }
    }
}

