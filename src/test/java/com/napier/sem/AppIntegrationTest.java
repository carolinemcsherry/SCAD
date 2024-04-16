package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.napier.sem.App.con;
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

    }

    @Test
    public void testGetCountryInfoWithInvalidCode() {
        // Pass an invalid country code to get country information
        // For example, pass a code that does not exist in the database
        CountryInfo.getCountryInfo("XYZ");

        // In this test case, we are not validating the output, just ensuring no exceptions are thrown
        // You may add additional assertions to validate the output if needed
    }

    @Test
    public void testGetCountryLanguageWithValidLanguage() {
        // Test with a language that exists in the database
        ResultSet resultSet = CountryLanguage.getCountryLanguage("French");
        assertNotNull(resultSet);

        // Optional: Add more assertions to validate the result set, if needed
    }


    @Test
    public void testResultSetContent() throws SQLException {
        // Test the content of the result set returned by getCountryLanguage method
        ResultSet resultSet = CountryLanguage.getCountryLanguage("Spanish");
        assertNotNull(resultSet);

        // Check if the result set contains expected columns
        assertTrue(resultSet.next());
        assertNotNull(resultSet.getString("CountryCode"));
        assertNotNull(resultSet.getString("Language"));
        assertNotNull(resultSet.getString("IsOfficial"));
        assertTrue(resultSet.getDouble("Percentage") >= 0);

        // Optional: Add more assertions to validate the result set content, if needed
    }
    @Test
    public void testGetPopulation1() {
        // Test the getPopulationByRegion method
        ArrayList<User_report_1_Continent.PopulationReport> populationReports = User_report_1_Continent.getPopulationByRegion(con);
        assertNotNull(populationReports);

        // Check if the result is not empty
        assertFalse(populationReports.isEmpty());
    }
    @Test
    public void testPrintPopulation1() {
        // Test the printPopulationReport method
        ArrayList<User_report_1_Continent.PopulationReport> populationReports = User_report_1_Continent.getPopulationByRegion(con);
        assertNotNull(populationReports);

        // Call the printPopulationReport method with the retrieved data
        User_report_1_Continent.printPopulationReport(populationReports);

        // No direct assertions, as it prints to the console
    }

    @Test
    public void testGetPopulationByCountry() {
        // Test the getPopulationByRegion method
        ArrayList<User_report_1_Country.PopulationReport> populationReports = User_report_1_Country.getPopulationByRegion(con);
        assertNotNull(populationReports);

        // Check if the result is not empty
        assertFalse(populationReports.isEmpty());

        // Optionally, you can add more assertions to validate the contents of the result
    }

    @Test
    public void testPrintPopulationReportCountry() {
        // Test the printPopulationReport method
        ArrayList<User_report_1_Country.PopulationReport> populationReports = User_report_1_Country.getPopulationByRegion(con);
        assertNotNull(populationReports);

        // Call the printPopulationReport method with the retrieved data
        User_report_1_Country.printPopulationReport(populationReports);

        // No direct assertions, as it prints to the console
    }
    @Test
    public void testGetPopulationByRegion() {
        // Test the getPopulationByRegion method
        ArrayList<User_report_1_Region.PopulationReport> populationReports = User_report_1_Region.getPopulationByRegion(con);
        assertNotNull(populationReports);

        // Check if the result is not empty
        assertFalse(populationReports.isEmpty());

        // Optionally, you can add more assertions to validate the contents of the result
    }

    @Test
    public void testPrintPopulationReport() {
        // Test the printPopulationReport method
        ArrayList<User_report_1_Region.PopulationReport> populationReports = User_report_1_Region.getPopulationByRegion(con);
        assertNotNull(populationReports);

        // Call the printPopulationReport method with the retrieved data
        User_report_1_Region.printPopulationReport(populationReports);

        // No direct assertions, as it prints to the console
    }
    @Test
    public void testGetAllCapitalCities() {
        // Test the getAllCapitalCities method
        ArrayList<User_report_2.CapitalCityReport> capitalCities = User_report_2.getAllCapitalCities(con);
        assertNotNull(capitalCities);

        // Check if the result is not empty
        assertFalse(capitalCities.isEmpty());

        // Optionally, you can add more assertions to validate the contents of the result
    }

    @Test
    public void testPrintCapitalCityReport() {
        // Test the printCapitalCityReport method
        ArrayList<User_report_2.CapitalCityReport> capitalCities = User_report_2.getAllCapitalCities(con);
        assertNotNull(capitalCities);

        // Call the printCapitalCityReport method with the retrieved data
        User_report_2.printCapitalCityReport(capitalCities);
    }

    @Test
    void testGetCityReport() {
        // Test if the getCityReport method returns a non-null ArrayList
        ArrayList<User_report_3.CityReport> cities = User_report_3.getCityReport(con);
        assertNotNull(cities);
    }

    @Test
    void testPrintCityReport() {
        // Test if the printCityReport method prints something (no assertion)
        ArrayList<User_report_3.CityReport> cities = User_report_3.getCityReport(con);
        User_report_3.printCityReport(cities);
    }


    @Test
    void testGetLanguageStatistics() {
        // Test if the getLanguageStatistics method returns a non-null ArrayList
        ArrayList<User_report_5.LanguageStats> languageStatsList = User_report_5.getLanguageStatistics(con);
        assertNotNull(languageStatsList);
    }

    @Test
    void testPrintLanguageStatistics() {
        // Test if the printLanguageStatistics method prints something (no assertion)
        ArrayList<User_report_5.LanguageStats> languageStatsList = User_report_5.getLanguageStatistics(con);
        User_report_5.printLanguageStatistics(languageStatsList);
    }


}


