package pokes;

import attacks.HealBell;
import attacks.HydroPump;
import attacks.ShadowPunch;
import attacks.Substitute;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Ludicolo extends Pokemon {
    public Ludicolo(String name, int lvl) {
        super(name, lvl);
        setStats(45, 60, 30, 80, 50, 65);
        setType(Type.DARK, Type.FIRE);
        setMove(new HydroPump(), new HealBell(), new Substitute(), new ShadowPunch());

    }
}
