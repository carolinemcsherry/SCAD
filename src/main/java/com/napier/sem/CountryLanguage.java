package com.napier.sem;

import java.sql.ResultSet;
import java.sql.Statement;

import static com.napier.sem.App.con;

public class CountryLanguage {
    public static ResultSet getCountryLanguage(String language) {
        ResultSet resultSet = null;
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT * FROM countrylanguage WHERE Language = '" + language + "'";
            // Execute SQL statement
            resultSet = stmt.executeQuery(strSelect);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country language information");
        }
        return resultSet;
    }
}
