package pokes;

import attacks.IceBeam;
import attacks.MorningSun;
import attacks.ThunderWave;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Registeel extends Pokemon {
    public Registeel(String name, int lvl) {
        super(name, lvl);
        setStats(80, 75, 150, 75, 150, 50);
        setType(Type.STEEL);
        setMove(new ThunderWave(), new IceBeam(), new MorningSun());
    }
}
