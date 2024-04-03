package com.napier.sem;

import org.junit.jupiter.api.*;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest {

    private static Connection con;

    @BeforeAll
    public static void setUp() {
        System.out.println("App a = new App();");
        App a = new App();
        System.out.println("a.connect(localhost:3306, 5000);");
        a.connect("localhost:3306",5000);
        System.out.println("con = App.con;");
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
    public void connect(String location, int delay) {
        System.out.println("Moving to connection public void");
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        boolean shouldWait = false;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                if (shouldWait) {
                    // Wait a bit for db to start
                    Thread.sleep(delay);
                }

                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://" + location
                                + "/world?useSSL=false",
                        "root", "example");

                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(sqle.getMessage());

                // Let's wait before attempting to reconnect
                shouldWait = true;
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }
}
