import greenfoot.*;
import java.awt.Color;

/**
 * Balle envoyée par le vaisseau 3 (et aussi le vaisseau 1 lors de la présence d'un boss) à haute cadence.
 */
public class Weapon2 extends Weapons
{
    /**
     * Rotation du vaisseau
     */
    public int rotation;
    /**
     * Dégâts par balle
     */
    public static final int weapon2Damage = 500;
    private int counter;
    /**
     * Vitesse de la balle
     */
    public static int speed;
    /**
     * Son produit par la balle
     */
    GreenfootSound sound = new GreenfootSound("shoot.wav");

    /**
     * Construit la balle pour le 3e vaisseau
     */
    public Weapon2(int rotation, int speed)
    {
        setRotation(rotation);
        this.speed = speed;
    }
    
    /**
     * Construit la balle pour les autres vaisseaux
     */
    public Weapon2(int rotation, int speed, int i)
    {
        setRotation(rotation);
        this.speed = speed;
        sound.play();
    }

    public void act() 
    {
        move(speed);
        vanish();
        disappear2(Magnet.class);
        disappear2(Obstacle.class);
        disappear();
    }

    /**
     * S'il touche un Arc électrique, le joueur est puni par un éclair
     */
    public void disappear()
    {
        if(getWorld()!= null)
        {
            if(this.isTouching(Arc.class))
            {
                if(getWorld().getObjects(MegaLightning.class).isEmpty())
                {
                    getWorld().addObject(new MegaLightning(), getWorld().getWidth()/2, getWorld().getHeight()/2);
                }
                getWorld().removeObject(this);
            }
        }
    }

    /**
     * Disparaît 2 act après qu'il ait touché telle classe
     * @param classe: classe à toucher
     */
    public void disappear2(Class classe)
    {
        if(getWorld()!= null)
        {
            if(this.isTouching(classe))
            {
                counter++;
                if(counter==2)
                {
                    getWorld().removeObject(this);
                    counter = 0;
                }
            }
        }
    }

}
