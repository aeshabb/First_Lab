package characters;

import items.Salt;
import places.Place;

public class Mistress implements Buyer {
    private String name;
    private double height;
    private double weight;
    private double balance;
    private Salt salt;
    private Place place;

    public Mistress(String name, double height, double weight, double balance, Salt salt, Place place) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.balance = balance;
        this.salt = salt;
        this.place = place;
    }

    public void buySalt(double money, Ponchik ponchik) {
        balance -= money;
        salt.setAmount(salt.getAmount() + money / ponchik.getSaltValue());
        ponchik.sellSalt(money);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Salt getSalt() {
        return salt;
    }

    public void setSalt(Salt salt) {
        this.salt = salt;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }


}
