import greenfoot.*;
import java.util.List;
import java.awt.Color;

/**
 * A variation of an actor that maintains a precise location (using doubles for the co-ordinates
 * instead of ints).  This allows small precise movements (e.g. movements of 1 pixel or less)
 * that do not lose precision.
 * 
 * @author Poul Henriksen
 * @author Michael Kolling
 * @author Neil Brown
 * 
 * @version 3.0
 */
public abstract class SmoothMover extends Collision // Contient également des Méthodes Personnelles marquées d'un *, elles sont à la fin.
{
    private Vector movement;
    private double exactX;
    private double exactY;
    public static final double GRAVITY = 5.5; //*

    public SmoothMover()
    {
        this(new Vector());
    }

    public SmoothMover(Vector movement)
    {
        this.movement = movement;
    }

    /**
     * Move in the current movement direction.
     */
    public void move() 
    {
        exactX = exactX + movement.getX();
        exactY = exactY + movement.getY();
        super.setLocation((int) exactX, (int) exactY);
    }

    /**
     * Accelerate the speed of this mover by the given factor. (Factors less than 1 will
     * decelerate.) The direction remains unchanged.
     */
    public void accelerate(double factor)
    {
        movement.scale(factor);
        if (movement.getLength() < 0.15) {
            movement.setNeutral();
        }
    }

    /**
     * Return the speed of this actor.
     */
    public double getSpeed()
    {
        return movement.getLength();
    }

    /**
     * Increase the speed with the given vector.
     */
    public void addForce(Vector force) 
    {
        movement.add(force);
    }

    /**
     * Move forward by the specified distance.
     * (Overrides the method in Actor).
     */
    @Override
    public void move(int distance)
    {
        move((double)distance);
    }

    /**
     * Move forward by the specified exact distance.
     */
    public void move(double distance)
    {
        double radians = Math.toRadians(getRotation());
        double dx = Math.cos(radians) * distance;
        double dy = Math.sin(radians) * distance;
        setLocation(exactX + dx, exactY + dy);
    }

    /**
     * Set the location using exact coordinates.
     */
    public void setLocation(double x, double y) 
    {
        exactX = x;
        exactY = y;
        super.setLocation((int) (x + 0.5), (int) (y + 0.5));
    }

    /**
     * Set the location using integer coordinates.
     * (Overrides the method in Actor.)
     */
    @Override
    public void setLocation(int x, int y) 
    {
        exactX = x;
        exactY = y;
        super.setLocation(x, y);
    }

    /**
     * Return the exact x-coordinate (as a double).
     */
    public double getExactX() 
    {
        return exactX;
    }

    /**
     * Return the exact y-coordinate (as a double).
     */
    public double getExactY() 
    {
        return exactY;
    }
    
    /**
     * Retourne si la souris est sur cet objet ou non.
     */
    public boolean mouseOn()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse != null)
        {
            if( (mouse.getX() < (getX()+ this.getImage().getWidth()/2)) &&
            (mouse.getX() > (getX()- this.getImage().getWidth()/2)) &&
            (mouse.getY() < (getY()+ this.getImage().getHeight()/2)) &&
            (mouse.getY() > (getY()- this.getImage().getHeight()/2)))
            {
                return true;
            }
        }
        return false;
    }

    public boolean getMouseOn() //*
    {
        return mouseOn();
    }
    
    /**
     * Retourne si le jeu est en pause ou non.
     */
    public boolean isPaused()
    {
        if (!getWorld().getObjects(Pause.class).isEmpty() || !getWorld().getObjects(Informations2.class).isEmpty())
        {
            return true;
        }
        return false;
    }
    
    /**
     * Méthode isAtEdge implémentée par moi-même, similaire à celle de Greenfoot mais absente de la version 2.3
     */
    public boolean isAtEdgePerso()
    {
        if(getWorld() != null)
        {
            if(this.getX()== 0 || this.getX() >= getWorld().getWidth()-1||
            this.getY()== 0 || this.getY() >= getWorld().getHeight()-1)
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Si tel acteur est au bord du monde, il disparaît automatiquement
     */
    public void vanish()
    {
        if(getWorld() != null)
        {
            if(this.isAtEdgePerso())
            {
                getWorld().removeObject(this);
            }
        }
    }
    
    /**
     * remplace la force vectorielle actuelle (les forces ne se cumulent pas avec cette méthode)
     */
    public void myAddForce(Vector force)
    {
        movement.myAdd(force);
    }
    
    /**
     * Méthode faisant rebondir les objets contre les bords du monde.
     */
    public void bounceAtEdge(int speed, int counter)
    {
        if(counter %1 == 0)
        {
            if(getWorld() != null)
            {
                if(this.getX() <= this.getImage().getWidth()/2)
                {
                    this.setRotation(360-(this.getRotation()-180));
                    adjust();
                    //move(speed);
                }
                if(this.getY() <= this.getImage().getWidth()/2)
                {
                    this.setRotation(180+(360-(this.getRotation()-180)));
                    adjust();
                    //move(speed);
                }
                if(this.getX()>= getWorld().getWidth()-this.getImage().getWidth()/2)
                {
                    this.setRotation(180-this.getRotation());
                    adjust();
                    //move(speed);
                }
                if(this.getY()>= getWorld().getHeight()-this.getImage().getWidth()/2)
                {
                    this.setRotation(360-this.getRotation());
                    adjust();
                    //move(speed);
                }
            }
        }
    }
    
    /**
     * Méthode utilisée dans bounceAtEdge pour pallier le problèmes des rotations qui n'appartient pas à l'intervalle[0;359]
     */
    public void adjust()
    {
        if(this.getRotation()>=360)
        {
            this.setRotation(this.getRotation()-360);
        }
        else if(this.getRotation()<0)
        {
            this.setRotation(360+this.getRotation());
        }
    }
    
    /**
     * Méthode donnant un mouvement vibratoir réaliste
     */
    public void stun()
    {
        if(getWorld()!= null)
        {
            if(this.isTouching(Gears.class))
            {
                Gears gear = (Gears) getWorld().getObjects(Gears.class).get(0);
                int random = Greenfoot.getRandomNumber(4);
                switch(random)
                {
                    case 0:
                    gear.setLocation(gear.getX()+3, gear.getY());
                    break;

                    case 1:
                    gear.setLocation(gear.getX()-3, gear.getY());
                    break;

                    case 2:
                    gear.setLocation(gear.getX(), gear.getY()+3);
                    break;

                    case 3:
                    gear.setLocation(gear.getX(), gear.getY()-3);
                    break;
                }
            }
        }
    }
    
}
