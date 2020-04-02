package cpsc304.UI;

import cpsc304.controller.Admin;
import cpsc304.database.DatabaseConnectionHandler;

public class Main{

    public static void main(String args[]) {
        Admin admin = new Admin();

        DatabaseConnectionHandler dbHandler = new DatabaseConnectionHandler();
        boolean didConnect = dbHandler.login("ora_huangk37", "a80403090");
        if (didConnect) {
            admin.adminHandler = dbHandler.getAdminHandler();
        }


        admin.start();
    }



}
