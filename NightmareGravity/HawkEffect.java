import greenfoot.*;

/**
 * Effet qui suit les missile du 4e vaisseau
 */
public class HawkEffect extends SmoothMover
{
    private int counter;

    public void act() 
    {
        if(isPaused() == false)
        {
            counter++;
            try
            {
                getImage().setTransparency(getImage().getTransparency()-2*counter);
            }
            catch(IllegalArgumentException e)
            {
                getWorld().removeObject(this);
            }
        }
    }

}
