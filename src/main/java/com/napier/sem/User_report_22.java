package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class User_report_22 {

    public static class CityReport {
        private String cityName;
        private String countryName;
        private String district;
        private Long population;

        public CityReport(String cityName, String countryName, String district, Long population) {
            this.cityName = cityName;
            this.countryName = countryName;
            this.district = district;
            this.population = population;
        }

        public String toString() {
            return cityName + " " + countryName + " " + district + " " + population;
        }
    }

    public static ArrayList<CityReport> getCityReportByCountry(Connection con, String country) {
        String input = JOptionPane.showInputDialog("Enter the Number of top Populated Regions");

        try {
            Statement stmt = con.createStatement();

            String strSelect = "SELECT A.Name AS CityName, B.Name AS CountryName, A.District, A.Population " +
                    "FROM city A " +
                    "LEFT JOIN country B ON A.CountryCode = B.Code " +
                    "WHERE B.Name = '" + country + "' " +
                    "ORDER BY A.Population DESC " +
                    "LIMIT " + input;

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<CityReport> cityReports = new ArrayList<>();

            while (rset.next()) {
                String cityName = rset.getString("CityName");
                String countryName = rset.getString("CountryName");
                String district = rset.getString("District");
                Long population = rset.getLong("Population");

                CityReport city = new CityReport(cityName, countryName, district, population);
                cityReports.add(city);
            }

            rset.close();
            stmt.close();

            return cityReports;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city report details");
            return null;
        }
    }

    public static void printCityReport(ArrayList<CityReport> cities) {
        if (cities == null) {
            System.out.println("No cities");
            return;
        }
        System.out.println("City Report:");
        System.out.println(String.format("%-25s %-25s %-25s %-25s", "CityName", "CountryName", "District", "Population"));
        for (CityReport city : cities) {
            if (city == null)
                continue;
            String tableString =
                    String.format("%-25s %-25s %-25s %-25s",
                            city.cityName, city.countryName, city.district, city.population);
            System.out.println(tableString);
        }
    }
}