import greenfoot.*;
import java.util.List;
import java.awt.Color;

/**
 * Petit carré qui constitue le laser du vaisseau 1.
 */
public class Weapon1 extends Weapons
{
    /**
     * Dégâts infligés par le laser
     */
    public static int littleDamage = 60;
    GreenfootImage image = new GreenfootImage(2, 2);
    
    /**
     * Initialise le carré (Chaque carré est détruit et recréé à chaque act pour la fluidité du laser)
     */
    public Weapon1()
    {
        
    }

    public void act() 
    {
        detectShield();
    }
    
    /**
     * Si le laser du vaisseau 1 touche le boucler de la classe Reflector, le joueur subit des dégâts
     */
    public void detectShield()
    {
        if(this.isTouching(Shield.class))
        {
            Gear1.stunned = true;
        }
    }
    
}
