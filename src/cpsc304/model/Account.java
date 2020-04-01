package model;

public class Account {
    public String userID;
    public String email;
    public int PIN;

    public Account(String userID, String email, int PIN) {
        this.userID = userID;
        this.email = email;
        this.PIN = PIN;
    }
}
