// Cameron Warton - 44635931
import java.awt.*;
import java.util.*;
import java.time.*;
import java.util.List;

import bos.NoMove;
import bos.RelativeMove;

public class Stage extends KeyObservable {
    protected Grid grid;
    protected Character sheep;
    protected Character shepherd;
    protected Character wolf;
    private List<Character> allCharacters;
    protected Player player;
    protected static Block block;

    private Instant timeOfLastMove = Instant.now();
    private Originator originator;
    private GameState currentGameState;

    public Stage() {
        originator = new Originator(this);
        currentGameState = new GameState();
        SAWReader sr = new SAWReader("data/stage1.saw");
        grid = new Grid(10, 10);
        shepherd = new Shepherd(grid.cellAtRowCol(sr.getShepherdLoc().first, sr.getShepherdLoc().second), new StandStill(), currentGameState);
        sheep = new Sheep(grid.cellAtRowCol(sr.getSheepLoc().first, sr.getSheepLoc().second), new MoveTowards(shepherd), currentGameState);
        wolf = new Wolf(grid.cellAtRowCol(sr.getWolfLoc().first, sr.getWolfLoc().second), new MoveTowards(sheep), currentGameState);
        block   = new Block(grid.cellAtRowCol(sr.getBlockLoc().first, sr.getBlockLoc().second), new StandStill(), currentGameState);

        Cell c = grid.getRandomCell();

        player = new Player(c, currentGameState, originator);

        currentGameState.setPlayerLocation(c);
        this.register(player);

        allCharacters = new ArrayList<Character>();
        allCharacters.add(sheep);
        allCharacters.add(shepherd);
        allCharacters.add(wolf);
    }

    public void update() {
        if (!player.inMove()) {
            if (sheep.location == shepherd.location) {
                System.out.println("The sheep is safe :)");
                System.exit(0);
            } else if (sheep.location == wolf.location) {
                System.out.println("The sheep is dead :(");
                System.exit(1);
            } else {
                if (sheep.location.x == sheep.location.y) {
                    sheep.setBehaviour(new StandStill());
                    shepherd.setBehaviour(new MoveTowards(sheep));
                }
                allCharacters.forEach((c) -> c.aiMove(this).perform());
                player.startMove();
                timeOfLastMove = Instant.now();
            }
        }
    }

    public void paint(Graphics g, Point mouseLocation) {
        grid.paint(g, mouseLocation);
        sheep.paint(g);
        shepherd.paint(g);
        wolf.paint(g);
        player.paint(g);
        block.paint(g);
    }
}

class Originator {

    private GameState currentGameState, savedGameState;
    private Stage stage;

    public Originator(Stage stage) {
        this.stage = stage;
    }

    public void setGameState(GameState gameState) {
        this.currentGameState = gameState;
    }

    public GameState saveToMemento() {
        System.out.println("The game has been saved");
        savedGameState = new GameState();
        savedGameState.setPlayerLocation(currentGameState.getPlayerLocation());
        savedGameState.setSheepLocation(currentGameState.getSheepLocation());
        savedGameState.setShephardLocation(currentGameState.getShephardLocation());
        savedGameState.setWolfLocation(currentGameState.getWolfLocation());
        return savedGameState;
    }

    public void restoreFromMemento() {
        if (savedGameState == null) {
            System.out.println("Need to save to memento before restoring.");
            return;
        }
        System.out.println("Restoring from memento.");
        stage.wolf.setLocationOf(savedGameState.getWolfLocation());
        stage.sheep.setLocationOf(savedGameState.getSheepLocation());
        stage.shepherd.setLocationOf(savedGameState.getShephardLocation());
        stage.player.location = savedGameState.getPlayerLocation();
        stage.update();
    }
}
