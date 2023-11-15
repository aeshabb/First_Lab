import characters.Buyers;
import characters.Mistress;
import characters.Ponchik;
import items.Salt;
import items.Sheet;
import places.*;
import timeline.Day;
import timeline.DayTime;

public class Main {
    public static void main(String[] args) {
        Day day = new Day(DayTime.NIGHT);
        Place station = new FoodFillingStation("wood", "Food Station", 100);
        Place city = new City("New York", "metal", 100000000);
        Sea sea = new Sea(new Salt(20), "Black sea", "water", 999999999999.52);
        Buyers buyers = new Buyers(10, new Salt(0), station);

        Mistress mistress = new Mistress("Хозяйка", 170, 55, 100, new Salt(0), station);
        Ponchik ponchik = new Ponchik("Пончик", 130, 40, 50, new Salt(0), city, false);

        day = new Day(DayTime.MORNING);

        ponchik.setPlace(sea);
        ponchik.addSaltFromTheSea(sea);

        ponchik.setPlace(station);
        Table table = new Table("Table", "wood", 5);
        table.setSheet(new Sheet("Продажа соли"));

        ponchik.setPlace(table);
        buyers.buySalt(20, ponchik);
        ponchik.eat();

        Place veranda = new Veranda("Veranda", "wood", 30);
        Place kitchen = new Kitchen("Kitchen", "gold", 50);
        Place annex = new Annex("Annex", "bedrock", 52);
        kitchen.setSize(kitchen.getSize() + 30);
        Canopy canopy = new Canopy("Canopy", "Брезент", 20);

        Restaurant restaurant = new Restaurant("gold", "ShwermaOtShaha", 2052);
        restaurant.addNewBuilding(veranda);
        restaurant.addNewBuilding(kitchen);
        restaurant.addNewBuilding(annex);

        Seafront seafront = new Seafront("sand", "OstrovShaha", 525252);
        for (int i = 0; i < 20; i++) {
            canopy.addNewBuilding(new Table("Table", "wood", 5));
        }
        seafront.addNewBuilding(canopy);

        ponchik.addSaltFromSomewhere(50);
        mistress.buySalt(ponchik.getSalt().getAmount() * ponchik.getSaltValue(), ponchik);
    }
}