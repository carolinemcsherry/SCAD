
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class User_report_21 {

    public static class CityReport {
        private String CityName;
        private String CountryName;
        private String District;
        private Long Population;

        public CityReport(String CityName, String CountryName, String District, Long Population) {
            this.CityName = CityName;
            this.CountryName = CountryName;
            this.District = District;
            this.Population = Population;
        }

        public String toString() {
            return CityName + " " + CountryName + " " + District + " " + Population;
        }
    }

    public static ArrayList<CityReport> getCityReportByDistrict(Connection con, String district) {
        try {
            String input = JOptionPane.showInputDialog("Enter the Number of top Populated Regions");

            Statement stmt = con.createStatement();

            String strSelect = "SELECT A.Name AS CityName, B.Name AS CountryName, A.District, A.Population " +
                    "FROM city A " +
                    "LEFT JOIN country B ON A.CountryCode = B.Code " +
                    "WHERE A.District = '" + district + "' " +
                    "ORDER BY A.Population DESC " +
                    "LIMIT " + input;

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<CityReport> cityReports = new ArrayList<>();

            while (rset.next()) {
                String CityName = rset.getString("CityName");
                String CountryName = rset.getString("CountryName");
                String District = rset.getString("District");
                Long Population = rset.getLong("Population");

                CityReport city = new CityReport(CityName, CountryName, District, Population);
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
            String Table_string =
                    String.format("%-25s %-25s %-25s %-25s",
                            city.CityName, city.CountryName, city.District, city.Population);
            System.out.println(Table_string);
        }
    }
}