import greenfoot.*;

/**
 * Mur bloquant les tirs ennemis. Nécessite la compétence ShieldSpell
 */
public class Block extends SmoothMover
{

    /**
     * Bloque les armes ennemies
     */
    public void act() 
    {
        if(touch(Weapons.class))
        {
            removeTouching(Weapons.class);
        }
        Gears gear = (Gears) getWorld().getObjects(Gears.class).get(0);
        setLocation(gear.getX(), gear.getY());
    }
}
