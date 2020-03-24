import greenfoot.*;
import java.util.List;

/**
 * Ennemi de classe Raptor.
 */
public class Raptor extends Enemies
{
    /**
     * Vitesse d'attque
     */
    private static final int ATTACK_SPEED = 15;
    /**
     * Compteur pour les tirs
     */
    private int attackTimer;
    /**
     * Séquence les tirs
     */
    private int ready;
    /**
     * Vitesse de cet ennemi
     */
    private int speed = 2;

    public Raptor()
    {
        ready = 0;
        Life = 3000;
    }

    public void act() 
    {
        if(isPaused() == false)
        {
            if(getWorld()!= null)
            {
                try
                {
                    super.act();
                    movements();
                    faceGear();
                    fire();
                    attackTimer++;
                    ready++;
                    vanish();
                }catch(IllegalStateException e){}
            }
        }
    }

    /**
     * Déplacement horizontal de droite à gauche quelle que soit la direction de ce objet
     */
    public void movements()
    {
        if(getWorld()!= null)
        {
            this.setLocation(this.getX()-speed, this.getY());
        }
    }

    /**
     * S'oriente en direction du vaisseau de l'utilisateur
     */
    public void faceGear()
    {
        if(getWorld() != null)
        {
            Actor gear = (Actor)getWorld().getObjects(Gears.class).get(0);
            int xGear = gear.getX();
            int yGear = gear.getY();
            this.turnTowards(xGear, yGear);
        }
    }

    /**
     * Tire sur le vaisseau de l'utilisateur périodiquement. Fréquence assez élevée
     */
    public void fire()
    {
        int spread = Greenfoot.getRandomNumber(2);
        if(ready >= 50 && attackTimer >= ATTACK_SPEED)
        {
            if(spread == 0)
            {
                RaptorWeapon weapon = new RaptorWeapon((this.getRotation()+Greenfoot.getRandomNumber(10)));
                getWorld().addObject(weapon, this.getX(), this.getY());
                attackTimer = 0;
            }
            else if(spread == 1)
            {
                RaptorWeapon weapon = new RaptorWeapon((this.getRotation()-Greenfoot.getRandomNumber(10)));
                getWorld().addObject(weapon, this.getX(), this.getY());
                attackTimer = 0;
            }
        }
    }

}
