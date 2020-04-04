package cpsc304.database;

import java.sql.*;
import java.util.ArrayList;

import cpsc304.model.entities.Driver;
import cpsc304.model.entities.Vehicle;
import cpsc304.model.entities.VehicleFee;

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
                        rs.getInt("sin"),
                        rs.getString("phone"),
                        rs.getString("name").trim(),
                        rs.getString("license_id"));
                result.add(driver);
                System.out.println(driver.name);
            }
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result.toArray(new Driver[result.size()]);
    }

    public String deletePassenger(int sin) {
        String msg = " ";
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM passenger_card1 WHERE sin = ?");
            ps.setInt(1, sin);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                msg = WARNING_TAG + " Passenger " + sin + " does not exist! Please enter a valid SIN! ";
            }

            connection.commit();

            ps.close();
        } catch (SQLException e) {
            msg = EXCEPTION_TAG + " " + e.getMessage();
            super.rollbackConnection();
        }
        return msg;
    }

    public VehicleFee[] getFeesums() {
        ArrayList<VehicleFee> result = new ArrayList<VehicleFee>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select vehicle_follow_drive1.vehicle_id,sum(fee) as totalfee from "
                    + "vehicle_follow_drive1,passenger_take_vehicle where "
                    + "vehicle_follow_drive1.vehicle_id=passenger_take_vehicle.vehicle_id "
                    + "group by vehicle_follow_drive1.vehicle_id" + " order by vehicle_follow_drive1.vehicle_id");

            while (rs.next()) {
                VehicleFee vh = new VehicleFee(rs.getInt("vehicle_id"), rs.getDouble("totalfee"));
                result.add(vh);
                //System.out.println(vh.vehicle_id);
                //System.out.println(vh.fee);
            }
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result.toArray(new VehicleFee[result.size()]);
    }

    public String [] getGoodPassengers() {
        ArrayList<String> result = new ArrayList<String>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select user_id from passenger_card1"
                    + " where not exists ((select vehicle_follow_drive1.vehicle_id from vehicle_follow_drive1)"+
                    " minus (select passenger_take_vehicle.vehicle_id from passenger_take_vehicle"+
                    " where passenger_take_vehicle.sin=passenger_card1.sin))");

            while (rs.next()) {
                result.add(rs.getString("user_id"));
                //System.out.println(rs.getString("user_id"));
            }
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result.toArray(new String[result.size()]);
    }
}
