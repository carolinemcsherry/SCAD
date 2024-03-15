package com.napier.sem;

import com.napier.sem.test_sql;

import java.sql.Connection;
import java.util.ArrayList;

public class Switch_Report {

    public static void ReportArray(String SelectedReport, Connection con){


        switch(SelectedReport) {
            case "All the countries in the world organised by largest population to smallest. ":

                ArrayList<test_sql.CapitalCityReport> ReportArray1 = test_sql.getAllCities(con);
                        if (ReportArray1 != null) {
                    // Print the retrieved cities to the console
                    //log post
                    System.out.println("going to print reports.");

                    test_sql.printAllCities(ReportArray1);
                } else {
                    System.out.println("No report retrieved from the database.");
                }
                break;
            case "All the countries in a continent organised by largest population to smallest. ":

                ArrayList<test_sql.CapitalCityReport> ReportArray2 = test_sql.getAllCities(con);
                if (ReportArray2 != null) {
                    // Print the retrieved cities to the console
                    //log post
                    System.out.println("going to print reports.");

                    test_sql.printAllCities(ReportArray2);
                } else {
                    System.out.println("No report retrieved from the database.");
                }
                break;
        }



    }

}
