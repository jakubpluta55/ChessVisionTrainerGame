import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;

public class Main extends JFrame{
    private Timer timer;
    public ActionWindow game; // Deklaracja pozycji, w której dzieje się akcja gry
    class Loop extends TimerTask {

        // run jest funkcja abstrakcyjna
        public void run() {
            if (game.run) {
                game.update();
                repaint();
            }
        }
    }
    Main(){
        super("Chess Vision Trainer"); //Napis na okienku
        setMinimumSize(new Dimension(1030,800));
        setMaximumSize(new Dimension(1030,800));
        setPreferredSize(new Dimension(1030,800)); // Wymiary okienka
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Możliwość wyłączenia programu przez krzyżyk
        setResizable(false); //Zablokowanie możliwości rozciągania okienka
        setVisible(true);
        createBufferStrategy(2);
        this.game = new ActionWindow(1024,768);
        timer = new Timer(); // funkcja czasu, dziala 60 razy na sekunde
        timer.scheduleAtFixedRate(new Loop(),0,1000/60);
        getContentPane().addMouseListener(new MouseE(){ //Obsługa kliknięć myszy

            public void mouseClicked(MouseEvent e)
            {
                game.click(e.getX(), e.getY());

                //Wywołanie funkcji opowiedzialnej za rysowanie
                repaint();
            }
            public void mousePressed(MouseEvent e)
            {

                game.press(e.getX(), e.getY());

                //Wywołanie funkcji opowiedzialnej za rysowanie
                repaint();
            }
            public void mouseReleased(MouseEvent e) {

                game.release(e.getX(), e.getX());
                repaint();
            }
        });

        getContentPane().addMouseMotionListener(new MouseE(){
            public void mouseDragged (MouseEvent e)
            {

                game.move(e.getX(), e.getY());

                repaint();
            }
        });
    }

    public static void main(String[] args)
    {
        Main window = new Main();
        window.repaint();
    }

    public void paint(Graphics g)
    {

        BufferStrategy bstrategy = this.getBufferStrategy();
        Graphics2D g2D = (Graphics2D)bstrategy.getDrawGraphics();
        //Zmiana punktu (0,0)
        g2D.translate(3,32);
        this.game.draw(g2D);
        g2D.dispose();
        bstrategy.show();

    }
}


