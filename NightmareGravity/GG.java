import greenfoot.*;

/**
 * Foyer de création des ennemis.
 */
public class GG extends Obstacle
{
    private int counter;

    public GG()
    {

    }

    public void act() 
    {
        if(getWorld().getObjects(Informations2.class).isEmpty())
        {
            counter++;
            if(counter%120 == 0)
            {
                getWorld().addObject(new Ghost(), this.getX(), this.getY());
            }
        }
    }

}
