package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class AppTest {
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void printCapitalCityReportTestNull()
    {
        User_report_2.printCapitalCityReport(null);
    }

    @Test
    void printCapitalCityReportTestEmpty()
    {
        ArrayList<User_report_2.CapitalCityReport> ReportArray = new ArrayList<User_report_2.CapitalCityReport>();
        User_report_2.printCapitalCityReport(ReportArray);
    }

    @Test
    void printCapitalCityReportTestContainsNull()
    {
        ArrayList<User_report_2.CapitalCityReport> ReportArray = new ArrayList<User_report_2.CapitalCityReport>();
        ReportArray.add(null);
        User_report_2.printCapitalCityReport(ReportArray);
    }
    @Test
    void CityPopulationReportTestNull()
    {
        User_report_1.printPopulationReport(null);
    }

    @Test
    void CityPopulationReportTestEmpty()
    {
        ArrayList<User_report_1.PopulationReport> ReportArray1 = new ArrayList<User_report_1.PopulationReport>();
        User_report_1.printPopulationReport(ReportArray1);
    }

    @Test
    void printCityPopulationReportTestContainsNull()
    {
        ArrayList<User_report_1.PopulationReport> ReportArray1 = new ArrayList<User_report_1.PopulationReport>();
        ReportArray1.add(null);
        User_report_1.printPopulationReport(ReportArray1);
    }

    //User report 3
    @Test
    void CityReportTestNull()
    {
        User_report_3.printCityReport(null);
    }

    @Test
    void CityReportTestEmpty()
    {
        ArrayList<User_report_3.CityReport> ReportArray2 = new ArrayList<User_report_3.CityReport>();
        User_report_3.printCityReport(ReportArray2);
    }

    @Test
    void printCityReportTestContainsNull()
    {
        ArrayList<User_report_3.CityReport> ReportArray2 = new ArrayList<User_report_3.CityReport>();
        ReportArray2.add(null);
        User_report_3.printCityReport(ReportArray2);
    }
    //user report 4
    @Test
    void user_report4_TestNull()
    {
        User_report_4.printCountryReport(null);
    }

    @Test
    void user_report4_TestEmpty()
    {
        ArrayList<User_report_4.CountryReport> ReportArray3 = new ArrayList<User_report_4.CountryReport>();
        User_report_4.printCountryReport(ReportArray3);
    }

    @Test
    void print_user_report4_TestContainsNull()
    {
        ArrayList<User_report_4.CountryReport> ReportArray3 = new ArrayList<User_report_4.CountryReport>();
        ReportArray3.add(null);
        User_report_4.printCountryReport(ReportArray3);
    }
    //user report 5
    @Test
    void user_report5_TestNull()
    {
        User_report_5.printLanguageStatistics(null);
    }

    @Test
    void user_report5_TestEmpty()
    {
        ArrayList<User_report_5.LanguageStats> ReportArray4 = new ArrayList<User_report_5.LanguageStats>();
        User_report_5.printLanguageStatistics(ReportArray4);
    }

    @Test
    void print_user_report5_TestContainsNull()
    {
        ArrayList<User_report_5.LanguageStats> ReportArray4 = new ArrayList<User_report_5.LanguageStats>();
        ReportArray4.add(null);
        User_report_5.printLanguageStatistics(ReportArray4);

    }
    //user report 6
    @Test
    void user_report6_TestNull()
    {
        User_report_6.printCityReport(null);
    }

    @Test
    void user_report6_TestEmpty()
    {
        ArrayList<User_report_6.CityReport> ReportArray5 = new ArrayList<User_report_6.CityReport>();
        User_report_6.printCityReport(ReportArray5);
    }

    @Test
    void print_user_report6_TestContainsNull()
    {
        ArrayList<User_report_6.CityReport> ReportArray5 = new ArrayList<User_report_6.CityReport>();
        ReportArray5.add(null);
        User_report_6.printCityReport(ReportArray5);

    }
    //user report 7
    @Test
    void user_report7_TestNull()
    {
        User_report_7.printRegionPopulation(null);
    }

    @Test
    void user_report7_TestEmpty()
    {
        ArrayList<User_report_7.RegionPopulationReport> ReportArray6 = new ArrayList<User_report_7.RegionPopulationReport>();
        User_report_7.printRegionPopulation(ReportArray6);
    }

    @Test
    void print_user_report7_TestContainsNull()
    {

        ArrayList<User_report_7.RegionPopulationReport> ReportArray6 = new ArrayList<User_report_7.RegionPopulationReport>();
        ReportArray6.add(null);
        User_report_7.printRegionPopulation(ReportArray6);

    }
    //user report 8
    @Test
    void user_report8_TestNull()
    {
        User_report_8.printCountryPopulation(null);
    }

    @Test
    void user_report8_TestEmpty()
    {
        ArrayList<User_report_8.CountryPopulation> ReportArray7 = new ArrayList<User_report_8.CountryPopulation>();
        User_report_8.printCountryPopulation(ReportArray7);
    }

    @Test
    void print_user_report8_TestContainsNull()
    {

        ArrayList<User_report_8.CountryPopulation> ReportArray7 = new ArrayList<User_report_8.CountryPopulation>();
        ReportArray7.add(null);
        User_report_8.printCountryPopulation(ReportArray7);

    }
    //user report 9
    @Test
    void user_report9_TestNull()
    {
        User_report_9.printAllRegionsPopulation(null);
    }

    @Test
    void user_report9_TestEmpty()
    {
        ArrayList<User_report_9.RegionPopulationReport> ReportArray8 = new ArrayList<User_report_9.RegionPopulationReport>();
        User_report_9.printAllRegionsPopulation(ReportArray8);
    }

    @Test
    void print_user_report9_TestContainsNull()
    {
        ArrayList<User_report_9.RegionPopulationReport> ReportArray8 = new ArrayList<User_report_9.RegionPopulationReport>();
        ReportArray8.add(null);
        User_report_9.printAllRegionsPopulation(ReportArray8);

    }
    //user report 34
    @Test
    void user_report34_TestNull()
    {
        User_report_34.printTopPopulatedCitiesInContinent(null);
    }

    @Test

    void user_report34_TestEmpty() {
        ArrayList<User_report_34.TopCitiesInContinent> ReportArray33 = new ArrayList<>();
        User_report_34.printTopPopulatedCitiesInContinent(ReportArray33);
    }

    @Test
    void print_user_report34_TestContainsNull() {
        ArrayList<User_report_34.TopCitiesInContinent> ReportArray33 = new ArrayList<>();
        ReportArray33.add(null);
        User_report_34.printTopPopulatedCitiesInContinent(ReportArray33);
    }

}
