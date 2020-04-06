package cpsc304.controller;

import cpsc304.UI.PassengerWindow;
import cpsc304.database.DatabaseConnectionHandler;
import cpsc304.database.PassengerHandler;
import cpsc304.delegates.PassengerDelegate;
import cpsc304.model.entities.PassengerCard1;

public class Passenger implements PassengerDelegate {

    private PassengerWindow passengerWindow = null;
    public PassengerHandler passengerHandler = null;
    public DatabaseConnectionHandler dbHandler = null;

    public void start() {
        passengerWindow = new PassengerWindow();
        passengerWindow.launch(this);
    }

//    public PassengerCard1 getPassengerCard(String user_id) {
//        return passengerHandler.getCardAccount(user_id);
//
//    }
//
//    public boolean verifyPassenger(String user_id){
//        return passengerHandler.verifyUser(user_id);
//    }
//
//
//    public void updateBalance(double value) {
//        passengerHandler.updatePassengerCardBalance(value);
//    }
//
//    public void insertPassengerCard(int sin, String userID, String phone, String name, String email, String cardNo, int pin) {
//        passengerHandler.insertPassengerCard(sin, userID, phone, name, email, cardNo, pin);
//    }
}
