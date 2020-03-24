import greenfoot.*;
import java.awt.Color;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

/**
 * Figure apparaissant à certains moments de la partie pour donner des instructions au joueur
 */
public class Robot extends Interface
{
    private int counter = 0;
    private int count;
    /**
     * Durée de passage d'une image couleur à une image en niveaux de gris
     */
    private final int GRAY_DURATION = 90;
    GreenfootImage image1 = new GreenfootImage("HappyRobot.png");
    GreenfootImage image2 = new GreenfootImage("SadRobot.png");
    GreenfootImage image3 = new GreenfootImage("SurprisedRobot.png");
    GreenfootImage image4 = new GreenfootImage("SurprisedRobot2.png");

    /**
     * Définit quelle image prend le robot pour telle ou telle action
     */
    public void act() 
    {
        count++;
        if(Gears.Life < 200 && !getWorld().getObjects(Gears.class).isEmpty())
        {
            if(count%2 == 0) setImage(image3);
            else setImage(image4);
        }
        else if(!getWorld().getObjects(Informations2.class).isEmpty())
        {
            setImage(image1);
        }
        else if(getWorld().getObjects(Gears.class).isEmpty() && getWorld().getObjects(Screenshot.class).isEmpty())
        {
            setImage(image2);
            Screenshot ss = new Screenshot(getWorld().getBackground(), getWorld().getObjects(null));
            getWorld().addObject(ss, getWorld().getWidth()/2, getWorld().getHeight()/2);
            List<Actor> actors = getWorld().getObjects(Actor.class);
            for(Actor actor : actors)
            {
                if(actor != this && actor != ss)
                {
                    getWorld().removeObject(actor);
                }
            }
            getWorld().removeObject(this);
        }
        else
        {
            setImage("TransparentButtons.png");
        }
    }

}
