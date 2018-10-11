import java.awt.Color;
import java.awt.Graphics;
import java.util.Optional;

import bos.GamePiece;
import bos.RelativeMove;

enum CHARACTERS {
    SHEEP, SHEPHERD, WOLF, BLOCK
};

public abstract class Character implements GamePiece<Cell> {
    Optional<Color> display;
    Cell location;
    Behaviour behaviour;
    GameState gameState;
    CHARACTERS id;

    public Character(CHARACTERS id, Cell location, Behaviour behaviour, GameState gameState) {
        this.id = id;
        this.location = location;
        this.display = Optional.empty();
        this.behaviour = behaviour;
        this.gameState = gameState;
    }

    public void paint(Graphics g) {
        if (display.isPresent()) {
            g.setColor(display.get());
            g.fillOval(location.x + location.width / 4, location.y + location.height / 4, location.width / 2,
                    location.height / 2);
        }
    }

    public void setLocationOf(Cell loc) {
        this.location = loc;
        switch (id) {
            case SHEEP:
                gameState.setSheepLocation(loc);
                break;
            case WOLF:
                gameState.setWolfLocation(loc);
                break;
            case SHEPHERD:
                gameState.setShephardLocation(loc);
                break;
            case BLOCK:
                gameState.setBlockLocation(loc);
                break;

        }
    }

    public Cell getLocationOf() {
        return this.location;
    }

    public void setBehaviour(Behaviour behaviour) {
        this.behaviour = behaviour;
    }

    public RelativeMove aiMove(Stage stage) {
        return behaviour.chooseMove(stage, this);
    }

}
