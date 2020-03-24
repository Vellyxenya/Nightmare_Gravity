import greenfoot.*;

/**
 * Bomb qui explose au contact du boss ou du joueur, leur infligeant des dégâts.
 */
public class Bomb extends NotHurting
{
    private int counter;

    public Bomb()
    {
        counter = 300;
    }

    public void act() 
    {
        if(isPaused() == false)
        {
            counter--;
            explode();
            if(counter <= 0)
            {
                getWorld().removeObject(this);
            }
        }
    }
    
    public void explode()
    {
        if(this.isTouching(Gears.class))
        {
            getWorld().addObject(new AsteroidExplosion(), this.getX(), this.getY());
            getWorld().removeObject(this);
        }
    }

}
