package com.napier.sem;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class App {

    public static void main(String[] args) {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();
        System.out.println("Database has successfully connected and will now pull a query");

        // Retrieve and print city information
        ArrayList<City> cities = a.getAllCities();
        System.out.println("There are a total of " + cities.size() + " cities in the database");

        // Disconnect from database
        a.disconnect();
        System.out.println("Database has successfully disconnected");
    }

    private Connection con = null;

    public void connect() {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sql) {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(sql.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    public void disconnect() {
        if (con != null) {
            try {
                // Close connection
                con.close();
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }

    public ArrayList<City> getAllCities() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            // Create string for SQL statement
            String strSelect =
                    "SELECT ID, Name, CountryCode, District, Population " +
                            "FROM city " +
                            "ORDER BY ID ASC";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            // Extract city information
            ArrayList<City> cities = new ArrayList<>();
            while (rset.next()) {
                City city = new City();
                city.setId(rset.getInt("ID"));
                city.setName(rset.getString("Name"));
                city.setCountryCode(rset.getString("CountryCode"));
                city.setDistrict(rset.getString("District"));
                city.setPopulation(rset.getInt("Population"));
                cities.add(city);
            }

            return cities;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }

    public class City {
        private int id;
        private String name;
        private String countryCode;
        private String district;
        private int population;

        

        public String toString() {
            return "City{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", countryCode='" + countryCode + '\'' +
                    ", district='" + district + '\'' +
                    ", population=" + population +
                    '}';
        }

        public void setPopulation(int population) {
        }

        public void setDistrict(String district) {
        }

        public void setCountryCode(String countryCode) {
        }

        public void setId(int id) {
        }

        public void setName(String name) {
        }
    }
}
