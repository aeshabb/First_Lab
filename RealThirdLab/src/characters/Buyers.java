package characters;

import items.Salt;
import places.Place;

public class Buyers implements Buyer{
    private double balance;
    private Salt salt;
    private Place place;

    public Buyers(double balance, Salt salt, Place place) {
        this.balance = balance;
        this.salt = salt;
        this.place = place;
    }

    public void buySalt(double money, Ponchik ponchik) {
        balance -= money;
        salt.setAmount(salt.getAmount() + money / ponchik.getSaltValue());
        ponchik.sellSalt(money);
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Salt getSaltStorage() {
        return salt;
    }

    public void setSaltStorage(Salt salt) {
        this.salt = salt;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}
