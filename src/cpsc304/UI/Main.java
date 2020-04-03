package cpsc304.UI;

import cpsc304.controller.*;
import cpsc304.database.DatabaseConnectionHandler;

public class Main{

    public static void main(String args[]) {
        Admin admin = new Admin();
        Drive drive = new Drive();

        DatabaseConnectionHandler dbHandler = new DatabaseConnectionHandler();
        boolean didConnect = dbHandler.login("ora_zjq1102", "a99479289");
        if (didConnect) {
            admin.adminHandler = dbHandler.getAdminHandler();
            drive.driverHandler = dbHandler.getDriverHandler();
        }


        //admin.start();
        drive.start();
    }



}
