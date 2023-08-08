package attacks;

import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class ThunderWave extends PhysicalMove {
    public ThunderWave() {
        super(Type.ELECTRIC, 0, 90);
    }

    @Override
    protected void applyOppEffects(Pokemon name) {
        Effect.paralyze(name);

    }

}
