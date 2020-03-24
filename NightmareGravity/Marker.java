import greenfoot.*;
import java.awt.Color;

/**
 * Prolonge les segments des hexagones pour faciliter le Nightmare (puzzle)
 */
public class Marker extends Actor
{
    GreenfootImage image = new GreenfootImage(50, 1);
    private int counter;
    
    public Marker(int rotation)
    {
        counter = 0;
        image.setColor(Color.RED);
        image.fill();
        setImage(image);
        setRotation(rotation);
    }
    
    public void act() 
    {
        if(counter == 0)
        {
            move(25);
        }
        counter++;
    }
    
}
