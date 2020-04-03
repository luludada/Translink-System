package cpsc304.model.entities;

public class PassengerCard {
    public int SIN;
    public String cardNo; //Unique key
    public double fee;
    public int vehicle_id;
    public int PIN;
    public String phone;
    public String name;
    public String userID;
    public String email;

    public PassengerCard(int SIN, String cardNo, int vehicle_id, String phone, String name, String userID, String email, int PIN) {
        this.SIN = SIN;
        this.cardNo = cardNo;
        this.vehicle_id = vehicle_id;
        this.PIN = PIN;
        this.phone = phone;
        this.name = name;
        this.userID = userID;
        this.email = email;
    }

    public String getCardNo() {return  cardNo;}

    public int getVehicle_id() { return vehicle_id; }

    public int getPIN() {return  PIN;}

    public int getSIN() { return SIN; }

    public String getPhone() {
        return phone;
    }

    public String getName() { return name; }

    public String getUserID() {
        return userID;
    }

    public String getEmail() {
        return email;
    }

}
