package attacks;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.SpecialMove;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.Type;

public class MorningSun extends SpecialMove {
    public MorningSun() {
        super(Type.NORMAL, 65, 100);
    }

    @Override
    protected void applySelfEffects(Pokemon name) {
        name.setMod(Stat.HP, -2);

    }
}
