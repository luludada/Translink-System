package cpsc304.database;

import java.sql.*;
import java.util.ArrayList;

import cpsc304.model.entities.Driver;

public class AdminHandler extends DatabaseConnectionHandler{
    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private static final String WARNING_TAG = "[WARNING]";
    private Connection connection;

    public AdminHandler(Connection connection) {
        this.connection = connection;
    }

    public Driver[] getAllDrivers() {
        ArrayList<Driver> result = new ArrayList<Driver>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select name, vehicle_follow_drive1.phone, sin, license_id from "
                                                    + "vehicle_follow_drive1, vehicle_follow_drive2 where "
                                                    + "vehicle_follow_drive1.phone=vehicle_follow_drive2.phone "
                                                    + "order by name");

            /*// get info on ResultSet
    		ResultSetMetaData rsmd = rs.getMetaData();

    		System.out.println(" ");

    		// display column names;
    		for (int i = 0; i < rsmd.getColumnCount(); i++) {
    			// get column name and print it
    			System.out.printf("%-15s", rsmd.getColumnName(i + 1));
    		}*/

            while (rs.next()) {
                Driver driver = new Driver(-1,
                        -1,
                        -1,
                        Integer.parseInt(rs.getString("sin").trim()),
                        rs.getString("phone"),
                        rs.getString("name"),
                        rs.getString("license_id"));
                result.add(driver);
                System.out.println(driver.name);
            };
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result.toArray(new Driver[result.size()]);
    }

    public void deletePassenger(int sin) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM passenger_card1 WHERE sin = ?");
            ps.setInt(1, sin);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println(WARNING_TAG + " Passenger " + sin + " does not exist!");
            }

            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            super.rollbackConnection();
        }
    }

    public void getFeesums() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select vehicle_follow_drive1.vehicle_id,sum(fee) as totalfee from "
                    + "vehicle_follow_drive1,passenger_take_vehicle where "
                    + "vehicle_follow_drive1.vehicle_id=passenger_take_vehicle.vehicle_id "
                    + "group by vehicle_follow_drive1.vehicle_id");

            while (rs.next()) {
                System.out.println(rs.getString("vehicle_id"));
                System.out.println(rs.getString("totalfee"));
            };
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result.toArray(new Driver[result.size()]);
    }
}
