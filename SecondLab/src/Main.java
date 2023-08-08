import pokes.Houndour;
import pokes.Ludicolo;
import pokes.Regice;
import pokes.Regirock;
import pokes.Registeel;
import pokes.Skarmory;
import ru.ifmo.se.pokemon.Battle;

public class Main {
    public static void main(String[] args) {
        Battle b = new Battle();
        b.addAlly(new Regirock("Rock", 1));
        b.addAlly(new Regice("Ice", 1));
        b.addAlly(new Registeel("Steel", 1));
        b.addFoe(new Ludicolo("Colo", 1));
        b.addFoe(new Skarmory("Bird", 1));
        b.addFoe(new Houndour("Dog", 1));
        b.go();
    }

}
