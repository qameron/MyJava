import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import java.time.Duration;
import java.time.Instant;


public class Main extends JFrame implements Runnable {

    private class Canvas extends JPanel implements KeyListener {

        private Stage stage;

        public Canvas() {
            setPreferredSize(new Dimension(1280, 720));

            stage = new Stage();
        }

        @Override
        public void paint(Graphics g) {
            stage.update();
            stage.paint(g, getMousePosition());
        }


        @Override
        public void keyTyped(KeyEvent e) {
            stage.notifyAll(e.getKeyChar(), stage.grid);

            /////////////////////////////////////////////
         //   Originator originator = new Originator();
         //   originator.set(new Cell(stage.player.location.x,stage.player.location.y));

          //  Caretaker caretaker = new Caretaker();
        //    caretaker.addMemento(originator.storeInMemento());
            /////////////////////////////////////////////
        }

        @Override
        public void keyPressed(KeyEvent e) {
            /////////////////////////////////////////////
            if(e.getKeyCode() == KeyEvent.VK_SPACE) {
                Originator originator = new Originator();
                originator.set(new Cell(stage.player.location.x, stage.player.location.y));

                Caretaker caretaker = new Caretaker();
                caretaker.addMemento(originator.storeInMemento());
            }
            if(e.getKeyCode() == KeyEvent.VK_R) {
                Originator originator = new Originator();
                stage.player.location = originator.restoreFromMemento(Caretaker.getMemento(0));

            }
            /////////////////////////////////////////////
        }

        @Override
        public void keyReleased(KeyEvent e) {}
    }

    public static void main(String[] args) {
        Main window = new Main();
        window.run();
    }

    public Main() {


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Canvas canvas = new Canvas();
        this.setContentPane(canvas);
        this.addKeyListener(canvas);
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void run() {
        while (true) {
            Instant startTime = Instant.now();
            this.repaint();
            Instant endTime = Instant.now();
            try {
                Thread.sleep(20 - Duration.between(startTime, endTime).toMillis());
            } catch (InterruptedException e) {
                System.out.println("thread was interrupted, but really, who cares?");
                System.out.println(e.toString());
            }
        }
    }
}
