package com.napier.sem;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

public class The_top_populated_capital {
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
        System.out.println("1) The top Nº populated capital cities in the world where Nº is provided by the user.");
        System.out.println("2) The top Nº populated capital cities in a continent where Nº is provided by the user.");
        System.out.println("3) The top Nº populated capital cities in a region where Nº is provided by the user. ");
        System.out.println("Please make your choice: ");
    }

    private static int getInput(boolean exit) {
        //set vars up
        Scanner kb = new Scanner(System.in);
        int choice = 0;
        int i = 5;
        boolean validInput = false;
//while loop to get valid input from user
        while (!validInput && i!=0) {
            if (kb.hasNextLine()) {
                try {
                    choice = Integer.parseInt(kb.nextLine());
                    validInput = true;
                } catch (NumberFormatException e) {
                    i--;
                    System.out.println("Invalid input!! Please enter a valid Number.\nYou have "+ i + " Attempts of 5!");

                }
            }
        }

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
                // user report 13
                ArrayList<User_report_13.TopCapitalCitiesInWorld> ReportArray11 = User_report_13.getTopPopulatedCapitalCitiesInWorld(con);
                User_report_13.printTopCapitalCitiesInWorld(ReportArray11);
                break;
            case 2:
                // user report 19
                ArrayList<User_report_19.CapitalCityDataInContinent> ReportArray19 = User_report_19.getTopPopulatedCapitalCitiesInContinent(con);
                User_report_19.printTopPopulatedCapitalCitiesInContinent(ReportArray19);
                break;
            case 3:
                // user report 12
                ArrayList<User_report_12.PopulationbyRegionReport> ReportArray10 = User_report_12.getPopulationbyRegionReport(con);
                User_report_12.printRegionPopulation(ReportArray10);
                break;
            default:
                System.out.println("\nNot Valid Choice \n Try Again ");
                break;

        }


    }


}

