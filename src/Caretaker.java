// Memento Design Pattern Tutorial
import bos.GameBoard;

import java.security.Key;
import java.util.ArrayList;

class Caretaker implements KeyObserver {

    // Where all mementos are saved

    static ArrayList<Memento> savedArticles = new ArrayList<Memento>();

    // Adds memento to the ArrayList

    public static void addMemento(Memento m) {
        savedArticles.add(m);
    }

    // Gets the memento requested from the ArrayList

    public static Memento getMemento(int index) {
        return savedArticles.get(index);
    }

    @Override
    public void notify(char c, GameBoard<Cell> gb) {

    }
    // public key

}