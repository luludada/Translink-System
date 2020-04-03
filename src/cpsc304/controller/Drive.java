package cpsc304.controller;

import cpsc304.UI.DriverWindow;
import cpsc304.database.DriverHandler;
import cpsc304.delegates.DriverWindowDelegate;
import cpsc304.model.entities.Driver;


public class Drive implements DriverWindowDelegate {

    private DriverWindow driverWindow = null;
    public DriverHandler driverHandler = null;


    public void start() {
        driverWindow = new DriverWindow();
        driverWindow.launch(this);
    }

    public Driver getVehicleRoute(String name) {
        Driver driver  = driverHandler.getVehicleRoute("Alice");
        driverWindow.displayTwoids(driver, name);
        return driver;
    }

    public int getPassengernum(String name) {
        int num = driverHandler.getPassengernum("Alice");
        System.out.println(num);
        driverWindow.displayPnum("Alice", num);
        return num;
    }
}

