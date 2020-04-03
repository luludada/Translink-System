package cpsc304.delegates;

import cpsc304.model.entities.Driver;

public interface DriverWindowDelegate {
    Driver getVehicleRoute(String name);
    int getPassengernum(String name);
}
