package pokes;

import attacks.SlackOff;
import ru.ifmo.se.pokemon.Type;

public class Houndour extends Skarmory {
    public Houndour(String name, int lvl) {
        super(name, lvl);
        setStats(45, 60, 30, 80, 50, 65);
        setType(Type.DARK, Type.FIRE);
        addMove(new SlackOff());

    }
}
