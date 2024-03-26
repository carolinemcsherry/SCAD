package com.napier.sem;


import java.sql.ResultSet;
import java.sql.Statement;

import static com.napier.sem.App.con;

public class City {
    public int ID;
    public String Name;
    public String CountryCode;
    public String District;
    public int Population;

    public City getCity(int ID)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "select * "
                + "from city "
                + "where ID = " + ID;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            if (rset.next())
            {
                City oneCity = new City();
                oneCity.ID = rset.getInt("ID");
                oneCity.Name = rset.getString("Name");
                oneCity.CountryCode = rset.getString("CountryCode");
                oneCity.District = rset.getString("District");
                oneCity.Population = rset.getInt("Population");
                return oneCity;
            }
            else
                return null;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city");
            return null;
        }
    }

    public void displayCity(City oneCity)
    {
        if (oneCity != null)
        {
            System.out.println(
                    oneCity.ID + " "
                            + oneCity.Name + " "
                            + oneCity.CountryCode + "\n"
                            + oneCity.District+ "\n"
                            + oneCity.Population);
        }
    }
}