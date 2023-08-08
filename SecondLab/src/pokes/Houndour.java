package pokes;

import attacks.Endeavor;
import attacks.HydroPump;
import attacks.SlackOff;
import attacks.ThunderWave;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Houndour extends Pokemon {
    public Houndour(String name, int lvl) {
        super(name, lvl);
        setStats(45, 60, 30, 80, 50, 65);
        setType(Type.DARK, Type.FIRE);
        setMove(new ThunderWave(), new Endeavor(), new HydroPump(), new SlackOff());

    }
}
