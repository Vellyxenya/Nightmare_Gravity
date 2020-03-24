import greenfoot.*;
import java.awt.Color;

/**
 * Faille créée par le 4e Boss
 */
public class Fault extends Rift
{
    /**
     * Longueur de la faille
     */
    private final int WIDTH = 140;
    /**
     * Largeur de la faille
     */
    private final int HEIGHT = 50;
    /**
     * Image surlaquelle est basée faille pour sa création
     */
    GreenfootImage baseImage = new GreenfootImage("Rift.png");
    GreenfootImage rift;

    public Fault(int rotation)
    {
        setRotation(rotation);
        rift = new GreenfootImage(WIDTH, HEIGHT);
    }

    /**
     * Scan la couleur de l'image et génère une image de même forme mais qui déforme l'espace visuellement
     */
    public void act()
    {
        if(isPaused() == false)
        {
            for(int i = 0; i<WIDTH; i++)
            {
                for (int j = 0; j<HEIGHT; j++)
                {
                    baseImage.scale(WIDTH, HEIGHT);
                    Color color = baseImage.getColorAt(i, j);
                    int b = color.getBlue();
                    if(b != 0)
                    {
                        rift.setColorAt(i, j, getWorld().getColorAt(Greenfoot.getRandomNumber(getWorld().getWidth()), Greenfoot.getRandomNumber(getWorld().getHeight())));
                    }
                }
            }
            rift.setTransparency(150+Greenfoot.getRandomNumber(100));
            setImage(rift);
            move(6);
            vanish();
        }
    }
    
}
