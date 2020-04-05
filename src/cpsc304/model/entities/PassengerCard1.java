package cpsc304.model.entities;

import java.util.ArrayList;
import java.util.List;

public class PassengerCard1 {
    public int SIN;
    public String card_num; //Unique key
    public int PIN;
    public String phone;
    public String user_id;
    public String email;
    public int age;



    public PassengerCard1(int SIN, String card_num, int vehicle_id, String phone, String name, String user_id, String email, int PIN, int age) {
        this.SIN = SIN;
        this.card_num = card_num;
        this.PIN = PIN;
        this.phone = phone;
        this.user_id = user_id;
        this.email = email;
        this.age = age;
    }


    public String getCardNo() {return  card_num;}

    public int getPIN() {return  PIN;}

    public int getSIN() { return SIN; }

    public String getPhone() {
        return phone;
    }

    public String getUserID() {
        return user_id;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() { return age; }


}
