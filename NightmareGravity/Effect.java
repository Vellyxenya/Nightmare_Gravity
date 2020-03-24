import greenfoot.*;
import java.awt.Color;

/**
 * Petite particule invoquée par la souris (simple effet visuel)
 */
public class Effect extends DATA
{
    GreenfootImage image = new GreenfootImage(150, 30);

    /**
     * Initialise la particule
     */
    public Effect()
    {
        image.setColor(Color.RED);
        image.drawRect(0, 0, 149, 29);
        image.setTransparency(100);
        setImage(image);
    }

    /**
     * La particule grandit et s'efface
     */
    public void act() 
    {
        try
        {
            getImage().scale(getImage().getWidth()-5, getImage().getHeight()-1);
            getImage().setTransparency(getImage().getTransparency()-3);
            setImage(image);
        }catch(IllegalArgumentException e){getWorld().removeObject(this);}
    }

}
