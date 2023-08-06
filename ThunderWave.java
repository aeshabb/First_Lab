import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.Type;

public class ThunderWave extends PhysicalMove {
    public ThunderWave() {
        super(Type.ELECTRIC, 0, 90);
    }

    @Override
    protected void applyOppEffects(Pokemon name) {
        double speed = name.getStat(Stat.SPEED) / 2;
        Effect ThunderWave = new Effect().chance(0.25).turns(0).attack(0);
        if (Math.random() <= 0.25) {
            name.setMod(Stat.SPEED, (int) Math.round(speed));
        }
    }

}