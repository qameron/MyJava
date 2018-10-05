import java.awt.*;
import java.util.*;
import java.time.*;
import java.util.List;

import bos.RelativeMove;

public class Stage extends KeyObservable {
    protected Grid grid;
    public Character sheep;
    protected Character shepherd;
    protected Character wolf;
    public List<Character> allCharacters;
    protected Player player;

    private Instant timeOfLastMove = Instant.now();

    public Stage() {
        SAWReader sr = new SAWReader("data/stage1.saw");
        grid     = new Grid(10, 10);
        shepherd = new Shepherd(grid.cellAtRowCol(sr.getShepherdLoc().first, sr.getShepherdLoc().second), new StandStill());
        sheep    = new Sheep(grid.cellAtRowCol(sr.getSheepLoc().first, sr.getSheepLoc().second), new MoveTowards(shepherd));
        wolf     = new Wolf(grid.cellAtRowCol(sr.getWolfLoc().first, sr.getWolfLoc().second), new MoveTowards(sheep));

        player = new Player(grid.getRandomCell());
        this.register(player);

        allCharacters = new ArrayList<Character>();
        allCharacters.add(sheep); allCharacters.add(shepherd); allCharacters.add(wolf);

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