package cpsc304.model.entities;


public class PassengerCard1 {
    public int SIN;
    public String card_num; //Unique key
    public int PIN;
    public String phone;
    public String user_id;
    public String email;
    public int age;



    public PassengerCard1(int SIN,  String phone, String user_id, String email, int age, int PIN, String card_num) {
        this.SIN = SIN;
        this.phone = phone;
        this.user_id = user_id;
        this.email = email;
        this.age = age;
        this.PIN = PIN;
        this.card_num = card_num;
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
