package com.napier.sem;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

public class Additionally {
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
        System.out.println("|          The Additional Report Menu                |");
        System.out.println("+----------------------------------------------------+");

    }

    private static void printMenu(){
//menu options
        System.out.println("\nPlease choose report to process: ");
        System.out.println("0) Main Menu ");
        System.out.println("1)Number of people who speak\n" +
                "•\tChinese. \n" +
                "•\tEnglish. \n" +
                "•\tHindi. \n" +
                "•\tSpanish. \n" +
                "•\tArabic. \n");
        System.out.println("from greatest number to smallest and % of the world population ");
        System.out.println("2) Country Report \n" +
                "A country report has the following columns: \n" +
                "•\tcountry Code. \n" +
                "•\tcountry Name. \n" +
                "•\tContinent. \n" +
                "•\tRegion. \n" +
                "•\tPopulation. \n" +
                "•\tcountry Capital. \n ");
        System.out.println("3) City Report \n" +
                "A city report has the following columns: \n" +
                "•\tCity Name. \n" +
                "•\tCity Country. \n" +
                "•\tCity District. \n" +
                "•\tCity Population. \n ");
        System.out.println("4) Capital City Report \n" +
                "A capital city report has the following columns: \n" +
                "•\tCapital City Name. \n" +
                "•\tCapital City Country. \n" +
                "•\tCapital City Population. \n");
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
                // user report 5
                ArrayList<User_report_5.LanguageStats> ReportArray3 = User_report_5.getLanguageStatistics(con);
                User_report_5.printLanguageStatistics(ReportArray3);
                break;
            case 2:
                //user report 4
                ArrayList<User_report_4.CountryReport> ReportArray2 = User_report_4.getAllCountries(con);
                User_report_4.printCountryReport(ReportArray2);
                break;
            case 3:
                // user report 3
                ArrayList<User_report_3.CityReport> ReportArray1 = User_report_3.getCityReport(con);
                User_report_3.printCityReport(ReportArray1);
                break;
            case 4:
                // user report 2
                ArrayList<User_report_2.CapitalCityReport> ReportArray = User_report_2.getAllCapitalCities(con);
                User_report_2.printCapitalCityReport(ReportArray);
                break;
            default:
                System.out.println("\nNot Valid Choice \nTry Again ");
                break;

        }


    }


}

