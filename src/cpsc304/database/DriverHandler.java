package cpsc304.database;

import cpsc304.model.entities.Driver;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DriverHandler {
    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private Connection connection;

    public DriverHandler(Connection connection) {
        this.connection = connection;
    }

    public void getVehicleRoute(String name) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select vehicle_follow_drive1.vehicle_id,vehicle_follow_drive1.route_id"
                    + " from vehicle_follow_drive1,vehicle_follow_drive2 where " +
                    "vehicle_follow_drive1.phone=vehicle_follow_drive2.phone and vehicle_follow_drive2.name='" + name + "'");

            while (rs.next()) {
                Driver driver = new Driver(Integer.parseInt(rs.getString("vehicle_id").trim()),
                        0,
                        Integer.parseInt(rs.getString("route_id").trim()),
                        0,
                        null,
                        name,
                        null);
                System.out.println(name);
                System.out.println(driver.vehicle_id);
                System.out.println(driver.route_id);
            };
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public void getPassengernum(String name) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select count(*) as total_num"
                    + " from vehicle_follow_drive1,vehicle_follow_drive2,passenger_take_vehicle where " +
                    "vehicle_follow_drive1.phone=vehicle_follow_drive2.phone and vehicle_follow_drive2.name='" + name
                    + "'" + " and vehicle_follow_drive1.vehicle_id=passenger_take_vehicle.vehicle_id");

            while (rs.next()) {
                int passengernum = Integer.parseInt(rs.getString("total_num").trim());
                System.out.println(name);
                System.out.println(passengernum);
            };
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

}