import greenfoot.*;
import java.util.List;

/**
 * Ennemi de type Monster, le plus basique.
 */
public class Monsters extends Enemies
{
    private final int MAX_DELAY = 100;
    private final int MAX_DELAY_2 = MAX_DELAY+200;
    private int delay;
    private int speed = 2;

    public Monsters()
    {
        delay = 0;
        Life = 3000;
    }

    public void act() 
    {
        if(isPaused() == false)
        {
            try
            {
                super.act();
                move(-(1+Greenfoot.getRandomNumber(3)));
                upAndDown();
                delay++;
                vanish();
            }catch(IllegalStateException e){}
        }
    }

    /**
     * Donne un mouvement ondulatoire et aléatoire au monstre
     */
    public void upAndDown()
    {
        int random1 = Greenfoot.getRandomNumber(2);
        if(random1 == 0 && delay > MAX_DELAY)
        {
            this.setLocation(this.getX(),this.getY()+4);
        }
        else if(delay >= MAX_DELAY_2) delay = 0;
        if(random1 == 1 && delay > MAX_DELAY)
        {
            this.setLocation(this.getX(),this.getY()-4);
        }
        else if(delay >= MAX_DELAY_2) delay = 0;
    }

}
