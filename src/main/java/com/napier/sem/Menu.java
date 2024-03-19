package com.napier.sem;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Menu {
static boolean exit;
    public static void runMenu(Connection con){

    PrintHeader();
    while (!exit){
        printMenu();
        System.out.println("Moving to getinput()");
        int choice = getInput();
        System.out.println("leaving getinput()");
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
        System.out.println("1)All the countries in the world organised by largest population to smallest.");
        System.out.println("2)All the countries in a continent organised by largest population to smallest.");
        System.out.println("3) All the countries in a region organised by largest population to smallest. ");
    }

    private static int getInput(){
        System.out.print("Building Scanner ");
        Scanner kb = new Scanner(System.in);
        System.out.print("Build Scanner ");
        System.out.print("Integer choice being declared");
        int choice; // Initialize choice to a value that will enter the loop at least once
        System.out.print("Integer choice declared");

                System.out.print("Please enter a number: ");
                choice = kb.nextInt(); // Wait for user input
                //break; // Exit the loop if input is successfully parsed



        // Close the scanner when done
        kb.close();

        return choice;
    }

    private static void performAction(int choice, Connection con){

        switch(choice){
            case 0:
                exit = true;
                System.out.println("Thank you for using this service!");
                break;
            case 1:
                ArrayList<test_sql.CapitalCityReport> ReportArray1 = test_sql.getAllCities(con);
                if (ReportArray1 != null) {
                    // Print the retrieved cities to the console
                    //log post
                    System.out.println("going to print reports.");

                    test_sql.printAllCities(ReportArray1);
                } else {
                    System.out.println("No report retrieved from the database.");
                }
                break;
            case 2:
                System.out.println("testing.");
                break;
            case 3:
                System.out.println("testing.");
                break;
            default:
                System.out.println("testing.");
                break;

        }


    }


}
