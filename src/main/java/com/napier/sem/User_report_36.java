package com.napier.sem;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class User_report_36 {

    public static class CityPopulation {
        private String cityName;
        private int population;

        public CityPopulation(String cityName, int population) {
            this.cityName = cityName;
            this.population = population;
        }

        public String getCityName() {
            return cityName;
        }

        public int getPopulation() {
            return population;
        }
    }

    public static Map<String, Map<String, Integer>> getTopNCitiesPopulation(Connection con, int N) {
        Map<String, Map<String, Integer>> topNCities = new HashMap<>();

        try {
            Statement stmt = con.createStatement();

            String strSelect = "SELECT R.Name AS Region, C.Name AS City, C.Population " +
                    "FROM city C " +
                    "JOIN country CO ON C.CountryCode = CO.Code " +
                    "JOIN region R ON CO.Region = R.Code " +
                    "ORDER BY R.Name, C.Population DESC";

            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next()) {
                String region = rset.getString("Region");
                String city = rset.getString("City");
                int population = rset.getInt("Population");

                topNCities.putIfAbsent(region, new HashMap<>());
                Map<String, Integer> cities = topNCities.get(region);

                if (cities.size() < N) {
                    cities.put(city, population);
                }
            }

            rset.close();
            stmt.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get top N cities population");
        }

        return topNCities;
    }

    public static void printTopNCities(Map<String, Map<String, Integer>> topNCities) {
        System.out.println("Top N Populated Cities Report");
        for (String region : topNCities.keySet()) {
            System.out.println("Region: " + region);
            Map<String, Integer> cities = topNCities.get(region);
            for (String city : cities.keySet()) {
                System.out.println("City: " + city + ", Population: " + cities.get(city));
            }
            System.out.println();
        }
    }
}
