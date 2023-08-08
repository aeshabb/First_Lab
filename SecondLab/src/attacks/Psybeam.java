package attacks;

import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Psybeam extends PhysicalMove {
    public Psybeam() {
        super(Type.PSYCHIC, 65, 100);
    }

    @Override
    protected void applyOppEffects(Pokemon poke) {
        if (Math.random() <= 0.1) {
            Effect.confuse(poke);
        }
    }

}