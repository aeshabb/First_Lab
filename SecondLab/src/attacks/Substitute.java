package attacks;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.SpecialMove;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.Type;

public class Substitute extends SpecialMove {
    public Substitute() {
        super(Type.NORMAL, 0, 0);
    }

    @Override
    protected void applySelfEffects(Pokemon name) {
        name.setMod(Stat.HP, -(int) (name.getStat(Stat.HP) / 4));

    }
}
