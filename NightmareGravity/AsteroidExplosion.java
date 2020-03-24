import greenfoot.*;
import java.util.List;

public class AsteroidExplosion extends Animations
{
    /**
     * Tableau contenant les images de l'animation
     */
    public static GreenfootImage[] images;
    public static final int damage = 10;
    public int numberImages = 0;
    /**
     * Portée de l'explosion
     */
    public static int range = 20;
    private static int increaseRange = 3;

    public AsteroidExplosion()
    {
        initializeImagesShock("AsteroidExplosion.png");
        setImage(images[0]);
    }

    public void act() 
    {
        propagate();
    }

    /**
     * Initialise les images de l'animation
     */
    private static void initializeImagesShock(String fileName)
    {
        if(images == null)
        {
            GreenfootImage firstImage = new GreenfootImage(fileName);
            images = new GreenfootImage[range];
            for (int i=0; i<range; i++)
            {
                int size = 2*(i+1)*(firstImage.getWidth()/range);
                images[i] = new GreenfootImage(firstImage);
                images[i].scale(size, size);
            }
        }
    }

    /**
     * La vague grossit et se propage jusqu'à ce que sa taille maximale soit atteinte avant de disparaître
     */
    public void propagate()
    {
        if (numberImages >= range) 
        {
            getWorld().removeObject(this);
        }
        else 
        {
            setImage(images[numberImages]);
            numberImages++;
        }
    }
    
}
