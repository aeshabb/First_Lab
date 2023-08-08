package pokes;

import attacks.Psybeam;
import ru.ifmo.se.pokemon.Type;

public class Regice extends Regirock {
    public Regice(String name, int lvl) {
        super(name, lvl);
        setStats(80, 100, 200, 50, 100, 50);
        setType(Type.ICE);
        addMove(new Psybeam());
    }
}