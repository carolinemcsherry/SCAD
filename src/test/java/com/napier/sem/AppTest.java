package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


public class AppTest {
    static App app;
    @BeforeAll
    static void init()
    {
        app = new App();
    }

    //User report 1 Continent tests
    @Test
    void User_report_1_Continent_TestNull()
    {

        User_report_1_Continent.printPopulationReport(null);
    }

    @Test
    void User_report_1_Continent_TestEmpty()
    {
        ArrayList<User_report_1_Continent.PopulationReport> ReportArray1A = new ArrayList<User_report_1_Continent.PopulationReport>();
        User_report_1_Continent.printPopulationReport(ReportArray1A);
    }

    @Test
    void User_report_1_Continent_TestContainsNull()
    {
        ArrayList<User_report_1_Continent.PopulationReport> ReportArray1A = new ArrayList<User_report_1_Continent.PopulationReport>();
        ReportArray1A.add(null);
        User_report_1_Continent.printPopulationReport(ReportArray1A);
    }

    //User report 1 Country tests
    @Test
    void User_report_1_Country_TestNull()
    {

        User_report_1_Country.printPopulationReport(null);
    }

    @Test
    void User_report_1_Country_TestEmpty()
    {
        ArrayList<User_report_1_Country.PopulationReport> ReportArray1B = new ArrayList<User_report_1_Country.PopulationReport>();
        User_report_1_Country.printPopulationReport(ReportArray1B);
    }


    @Test
    void User_report_1_Country_TestContainsNull()
    {
        ArrayList<User_report_1_Country.PopulationReport> ReportArray1B = new ArrayList<User_report_1_Country.PopulationReport>();
        ReportArray1B.add(null);
        User_report_1_Country.printPopulationReport(ReportArray1B);
    }

    //User report 1 Region
    @Test
    void CityPopulationReportTestNull()
    {

        User_report_1_Region.printPopulationReport(null);
    }
    @Test
    void CityPopulationReportTestEmpty()
    {
        ArrayList<User_report_1_Region.PopulationReport> ReportArray1 = new ArrayList<User_report_1_Region.PopulationReport>();
        User_report_1_Region.printPopulationReport(ReportArray1);
    }

    @Test
    void printCityPopulationReportTestContainsNull()
    {
        ArrayList<User_report_1_Region.PopulationReport> ReportArray1 = new ArrayList<User_report_1_Region.PopulationReport>();
        ReportArray1.add(null);
        User_report_1_Region.printPopulationReport(ReportArray1);
    }


    //User report 2
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


//User_Report_10
//User_Report_11
//User_Report_12
//User_Report_13
//User_Report_14
//User_Report_15
//User_Report_16
//User_Report_17
//User_Report_18
//User_Report_19
//User_Report_20
//User_Report_21
//User_Report_22
//User_Report_23
//User_Report_24
//User_Report_25
//User_Report_26
//User_Report_27
    @Test
    void user_report27_TestNull() {
        User_report_27.printTopPopulatedCountriesInContinent(null);
    }

    @Test
    void user_report27_TestEmpty() {
        ArrayList<User_report_27.TopCountriesInContinent> reportArray27 = new ArrayList<>();
        User_report_27.printTopPopulatedCountriesInContinent(reportArray27);
    }

    @Test
    void print_user_report27_TestContainsNull() {
        ArrayList<User_report_27.TopCountriesInContinent> reportArray27 = new ArrayList<>();
        reportArray27.add(null);
        User_report_27.printTopPopulatedCountriesInContinent(reportArray27);
    }

//User_Report_28

    @Test
    void user_report28_TestNull() {
        User_report_28.printPopulationReport(null);
    }

    @Test
    void user_report28_TestEmpty() {
        ArrayList<User_report_28.Population> reportArray28 = new ArrayList<>();
        User_report_28.printPopulationReport(reportArray28);
    }

    @Test
    void print_user_report28_TestContainsNull() {
        ArrayList<User_report_28.Population> reportArray28 = new ArrayList<>();
        reportArray28.add(null);
        User_report_28.printPopulationReport(reportArray28);
    }


    //User_Report_29
    @Test
    void user_report29_TestNull() {
        User_report_29.printPopulationByContinent(null);
    }

    @Test
    void user_report29_TestEmpty() {
        ArrayList<User_report_29.ContinentPopulation> ReportArray28 = new ArrayList<>();
        User_report_29.printPopulationByContinent(ReportArray28);
    }

    @Test
    void print_user_report29_TestContainsNull() {
        ArrayList<User_report_29.ContinentPopulation> ReportArray28 = new ArrayList<>();
        ReportArray28.add(null);
        User_report_29.printPopulationByContinent(ReportArray28);
    }

//User_Report_30

    @Test
    void user_report30_TestNull() {
        User_report_30.printCountriesByRegion(null);
    }

    @Test
    void user_report30_TestEmpty() {
        ArrayList<User_report_30.CountryInRegion> ReportArray29 = new ArrayList<>();
        User_report_30.printCountriesByRegion(ReportArray29);
    }

    @Test
    void print_user_report30_TestContainsNull() {
        ArrayList<User_report_30.CountryInRegion> ReportArray29 = new ArrayList<>();
        ReportArray29.add(null);
        User_report_30.printCountriesByRegion(ReportArray29);
    }

    //User_Report_31

    @Test
    void user_report31_TestNull() {
        User_report_31.printCountriesByContinent(null);
    }

    @Test
    void user_report31_TestEmpty() {
        ArrayList<User_report_31.CountryInContinent> ReportArray30 = new ArrayList<>();
        User_report_31.printCountriesByContinent(ReportArray30);
    }


    //User_Report_32
    @Test
    void print_user_report31_TestContainsNull() {
        ArrayList<User_report_31.CountryInContinent> ReportArray30 = new ArrayList<>();
        ReportArray30.add(null);
        User_report_31.printCountriesByContinent(ReportArray30);
    }


    @Test
    void user_report32_TestNull() {
        User_report_32.printCountriesByPopulation(null);
    }

    @Test
    void user_report32_TestEmpty() {
        ArrayList<User_report_32.CountryData> ReportArray31 = new ArrayList<>();
        User_report_32.printCountriesByPopulation(ReportArray31);
    }
    @Test
    void print_user_report32_TestContainsNull() {
        ArrayList<User_report_32.CountryData> ReportArray31 = new ArrayList<>();
        ReportArray31.add(null);
        User_report_32.printCountriesByPopulation(ReportArray31);
    }




    //user report 33
    @Test
    void user_report33_TestNull()
    {
        User_report_33.printTopPopulatedCountriesInRegion(null);
    }

    @Test
    void user_report33_TestEmpty() {
        ArrayList<User_report_33.CountryDataInRegion> ReportArray32 = new ArrayList<>();
        User_report_33.printTopPopulatedCountriesInRegion(ReportArray32);
    }

    @Test
    void print_user_report33_TestContainsNull() {
        ArrayList<User_report_33.CountryDataInRegion> ReportArray32 = new ArrayList<>();
        ReportArray32.add(null);
        User_report_33.printTopPopulatedCountriesInRegion(ReportArray32);
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
