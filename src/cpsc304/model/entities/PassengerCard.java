package cpsc304.model.entities;

import java.util.ArrayList;
import java.util.List;

public class PassengerCard {
    public int SIN;
    public String card_num; //Unique key
    public double fee;
    public int vehicle_id;
    public int PIN;
    public String phone;
    public String name;
    public String user_id;
    public String email;
    public double balance;
    private List<PassengerCard> passengerCards;


    public PassengerCard(int SIN, String card_num, int vehicle_id, String phone, String name, String user_id, String email, int PIN, List<PassengerCard> passengerCards) {
        this.SIN = SIN;
        this.card_num = card_num;
        this.vehicle_id = vehicle_id;
        this.PIN = PIN;
        this.phone = phone;
        this.name = name;
        this.user_id = user_id;
        this.email = email;

        if (passengerCards == null) {
            this.passengerCards = new ArrayList<>();
        } else {
            this.passengerCards = passengerCards;
        }
    }

    public PassengerCard(int sin, String phone, String user_id, String email, int pin, String name, String card_num, double balance, List<PassengerCard> passengerCards) {
        this.SIN = sin;
        this.phone = phone;
        this.user_id = user_id;
        this.email = email;
        this.PIN = pin;
        this.name = name;
        this.card_num = card_num;
        this.balance = balance;
        if (passengerCards == null) {
            this.passengerCards = new ArrayList<>();
        } else {
            this.passengerCards = passengerCards;
        }
    }

    public String getCardNo() {return  card_num;}

    public int getVehicle_id() { return vehicle_id; }

    public int getPIN() {return  PIN;}

    public int getSIN() { return SIN; }

    public String getPhone() {
        return phone;
    }

    public String getName() { return name; }

    public String getUserID() {
        return user_id;
    }

    public String getEmail() {
        return email;
    }

    public List<PassengerCard> getPassengerCards() {
        return passengerCards;
    }



}
