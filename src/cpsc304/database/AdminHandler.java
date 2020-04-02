package cpsc304.database;

import java.sql.*;
import cpsc304.model.entities.Driver;

public class AdminHandler {
    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private Connection connection;

    public AdminHandler(Connection connection) {
        this.connection = connection;
    }

    public void getAllDrivers() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from vehicle_follow_drive1, vehicle_follow_drive2 where "
                                                    + "vehicle_follow_drive1.phone=vehicle_follow_drive2.phone");

            /*// get info on ResultSet
    		ResultSetMetaData rsmd = rs.getMetaData();

    		System.out.println(" ");

    		// display column names;
    		for (int i = 0; i < rsmd.getColumnCount(); i++) {
    			// get column name and print it
    			System.out.printf("%-15s", rsmd.getColumnName(i + 1));
    		}*/

            while (rs.next()) {
                Driver driver = new Driver(Integer.parseInt(rs.getString("vehicle_id").trim()),
                        Integer.parseInt(rs.getString("capacity").trim()),
                        Integer.parseInt(rs.getString("route_id").trim()),
                        Integer.parseInt(rs.getString("sin").trim()),
                        rs.getString("phone"),
                        rs.getString("name"),
                        rs.getString("license_id"));
                System.out.println(driver.name);
                System.out.println(driver.SIN);
            } while (rs.next());
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }
}
