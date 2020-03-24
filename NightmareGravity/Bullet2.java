import greenfoot.*;
import java.awt.Color;

/**
 * Tirs des tourelles dans le labyrinthe
 */
public class Bullet2 extends Creatures
{
    GreenfootImage image = new GreenfootImage(10, 2);
    private int speed;

    /**
     * Initialise la balle
     */
    public Bullet2(int rotation)
    {
        image.setColor(new Color(87, 4, 50));
        image.fill();
        setImage(image);
        setRotation(rotation);
        speed = 10;
    }

    public void act() 
    {
        move(speed);
        disappear();
    }

    /**
     * Disparaît si la balle touche un mur
     */
    public void disappear()
    {
        if(getWorld() != null)
        {
            if(getWorld().getColorAt(this.getX(), this.getY()).equals(Color.BLACK))
            {
                getWorld().removeObject(this);
            }
        }
    }

}
