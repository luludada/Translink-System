package cpsc304.database;

import cpsc304.model.entities.Driver;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DriverHandler {
    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private Connection connection;

    public DriverHandler(Connection connection) {
        this.connection = connection;
    }

    public Driver[] getVehicleRoute(String name) {
        System.out.println(name);
        //Driver driver = new Driver(0,0,0,0,null," ",null);
        //Driver driver = new Driver(-1,-1,-1,-1,null," ", null);
        ArrayList<Driver> driver = new ArrayList<Driver>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select distinct vehicle_follow_drive1.vehicle_id,vehicle_follow_drive1.route_id"
                    + " from vehicle_follow_drive1,vehicle_follow_drive2 where " +
                    "vehicle_follow_drive1.phone=vehicle_follow_drive2.phone and " + name +" order by vehicle_id");

            while (rs.next()) {
                Driver d = new Driver(Integer.parseInt(rs.getString("vehicle_id").trim()),
                        0,
                        Integer.parseInt(rs.getString("route_id").trim()),
                        0,
                        null,
                        name,
                        null);
                driver.add(d);
                System.out.println(name);
                System.out.println(d.vehicle_id);
                System.out.println(d.route_id);
            }
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return driver.toArray(new Driver[driver.size()]);
    }

    public int getPassengernum(String name) {
        int passengernum = 0;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select count(*) as total_num"
                    + " from vehicle_follow_drive1,vehicle_follow_drive2,passenger_take_vehicle where " +
                    "vehicle_follow_drive1.phone=vehicle_follow_drive2.phone and vehicle_follow_drive2.name='" + name
                    + "'" + " and vehicle_follow_drive1.vehicle_id=passenger_take_vehicle.vehicle_id");

            while (rs.next()) {
                passengernum = Integer.parseInt(rs.getString("total_num").trim());
                System.out.println(name);
                System.out.println(passengernum);
            }
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return passengernum;
    }

}