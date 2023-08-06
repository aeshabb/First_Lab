import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Regirock extends Pokemon {
    public Regirock(String name, int lvl) {
        super(name, lvl);
        setStats(80, 100, 200, 50, 100, 50);
        setType(Type.ROCK);
        setMove(new ThunderWave(), new IceBeam());
    }

}