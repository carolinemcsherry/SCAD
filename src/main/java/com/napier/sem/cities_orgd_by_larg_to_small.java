package com.napier.sem;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

public class cities_orgd_by_larg_to_small {
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
        System.out.println("|      All the cities organised by largest           |");
        System.out.println("|               to smallest Menu                     |");
        System.out.println("+----------------------------------------------------+");

    }

    private static void printMenu(){
        //menu options
        System.out.println("\nPlease choose report to process: ");
        System.out.println("0) Main Menu ");
        System.out.println("1) All the cities in the world organised by largest population to smallest.");
        System.out.println("2) All the cities in a continent organised by largest population to smallest.");
        System.out.println("3) All the cities in a region organised by largest population to smallest. ");
        System.out.println("4) All the cities in a country organised by largest population to smallest. ");
        System.out.println("5) All the cities in a district organised by largest population to smallest. ");
        System.out.println("Please make your choice: ");
    }
    //exit code when 0
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
                // user report 25
                ArrayList<User_report_25.CityReport> ReportArray25 = User_report_25.getCitiesInWorld(con);
                User_report_25.printCityReport(ReportArray25);
                break;
            case 2:
                // user report 24
                ArrayList<User_report_24.CityReport> ReportArray24 = User_report_24.getCitiesByContinent(con);
                User_report_24.printCityReport(ReportArray24);
                break;
            case 3:
                // user report 23
                ArrayList<User_report_23.CityReport> ReportArray23 = User_report_23.getCitiesByRegion(con);
                User_report_23.printCityReport(ReportArray23);
                break;
            case 4:
                // user report 22
                ArrayList<User_report_22.CityReport> ReportArray22 = User_report_22.getCityReport(con);
                User_report_22.printCityReport(ReportArray22);
                break;
            case 5:
                // user report 21
                ArrayList<User_report_21.CityReport> ReportArray21 = User_report_21.getCityReportByDistrict(con);
                User_report_21.printCityReport(ReportArray21);
                break;
            default:
                System.out.println("\nNot Valid Choice \n Try Again ");
                break;

        }


    }


}



