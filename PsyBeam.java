import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class PsyBeam extends PhysicalMove {
    public PsyBeam() {
        super(Type.ICE, 65, 100);
    }

    @Override
    protected void applyOppEffects(Pokemon name) {
        Effect PsyBeam = new Effect().chance(0.1).turns(0);
        if (Math.random() <= 0.1) {
            Effect.confuse(name);
        }
    }

}
