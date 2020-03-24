import greenfoot.*;

/**
 * Donne une chance aux ennemis de drop des crédits (utilisés dans le store pour acheter des items)
 * Aucun item disponible pour le moment.
 */
public class Credit extends Drop
{
    /**
     * Durée maximale de cet objet
     */
    public static final int MAX_DURATION = 600;
    /**
     * Compteur pour la durée de cet objet
     */
    public static int duration;
    /**
     * Taux de crédits gagnés par le joueur une fois cet objet collecté
     */
    public static int amount;
    /**
     * Vitesse de déplacement de cet objet
     */
    private int speed = 1;

    public Credit(int x)
    {
        duration = 0;
        amount = Greenfoot.getRandomNumber(x);
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
                    move(-speed);
                    duration++;
                    collect();
                    credit();
                }catch(IllegalStateException e){}
            }
        }
    }

    /**
     * Donne des crédits au joueur
     */
    public void credit()
    {
        if(isTouching(Gears.class))
        {
            if(getWorld()!= null)
            {
                getWorld().removeObject(this);
                Game.credit += 20+amount;
            }
        }
        else if(duration>=MAX_DURATION)
        {
            if(getWorld()!=null)
            {
                getWorld().removeObject(this);
            }
        }
    }
    
}
