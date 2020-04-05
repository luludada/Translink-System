package cpsc304.UI;

import cpsc304.controller.*;
import cpsc304.database.DatabaseConnectionHandler;

public class Main{

    public static void main(String args[]) {
        StartWindow start = new StartWindow();
        Admin admin = new Admin();
        Drive drive = new Drive();
        Passenger passenger = new Passenger();


        DatabaseConnectionHandler dbHandler = new DatabaseConnectionHandler();
//        boolean didConnect = dbHandler.login("ora_huangk37", "a80403090");
//        if (didConnect) {
//            admin.adminHandler = dbHandler.getAdminHandler();
//            drive.driverHandler = dbHandler.getDriverHandler();
//            passenger.passengerHandler = dbHandler.getPassengerHandler();
//        }

        new InsertWindow(dbHandler);

        start.launch(admin, drive, passenger);
        //admin.start();
        //drive.start();
        //passenger.start();

    }



}
