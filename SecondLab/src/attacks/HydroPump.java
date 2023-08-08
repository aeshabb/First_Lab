package attacks;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.Type;

public class HydroPump extends PhysicalMove {
    public HydroPump() {
        super(Type.WATER, 110, 80);
    }

    protected void applyOppDamage(Pokemon poke, double damage) {
        poke.setMod(Stat.HP, (int) Math.round(damage));
    }

    @Override
    protected String describe() {
        return "наносит водяной удар";
    }
}
