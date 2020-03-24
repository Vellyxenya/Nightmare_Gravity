import greenfoot.*;

public class BonusLife extends Drop
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
     * Vitesse de déplacement de cet objet
     */
    private int speed = 1;

    public BonusLife()
    {
        duration = 0;
    }

    public void act() 
    {
        if(isPaused() == false)
        {
            if(getWorld()!= null)
            {
                super.act();
                move(-speed);
                duration++;
                collect();
                bonus();
            }
        }
    }

    /**
     * Ajoute une vie au joueur si collecté
     */
    public void bonus()
    {
        if(getWorld()!= null)
        {
            if(isTouching(Gears.class))
            {
                if(getWorld()!= null)
                {
                    getWorld().removeObject(this);
                    Game.lives++;
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
    
}
