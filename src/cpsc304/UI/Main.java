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
        boolean didConnect = dbHandler.login("ora_ymy0801", "a87216792");
        if (didConnect) {
            admin.adminHandler = dbHandler.getAdminHandler();
            drive.driverHandler = dbHandler.getDriverHandler();
            passenger.passengerHandler = dbHandler.getPassengerHandler();
        }

        start.launch(admin, drive, passenger);
        //admin.start();
        //drive.start();
        //passenger.start();

    }



}
