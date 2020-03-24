import greenfoot.*;
import java.awt.Color;
import java.util.List;

/**
 * Ecran noir avec un disque transparent centré sur le personnage pendant le labyrinthe et servant à réduire le champ de vision.
 */
public class Blind extends Interface
{
    /**
     * Largeur de la scène
     */
    int width = 750;
    /**
     * Hauteur de la scène
     */
    int height = 563;
    /**
     * Image de l'écran noir
     */
    GreenfootImage image = new GreenfootImage(width*2, height*2);
    /**
     * Tableau de pixels de l'écran
     */
    int pixels[][] = new int[width][height];
    /**
     * Rayon de vision
     */
    public static int radius = 350;

    /**
     * Construit l'écran noir
     */
    public Blind()
    {
        image.setColor(Color.BLACK);
        image.fill();
        draw();
        setImage(image);
    }

    /**
     * Dessine un disque transparent au milieu de l'écran
     */
    public void draw()
    {
        for(int x = 750-radius; x < 750+radius; x++)
        {
            for(int y = 563-radius; y < 563+radius; y++)
            {
                if(Math.hypot(Math.abs(x-750),Math.abs(y-563)) <= radius)
                {
                    int trans = (int)Math.hypot(Math.abs(x-750),Math.abs(y-563))*2;
                    if(trans>255)
                    {
                        trans = 255;
                    }
                    image.setColorAt(x,y, new Color(0,0,0,trans));
                }
            }
        }
    }

}
