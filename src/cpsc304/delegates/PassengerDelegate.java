package cpsc304.delegates;

import cpsc304.controller.Passenger;
import cpsc304.model.entities.PassengerCard;

public interface PassengerDelegate {
    void insertPassengerCard(PassengerCard passengerCard);
    void getPassengerCard();
    boolean verifyPassenger(String id);
    void updateBalance(double value);
}
