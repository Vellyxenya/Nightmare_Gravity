import greenfoot.*;
import java.awt.Color;

/**
 * Petite image qui donne un effet visuel au 4e Vaisseau quand celui-ci tire en rafales
 */
public class Shadow extends Actor
{
    GreenfootImage image;
    Color trans = new Color(0, 0, 0, 0);
    
    /**
     * Crée l'objet qui servira de calque coloré au vaisseau
     */
    public Shadow()
    {
        image = new GreenfootImage("Gear4.png");
        for(int i = 0; i<image.getWidth(); i++)
        {
            for(int j = 0; j<image.getHeight(); j++)
            {
                if(!image.getColorAt(i, j).equals(trans))
                {
                    image.setColorAt(i, j, Color.BLUE);
                }
            }
        }
        image.setTransparency(10);
        setImage(image);
    }
    
    /**
     * Suit le 4e vaisseau sur la scène
     */
    public void act() 
    {
        Gear4 gear = (Gear4) getWorld().getObjects(Gear4.class).get(0);
        setRotation(gear.getRotation());
        setLocation(gear.getX(), gear.getY());
        image.setTransparency(10+3*Gear4.power);
    }
    
}
