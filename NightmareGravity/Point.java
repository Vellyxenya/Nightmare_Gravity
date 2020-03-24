import greenfoot.*;
import java.awt.Color;

/**
 * Point défilant sur les classes Informations (Effet visuel).
 */
public class Point extends Interface
{
    /**
     * définit si le point bouge horizontalement ou verticalement
     */
    private int number;
    /**
     * Durée avant que le point ne disparaisse
     */
    private int duration;
    /**
     * Image du point
     */
    GreenfootImage image = new GreenfootImage(4, 4);

    /**
     * Initialise un point vert.
     * @param number 0 fait déplacer le point horizontalement; 1 fait déplacer le point verticalement
     * @param duration
     */
    public Point(int number, int duration)
    {
        this.number = number;
        this.duration = duration-2;
        image.setColor(Color.GREEN);
        image.fillOval(0, 0, 4, 4);
        setImage(image);
    }

    /**
     * Définit les événements auxquels réagit le point
     */
    public void act() 
    {
        if(getWorld()!= null)
        {
            if(getWorld().getObjects(Informations.class).isEmpty()) return;
            else
            {
                Informations info = (Informations)getWorld().getObjects(Informations.class).get(0);
                if(number == 0)
                {
                    move(1);
                }
                else if(number == 1)
                {
                    setLocation(getX(), getY()+1);
                }
                duration--;
                if(duration<= 0 || this.getX()>info.getX()+info.getImage().getWidth()/2 || this.getY()>info.getY()+info.getImage().getHeight()/2)
                {
                    getWorld().removeObject(this);
                }
            }
        }
    }

}
