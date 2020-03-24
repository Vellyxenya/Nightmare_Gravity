import greenfoot.*;

/**
 * Effet d'explosion
 */
public class Dust extends SpecialEffects
{
    
    public Dust(int rotation)
    {
        getImage().setTransparency(200);
        setRotation(rotation);
    }
    
    public void act() 
    {
        if(isPaused() == false)
        {
            move(2);
            getImage().setTransparency(getImage().getTransparency()-5);
            if(getImage().getTransparency()<= 6)
            {
                getWorld().removeObject(this);
            }
        }
    }
    
}
