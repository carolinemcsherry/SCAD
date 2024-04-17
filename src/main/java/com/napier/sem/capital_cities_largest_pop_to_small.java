package com.napier.sem;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

public class capital_cities_largest_pop_to_small {
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
        System.out.println("|                  Welcome To                        |");
        System.out.println("|       All the capital cities largest population    |");
        System.out.println("|               to smallest Menu                     |");
        System.out.println("+----------------------------------------------------+");

    }

    private static void printMenu(){
        //menu options
        System.out.println("\nPlease choose report to process: ");
        System.out.println("0) Main Menu ");
        System.out.println("1) All the capital cities in the world organised by largest population to smallest.");
        System.out.println("2) All the capital cities in a continent organised by largest population to smallest.");
        System.out.println("3) All the capital cities in a region organised by largest population to smallest. ");
        System.out.println("Please make your choice: ");
    }
    //exit code when 0
    private static int getInput(boolean exit) {
        Scanner kb = new Scanner(System.in);

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
                // user report 25
                // user report 16
                ArrayList<User_report_16.CapitalCitiesWorld> ReportArray14 = User_report_16.getCapitalCitiesWorld(con);
                User_report_16.printCapitalCitiesWorld(ReportArray14);
                break;
            case 2:
                // user report 15
                ArrayList<User_report_15.PopulationbyContinentReport> ReportArray13 = User_report_15.getPopulationbyRegionReport(con);
                User_report_15.printRegionPopulation(ReportArray13);
                break;
            case 3:
                // user report 14
                ArrayList<User_report_14.PopulationbyRegionReport> ReportArray12 = User_report_14.getPopulationbyRegionReport(con);
                User_report_14.printRegionPopulation(ReportArray12);
                break;
            default:
                Menu.runMenu(con);
                break;

        }


    }


}

