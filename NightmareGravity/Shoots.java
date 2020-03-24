import greenfoot.*;

/**
 * Tirs créés par le 9e Boss
 */
public class Shoots extends Hurting
{
    
    public Shoots()
    {
        setRotation(90+Greenfoot.getRandomNumber(180));
    }
    
    /**
     * S'ils sont interceptés par un objet de la classe TP, ils sont déviés
     */
    public void act() 
    {
        if(isPaused() == false)
        {
            move(6);
            if(isTouching(TP.class))
            {
                TP.number++;
                getWorld().removeObject(this);
            }
            vanish();
        }
    }
    
}
