import greenfoot.*;
import java.awt.Color;

/**
 * Classe mal nommée... utilisée dans le monde Nightmare. Permet de suivre le décalement de la carte etc...
 */
public class Obstacle extends SmoothMover
{
    public static boolean ready = true;
    
    public void act() 
    {
        
    }
    
    /**
     * Fait apparaître l'image pixel par pixel
     */
    public void setImage(int width, GreenfootImage image) //Permet de faire apparaître le boss pixel par pixel
    {
        GreenfootImage setter = new GreenfootImage(width, image.getHeight());
        setter.drawImage(image, 0, 0);
        setImage(setter);
    }
    
    /**
     * Enlève les ennemis qui sortent du labyrinthe à certaines occasions
     */
    public void debug()
    {
        if(!getWorld().getColorAt(this.getX(), this.getY()).equals(Color.WHITE))
        {
            getWorld().removeObject(this);
        }
    }

}
