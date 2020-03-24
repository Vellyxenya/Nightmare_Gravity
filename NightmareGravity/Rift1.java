import greenfoot.*;
import java.awt.Color;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 * Autre type de faille plus long et plus dynamique généré par le 4e Boss
 */
public class Rift1 extends Rift 
{
    Color[][] pixels = new Color[140][50];
    GreenfootImage baseImage = new GreenfootImage("Rift.png");
    GreenfootImage rift;
    private int duration;
    private int rotation;
    public static int width;
    public static int height;

    public Rift1(int rotation)
    {
        setRotation(rotation);
        this.rotation = rotation;
        duration = 180;
        width = 140;
        height = 50;
        rift = new GreenfootImage(width, height);
    }

    /**
     * Déforme l'espace
     */
    public void act()
    {
        if(isPaused() == false)
        {
            for(int i = 0; i<width; i++)
            {
                for (int j = 0; j<height; j++)
                {
                    rift.scale(width+i, height);
                    baseImage.scale(width, height);
                    Color color = baseImage.getColorAt(i, j);
                    int b = color.getBlue();
                    if(b != 0)
                    {
                        rift.setColorAt(i, j, getWorld().getColorAt(Greenfoot.getRandomNumber(getWorld().getWidth()), Greenfoot.getRandomNumber(getWorld().getHeight())));
                    }
                }
            }
            setImage(rift);
            move(7);
            vanish();
        }
    }
    
}
