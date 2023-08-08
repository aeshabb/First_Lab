import pokes.Houndour;
import pokes.Ludicolo;
import pokes.Regice;
import pokes.Regirock;
import pokes.Registeel;
import pokes.Skarmory;
import ru.ifmo.se.pokemon.Battle;

public class Main {
    public static void main(String[] args) {
        Battle worldsBestBattle = new Battle();
        worldsBestBattle.addAlly(new Regirock("Rock", 1));
        worldsBestBattle.addAlly(new Regice("Ice", 1));
        worldsBestBattle.addAlly(new Registeel("Steel", 1));
        worldsBestBattle.addFoe(new Ludicolo("Colo", 1));
        worldsBestBattle.addFoe(new Skarmory("Bird", 1));
        worldsBestBattle.addFoe(new Houndour("Dog", 1));
        worldsBestBattle.go();
    }

}
