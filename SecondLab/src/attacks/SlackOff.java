package attacks;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.SpecialMove;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.Type;

public class SlackOff extends SpecialMove {
    public SlackOff() {
        super(Type.NORMAL, 0, 0);
    }

    @Override
    protected void applySelfEffects(Pokemon name) {
        if (name.getHP() < (name.getStat(Stat.HP) / 2))
            name.setMod(Stat.HP, (int) (name.getHP() - (name.getStat(Stat.HP) / 2)));
    }
}
