package attacks;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.StatusMove;
import ru.ifmo.se.pokemon.Type;

public class SlackOff extends StatusMove {
    public SlackOff() {
        super(Type.NORMAL, 0, 0);
    }

    @Override
    protected void applySelfEffects(Pokemon poke) {
        if (poke.getHP() < (poke.getStat(Stat.HP) / 2))
            poke.setMod(Stat.HP, (int) (poke.getHP() - (poke.getStat(Stat.HP) / 2)));
    }
}
