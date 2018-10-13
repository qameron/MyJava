// Cameron Warton - 44635931
import java.awt.*;
import java.util.List;
import java.util.Optional;
import bos.*;

public class Sheep extends Character {

    public Sheep(Cell location, Behaviour behaviour, GameState gameState) {
        super(CHARACTERS.SHEEP, location, behaviour, gameState);
        display = Optional.of(Color.WHITE);
        gameState.setSheepLocation(location);
    }
}