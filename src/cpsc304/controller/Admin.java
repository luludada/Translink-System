package cpsc304.controller;

import cpsc304.UI.AdminWindow;
import cpsc304.database.AdminHandler;
import cpsc304.database.DatabaseConnectionHandler;
import cpsc304.delegates.AdminWindowDelegate;
import cpsc304.model.entities.Driver;


public class Admin implements AdminWindowDelegate {

    private AdminWindow adminWindow = null;
    public AdminHandler adminHandler = null;


    public void start() {
        adminWindow = new AdminWindow();
        adminWindow.launch(this);
    }

    public void getAllDrivers() {
        Driver[] drivers  = adminHandler.getAllDrivers();
        adminWindow.displayDriverList(drivers);
    }
}
