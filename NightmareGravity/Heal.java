import greenfoot.*;

/**
 * Drop de la vie pour regen le vaisseau
 */
public class Heal extends Drop
{
    /**
     * Durée avant que cet objet ne disparaisse
     */
    public static final int MAX_DURATION = 600;
    /**
     * Compteur pour la durée de cet objet
     */
    public int duration;
    /**
     * Si true, l'objet se dirige vers le vaisseau
     */
    private boolean travel = false;

    public Heal()
    {
        duration = 0;
    }

    public Heal(int z)
    {
        travel = true;
    }

    public void act() 
    {
        if(isPaused() == false)
        {
            super.act();
            duration++;
            heal();
            if(travel == true)
            {
                if(getWorld()!= null)
                {
                    Gears gear = (Gears)getWorld().getObjects(Gears.class).get(0);
                    turnTowards(gear.getX(), gear.getY());
                    move(6);
                }
            }
            move(-1);
            collect();
        }
    }

    /**
     * Disparaît après la durée maximale
     */
    public void disappear()
    {
        if(duration>MAX_DURATION)
        {
            getWorld().removeObject(this);
        }
    }

    /**
     * Régénère les points de vie du joueur si celui-ci vient le collecter
     */
    public void heal()
    {
        if(getWorld() != null)
        {
            if(isTouching(Gears.class))
            {
                getWorld().removeObject(this);
                Gears.Life += 100;
            }
        }
    }
    
}
