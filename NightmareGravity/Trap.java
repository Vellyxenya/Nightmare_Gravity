import greenfoot.*;
import java.util.List;

/**
 * Objet gravitationnel qui, si touché par le joueur, le fera rentrer dans le mode Nightmare.
 */
public class Trap extends Traps
{
    private int counter;
    /**
     * Variable définissant le style de mouvement du trou noir
     */
    int i;

    public Trap()
    {
        counter = 0;
        i = Greenfoot.getRandomNumber(3);
    }

    public void act() 
    {
        if(isPaused() == false)
        {
            counter++;
            movements();
        }
    }

    /**
     * Mouvements du trou noir
     */
    public void movements()
    {
        if(getWorld() !=null)
        {
            if(this !=null)
            {
                turn(5);
                if(counter%10 == 0)
                {
                    switch(i)
                    {
                        case 0: setLocation(getX()-7, getY()-1); break;
                        case 1: setLocation(getX()-4, getY()-3); break;
                        case 2: setLocation(getX()-4, getY()+3); break;
                    }
                    vanish();
                }
            }
        }
    }

}
