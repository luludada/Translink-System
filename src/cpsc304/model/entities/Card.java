package cpsc304.model.entities;
public class Card {

    //CardNo must be unique
    public String card_num;
    public double balance;
    public int CVN;

    public Card (String card_num, double balance, int CVN) {
        this.card_num = card_num;
        this.balance = balance;
        this.CVN = CVN;
    }


    public String getCardNo() {return  card_num;}

    public Double getBalance() {return  balance;}

    public int getCVN() {return  CVN;}
}
