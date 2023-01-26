import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import javax.swing.ImageIcon;

import static java.lang.Math.abs;
import java.util.Random;

public class ActionWindow {
    public Button startButton;
    public Image background;
    public Image bottomPanel;
    public int width;
    public int height;

    public boolean hardship = true;
    public ArrayList<CheckBox> checkBoxes = new ArrayList<CheckBox>();
    public ChessBoard chessBoard;

    //licznik poprawnych postawień
    public int counter;
    //limit poprawnych postawień
    public int limit;
    //licznik klatek
    public int frames = 0;
    //flaga mówiąca o tym że gra już trwa
    public boolean run;
    //flaga mówiąca o tym że gra jest przegrana
    public boolean gameOver;
    //flaga mówiąca o tym że gra jest wygrana
    public boolean finish;
    ActionWindow(int width, int height)
    {
        this.limit = 10;
        this.run = false;
        this.width = width;
        this.height = height;

        this.startButton = new Button((width-130)/2, 620,130,60,"Start");
        this.background = new ImageIcon("obrazy/background.png").getImage();
        this.bottomPanel = new ImageIcon("obrazy/bottomPanel.png").getImage();
        this.chessBoard = new ChessBoard(220,50);

        this.checkBoxes.add(new CheckBox(20, 600, 30, 30, true, "Łatwy"));
        this.checkBoxes.add(new CheckBox(20, 650, 30, 30, false, "Trudny"));
    }

    //inicjowanie nowej rozgrywki (resetowanie ustawień)
    public void initiate()
    {
        this.finish = false;
        this.gameOver = false;
        this.counter = 0;
        frames = 0;
        this.run = true;
        this.chessBoard.newTask();
    }

    //funkcja działająca co klatkę, jesli wygramy/przegramy to zwroci 'return' zeby dalej sie nic nie wykonywało
    public void update()
    {
        if(finish || gameOver)
        {
            return;
        }

        this.frames ++;
        //warunek wygranej
        if(this.counter>=this.limit)
        {
            this.finish = true;
        }
    }
    //wykona sie przy zwolnieniu przycisku myszy
    public void release(int x, int y)
    {
        if(this.chessBoard.figure!=null && this.chessBoard.figure.dragged)
        {
            this.chessBoard.figure.dragged = false;
            this.chessBoard.placeFigure();
            int placeState = this.chessBoard.checkResult();
            if(placeState == 0)
            {
                this.gameOver = true;
            }
            else if(placeState==1)
            {
                this.counter++;
                if(this.counter<this.limit)
                {
                    this.chessBoard.newTask();
                }
            }
        }
    }
    //kliknięcie myszą (nie przytrzymanie)
    public void click(int x, int y)
    {
        //sprawdzanie czy naciśnięty został przycisk
        if(startButton.clicked(x,y)) // tutaj dzieje sie cala mechanika kliku myszy, nie w klasie Button
        {
            this.initiate();
            return;
        }

        //sprawdzenie czy został naciśnięty jakiś checkbox
        int checkBoxIndex = -1;
        for(int i =0;i< checkBoxes.size();i++) // iterujemy po kazdym checkboxie na ktorym kliknelismy, jezeli klikniemy kwadrat LATAWY to zmienia na na true, analogicznie jesli trudny to false
        {
            if(checkBoxes.get(i).clicked(x,y) &&!checkBoxes.get(i).state)
            {
                checkBoxIndex = i;
                if(checkBoxes.get(i).text.equals("Łatwy"))
                {
                   this.hardship = true;
                }
                else
                {
                    this.hardship = false;
                }
            }
        }
        if(checkBoxIndex!=-1) // jesli by bylo wiecej checkboxow to temu kliknietemu zmienia wartosc na true, a pozostalym na false
        {
            for(int i =0;i< checkBoxes.size();i++)
            {
                if(checkBoxIndex == i)
                {
                    checkBoxes.get(i).changeState(true);

                }
                else
                {
                    checkBoxes.get(i).changeState(false);
                }
            }
            if(this.run) {
                initiate(); // resetowanie stanu gry, rozpoczecie nowej gry, dziala tylko, gdy gra jest juz rozpoczeta
            }
            return;
        }



    }
    //ruszanie myszką po ekranie
    public void move(int x, int y)
    {

        if(this.chessBoard.figure!=null && this.chessBoard.figure.dragged)
        {

            this.chessBoard.figure.x=x-this.chessBoard.figure.width/2;
            this.chessBoard.figure.y=y-this.chessBoard.figure.height/2;
            this.chessBoard.figure.borders();
        }

    }
    //przyciśnięcie przycisku myszy, jesli figura sie pojawi i zaczniemy ja przenosic to zachodzi interackaja z funckja move
    public void press(int x, int y)
    {
        if(!finish && !gameOver&&this.chessBoard.figure!=null && this.chessBoard.figure.clicked(x,y))
        {
            this.chessBoard.figure.dragged = true;
        }
    }
    //Czyszczenie wszelkich danych związanych z poziomem przed wyjściem
    public void quit()
    {

        System.exit(0);
    }
    //rysowanie, wyswietlanie napisow
    public void draw(Graphics2D g2D)
    {
        g2D.drawImage(this.background, 0, 0, this.width, this.height, (ImageObserver)null);
        g2D.drawImage(this.bottomPanel, 0, this.height-200, this.width, 200, (ImageObserver)null);
        this.startButton.draw(g2D);
        g2D.setColor(Color.WHITE);
        g2D.setFont(new Font("Arial",Font.BOLD,20));
        String minutes = ""+(this.frames/3600);
        String seconds = ""+((this.frames/60)%60);

        if((this.frames/3600)<10)minutes="0"+minutes;
        if((this.frames/60)%60<10)seconds="0"+seconds;

        g2D.drawString("Czas: "+minutes+":"+seconds,this.width-200,this.height-150);
        if(run && this.chessBoard.destinationField!=null) {
            g2D.drawString(this.counter+"/"+this.limit, this.width - 300, this.height - 130);

            g2D.drawString("Pole: " + this.chessBoard.destinationField.name1 + this.chessBoard.destinationField.name2, this.width - 300, this.height - 100);
        }
        if(gameOver || finish)
        {
            String gameResult;
            if(gameOver)
            {
                g2D.setColor(Color.RED);
                gameResult = "Przegrałeś";
            }
            else
            {
                g2D.setColor(Color.GREEN);
                gameResult = "Wygrałeś";
            }
            g2D.setFont(new Font("Arial",Font.BOLD,20));
            g2D.drawString(gameResult, this.width - 300, this.height - 70);
        }

        for(int i =0;i< checkBoxes.size();i++)
        {
            this.checkBoxes.get(i).draw(g2D);
        }

        this.chessBoard.draw(g2D, this.hardship);
    }
}

