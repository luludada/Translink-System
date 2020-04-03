package cpsc304.controller;

import cpsc304.UI.DriverWindow;
import cpsc304.UI.PassengerWindow;
import cpsc304.database.PassengerHandler;
import cpsc304.delegates.PassengerDelegate;
import cpsc304.model.entities.PassengerCard;

public class Passenger implements PassengerDelegate {

    private PassengerWindow passengerWindow = null;
    public PassengerHandler passengerHandler = null;

    public void start() {
        passengerWindow = new PassengerWindow();
        passengerWindow.launch(this);
    }

    public void insertPassengerCard(PassengerCard passengerCard){
        passengerHandler.insertPassengerCard(passengerCard);
    }

    public void getPassengerCard() {
        passengerHandler.viewCardAccount();
    }

    public void updateBalance(double value) {
        passengerHandler.updatePassengerCardBalance(value);
    }
}