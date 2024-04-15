package com.napier.sem;

import java.sql.ResultSet;
import java.sql.Statement;

import static com.napier.sem.App.con;

public class CityPopulation {
    public int getPopulation(int cityId) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT Population FROM city WHERE ID = " + cityId;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Check if result set is not null
            if (rset.next()) {
                // Return population if valid
                return rset.getInt("Population");
            } else {
                // Return 0 if city ID not found
                System.out.println("City with ID " + cityId + " not found");
                return 0;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city population");
            return 0;
        }
    }
}
