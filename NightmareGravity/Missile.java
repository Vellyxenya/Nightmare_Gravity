import greenfoot.*;
import java.util.List;

/**
 * Missile à tête chercheuse
 */
public class Missile extends EW
{
    private int timer;
    public static final int damage = 20;
    private int counter = 0;
    /**
     * Durée de vie du missile avant qu'il ne disparaisse
     */
    private int duration;

    /**
     * Initialise le missile
     */
    public Missile()
    {
        timer = 0;
        duration = 300;
    }

    public void act() 
    {
        if(isPaused() == false)
        {
            count();
            vanish();
            disappear();
            move(3);
            timer++;
            faceGear();
            duration--;
        }
    }

    /**
     * Permet au missile de suivre sa trajectoire et de s'auto-détruire si l'angle de rotation est trop grand
     */
    public void faceGear()
    {
        if(getWorld() != null)
        {
            if(timer % 5 == 0)
            {
                Actor gear = (Actor)getWorld().getObjects(Gears.class).get(0);
                int xGear = gear.getX();
                int yGear = gear.getY();
                int currentRotation = getRotation();
                turnTowards(xGear, yGear);
                int newRotation = getRotation();
                // if change in rotation passes the zero degree mark
                if (Math.abs(currentRotation-newRotation) > 180)
                {
                    if (currentRotation < 180) currentRotation += 360;
                    else newRotation += 360;
                }
                // if change in rotation is greater than ninety degrees
                if (Math.abs(currentRotation-newRotation) >= 20)
                {
                    getWorld().removeObject(this);
                }
                else if(Math.abs(currentRotation-newRotation) <20)
                {  
                    this.turnTowards(xGear, yGear);
                }
            }
        }
    }

    /**
     * Déclenche le compteur s'il touche un objet de la classe Gears
     */
    private void count()
    {
        if(getWorld() != null)
        {
            if(this.isTouching(Gears.class))
            {
                counter++;
            }
        }
    }

    /**
     * Disparaît après 2 acts, pour qu'il ait le temps d'infliger des dégâts
     */
    private void disappear()
    {
        if(counter == 2 || duration <= 0)
        {
            getWorld().removeObject(this);
        }
    }

}
