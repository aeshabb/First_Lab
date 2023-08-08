package attacks;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.Type;

public class Endeavor extends PhysicalMove {
    public Endeavor() {
        super(Type.NORMAL, 0, 100);
    }

    @Override
    protected void applyOppEffects(Pokemon poke) {
        if (poke.getHP() > poke.getLevel()) {
            poke.setMod(Stat.HP, poke.getLevel());
        }
    }
}
