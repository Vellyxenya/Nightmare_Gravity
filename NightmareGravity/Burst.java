import greenfoot.*;

/**
 * Bombe projetée par les ennemis qui viennent d'exploser
 */
public class Burst extends SpecialEffects
{
    private int counter;
    private int direction;
    public static int damage = 20;

    public Burst(int rotation)
    {
        counter = 0;
        setRotation(rotation);
        direction = Greenfoot.getRandomNumber(2);
    }

    /**
     * Reste quelques secondes sur scène avant d'exploser à son tour
     */
    public void act() 
    {
        if(isPaused() == false)
        {
            try
            {
                counter++;
                if(counter%10 == 0)
                {
                    if(direction == 0)setRotation(getRotation()+10);
                    else if(direction == 1)setRotation(getRotation()-10);
                }
                if(counter<30)
                {
                    move(4);
                    if(counter%2 == 0)
                    {
                        int random = Greenfoot.getRandomNumber(2);
                        if(random == 0)getWorld().addObject(new Dust(getRotation()-200), getX(), getY());
                        else if(random == 1)getWorld().addObject(new Dust(getRotation()-160), getX(), getY());
                    }
                }
                if(counter >= 90)
                {
                    for(int i = 0; i<8; i++)
                    {
                        getWorld().addObject(new Dust(Greenfoot.getRandomNumber(360)), getX(), getY());
                    }
                    getWorld().removeObject(this);
                }
                vanish();
            }catch(IllegalStateException e){}
        }
    }

}
