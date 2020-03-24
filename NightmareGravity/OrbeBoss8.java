import greenfoot.*;

/**
 * Objets créés sur scène et qui immobilisent le vaisseau s'il entre en collision avec.
 */
public class OrbeBoss8 extends Boss8Stuff
{
    /**
     * Durée de chacun de ces objets
     */
    private int duration;

    public OrbeBoss8()
    {
        duration = 600;
        setRotation(Greenfoot.getRandomNumber(360));
        getImage().scale(50, 50);
    }

    public void act() 
    {
        if(isPaused() == false)
        {
            move(8);
            turn(9);
            stun();
            stick();
            vanish();
            duration--;
            if(duration<= 0)
            {
                getWorld().removeObject(this);
            }
        }
    }

    /**
     * Se colle au vaisseau touché est l'immobilise
     */
    public void stick()
    {
        if(getWorld()!= null)
        {
            if(isTouching(Gears.class))
            {
                Gears gear = (Gears) getWorld().getObjects(Gears.class).get(0);
                setLocation(gear.getX(), gear.getY());
                if(duration>150)
                {
                    duration = 120;
                }
            }
        }
    }

}
