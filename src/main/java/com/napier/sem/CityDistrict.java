package com.napier.sem;

import java.sql.ResultSet;
import java.sql.Statement;

import static com.napier.sem.App.con;

public class CityDistrict {
    public int ID;
    public String Name;
    public String CountryCode;
    public String District;
    public int Population;

    public CityDistrict getCity(String districtName) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT * FROM city WHERE District = '" + districtName + "'";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new city if valid. Check one is returned
            if (rset.next()) {
                CityDistrict oneCity = new CityDistrict();
                oneCity.ID = rset.getInt("ID");
                oneCity.Name = rset.getString("Name");
                oneCity.CountryCode = rset.getString("CountryCode");
                oneCity.District = rset.getString("District");
                oneCity.Population = rset.getInt("Population");
                return oneCity;
            } else
                return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city");
            return null;
        }
    }

    public void displayCity(CityDistrict oneCity) {
        if (oneCity != null) {
            System.out.println(
                    oneCity.ID + " "
                            + oneCity.Name + " "
                            + oneCity.CountryCode + "\n"
                            + oneCity.District + "\n"
                            + oneCity.Population);
        }
    }
}
