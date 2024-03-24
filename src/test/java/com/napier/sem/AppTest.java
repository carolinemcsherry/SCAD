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


}
