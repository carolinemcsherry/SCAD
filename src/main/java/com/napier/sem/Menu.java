package com.napier.sem;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
static boolean exit;
    public static void runMenu(Connection con){

    PrintHeader();
    while (!exit){
        printMenu();
        int choice = getInput(exit);
        performAction(choice, con);
    }

    }


    private static void PrintHeader(){
        System.out.println("+----------------------------------------------------+");
        System.out.println("|                 Welcome To                         |");
        System.out.println("|                 World Stats                        |");
        System.out.println("+----------------------------------------------------+");

    }

    private static void printMenu(){

        System.out.println("\nPlease choose report to process: ");
        System.out.println("0) EXIT ");
        System.out.println("1) All the countries organised by largest population to smallest menu.");
        System.out.println("2) The top ?? populated countries menu.");
        System.out.println("3) . ");
        System.out.println("4) ");
        System.out.println("5) ");
        System.out.println("6) ");
        System.out.println("7) ");
        System.out.println("8) ");
        System.out.println("9) ");
        System.out.println("10) ");
        System.out.println("11) ");
        System.out.println("12) ");
        System.out.println("13) ");
        System.out.println("14) ");
        System.out.println("15) ");
        System.out.println("16) ");
        System.out.println("17) ");
        System.out.println("18) ");
        System.out.println("19) ");
        System.out.println("20) ");
        System.out.println("21) ");
        System.out.println("22) ");
        System.out.println("23) ");
        System.out.println("24) ");
        System.out.println("25) ");
        System.out.println("26) ");
        System.out.println("27) ");
        System.out.println("28) ");
        System.out.println("29) ");
        System.out.println("30) ");
        System.out.println("31) ");
        System.out.println("32) ");
        System.out.println("33) ");
        System.out.println("34) ");
        System.out.println("35) ");
        System.out.println("36) ");

        System.out.println("Please make your choice: ");
    }

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

        switch(choice){
            case 0:
                exit = true;
                System.out.println("Thank you for using this service!");

                 break;
            case 1:
                // user report 32
                countries_largest_population.runMenu(con);
                break;
            case 2:
                // user report 31
                ArrayList<User_report_31.CountryInContinent> ReportArray31 = User_report_31.getCountriesByContinent(con);
                User_report_31.printCountriesByContinent(ReportArray31);
                break;
            case 3:
                // user report 30
                ArrayList<User_report_30.CountryInRegion> ReportArray30 = User_report_30.getCountriesByRegion(con);
                User_report_30.printCountriesByRegion(ReportArray30);
                break;
            default:
                System.out.println("testing.");
                break;

        }


    }


}
