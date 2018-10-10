import bos.NoMove;
import bos.RelativeMove;

import java.util.List;

public class MoveTowards implements Behaviour {
    Character target;

    public MoveTowards(Character character){
        this.target = character;
    }

    @Override
    public RelativeMove chooseMove(Stage stage, Character mover) {
        System.out.println(Stage.block.location);
        if(this.target.location.equals(Stage.block.location)){

            System.out.println("collided");
            return new NoMove(stage.grid, mover);
        }
        List<RelativeMove> movesToTarget = stage.grid.movesBetween(mover.location,target.location, mover);
        if (movesToTarget.size() == 0)
            return new NoMove(stage.grid, mover);
        else
            return movesToTarget.get(0);    }
}
