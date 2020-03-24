import greenfoot.*;
import java.util.List;

/**
 * Vague créée par le Vaisseau 1, inflige de sérieux dégâts à tous les ennemis (pas encore pour le moment).
 */
public class ShockWave extends Animations
{
    /**
     * Tableau contenant les images de l'animation
     */
    public static GreenfootImage[] images;
    public int numberImages = 0;
    /**
     * Portée maximale de l'animation
     */
    public static int range = 160;
    private static int increaseRange = 3;

    public ShockWave()
    {
        initializeImagesShock("wave.png");
        setImage(images[0]);
    }
    
    public void act() 
    {
        propagate();
        damageEnemies();
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
                int size = increaseRange*(i+1)*(firstImage.getWidth()/range);
                images[i] = new GreenfootImage(firstImage);
                images[i].scale(size, size);
            }
        }
    }
    
    /**
     * La vague grossit jusqu'à ce que sa taille maximale soit atteinte avant de disparaître
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