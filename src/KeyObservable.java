// Cameron Warton - 44635931
import bos.GameBoard;

import java.util.ArrayList;

public class KeyObservable extends ArrayList<KeyObserver> {
    public void register(KeyObserver ko){
        add(ko);
    }

    public void notifyAll(char c, GameBoard<Cell> gb){
        this.forEach((KeyObserver ko) -> ko.notify(c, gb));
    }
}
