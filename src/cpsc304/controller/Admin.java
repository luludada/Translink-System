package cpsc304.controller;

import cpsc304.UI.AdminWindow;
import cpsc304.database.AdminHandler;
import cpsc304.database.DatabaseConnectionHandler;
import cpsc304.delegates.AdminWindowDelegate;
import cpsc304.model.entities.Driver;
import cpsc304.model.entities.VehicleFee;


public class Admin implements AdminWindowDelegate {

    private AdminWindow adminWindow = null;
    public AdminHandler adminHandler = null;


    public void start() {
        adminWindow = new AdminWindow();
        adminWindow.launch(this);
    }

    public void getAllDrivers(String field, int numFields) {
        String[][] drivers  = adminHandler.getAllDrivers(field, numFields);
        adminWindow.displayDriverList(drivers, field);
    }

    public void sumVehicleFee() {
        VehicleFee[] vehicles  = adminHandler.getFeesums();
        adminWindow.displayVehicleFee(vehicles);
    }

    public void deletePassenger(int sin) {
        String msg = adminHandler.deletePassenger(sin);
        adminWindow.displayDeleteResult(msg);
    }

    public void getGoodPassengers(){
        String[] passengers = adminHandler.getGoodPassengers();
        adminWindow.displayGoodPassengers(passengers);
    }
}
