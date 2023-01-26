import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class CheckBox { // jest to button z dwoma stanami
    public boolean state;
    public int x;
    public int y;
    public int width;
    public int height;
    public String text;
    public Image sprite;
    CheckBox(int x, int y, int width, int height, boolean state, String text)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
        this.changeState(state);
    }

    public boolean clicked(int x, int y)
    {
        if(x >=this.x && x<=this.x+this.width && y>=this.y && y<=this.y+this.height)
        {
            return true;

        }
        return false;

    }
    public void changeState(boolean state)
    {
        this.state = state;
        if(state)
        {
            this.sprite = sprite = (new ImageIcon("obrazy/checkBoxTrue.png")).getImage();
        }
        else
        {
            this.sprite = sprite = (new ImageIcon("obrazy/checkBoxFalse.png")).getImage();
        }
    }
    public void draw(Graphics2D g2D)
    {
        //rysowanie obrazka
        g2D.drawImage(this.sprite, this.x, this.y, this.width, this.height,null);
        //ustawienie koloru
        g2D.setColor(Color.WHITE);
        //Ustawienie czcionki
        g2D.setFont(new Font("Arial",Font.BOLD,20));
        //Wypisanie Tekstu
        g2D.drawString(text,x+this.width+20,y+25);
    }
}
