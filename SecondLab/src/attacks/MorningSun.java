package attacks;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.StatusMove;
import ru.ifmo.se.pokemon.Type;

public class MorningSun extends StatusMove {
    public MorningSun() {
        super(Type.NORMAL, 65, 100);
    }

    @Override
    protected void applySelfEffects(Pokemon poke) {
        poke.setMod(Stat.HP, -2);

    }
}
