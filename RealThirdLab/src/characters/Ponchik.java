package characters;

import items.Dish;
import items.Salt;
import places.Place;
import places.Sea;

public class Ponchik {
    int saltValue = 1;
    private String name;
    private double height;
    private double weight;
    private double balance;
    private Salt salt;
    private Place place;
    private boolean selling;

    public Ponchik(String name, double height, double weight, double balance, Salt salt, Place place, boolean selling) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.balance = balance;
        this.salt = salt;
        this.place = place;
        this.selling = selling;
    }

    protected void sellSalt(double money) {
        balance += money;
        salt.setAmount(salt.getAmount() - money / saltValue);
        System.out.println("Пончик заработал " + money + " медяков");
    }

    public void addSaltFromTheSea(Sea sea) {
        double seaSalt = sea.getSalt().getAmount();
        salt.setAmount(salt.getAmount() + seaSalt);
        System.out.println("Пончик накопал " + seaSalt + " грамм соли");
    }

    public void addSaltFromSomewhere(double value) {
        salt.setAmount(salt.getAmount() + value);
        System.out.println("Пончик нашел " + value + " грамм соли");
    }

    public void eat() {
        for (Dish dish : Dish.values()) {
            System.out.println("Пончик съел " + dish);
        }
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

    public Salt getSalt() {
        return salt;
    }

    public void setSalt(Salt salt) {
        this.salt = salt;
    }

    public int getSaltValue() {
        return saltValue;
    }

    public void setSaltValue(int saltValue) {
        this.saltValue = saltValue;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public boolean isSelling() {
        return selling;
    }

    public void setSelling(boolean selling) {
        this.selling = selling;
    }
}
