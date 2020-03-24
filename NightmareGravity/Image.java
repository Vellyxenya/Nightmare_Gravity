import greenfoot.*;

/**
 * Effet visuel apparaissant uniquement au tout début de la partie derrière le vaisseau
 */
public class Image extends Interface
{
    
    /**
     * Anime cet acteur
     */
    public void act() 
    {
        if(isPaused() == false)
        {
            move(-1);
            getImage().scale(getImage().getWidth(), getImage().getHeight()+2);
            getImage().setTransparency(getImage().getTransparency()-5);
            if(getImage().getTransparency()<20)
            {
                getWorld().removeObject(this);
            }
        }
    }
    
}
