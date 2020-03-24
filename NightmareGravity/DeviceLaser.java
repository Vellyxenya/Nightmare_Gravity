import greenfoot.*;
import java.awt.Color;

/**
 * image mininuscule de la taille mais qui forme la ligne du laser de la classe Devices
 */
public class DeviceLaser extends SmoothMover
{
    /**
     * Dégâts infligés au vaisseau du joueur
     */
    public static final int damage = 50;

    public void act() 
    {
        turnRed();
        vanish();
    }

    /**
     * Devient Rouge juste avant d'infliger des dégâts
     */
    public void turnRed()
    {
        if(Game.aimingCounter>=100 && Game.aimingCounter <150)
        {
            setImage("redLaser.png");
        }
        else if(Game.aimingCounter >= 150)
        {
            setImage("redLaserBig.png");
        }
    }
    
}
