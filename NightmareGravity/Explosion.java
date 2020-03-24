import greenfoot.*;
import java.util.List;

/**
 * Explosion engendrée par la téléportation du vaisseau 3
 */
public class Explosion extends Animations
{
    /**
     * Tableau de toutes les images de l'animation
     */
    public static GreenfootImage[] images;
    public int numberImages = 0;
    public int rotationImage = 0;
    /**
     * Portée maximale
     */
    public static int rangeThis = 30;

    public Explosion()
    {
        initializeImagesShock("Explosion.png");
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
            images = new GreenfootImage[rangeThis];
            for (int i=0; i<rangeThis; i++)
            {
                int size = ((i+1)*(firstImage.getWidth()/rangeThis))/2;
                images[i] = new GreenfootImage(firstImage);
                images[i].scale(size, size);
            }
        }
    }

    /**
     * L'explosion se propage puis disparaît
     */
    public void propagate()
    {
        if (numberImages >= rangeThis) 
        {
            getWorld().removeObject(this);
        }
        else 
        {
            setRotation(getRotation()+rotationImage);
            setImage(images[numberImages]);
            numberImages++;
            rotationImage++;
        }
    }
    
}
