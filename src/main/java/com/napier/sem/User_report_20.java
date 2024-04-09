import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class User_report_20 {

    public static class TopCitiesInWorld {
        private String cityName;
        private int population;

        public TopCitiesInWorld(String cityName, int population) {
            this.cityName = cityName;
            this.population = population;
        }

        public String toString() {
            return "City Name: " + cityName + ", " +
                    "Population: " + population;
        }
    }

    public static ArrayList<TopCitiesInWorld> getTopPopulatedCitiesInWorld(Connection con) {
        // Prompt the user for input
        String input = JOptionPane.showInputDialog("Enter the Number of top Populated Cities");

        try {
            Statement stmt = con.createStatement();

            String strSelect = "SELECT Name AS CityName, Population " +
                    "FROM city " +
                    "ORDER BY Population DESC " +
                    "LIMIT " + input;

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<TopCitiesInWorld> topCitiesList = new ArrayList<>();

            while (rset.next()) {
                String cityName = rset.getString("CityName");
                int population = rset.getInt("Population");

                TopCitiesInWorld topCity = new TopCitiesInWorld(cityName, population);
                topCitiesList.add(topCity);
            }
            return topCitiesList;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to get top populated cities in the world");
            return null;
        }
    }

    public static void printTopPopulatedCitiesInWorld(ArrayList<TopCitiesInWorld> topCitiesList) {
        if (topCitiesList == null) {
            System.out.println("No cities");
            return;
        }

        System.out.println("Top Populated Cities in the World Report:");
        for (TopCitiesInWorld topCity : topCitiesList) {
            System.out.println(topCity);
        }
    }
}