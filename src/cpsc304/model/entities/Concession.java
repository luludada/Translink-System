package cpsc304.model.entities;

public class Concession {
    public int cardNo;
    public double discountRate;

    public Concession(int cardNo, double discountRate) {
        this.cardNo = cardNo;
        this.discountRate = discountRate;
    }

    public int getCardNo() {
        return cardNo;
    }

    public double getDiscountRate(){
        return discountRate;
    }

}
