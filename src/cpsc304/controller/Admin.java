package cpsc304.controller;

import cpsc304.UI.AdminWindow;
import cpsc304.database.DatabaseConnectionHandler;
import cpsc304.delegates.AdminWindowDelegate;
import cpsc304.model.entities.Driver;


public class Admin implements AdminWindowDelegate {

    private AdminWindow adminWindow = null;


    public void start() {
        adminWindow = new AdminWindow();
        adminWindow.launch(this);
    }

    public void getAllDrivers() {
        Driver[] drivers = null;
        DatabaseConnectionHandler dbHandler = new DatabaseConnectionHandler();
        boolean didConnect = dbHandler.login("ora_huangk37", "a80403090");
        if (didConnect) {
            //dbHandler.databaseSetup();
            drivers = dbHandler.getAdminHandler().getAllDrivers();
            adminWindow.displayDriverList(drivers);
            //dbHandler.getAdminHandler().deletePassenger(505);
        }
    }
}
