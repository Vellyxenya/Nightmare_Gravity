import greenfoot.*;
import java.util.List;

/**
 * Objets qui envoient des orbes immobilisants et infligeant peu de dégâts mais mortels si joueur les reçoit alors qu'il se trouve
 * au mauvais endroit...
 */
public class Stunner extends Enemies
{
    /**
     * Vitesse d'attaque
     */
    private static final int ATTACK_SPEED = 240;
    /**
     * Compteur pour la vitesse d'attaque
     */
    private int attackTimer;
    /**
     * Permet de séquencer les tirs
     */
    private int ready;
    /**
     * Vitesse de déplacement de ce vaisseau
     */
    private int speed = 2;

    public Stunner()
    {
        Life = 3000;
    }

    public void act() 
    {
        if(isPaused() == false)
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

    /**
     * Déplacement horizontal de droite à gauche quelle que soit la direction de l'acteur.
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
     * Tire sur le vaisseau de l'utilisateur une boule énergétique. Très basse fréquence
     */
    public void fire()
    {
        if(ready >= 50 && attackTimer >= ATTACK_SPEED)
        {
            StunningOrbe stunningOrbe = new StunningOrbe((this.getRotation()+Greenfoot.getRandomNumber(10)));
            getWorld().addObject(stunningOrbe, this.getX(), this.getY());
            attackTimer = 0;
        }
    }

}
