package com.napier.sem;

import javax.swing.*;

public class User_Input {

    public static String UserReport(){

        String[] report = new String[25];
        report[0] = "All the countries in the world organised by largest population to smallest. ";
        report[1] = "All the countries in a continent organised by largest population to smallest. ";
        report[2] = "All the countries in a region organised by largest population to smallest. ";
        report[3] = "The top N populated countries in the world where N is provided by the user. ";
        report[4] = "The top N populated countries in a continent where N is provided by the user. ";
        report[5] = "The top N populated countries in a region where N is provided by the user. ";
        report[6] = "All the cities in the world organised by largest population to smallest. ";
        report[7] = "All the cities in a continent organised by largest population to smallest. ";
        report[8] = "All the cities in a region organised by largest population to smallest. ";
        report[9] = "All the cities in a country organised by largest population to smallest. ";
        report[10] = "All the cities in a district organised by largest population to smallest. ";
        report[11] = "The top N populated cities in the world where N is provided by the user. ";
        report[12] = "The top N populated cities in a continent where N is provided by the user. ";
        report[13] = "The top N populated cities in a region where N is provided by the user. ";
        report[14] = "The top N populated cities in a country where N is provided by the user. ";
        report[15] = "The top N populated cities in a district where N is provided by the user. ";
        report[16] = "All the capital cities in the world organised by largest population to smallest. ";
        report[17] = "All the capital cities in a continent organised by largest population to smallest. ";
        report[18] = "All the capital cities in a region organised by largest to smallest. ";
        report[19] = "The top N populated capital cities in the world where N is provided by the user. ";
        report[20] = "The top N populated capital cities in a continent where N is provided by the user. ";
        report[21] = "The top N populated capital cities in a region where N is provided by the user. ";
        report[22] = "The population of people, people living in cities, and people not living in cities in each continent. ";
        report[23] = "The population of people, people living in cities, and people not living in cities in each region. ";
        report[24] = "The population of people, people living in cities, and people not living in cities in each country. ";


     String SelectedReport = (String) JOptionPane.showInputDialog(null,"Choose a report", "Report selection",JOptionPane.QUESTION_MESSAGE,null, report,"Report");
        System.out.println("Selected Report is: "+ SelectedReport);
        return SelectedReport;
    }




}
