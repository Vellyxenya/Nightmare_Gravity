import greenfoot.*;

/**
 * Balles créées par le premier Boss
 */
public class BossBullet extends BossStuff
{
    /**
     * Dégâts infligés par cette boule
     */
    public static int damage = 20;
    /**
     * La boule ne disparaît qu'après 2 acts suivant l'impact, histoire qu'elle ait le temps d'infliger des dégâts.
     */
    private int counter = 0;

    public void act() 
    {
        if(isPaused() == false && getWorld().getObjects(Screenshot.class).isEmpty())
        {
            move(4);
            vanish();
            count();
            delete();
        }
    }   
    
    /**
     * Si elle touche le vaisseau du joueur le compteur se déclenche
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
     * Après 2 act, l'objet disparaît
     */
    private void delete()
    {
        if(counter == 2)
        {
            getWorld().removeObject(this);
        }
    }
    
}