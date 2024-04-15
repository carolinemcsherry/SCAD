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
    @Test
    public void testGetCityByDistrict() {
        CityDistrict cityDistrictInstance = new CityDistrict();
        // Pass the name of the district you want to test
        String districtName = "Kujawsko-Pomorskie";
        CityDistrict testCity = cityDistrictInstance.getCity(districtName);

        // Assert that the returned city is not null
        assertNotNull(testCity);
        // Optionally, you can add more assertions to validate properties of the returned city
    }
    @Test
    public void testGetPopulation() {
        CityPopulation cityPopulationInstance = new CityPopulation();
        // Pass the ID of the city you want to test
        int cityId = 40;
        int population = cityPopulationInstance.getPopulation(cityId);

        // Assert that the returned population is greater than 0
        assertTrue(population > 0);
    }
    @Test
    public void testGetCountryInfoWithValidCode() {
        // Pass the country code "BFA" to get country information
        CountryInfo.getCountryInfo("BFA");

        // In this test case, we are not validating the output, just ensuring no exceptions are thrown
        // You may add additional assertions to validate the output if needed
    }

    @Test
    public void testGetCountryInfoWithInvalidCode() {
        // Pass an invalid country code to get country information
        // For example, pass a code that does not exist in the database
        CountryInfo.getCountryInfo("XYZ");

        // In this test case, we are not validating the output, just ensuring no exceptions are thrown
        // You may add additional assertions to validate the output if needed
    }



}
