import greenfoot.*;

/**
 * Halo créé autour du joueur juste avant le combat contre un boss
 */
public class Halo extends SmoothMover
{
    private int counter;

    /**
     * Le halo clignote et mène le joueur au centre de la scène
     */
    public void act() 
    {
        counter++;
        if(counter%2 == 0)getImage().setTransparency(50);
        else getImage().setTransparency(250);
        try
        {
            Gears gear = (Gears) getWorld().getObjects(Gears.class).get(0);
            setLocation(gear.getX(), gear.getY());
        }catch(NullPointerException e){}
        if(isTouching(Weapons.class))removeTouching(Weapons.class);
    }

}
