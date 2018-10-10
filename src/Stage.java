import java.awt.*;
import java.util.*;
import java.time.*;
import java.util.List;

import bos.RelativeMove;

public class Stage extends KeyObservable {
    protected Grid grid;
    public Character sheep;
    protected Character shepherd;
    protected static Character wolf;
    public List<Character> allCharacters;
    protected static Player player;
    public static Block block;

    private Instant timeOfLastMove = Instant.now();

    public Stage() {
        SAWReader sr = new SAWReader("data/stage1.saw");
        grid     = new Grid(10, 10);
        shepherd = new Shepherd(grid.cellAtRowCol(sr.getShepherdLoc().first, sr.getShepherdLoc().second), new StandStill());
        sheep    = new Sheep(grid.cellAtRowCol(sr.getSheepLoc().first, sr.getSheepLoc().second), new MoveTowards(shepherd));
        wolf     = new Wolf(grid.cellAtRowCol(sr.getWolfLoc().first, sr.getWolfLoc().second), new MoveTowards(sheep));
        block    = new Block(grid.cellAtRowCol(sr.getBlockLoc().first, sr.getBlockLoc().second), new StandStill());

        player = new Player(grid.getRandomCell());
        this.register(player);

        allCharacters = new ArrayList<Character>();
        allCharacters.add(sheep); allCharacters.add(shepherd); allCharacters.add(wolf); //allCharacters.add(block);

    }

    public void update(){
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
               // if (player.location.x )
                allCharacters.forEach((c) -> c.aiMove(this).perform());
                /**
                 * It COULD be this .startMove
                 */
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
class Originator{

    private static Cell article;
    // Sets the value for the article

    public static void set(Cell newArticle) {

        System.out.println("From Originator: Current Version of Article\n"+newArticle+ "\n");
        article = newArticle;
    }

    // Creates a new Memento with a new article

    public static Memento storeInMemento() {

        System.out.println("From Originator: Saving to Memento");
        return new Memento(article);
    }
    // Gets the article currently stored in memento

    public Cell restoreFromMemento(Memento memento) {

        article = memento.getSavedArticle();
        System.out.println("From Originator: Previous Article Saved in Memento\n"+article + "\n");
        return article;
    }
}