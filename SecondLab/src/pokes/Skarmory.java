package pokes;

import attacks.Endeavor;
import attacks.HydroPump;
import attacks.ThunderWave;
import ru.ifmo.se.pokemon.Type;

public class Skarmory extends Houndour {
    public Skarmory(String name, int lvl) {
        super(name, lvl);
        setStats(65, 80, 140, 40, 70, 70);
        setType(Type.STEEL, Type.FLYING);
        setMove(new ThunderWave(), new Endeavor(), new HydroPump());
    }
}
