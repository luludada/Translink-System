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
        Driver[] driver = driverHandler.getVehicleRoute(name);
        Driver d = new Driver(0,0,0,0,null,null,null);
        if (driver==null) return null;
        driverWindow.displayTwoids(driver, name);
        return d;
    }

    public int getPassengernum(String name) {
        int num = driverHandler.getPassengernum(name);
        System.out.println(num);
        driverWindow.displayPnum(name, num);
        return num;
    }
}

