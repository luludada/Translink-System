package cpsc304.model.entities;
public class Card {

    //CardNo must be unique
    public String cardNo;
    public double cardBalance;
    public int cardCVN;

    public Card(String cardNo, double cardBalance, int cardCVN) {
        this.cardNo = cardNo;
        this.cardBalance = cardBalance;
        this.cardCVN = cardCVN;
    }
}
