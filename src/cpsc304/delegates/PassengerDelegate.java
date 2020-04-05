package cpsc304.delegates;

import cpsc304.controller.Passenger;
import cpsc304.model.entities.PassengerCard;

public interface PassengerDelegate {
    PassengerCard getPassengerCard(String user_id);
    boolean verifyPassenger(String user_id);
    void updateBalance(double value);
    void insertPassengerCard(int sin, String userID, String phone, String name, String email, String cardNo, int pin);
}
