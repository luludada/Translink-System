package cpsc304.model.entities;

public class Passenger {
    public int SIN;
    public int phone;
    public String name;
    public String userID;
    public String email;

    public Passenger(int SIN, int phone, String name, String userID, String email) {
        this.SIN = SIN;
        this.phone = phone;
        this.name = name;
        this.userID = userID;
        this.email = email;
    }

}
