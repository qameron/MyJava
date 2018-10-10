import bos.GameBoard;

import java.awt.*;

public class Player implements KeyObserver {

    public Cell location;
    private Boolean inMove;

    public Player(Cell location){
        this.location = location;
        inMove = false;
    }

    public void paint(Graphics g){
        g.setColor(Color.ORANGE);
        g.fillOval(location.x + location.width / 4, location.y + location.height / 4, location.width / 2, location.height / 2);
    }

    public void startMove(){
        inMove = true;
    }

    public Boolean inMove(){
        return inMove;
    }
    Originator originator = new Originator();
    public void notify(char c, GameBoard<Cell> gb) {
        if (inMove){
            if (c == 'a') {
                location = gb.leftOf(location).orElse(location);
                //Stage.player.location = gb.leftOf(location).orElse(location);
               inMove = false;
            } else if (c == 'd') {
                location = gb.rightOf(location).orElse(location);
                inMove = false;
            } else if (c == 'w') {
                location = gb.above(location).orElse(location);
                inMove = false;
            } else if (c == 's') {
                location = gb.below(location).orElse(location);
                inMove = false;
            } else if (c == ' ') {
               // Originator originator = new Originator();
                originator.set(new Cell(Stage.player.location.x, Stage.player  .location.y));

                Caretaker caretaker = new Caretaker();
                caretaker.addMemento(originator.storeInMemento());
                inMove = true;
            } else if (c == 'r') {
             //   Originator originator = new Originator();
                Stage.player.location = originator.restoreFromMemento(Caretaker.getMemento(0));
              //  System.out.println("test repeating r");
               // Stage.update();
                inMove = true;

            }

        }
    }
}
