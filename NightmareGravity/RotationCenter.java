import greenfoot.*;

/**
 * Objet qui donne un mouvement spécial au Boss7
 */
public class RotationCenter extends NotHurting
{

    public void act() 
    {
        movement1();
    }
    
    /**
     * Rotation de cet objet qui donne à son tour une orientation bizarre mais régulière du 7e Boss
     */
    public void movement1()
    {
        move(2);
        turn(4);
    }

}
