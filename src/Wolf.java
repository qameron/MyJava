// Cameron Warton - 44635931
import bos.*;

import java.awt.*;
import java.util.Optional;
import java.util.List;

public class Wolf extends Character {

    public Wolf(Cell location, Behaviour behaviour, GameState gameState) {
        super(CHARACTERS.WOLF, location, behaviour, gameState);
        gameState.setWolfLocation(location);
        display = Optional.of(Color.RED);
    }
}