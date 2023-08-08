package pokes;

import attacks.MorningSun;
import ru.ifmo.se.pokemon.Type;

public class Registeel extends Regice {
    public Registeel(String name, int lvl) {
        super(name, lvl);
        setStats(80, 75, 150, 75, 150, 50);
        setType(Type.STEEL);
        addMove(new MorningSun());
    }
}
