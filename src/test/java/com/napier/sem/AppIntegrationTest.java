package com.napier.sem;

import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest {

    private static Connection con;

    @BeforeAll
    public static void setUp() {
        // Establish a connection to the database
        App app = new App();
        app.connect("localhost:33060", 10000); // Adjust parameters as needed

        // Set the connection for fetching the expected city
        con = App.con;
    }

    @AfterAll
    public static void tearDown() {
        // Disconnect from the database
        App.disconnect();
        System.out.println("Database has been disconnected");
    }

    @Test
    public void testGetCity() {
        City city = new City();
        int testID = 30;

        // Call the getCity method with ID 30
        City resultCity = city.getCity(testID);

        // Check if the returned city is not null
        assertNotNull(resultCity, "City with ID " + testID + " should not be null");

        // Fetch expected city from the database
        City expectedCity = fetchExpectedCity(testID);

        // Check if the properties of the returned city match expectations
        assertEquals(expectedCity.ID, resultCity.ID, "ID should match");
        assertEquals(expectedCity.Name, resultCity.Name, "Name should match");
        assertEquals(expectedCity.CountryCode, resultCity.CountryCode, "CountryCode should match");
        assertEquals(expectedCity.District, resultCity.District, "District should match");
        assertEquals(expectedCity.Population, resultCity.Population, "Population should match");
    }

    private City fetchExpectedCity(int cityID) {
        try {
            Statement stmt = con.createStatement();
            String query = "SELECT * FROM city WHERE ID = " + cityID;
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                City city = new City();
                city.ID = rs.getInt("ID");
                city.Name = rs.getString("Name");
                city.CountryCode = rs.getString("CountryCode");
                city.District = rs.getString("District");
                city.Population = rs.getInt("Population");
                return city;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
