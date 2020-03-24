import greenfoot.*;

/**
 * Objet servant à immobiliser mon vaisseau.
 */
public class StunGear extends NotHurting
{
    private int counter;
    
    /**
     * Immobilise le vaisseau s'il le touche
     */
    public void act() 
    {
        if(isPaused() == false)
        {
            counter++;
            if(getWorld()!= null)
            {
                Gears gear = (Gears) getWorld().getObjects(Gears.class).get(0);
                if(isTouching(Gears.class) && Gears.isMoved == false)
                {
                    gear.setLocation(this.getX(), this.getY());
                }
                else if (!isTouching(Gears.class) && counter >= 120)
                {
                    getWorld().removeObject(this);
                }
            }
        }
    }
    
}
