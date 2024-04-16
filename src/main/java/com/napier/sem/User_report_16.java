

    package com.napier.sem;

    import java.sql.Connection;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.sql.Statement;
    import java.util.ArrayList;

    // Capital cities in the world, sorted by population from largest to smallest

    public class User_report_16 {
        public static class CapitalCitiesWorld {
            private String countryName;
            private String cityName;
            private int population;

        // Constructor for CapitalCitiesWorld class
        public CapitalCitiesWorld(String countryName, String cityName, int population) {
            this.countryName = countryName;
            this.cityName = cityName;
            this.population = population;
        }

        // Method to represent the object as a string
        public String toString() {
            return countryName + cityName +
                     population;
        }
    }

    // Method to retrieve capital cities in the world
    public static ArrayList<CapitalCitiesWorld> getCapitalCitiesWorld(Connection con) {
        try {
            Statement stmt = con.createStatement();

            // SQL query to retrieve capital cities in the world
                    String strSelect = "SELECT country.Name, city.Name AS City, city.Population " +
                    "FROM city " +
                    "JOIN country ON city.CountryCode = country.Code " +
                    "WHERE city.ID = country.Capital " +
                    " ORDER BY city.Population DESC " ;

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<CapitalCitiesWorld> capitalCitiesWorldList = new ArrayList<>();
            System.out.println("before while");
            // Iterate through the result set and create CapitalCitiesWorld objects
            while (rset.next()) {
                String name = rset.getString("name");
                String city = rset.getString("city");
                int population = rset.getInt("Population");

                // Create a CapitalCitiesWorld object and add it to the list
                CapitalCitiesWorld capitalCity = new CapitalCitiesWorld(name, city, population);
                capitalCitiesWorldList.add(capitalCity);
            }
            System.out.println("After while");
            return capitalCitiesWorldList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get capital cities in the world");
            return null;
        }
    }

    // Method to print capital cities in the world
    public static void printCapitalCitiesWorld(ArrayList<CapitalCitiesWorld> capitalCitiesWorldList) {
        if (capitalCitiesWorldList == null ) {
            System.out.println("No capital cities in the world to display");
            return;
        }

        //name of report
        System.out.println("Capital Cities in the World Report:");
        //print header
        System.out.println(String.format("%-35s %-35s %-20s", "Country Name", "Capatial City", "Population"));
        for (CapitalCitiesWorld capitalCity : capitalCitiesWorldList) {
            if (capitalCity == null) {
                System.out.println("Encountered a null");
                continue;
            }String emp_string =
                    String.format("%-35s %-35s %-20s",
                            capitalCity.countryName, capitalCity.cityName, capitalCity.population);
            System.out.println(emp_string);
        }
    }

    }

