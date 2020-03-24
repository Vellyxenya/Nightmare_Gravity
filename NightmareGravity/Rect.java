import greenfoot.*;
import java.awt.Color;
import java.util.List;

/**
 * Rectange apparaissant dans la scène PreNightmare et surlequel le joueur doit cliquer et il a une certaine
 * chance que ce rectangle ait une couleur cachée verte, auquel cas il accède au défi.
 * Si la couleur est rouge, la partie est terminée.
 */
public class Rect extends Interface
{
    GreenfootImage image = new GreenfootImage(60, 40);
    int radius = 10;
    int value;
    Color c1 = new Color(150, 150, 150, 255);
    Color c2 = new Color(100, 100, 100, 255);

    public Rect(int value)
    {
        this.value = value;
        draw(c1);
    }

    public void act() 
    {
        if(mouseOn() == true)
        {
            draw(c2);
        }
        else
        {
            draw(c1);
        }
        if(Greenfoot.mouseClicked(this))
        {
            List<Rect> rects = getWorld().getObjects(Rect.class);
            for(Rect rect : rects)
            {
                if(rect.value == 0) rect.draw(Color.RED);
                else rect.draw(Color.GREEN);
            }
            Greenfoot.delay(150);
            if(this.value == 1) Greenfoot.setWorld(new EasyNightmare());
            else
            {
                Game.gameMusic.stop();
                Greenfoot.setWorld(new GameOver());
            }
        }
    }

    /**
     * Dessine un rectangle avec des bords arrondis avec la couleur donnée
     */
    public void draw(Color color)
    {
        image.clear();
        image.setColor(color);
        image.fillOval(0, 0, radius, radius);
        image.fillOval(49, 0, radius, radius);
        image.fillOval(49, 29, radius, radius);
        image.fillOval(0, 29, radius, radius);
        image.fillRect(5, 0, 49, 40);
        image.fillRect(0, 5, 60, 29);
        setImage(image);
    }

}
