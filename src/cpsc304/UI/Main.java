package cpsc304.UI;

import cpsc304.controller.*;
import cpsc304.database.DatabaseConnectionHandler;
import cpsc304.model.entities.PassengerCard;

public class Main{

    public static void main(String args[]) {
        Admin admin = new Admin();
        Drive drive = new Drive();
        Passenger passenger = new Passenger();

        DatabaseConnectionHandler dbHandler = new DatabaseConnectionHandler();
        boolean didConnect = dbHandler.login("ora_zjq1102", "a99479289");
        if (didConnect) {
            admin.adminHandler = dbHandler.getAdminHandler();
            drive.driverHandler = dbHandler.getDriverHandler();
            passenger.passengerHandler = dbHandler.getPassengerHandler();
        }


        //admin.start();
        drive.start();
    }



}
