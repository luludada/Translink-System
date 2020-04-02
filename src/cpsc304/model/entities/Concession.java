package cpsc304.model.entities;

public class Concession {
    public String cardNo;
    public double discountRate;

    public Concession(String cardNo, double discountRate) {
        this.cardNo = cardNo;
        this.discountRate = discountRate;
    }

    public String getCardNo() {
        return cardNo;
    }

    public double getDiscountRate(){
        return discountRate;
    }

}
