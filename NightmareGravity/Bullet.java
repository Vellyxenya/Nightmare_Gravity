import greenfoot.*;
import java.awt.Color;

/**
 * Tirs du personnage dans le labyrinthe
 */
public class Bullet extends Obstacle
{
    GreenfootImage image = new GreenfootImage(10, 2);
    private int speed;
    GreenfootSound sound = new GreenfootSound("shoot.wav");

    /**
     * Initialise le tir
     */
    public Bullet(int rotation, int speed)
    {
        image.setColor(Color.RED);
        image.fill();
        setImage(image);
        setRotation(rotation);
        this.speed = speed;
        sound.play();
    }

    public void act() 
    {
        move(speed);
        disappear();
        kill();
    }

    /**
     * Disparaît s'il sort de la carte
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

    /**
     * Elimine les ennemis s'il les touche
     */
    public void kill()
    {
        if(getWorld()!= null)
        {
            if(isTouching(Creatures.class))
            {
                removeTouching(Creatures.class);
                getWorld().removeObject(this);
            }
        }
    }

}
