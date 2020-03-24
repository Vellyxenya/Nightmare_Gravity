import greenfoot.*;
import java.awt.Color;

/**
 * Effet visuel de la souris, sorte de poussière qui est dégagée au pointeur.
 */
public class MouseEffect extends SmoothMover
{
    Color color;
    GreenfootImage image = new GreenfootImage(25, 25);
    private int i;
    /**
     * Taille max des cercles utilisés pour l'animation
     */
    private final int MAX_SIZE = 15;
    
    /**
     * Initialise le cercle
     */
    public MouseEffect(int rotation)
    {
        color = new Color(Greenfoot.getRandomNumber(255), 0, Greenfoot.getRandomNumber(255));
        image.setColor(color);
        image.fillOval(0, 0, 5, 5);
        setImage(image);
        setRotation(rotation);
        i = 0;
    }
    
    /**
     * Le disque gradit et gagne en transparence jusqu'à ce qu'il disparaisse
     */
    public void act() 
    {
        move(1);
        i++;
        GreenfootImage img = new GreenfootImage(5+i, 5+i);
        img.setColor(color);
        img.fillOval(0, 0, 5+i, 5+i);
        img.setTransparency(170-10*i);
        setImage(img);
        if(i>MAX_SIZE)
        {
            getWorld().removeObject(this);
        }
    }
    
}
