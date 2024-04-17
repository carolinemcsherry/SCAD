package com.napier.sem;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

public class Population_Report {
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
        System.out.println("|               Population Report                    |");
        System.out.println("+----------------------------------------------------+");

    }

    private static void printMenu(){
        //menu options
        System.out.println("\nPlease choose report to process: ");
        System.out.println("0) Main Menu ");
        System.out.println("1) The name of the continent, total population, total population living in cities and not living in cities");
        System.out.println("2) The name of the region, total population, total population living in cities and not living in cities");
        System.out.println("3) The name of the country, total population, total population living in cities and not living in cities");
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
                // user report 1 Continent
                ArrayList<User_report_1_Continent.PopulationReport> ReportArray1a = User_report_1_Continent.getPopulationByRegion(con);
                User_report_1_Continent.printPopulationReport(ReportArray1a);
                break;
            case 2:
                // user report 1 Region
                ArrayList<User_report_1_Region.PopulationReport> ReportArray1c = User_report_1_Region.getPopulationByRegion(con);
                User_report_1_Region.printPopulationReport(ReportArray1c);
                break;
            case 3:
                // user report 1 Country
                ArrayList<User_report_1_Country.PopulationReport> ReportArray1b = User_report_1_Country.getPopulationByRegion(con);
                User_report_1_Country.printPopulationReport(ReportArray1b);
                break;
            default:
                Menu.runMenu(con);
                break;

        }


    }


}


