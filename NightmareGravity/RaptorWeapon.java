import greenfoot.*;

/**
 * Petits carreaux envoyés par la classe Raptor à grande fréquence
 */
public class RaptorWeapon extends EW
{
    /**
     * Dépend de la rotaion de Raptor
     */
    public int rotation;
    /**
     * Dégâts infligés
     */
    public static final int dDamage = 10;
    private int counter = 0;

    public RaptorWeapon(int rotation)
    {
        setRotation(rotation);
    }

    public void act() 
    {
        if(isPaused() == false)
        {
            move(4);
            vanish();
            count();
            disappear();
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
     * Disparaît après 2 acts
     */
    private void disappear()
    {
        if(counter == 2)
        {
            getWorld().removeObject(this);
        }
    }
    
}

    