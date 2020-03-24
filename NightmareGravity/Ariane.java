import greenfoot.*;
import java.awt.Color;

/**
 * Objets qui guident l'araignée dans sa traque.
 */
public class Ariane extends Obstacle
{
    
    public Ariane()
    {
        GreenfootImage image = new GreenfootImage(2, 2);
        image.setTransparency(0);
        setImage(image);
    }
    
    /**
     * Disparaît si la porte est désactivée
     */
    public void act() 
    {
        if(!getWorld().getObjectsAt(this.getX(), this.getY(), Opened.class).isEmpty())
        {
            Opened opened = (Opened) getWorld().getObjectsAt(getX(), getY(), Opened.class).get(0);
            if(opened.locked == false)
            {
                getWorld().removeObject(this);
            }
        }
    }
    
}
