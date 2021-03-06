package cpsc304.delegates;

import cpsc304.model.entities.Driver;

public interface AdminWindowDelegate {
    void getAllDrivers(String field, int numFields);
    void sumVehicleFee();
    void deletePassenger(int sin);
    void getGoodPassengers();
}
