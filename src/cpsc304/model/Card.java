package model;
public class Card {

    //CardNo must be unique
    public int cardNo;
    public double cardBalance;
    public int cardCVN;

    public Card(int cardNo, double cardBalance, int cardCVN) {
        this.cardNo = cardNo;
        this.cardBalance = cardBalance;
        this.cardCVN = cardCVN;
    }
}
