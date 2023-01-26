import javax.swing.*;
import java.awt.*;

public class Figure{

    public boolean dragged;
    public int x;
    public int y;
    public int width;
    public int height;
    public Image sprite;

    public int field_x;

    public int field_y;
    public String type;
    public int min_x;
    public int max_x;
    public int min_y;
    public int max_y;
    public int def_x;
    public int def_y;
    // decydujemy jaki to ma byc typ pionka
    Figure(int x, int y, int width, int height, int type, int field_x, int field_y, int min_x, int max_x, int min_y, int max_y)
    {
        this.def_x = field_x;
        this.def_y = field_y;
        this.min_x = min_x;
        this.max_x = max_x;
        this.min_y = min_y;
        this.max_y = max_y;
        this.field_x = field_x;
        this.field_y = field_y;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        switch(type) {
            case 0:
                this.type = "knight";
                break;
            case 1:
                this.type = "bishop";
                break;
            case 2:
                this.type = "rook";
                break;
            case 3:
                this.type = "queen";
                break;
            case 4:
                this.type = "king";
                break;
        }

        this.sprite = (new ImageIcon("obrazy/"+this.type+".png")).getImage(); // przyporzadkowujemy kazdemu typowi odpowiedni obrazek
    }

    //wywoluje sie gdy chce przeniesc figure poza ekran, te warunki na to nie pozwalaja
    public void borders() //
    {
        if(this.x <this.min_x)
        {
            this.x = this.min_x;
        }
        else if(this.x+this.width >this.max_x)
        {
            this.x = this.max_x-this.width;
        }
        if(this.y <this.min_y)
        {
            this.y = this.min_y;
        }
        else if(this.y+this.height >this.max_y)
        {
            this.y = this.max_y-this.height;
        }
    }
    //zwrócenie stanu kolizji myszki z figurą
    public boolean clicked(int x, int y)
    {
        if(x >=this.x && x<=this.x+this.width && y>=this.y && y<=this.y+this.height)
        {
            return true;

        }
        return false;

    }
    public void draw(Graphics2D g2D)
    {
        g2D.drawImage(this.sprite, this.x, this.y, this.width, this.height,null);
    }
}
