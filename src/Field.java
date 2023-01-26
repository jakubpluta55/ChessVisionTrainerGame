import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Field {
    public int x;
    public int y;
    public int field_x;
    public int field_y;
    public int width;
    public int height;
    public String name1;
    public String name2;
    public Image sprite;
    Field(int x, int y, int width, int height, String imageName, String name1, String name2, int field_x, int field_y)
    {
        this.sprite = (new ImageIcon("obrazy/"+imageName)).getImage();
        this.x = x;
        this.y = y;
        this.field_x = field_x;
        this.field_y = field_y;
        this.width = width;
        this.height = height;
        this.name1 = name1;
        this.name2 = name2;
    }

    public void draw(Graphics2D g2D, boolean hardship)
    {
        //rysowanie obrazka
        g2D.drawImage(this.sprite, this.x, this.y, this.width, this.height,null);
        if(hardship) // rysowanie obrazka z uwzglednieniem 'hardship'
        {
            //ustawienie koloru
            g2D.setColor(Color.WHITE);
            //Ustawienie czcionki
            g2D.setFont(new Font("Arial",Font.BOLD,20));
            //Wypisanie Tekstu

            if(field_x==0) {
                g2D.drawString(name1, x + 10, y + 30);
            }
            if(field_y ==7)
            {
                g2D.drawString(name2,x+40,y+50);
            }

        }
    }
}
