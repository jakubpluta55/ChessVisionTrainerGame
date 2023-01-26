import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.lang.Math;
import java.util.Collections;

public class ChessBoard { // klasa zawierająca mechanike i rozmieszczanie figur
    public int x;
    public int y;

    public Figure figure;
    public Field destinationField;

    public ArrayList<Field> fields = new ArrayList<Field>();

    ChessBoard(int x, int y)
    {

        this.x = x;
        this.y = y;

        this.createBoard();
    }

    public void generateFigure()  // generuje figure w losowym miejscu dzieki funkcji Math.random
    {
        int field_x = (int)(Math.random() * 8);
        int field_y = (int)(Math.random() * 8);
        int figureIndex = (int)(Math.random() * 5);
        Field chosenField = fields.get(field_y*8+field_x);
        this.figure = new Figure(chosenField.x,chosenField.y,chosenField.width, chosenField.height,figureIndex,field_x,field_y,fields.get(0).x,  fields.get(0).x+fields.get(0).width*8,fields.get(0).y,fields.get(0).y+fields.get(0).height*8 );

    }
    //generowanie wszystkich dostępnych pól na które figura może sie przenieść, jest to zależne od typu figury jaką wylosujemy
    // dla kazdej z figur losujemy osobną liste, ta lista się resetuje z każdym wywołaniem tej funkcji

    //najpierw losujemy figure, nastepnie dla wyloswanej figury sprawdzam wszystkie mozliwe miejsca do ktorych ta figura moze sie przemiescic
    // jezeli wylosujemy np. skoczka to nie beda wykonywane petle dla innych figur
    public void generateDestination()
    {
        ArrayList<Point> destinatedFields = new ArrayList<Point>();
        if(this.figure.type.equals("knight"))
        {
            if(figure.field_x>1 && figure.field_y>0)
            {
                destinatedFields.add(new Point(figure.field_x-2, figure.field_y-1));
            }
            if(figure.field_x>0 && figure.field_y>1)
            {
                destinatedFields.add(new Point(figure.field_x-1, figure.field_y-2));
            }
            if(figure.field_x>1 && figure.field_y<7)
            {
                destinatedFields.add(new Point(figure.field_x-2, figure.field_y+1));
            }
            if(figure.field_x<6 && figure.field_y>0)
            {
                destinatedFields.add(new Point(figure.field_x+2, figure.field_y-1));
            }
            if(figure.field_x<7 && figure.field_y>1)
            {
                destinatedFields.add(new Point(figure.field_x+1, figure.field_y-2));
            }
            if(figure.field_x<7 && figure.field_y<6)
            {
                destinatedFields.add(new Point(figure.field_x+1, figure.field_y+2));
            }
            if(figure.field_x<6 && figure.field_y<7)
            {
                destinatedFields.add(new Point(figure.field_x+2, figure.field_y+1));
            }
            if(figure.field_x>1 && figure.field_y>0)
            {
                destinatedFields.add(new Point(figure.field_x-2, figure.field_y-1));
            }
        }
        else if(this.figure.type.equals("bishop"))
        {
            int x = figure.field_x-1;
            int y = figure.field_y-1;
            while(x>=0 && y >=0)
            {
                destinatedFields.add(new Point(x,y));
                x--;
                y--;
            }
            x = figure.field_x+1;
            y = figure.field_y+1;
            while(x<=7 && y <=7)
            {
                destinatedFields.add(new Point(x,y));
                x++;
                y++;
            }
            x = figure.field_x-1;
            y = figure.field_y+1;
            while(x>=0 && y <=7)
            {
                destinatedFields.add(new Point(x,y));
                x--;
                y++;
            }
            x = figure.field_x+1;
            y = figure.field_y-1;
            while(x<=7 && y >=0)
            {
                destinatedFields.add(new Point(x,y));
                x++;
                y--;
            }
        }
        else if(this.figure.type.equals("rook"))
        {
            int x = figure.field_x-1;
            int y = figure.field_y;
            while(x>=0)
            {
                destinatedFields.add(new Point(x,y));
                x--;
            }
            x = figure.field_x+1;
            y = figure.field_y;
            while(x<=7)
            {
                destinatedFields.add(new Point(x,y));
                x++;
            }
            x = figure.field_x;
            y = figure.field_y-1;
            while(y>=0)
            {
                destinatedFields.add(new Point(x,y));
                y--;
            }
            x = figure.field_x;
            y = figure.field_y+1;
            while(y <=7)
            {
                destinatedFields.add(new Point(x,y));
                y++;
            }
        }
        else if(this.figure.type.equals("queen"))
        {
            int x = figure.field_x-1;
            int y = figure.field_y-1;
            while(x>=0 && y >=0)
            {
                destinatedFields.add(new Point(x,y));
                x--;
                y--;
            }
            x = figure.field_x+1;
            y = figure.field_y+1;
            while(x<=7 && y <=7)
            {
                destinatedFields.add(new Point(x,y));
                x++;
                y++;
            }
            x = figure.field_x-1;
            y = figure.field_y+1;
            while(x>=0 && y <=7)
            {
                destinatedFields.add(new Point(x,y));
                x--;
                y++;
            }
            x = figure.field_x+1;
            y = figure.field_y-1;
            while(x<=7 && y >=0)
            {
                destinatedFields.add(new Point(x,y));
                x++;
                y--;
            }
            x = figure.field_x-1;
            y = figure.field_y;
            while(x>=0)
            {
                destinatedFields.add(new Point(x,y));
                x--;
            }
            x = figure.field_x+1;
            y = figure.field_y;
            while(x<=7)
            {
                destinatedFields.add(new Point(x,y));
                x++;
            }
            x = figure.field_x;
            y = figure.field_y-1;
            while(y>=0)
            {
                destinatedFields.add(new Point(x,y));
                y--;
            }
            x = figure.field_x;
            y = figure.field_y+1;
            while(y <=7)
            {
                destinatedFields.add(new Point(x,y));
                y++;
            }
        }
        else if(this.figure.type.equals("king")) {
            if (figure.field_x > 0) {
                destinatedFields.add(new Point(figure.field_x-1, figure.field_y));
            }
            if (figure.field_x <7) {
                destinatedFields.add(new Point(figure.field_x+1, figure.field_y));
            }
            if (figure.field_y > 0) {
                destinatedFields.add(new Point(figure.field_x, figure.field_y-1));
            }
            if (figure.field_y < 7) {
                destinatedFields.add(new Point(figure.field_x, figure.field_y+1));
            }
            if (figure.field_x > 0 && figure.field_y > 0) {
                destinatedFields.add(new Point(figure.field_x-1, figure.field_y-1));
            }
            if (figure.field_x > 0 && figure.field_y < 7) {
                destinatedFields.add(new Point(figure.field_x-1, figure.field_y+1));
            }
            if (figure.field_x < 7 && figure.field_y > 0) {
                destinatedFields.add(new Point(figure.field_x+1, figure.field_y-1));
            }
            if (figure.field_x <7 && figure.field_y <7) {
                destinatedFields.add(new Point(figure.field_x+1, figure.field_y+1));
            }
        }
        Collections.shuffle(destinatedFields); // sluzy do wymieszania elementow listy, miesza kolejnosc
        Point chosenPoint = destinatedFields.get((int)(Math.random() * destinatedFields.size())); // wybiera losowy punkt z tej listy na ktorym figura musi sie pojwaic

        for(int i =0;i< fields.size();i++) // szuka pole na szachownicy do ktorego musimy sie odwolac
        {

            if(chosenPoint.x == fields.get(i).field_x && chosenPoint.y == fields.get(i).field_y)
            {

                destinationField = fields.get(i);
                break;
            }
        }

    }
    //funkcja wykonująca się w ramach losowania nowej figury i wyboru jej miejsca do postawienia
    public void newTask()
    {

        generateFigure();
        generateDestination();
    }
    //sprawdzenie czy gracz postawił figurę w jej domyślnym położeniu, poprawnym położeniu lub błędnym.
    public int checkResult()
    {
        if(this.figure.field_x == this.figure.def_x && this.figure.field_y == this.figure.def_y )
        {
            return -1; // zwraca stan -1 jesli chwycimy pionka i odlozymy go w to samo miejsce jakby nic sie nie stalo
        }
        else if(this.figure.field_x == destinationField.field_x && this.figure.field_y == destinationField.field_y )
        {
            return 1;
        }
        return 0;
    }
    //Funkcja wykonująca się przy położeniu figury w dowolnym miejscu na planszy
    public void placeFigure()
    {
        //sprawdzanie z jakimi polami koliduje figura, sprawdzamy warunek kolizji, dodajemy pola ktore sie dotykaja z ta figura i sprawdzamy odleglosci tych kolidujacych pol (x, np rogi planszy) i sprawdzam odleglosc ktorego bedzie najmniejsza i ta odleglosc
        // bedzie punktem na ktorym pionek musi zostac postawiony
        ArrayList<Field> collidedFields = new ArrayList<Field>();
        for(int i =0;i< fields.size();i++)
        {
            if(figure.x + figure.width > fields.get(i).x && figure.x < fields.get(i).x + fields.get(i).width && figure.y + figure.height > fields.get(i).y && figure.y < fields.get(i).y + fields.get(i).height)
            {
                collidedFields.add( fields.get(i));
            }
        }
        //wyznaczenie tego pola które jest najbliżej figury
        double min_dist= Math.sqrt(Math.pow((figure.x-collidedFields.get(0).x),2) + Math.pow((figure.y-collidedFields.get(0).y),2));
        double dist;
        int min_dist_field_index=0;
        for(int i =1;i<collidedFields.size();i++)
        {
            dist = Math.sqrt(Math.pow((figure.x-collidedFields.get(i).x),2) + Math.pow((figure.y-collidedFields.get(i).y),2));
            if(dist <min_dist)
            {
                min_dist = dist;
                min_dist_field_index=i;
            }
        }
        // naszemu pionkowi przypisujemy x,y,field.x, field.y tego pola na ktorym zostal postawiony
        // pikselowe wspolrzedne figury zmieniaja sie na pikselowe wspolrzedne tego pola
        this.figure.x = collidedFields.get(min_dist_field_index).x;
        this.figure.y = collidedFields.get(min_dist_field_index).y;
        // wspolrzedne na tablicy dwuwymiarowej (szachownica jest lista, ale traktujemy ja jako tablice dwuwymiarowa 8x8)
        this.figure.field_x =  collidedFields.get(min_dist_field_index).field_x;
        this.figure.field_y =  collidedFields.get(min_dist_field_index).field_y;

    }
    //tworzenie szachownicy
    public void createBoard()
    {
        int fields_size = 60; //dlugosc boku jednego pola
        int fields_width = 8; //wymiar szachownicy
        int fields_height = 8;
        String fieldImage = ""; //przechowuje sciezke do pliku, 322 linia
        String fieldName1 = "";
        String fieldName2 = "";
        String rowNames[] = {"8","7","6","5","4","3","2","1"};
        String columnNames[] = {"a","b","c","d","e","f","g","h"};
        for(int i = 0;i< fields_height;i++)
        {
            for(int j =0;j<fields_width;j++)
            {
                fieldName1 = rowNames[i];
                fieldName2 = columnNames[j];

                if((i+j)%2==0)
                {
                    fieldImage = "field1.png";
                }
                else
                {
                    fieldImage = "field2.png";
                }
                fields.add(new Field(j*fields_size+this.x,i*fields_size+this.y, fields_size, fields_size, fieldImage, fieldName1, fieldName2, j, i));

                fieldName1 = "";
                fieldName2 = "";
            }

        }
    }
    // przesylamy wartosc trudnosc 'hardship (true = latwy, false = trudny)
    public void draw(Graphics2D g2D, boolean hardship)
    {
        for(int i =0;i< fields.size();i++) // wysylamy wartosc hardship
        {
            fields.get(i).draw(g2D, hardship);
        }
        if(this.figure!=null)
        {
            this.figure.draw(g2D);
        }
    }
}
