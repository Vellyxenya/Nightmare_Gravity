import greenfoot.*;
import java.awt.Color;

/**
 * Portes permettant de guider l'araignée pour traquer le personnage.
 */
public class Opened extends Obstacle
{
    GreenfootImage image;
    /**
     * Retourne true si le personnage est passé par là
     */
    public boolean locked = false;
    public int direction;
    public int direction2;
    public boolean hurt = false;
    
    /**
     * Construit la porte avec la rotation et la largeur
     */
    public Opened(int rotation, int width)
    {
        image = new GreenfootImage(width, 3);
        image.setColor(new Color(0, 0, 0, 0));
        image.fill();
        setImage(image);
        setRotation(rotation);
    }

    public void act() 
    {
        switchDoors();
        changeImage();
        pass();
    }

    /**
     * S'active si le personnage passe par là
     */
    public void switchDoors()
    {
        Roamer roamer = (Roamer) getWorld().getObjects(Roamer.class).get(0);
        if(this.isTouching(Roamer.class) && hurt == false)
        {
            if(this.getRotation() == 90)
            {
                if(roamer.getX()-this.getX()>0)
                {
                    direction = 1;
                }
                else if(roamer.getX()-this.getX()<0)
                {
                    direction = 0;
                }
            }
            else if(this.getRotation() == 0)
            {
                if(roamer.getY()-this.getY()>0)
                {
                    direction = 1;
                }
                else if(roamer.getY()-this.getY()<0)
                {
                    direction = 0;
                }
            }
            hurt = true;
        }
    }

    /**
     * Prend en compte le passage en sens inverse et désactive la porte
     */
    public void pass()
    {
        Roamer roamer = (Roamer) getWorld().getObjects(Roamer.class).get(0);
        if(!isTouching(Roamer.class) && hurt == true)
        {
            if(this.getRotation() == 90)
            {
                if(roamer.getX()-this.getX()>0)
                {
                    direction2 = 1;
                }
                else if(roamer.getX()-this.getX()<0)
                {
                    direction2 = 0;
                }
            }
            else if(this.getRotation() == 0)
            {
                if(roamer.getY()-this.getY()>0)
                {
                    direction2 = 1;
                }
                else if(roamer.getY()-this.getY()<0)
                {
                    direction2 = 0;
                }
            }
            if(direction2 != direction)
            {
                if(locked == false)
                {
                    locked = true;
                }
                else if(locked == true)
                {
                    locked = false;
                }
                hurt = false;
            }
            else if(direction2 == direction)
            {
                hurt = false;
            }
        }
    }

    /**
     * Place un objet Ariane si active, l'enlève dans le cas contraire
     */
    public void changeImage()
    {
        if(locked == true)
        {
            image.setColor(Color.ORANGE);
            image.fill();
            image.clear();
            setImage(image);
            if(getWorld().getObjectsAt(this.getX(), this.getY(), Ariane.class).isEmpty())
            {
                getWorld().addObject(new Ariane(), this.getX(), this.getY());
            }
        }
        else if(locked == false)
        {
            image.setColor(Color.GREEN);
            image.fill();
            image.clear();
            setImage(image);
            if(!getWorld().getObjectsAt(this.getX(), this.getY(), Ariane.class).isEmpty())
            {
                removeTouching(Ariane.class);
            }
        }
    }

}
