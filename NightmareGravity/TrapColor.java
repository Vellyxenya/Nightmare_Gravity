import greenfoot.*;
import java.awt.Color;

/**
 * Objet qui apparaît sur les trous noirs du dernier boss et qui indique le niveau de surcharge
 * Une surcharge maximale fait propulser le trou noir dans la direction opposée à celle du vaisseau
 * du joueur
 */
public class TrapColor extends SmoothMover
{
    GreenfootImage image;
    Color trans = new Color(0, 0, 0, 0);

    /**
     * Scan l'iamge du trou noir et en fait une copie colorée et transparente
     */
    public TrapColor()
    {
        image = new GreenfootImage("Trap.png");
        for(int i = 0; i<image.getWidth(); i++)
        {
            for(int j = 0; j<image.getHeight(); j++)
            {
                if(!image.getColorAt(i, j).equals(trans))
                {
                    image.setColorAt(i, j, new Color(84, 31, 206));
                }
            }
        }
        image.setTransparency(0);
        setImage(image);
    }

    /**
     * Augmente l'opacité selon les dégâts subis par le trou noir
     */
    public void act() 
    {
        try
        {
            FinalTraps trap = (FinalTraps) getWorld().getObjects(FinalTraps.class).get(0);
            setRotation(trap.getRotation());
            setLocation(trap.getX(), trap.getY());
            image.setTransparency(trap.power);
        }catch(IndexOutOfBoundsException e){getWorld().removeObject(this);}
    }
}
