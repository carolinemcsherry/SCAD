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
}
