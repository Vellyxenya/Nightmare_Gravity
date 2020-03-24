import greenfoot.*;
import java.awt.Color;
import java.util.List;

/**
 * Objet qui forme une chaîne sur scène, si elle est touchée par un ennemi, celui-ci se transforme en "Renvenger"
 */
public class Web extends SpecialEffects
{
    public boolean more;
    public static boolean back;
    private int duration;
    private int counter;
    public static int damage = 1;

    public Web(int rotation)
    {
        counter = 0;
        getImage().scale(10, 10);
        getImage().setTransparency(200);
        more = true;
        back = false;
        setRotation(rotation);
        duration = 130;
        counter = 60;
    }

    /**
     * Mouvements plus détection de collision avec les ennemis
     */
    public void act() 
    {
        if(isPaused() == false)
        {
            try
            {
                if(more == true)
                {
                    try
                    {
                        move(12);
                        vanish();
                        int random = Greenfoot.getRandomNumber(2);
                        if(random == 0)
                        {
                            getWorld().addObject(new Web(getRotation()+5), getX(), getY());
                        }
                        else if(random == 1)
                        {
                            getWorld().addObject(new Web(getRotation()-5), getX(), getY());
                        }
                    }catch(IllegalStateException e){}
                }
                more = false;
                if(back == false)
                {
                    duration--;
                }
                if(duration<= 0)
                {
                    getWorld().removeObject(this);
                }
                if(isTouching(Enemies.class) && counter <= 0 && back == false)
                {
                    List<Web> webs = getWorld().getObjects(this.getClass());
                    for(Web web : webs)
                    {
                        web.more = false;
                        back = true;
                    }
                    getWorld().addObject(new Revenger(), this.getX(), this.getY());
                }
            }catch(IllegalStateException e){}
            counter--;
            back();
        }
    }

    /**
     * Se dirige vers Revenger une fois que celui-ci est créé
     */
    public void back()
    {
        try
        {
            if(back == true)
            {
                Revenger r = (Revenger) getWorld().getObjects(Revenger.class).get(0);
                turnTowards(r.getX(), r.getY());
                move(6);
            }
        }catch(IndexOutOfBoundsException e){getWorld().removeObject(this);}
    }

}
