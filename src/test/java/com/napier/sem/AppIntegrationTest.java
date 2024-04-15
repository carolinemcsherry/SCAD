package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest {
    static App app;

    @BeforeAll
    static void init() {
        app = new App();
        // Assuming connect method in App class establishes the database connection
        app.connect("localhost:33060", 30000);
    }

    @Test
    public void testGetCity() {
        // Use the existing connection from the App class
        City cityInstance = new City();
        // Pass the ID of the city you want to test
        // Make sure the city with ID 1 exists in your database for this test
        City testCity = cityInstance.getCity(1);

        // Assert that the returned city is not null
        assertTrue(testCity != null);
    }
    @Test
    public void testGetCityByName() {
        CityName cityNameInstance = new CityName();
        // Pass the name of the city you want to test
        String cityName = "Dunedin";
        CityName testCity = cityNameInstance;

        // Assert that the returned city is not null
        assertNotNull(testCity);
    }


}
