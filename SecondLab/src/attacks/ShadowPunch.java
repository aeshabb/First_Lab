package attacks;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.Type;

public class ShadowPunch extends PhysicalMove {
    public ShadowPunch() {
        super(Type.GHOST, 60, Double.POSITIVE_INFINITY);
    }

    @Override
    protected void applyOppDamage(Pokemon def, double damage) {
        def.setMod(Stat.HP, (int) damage);
    }
}
