// Cameron Warton - 44635931
import bos.*;
import java.awt.*;
import java.util.Optional;

public class Shepherd extends Character {

    public Shepherd(Cell location, Behaviour behaviour, GameState gameState) {
        super(CHARACTERS.SHEPHERD, location, behaviour, gameState);
        gameState.setShephardLocation(location);
        display = Optional.of(Color.GREEN);
    }

}