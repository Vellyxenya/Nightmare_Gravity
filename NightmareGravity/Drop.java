import greenfoot.*;

/**
 * Classe-mère de tous les drops, plusieurs encore à définir.
 */
public class Drop extends SmoothMover
{

    public void act() 
    {
        if(isAtEdge())
        {
            getWorld().removeObject(this);
        }
    }

    /**
     * Se dirige vers le vaisseau si celui-ci possède la compétence qui attire ces objets.
     */
    public void collect()
    {
        if(getWorld()!= null)
        {
            if(CollectIcon.used == true)
            {
                Gears gear = (Gears) getWorld().getObjects(Gears.class).get(0);
                turnTowards(gear.getX(), gear.getY());
                move(7);
            }
        }
    }

}
