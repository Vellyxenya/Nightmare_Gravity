import greenfoot.*;

/**
 * Objet qui émet des particules létales durant le boss 8.
 */
public class Crater extends Obstacle
{
    private int duration;
    private int counter;
    private int random;

    public Crater()
    {
        duration = 180;
        counter = 0;
        random =  Greenfoot.getRandomNumber(360);
    }

    public void act() 
    {
        if(isPaused() == false)
        {
            duration--;
            explode();
        }
    }

    /**
     * Tourne et émet des particules en forme de spirale.
     */
    public void explode()
    {
        if(duration <= 0 && duration >= -240)
        { 
            if(counter < 360)
            {
                Particles particle = new Particles();
                getWorld().addObject(particle, this.getX(), this.getY());
                particle.setRotation(random+counter);
                adjust();
                counter++;
            }
            else if(counter >= 360)
            {
                counter = 0;
            }
        }
        else if(duration < -240)
        {
            getWorld().removeObject(this);
        }
    }

}
