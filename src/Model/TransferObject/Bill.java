package Model.TransferObject;

public class Bill {

    private int ticketID;

    private String payMethod;
    private double amount;
    private String loyaltyID;
    private int loyaltyPoints;

    public Bill(){


    }

    public Bill(int ticketID, String payMethod, double amount, String loyaltyID, int loyaltyPoints){

        this.ticketID = ticketID;
        this.payMethod = payMethod;
        this.amount = amount;
        this.loyaltyID = loyaltyID;
        this.loyaltyPoints = loyaltyPoints;

    }

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getLoyaltyID() {
        return loyaltyID;
    }

    public void setLoyaltyID(String loyaltyID) {
        this.loyaltyID = loyaltyID;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }
}
