package com.napier.sem;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

public class population {
    //create exit boolean
    static boolean exit;
    public static void runMenu(Connection con){
        //run menu from here
        PrintHeader();
        while (!exit){
            printMenu();
            int choice = getInput(exit);
            performAction(choice, con);
        }

    }


    private static void PrintHeader(){
        //print header for menu
        System.out.println("+----------------------------------------------------+");
        System.out.println("|                 Welcome To                         |");
        System.out.println("|              The Populated Menu                    |");
        System.out.println("+----------------------------------------------------+");

    }

    private static void printMenu(){
//menu options
        System.out.println("\nPlease choose report to process: ");
        System.out.println("0) Main Menu ");
        System.out.println("1) The population of the world. ");
        System.out.println("2) The population of a continent. ");
        System.out.println("3) The population of a region. ");
        System.out.println("4) The population of a country. ");
        System.out.println("5) The population of a district. ");
        System.out.println("6) The population of a city. ");
        System.out.println("Please make your choice: ");
    }

    private static int getInput(boolean exit) {
        Scanner kb = new Scanner(System.in);
        //exit code when 0
        int choice = 0;
        if (kb.hasNextLine()) {
            // Read the input line and parse it to an integer
            try {
                choice = Integer.parseInt(kb.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                // You might want to handle this error case appropriately
            }
        } else {
            System.out.println("No input provided.");
            // You might want to handle this case appropriately
        }

        // Now you have the user's choice in the 'choice' variable
        System.out.println("Your choice is: " + choice);
        return choice;
    }

    private static void performAction(int choice, Connection con){
//switch case to run reports
        switch(choice){
            case 0:
                exit = true;
                System.out.println("Thank you for using this service!");

                break;
            case 1:
                // user report 11
                ArrayList<User_report_11.WorldPopulationReport> ReportArray9 = User_report_11.getWorldPopulationList(con);
                User_report_11.printWorldPopulationReport(ReportArray9);
                break;
            case 2:
                // user report 10
                ArrayList<User_report_10.ContinentPopulationReport> ReportArray8 = User_report_10.getAllContinentsPopulation(con);
                User_report_10.printAllContinentsPopulation(ReportArray8);
                break;
            case 3:
                // user report 9
                ArrayList<User_report_9.RegionPopulationReport> ReportArray7 = User_report_9.getAllRegionsPopulation(con);
                User_report_9.printAllRegionsPopulation(ReportArray7);
                break;
            case 4:
                // user report 8
                ArrayList<User_report_8.CountryPopulation> ReportArray6 = User_report_8.getCountryPopulation(con);
                User_report_8.printCountryPopulation(ReportArray6);
                break;
            case 5:
                // user report 7
                ArrayList<User_report_7.RegionPopulationReport> ReportArray5 = User_report_7.getRegionPopulation(con);
                User_report_7.printRegionPopulation(ReportArray5);
                break;
            case 6:
                // user report 6
                ArrayList<User_report_6.CityReport> ReportArray4 = User_report_6.getAllCities(con);
                User_report_6.printCityReport(ReportArray4);
                break;
            default:
                Menu.runMenu(con);
                break;

        }


    }


}

