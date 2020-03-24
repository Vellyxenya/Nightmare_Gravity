import greenfoot.*;
import java.awt.Color;

/**
 * Onde sonar de Spider qui lui permet de détecter le personnage.
 */
public class Beam extends Obstacle
{

    public Beam(int rotation)
    {
        setRotation(rotation);
        getImage().setTransparency(0);
    }

    public void act() 
    {
        move(4);
        debug();
    }

}
