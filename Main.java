import ru.ifmo.se.pokemon.Battle;
public class Main {
    public static void main(String[] args) {
        Battle b = new Battle();
        b.addAlly(new Regirock("Rock", 1));
        b.addFoe(new Regice("Ice", 1));
        b.go();
    }
        
}

