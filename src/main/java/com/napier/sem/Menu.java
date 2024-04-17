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
        System.out.println("1)  All the countries organised by largest to smallest MENU");
        System.out.println("2)  The top Nº populated countries MENU");
        System.out.println("3)  All the cities organised by largest population to smallest MENU");
        System.out.println("4)  The top Nº populated cities MENU");
        System.out.println("5)  All the capital cities organised by largest population to smallest");
        System.out.println("6)  The top Nº populated capital cities MENU");
        System.out.println("7)  The population of people, people living in cities, and people not living in cities.");
        System.out.println("8)  The population of **USER CHOICE**");
        System.out.println("9)  Languages Report, Country Report, City Report and Capital City Report ");
        System.out.println("10) Population Report % living in cities and % not living in cities.");


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
                // launch menu All the countries in the world organised by largest population to smallest.
                //All the countries in a continent organised by largest population to smallest.
                //All the countries in a region organised by largest population to smallest.
                countries_largest_population.runMenu(con);
                break;
            case 2:
                //The top N populated countries in the world where N is provided by the user.
                //The top N populated countries in a continent where N is provided by the user.
                //The top N populated countries in a region where N is provided by the user.
                The_top_populated_countries.runMenu(con);
                break;
            case 3:
                // All the cities in the world organised by largest population to smallest.
                //All the cities in a continent organised by largest population to smallest.
                //All the cities in a region organised by largest population to smallest.
                //All the cities in a country organised by largest population to smallest.
                //All the cities in a district organised by largest population to smallest.
                cities_orgd_by_larg_to_small.runMenu(con);
                break;
            case 4:
                // The top N populated cities in the world where N is provided by the user.
                //The top N populated cities in a continent where N is provided by the user.
                //The top N populated cities in a region where N is provided by the user.
                //The top N populated cities in a country where N is provided by the user.
                //The top N populated cities in a district where N is provided by the user.
                Top_pop_cities.runMenu(con);
                break;
            case 5:
                // All the capital cities in the world organised by largest population to smallest.
                //All the capital cities in a continent organised by largest population to smallest.
                //All the capital cities in a region organised by largest to smallest.
                capital_cities_largest_pop_to_small.runMenu(con);
                break;
            case 6:
                //The top N populated capital cities in the world where N is provided by the user.
                //The top N populated capital cities in a continent where N is provided by the user.
                //The top N populated capital cities in a region where N is provided by the user.
                The_top_populated_capital.runMenu(con);
                break;
            case 7:
                //The population of people, people living in cities, and people not living in cities in each continent.
                //The population of people, people living in cities, and people not living in cities in each region.
                //The population of people, people living in cities, and people not living in cities in each country.
                people_living_in_cities.runMenu(con);
                break;
            case 8:
                //Additionally, the following information should be accessible to the organisation:
                //The population of the world.
                //The population of a continent.
                //The population of a region.
                //The population of a country.
                //The population of a district.
                //The population of a city.
                population.runMenu(con);
                break;
            case 9:
                //Finally, the organisation has asked if it is possible to provide the number of people who speak the following the following languages from greatest number to smallest, including the percentage of the world population:
                //Chinese, English, Hindi, Spanish and Arabic.
                Additionally.runMenu(con);
                break;
            case 10:
                //or the population reports, the following information is requested:
                //
                //The name of the continent/region/country.
                //The total population of the continent/region/country.
                //The total population of the continent/region/country living in cities (including a %).
                //The total population of the continent/region/country not living in cities (including a %).
                Population_Report.runMenu(con);
                break;
            default:
                System.out.println("\nNot Valid Choice \n Try Again\n***OR 0 TO EXIT!!*** ");
                break;

        }


    }


}
