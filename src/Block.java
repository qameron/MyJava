import bos.GamePiece;
import bos.RelativeMove;

import java.awt.*;
import java.util.Optional;

public class Block implements GamePiece<Cell> {
        int celldim = 35;
        Optional<Color> display;
        Cell location;
        Behaviour behaviour;
        public Color BROWN = new Color(102,51,0);

        public Block(Cell location, StandStill standStill, GameState gameState){
            GameState.setBlockLocation(location);
            this.location = location;
            this.behaviour = behaviour;
            this.display = Optional.of(BROWN);
            }

    public Block(Cell cellAtRowCol, StandStill standStill) {
        GameState.setBlockLocation(location);
        this.location = location;
        this.behaviour = behaviour;
        this.display = Optional.of(BROWN);
    }

    public  void paint(Graphics g){
            if(display.isPresent()) {
                g.setColor(display.get());
                g.fillRect(location.x + location.width, location.y + location.height, location.width, location.height);
                g.setColor(Color.orange);
                int j = 30;
                g.drawLine(location.x +celldim, location.y +celldim,location.x +celldim *2, location.y +celldim* 2);
                //drawing the lines horizontal on the block
                for(int i = 5; i <35;i+=5,j-=5){
                    g.drawLine((location.x +celldim) +0, (location.y +celldim)+i ,location.x +celldim + j, location.y +celldim +35);
                    g.drawLine((location.x +celldim) +i, (location.y +celldim)+0 ,location.x +celldim + 35, location.y +celldim +j);
                }
            }
        }

        public void setLocationOf(Cell loc){
            this.location = loc;
        }

        public Cell getLocationOf(){
            return this.location;

        }

        public void setBehaviour(Behaviour behaviour){
            this.behaviour = behaviour;
        }

}
