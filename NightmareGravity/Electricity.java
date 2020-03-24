import greenfoot.*;

/**
 * Inflige des dégâts au vaisseau 1 lorsqu'il touche le bouclier des objets de la classe Reflector.
 */
public class Electricity extends SmoothMover
{
    /**
     * Dégâts infligés
     */
    public static int damage = 2;
    
    public void act() 
    {
        this.setRotation(this.getRotation()+10);
        Gears gear = (Gears) getWorld().getObjects(Gears.class).get(0);
        this.setLocation(gear.getX(), gear.getY());
        if(getWorld().getObjects(Reflector.class).isEmpty())
        {
            getWorld().removeObject(this);
        }
    }
    
}
