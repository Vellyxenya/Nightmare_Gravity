import greenfoot.*;

/**
 * Asteroids lancé par le Boss5 en courbe et infligeant de sérieux dégâts.
 */
public class Ash extends Hurting
{
    /**
     * Sens de rotaion de cet objet
     */
    private int coefficient;

    public Ash()
    {
        setRotation(120+Greenfoot.getRandomNumber(150));
        int random = Greenfoot.getRandomNumber(2);
        if(random == 0)
        {
            coefficient = 1;
        }
        else if (random == 1)
        {
            coefficient = -1;
        }
    }

    /**
     * Mouvement de cet objet
     */
    public void act() 
    {
        if(isPaused()== false)
        {
            vanish();
            move(7);
            if(getWorld()!= null)
            {
                setLocation(this.getX()-1, this.getY());
            }
            turn((1+Greenfoot.getRandomNumber(2))*coefficient);
        }
    }
    
}
