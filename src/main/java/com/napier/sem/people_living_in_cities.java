package com.napier.sem;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

public class people_living_in_cities {
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
        System.out.println("|    Population of people living in cities           |");
        System.out.println("|                 to smallest                        |");
        System.out.println("+----------------------------------------------------+");

    }

    private static void printMenu(){
        //menu options
        System.out.println("\nPlease choose report to process: ");
        System.out.println("0) Main Menu ");
        System.out.println("1) The population of people, people living in cities, and people not living in cities in each continent.");
        System.out.println("2) The population of people, people living in cities, and people not living in cities in each region.");
        System.out.println("3) The population of people, people living in cities, and people not living in cities in each country. ");
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
        return choice;
    }

    private static void performAction(int choice, Connection con){
        //switch case to run reports
        switch(choice){
            case 0:
                exit = true;
                System.out.println("Thank you for using this serviec!");

                break;
            case 1:
                // user report 29
                ArrayList<User_report_29.ContinentPopulation> ReportArray29 = User_report_29.getPopulationByContinent(con);
                User_report_29.printPopulationByContinent(ReportArray29);
                break;
            case 2:
                // user report 36
                ArrayList<User_report_36.ContinentPopulation> ReportArray36 = User_report_36.getPopulationByContinent(con);
                User_report_36.printPopulationByContinent(ReportArray36);
                break;
            case 3:
                // user report 28
                ArrayList<User_report_28.Population> ReportArray28 = User_report_28.getPopulationReport(con);
                User_report_28.printPopulationReport(ReportArray28);
                break;
            default:
                System.out.println("\nNot Valid Choice \n Try Again ");
                break;

        }


    }


}


