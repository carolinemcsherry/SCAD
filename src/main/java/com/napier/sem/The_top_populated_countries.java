package com.napier.sem;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

public class The_top_populated_countries {
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
        System.out.println("|      The top Nº populated countries                |");
        System.out.println("|                    menu                            |");
        System.out.println("+----------------------------------------------------+");

    }

    private static void printMenu(){
//menu options
        System.out.println("\nPlease choose report to process: ");
        System.out.println("0) Main Menu ");
        System.out.println("1) The top Nº populated countries in the world where Nº is provided by the user.");
        System.out.println("2) The top Nº populated countries in a continent where Nº is provided by the user.");
        System.out.println("3) The top Nº populated countries in a region where Nº is provided by the user. ");
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
                // user report 26
                ArrayList<User_report_26.CountryData> ReportArray26 = User_report_26.getTopPopulatedCountries(con);
                User_report_26.printTopPopulatedCountries(ReportArray26);
                break;
            case 2:
                // user report 31
                // user report 27
                ArrayList<User_report_27.TopCountriesInContinent> ReportArray27 = User_report_27.getTopPopulatedCountriesInContinent(con);
                User_report_27.printTopPopulatedCountriesInContinent(ReportArray27);
                break;
            case 3:
                // user report 33
                ArrayList<User_report_33.TopCountriesInContinent> ReportArray33 = User_report_33.getTopPopulatedCountriesInContinent(con);
                User_report_33.printTopPopulatedCountriesInContinent(ReportArray33);
                break;
            default:
                Menu.runMenu(con);
                break;

        }


    }


}
