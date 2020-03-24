import greenfoot.*;

/**
 * Tir des objets de la classe Revenger
 */
public class BlueHit extends EW
{
    private int laser;
    private int duration;
    private boolean more;
    public static int damage = 10;

    public BlueHit(int rotation, int laser)
    {
        setRotation(rotation);
        this.laser = laser;
        if(laser == 1)
        {
            setImage("Laser.png");
        }
        duration = 0;
        more = false;
    }

    /**
     * Mouvements
     */
    public void act() 
    {
        if(isPaused() == false)
        {
            if(laser == 0)
            {
                move(5);
                vanish();
            }
            if(laser == 1 && more == false)
            {
                try
                {
                    move(15);
                    vanish();
                    getWorld().addObject(new BlueHit(this.getRotation(), 1), getX(), getY());
                    more = true;
                }catch(IllegalStateException e){}
            }
            duration++;
            if(duration>= 90)
            {
                try
                {
                    getWorld().removeObject(this);
                }catch(NullPointerException e){}
            }
        }
    }

}
