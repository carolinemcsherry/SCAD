package com.napier.sem;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.util.ArrayList;

public class GetCountry {


    private Connection con;
    private String name;

    // Constructor to initialize connection
    public GetCountry(Connection con) {
        this.con = con;
    }

    // Method to retrieve all countries from the database
    public ArrayList<GetCountry> getAllCountries() {
        try {
            System.out.println("in country");
            // Create an SQL statement
            Statement stmt = con.createStatement();

            // Create string for SQL statement
            String strSelect = "SELECT Name FROM country"; // Corrected table name to uppercase

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            // Extract country names
            ArrayList<GetCountry> countries = new ArrayList<>();
            while (rset.next()) {
                GetCountry country = new GetCountry(con);
                country.setName(rset.getString("Name")); // Corrected field name to string
                countries.add(country);
            }

            return countries;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Getter and setter for country name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
