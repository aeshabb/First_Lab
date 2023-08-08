package pokes;

import attacks.IceBeam;
import attacks.Psybeam;
import attacks.ThunderWave;
import ru.ifmo.se.pokemon.Type;

public class Regice extends Registeel {
    public Regice(String name, int lvl) {
        super(name, lvl);
        setStats(80, 100, 200, 50, 100, 50);
        setType(Type.ICE);
        setMove(new ThunderWave(), new IceBeam(), new Psybeam());
    }

}