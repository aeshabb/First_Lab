package attacks;

import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.StatusMove;
import ru.ifmo.se.pokemon.Type;

public class HealBell extends StatusMove {
    public HealBell() {
        super(Type.NORMAL, 0, 0);
    }

    @Override
    protected void applySelfEffects(Pokemon poke) {
        Effect godMode = new Effect();
        godMode.clear();
        poke.addEffect(godMode);
    }

    @Override
    protected String describe() {
        return "сбрасывает все эффекты";
    }
}
