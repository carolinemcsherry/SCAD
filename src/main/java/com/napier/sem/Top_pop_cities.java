package com.napier.sem;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

public class Top_pop_cities {
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
        System.out.println("|          The top Nº populated cities               |");
        System.out.println("|                    menu                            |");
        System.out.println("+----------------------------------------------------+");

    }

    private static void printMenu(){
//menu options
        System.out.println("\nPlease choose report to process: ");
        System.out.println("0) Main Menu ");
        System.out.println("1) The top Nº populated cities in the world where Nº is provided by the user.");
        System.out.println("2) The top Nº populated cities in a continent where Nº is provided by the user.");
        System.out.println("3) The top Nº populated cities in a region where Nº is provided by the user ");
        System.out.println("4) The top Nº populated cities in a country  where Nº is provided by the user ");
        System.out.println("5) The top Nº populated cities in a district  where Nº is provided by the user ");
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
                // user report 20
                ArrayList<User_report_20.TopCitiesInWorld> ReportArray20 = User_report_20.getTopPopulatedCitiesInWorld(con);
                User_report_20.printTopPopulatedCitiesInWorld(ReportArray20);
                break;
            case 2:
                // user report 34
                ArrayList<User_report_34.TopCitiesInContinent> ReportArray34 = User_report_34.getTopPopulatedCitiesInContinent(con);
                User_report_34.printTopPopulatedCitiesInContinent(ReportArray34);
                break;
            case 3:
                // user report 35
                ArrayList<User_Report_35.PopulationbyRegionReport> ReportArray35 = User_Report_35.getPopulationbyRegionReport(con);
                User_Report_35.printRegionPopulation(ReportArray35);
                break;
            case 4:
                // user report 18
                ArrayList<User_report_18.TopCitiesInCountry> ReportArray16 = User_report_18.getTopPopulatedCitiesInCountry(con);
                User_report_18.printTopPopulatedCitiesInCountry(ReportArray16);
                break;
            case 5:
                // user report 17
                ArrayList<User_report_17.RegionPopulationReport> ReportArray15 = User_report_17.getRegionPopulation(con);
                User_report_17.printRegionPopulation(ReportArray15);
                break;
            default:
                System.out.println("\nNot Valid Choice \n Try Again ");
                break;

        }


    }


}
