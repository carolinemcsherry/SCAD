package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class User_report_5{

    /*the organisation has asked if it is possible to provide the number of people who speak the following the following languages from greatest number to smallest, including the percentage of the world population:

    Chinese.
    English.
    Hindi.
    Spanish.
    Arabic.

     */

    // Inner class to represent language statistics
    public static class LanguageStats {
        private String language;
        private int speakersWorldwide;
        private String percentageOfWorldPopulation;

        // Constructor for LanguageStats class
        public LanguageStats(String language, int speakersWorldwide, String percentageOfWorldPopulation) {
            this.language = language;
            this.speakersWorldwide = speakersWorldwide;
            this.percentageOfWorldPopulation = percentageOfWorldPopulation;
        }

        // Method to represent the object as a string
        public String toString() {
            return  language +
                     speakersWorldwide +
                     percentageOfWorldPopulation;
        }
    }

    // Method to retrieve language statistics
    public static ArrayList<LanguageStats> getLanguageStatistics(Connection con) {
        try {
            Statement stmt = con.createStatement();

            // SQL query to retrieve language statistics
            String strSelect = "SELECT countrylanguage.Language, " +
                    "SUM(ROUND(countrylanguage.Percentage / 100 * country.Population)) AS speakers_world_wide, " +
                    "CONCAT(ROUND((SUM(ROUND(countrylanguage.Percentage / 100 * country.Population)) / " +
                    "(SELECT SUM(Population) FROM country)) * 100, 2), '%') AS percentage_of_speakers " +
                    "FROM countrylanguage " +
                    "JOIN country ON country.Code = countrylanguage.CountryCode " +
                    "WHERE countrylanguage.Language IN ('Chinese', 'English', 'Hindi', 'Spanish', 'Arabic') " +
                    "GROUP BY countrylanguage.Language " +
                    "ORDER BY speakers_world_wide DESC";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<LanguageStats> languageStatsList = new ArrayList<>();

            // Iterate through the result set and create LanguageStats objects
            while (rset.next()) {
                String language = rset.getString("Language");
                int speakersWorldwide = rset.getInt("speakers_world_wide");
                String percentageOfWorldPopulation = rset.getString("percentage_of_speakers");

                // Create a LanguageStats object and add it to the list
                LanguageStats languageStats = new LanguageStats(language, speakersWorldwide, percentageOfWorldPopulation);
                languageStatsList.add(languageStats);
            }
            return languageStatsList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get language statistics");
            return null;
        }
    }

    // Method to print language statistics
    public static void printLanguageStatistics(ArrayList<LanguageStats> languageStatsList) {
        // Check Array List  is not null
        if (languageStatsList == null)
        {
            System.out.println("No language Stats List");
            return;
        }
        //print report name
        System.out.println("Language Statistics:");
        //format and print header
        System.out.println(String.format("%-25s %-25s %-25s", "Language", "speakers_world_wide", "percentage_of_speakers"));

        for (LanguageStats Stat : languageStatsList) {
            //If an attribute value is null the job will continue
            if (Stat == null)
                continue;
            //Prints table values in columns
            String Table_string =
                    String.format("%-25s %-25s %-25s",
                            Stat.language, Stat.speakersWorldwide, Stat.percentageOfWorldPopulation);
            System.out.println(Table_string);




        }
    }
}

// code end
