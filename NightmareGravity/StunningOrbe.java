import greenfoot.*;

/**
 * Orbe créé par les objets de la classe Stunner, s'il touche le vaisseau, celui-ci subit des secousses
 * et un peu de dégâts.
 */
public class StunningOrbe extends EW
{
    public static int damage;
    private int counter;

    public StunningOrbe(int rotation)
    {
        counter = 0;
        setRotation(rotation);
    }

    public void act() 
    {
        if(isPaused() == false)
        {
            counter++;
            move(15);
            stick();
            dealDamage();
            disappear();
            stun();
            vanish();
        }
    }

    /**
     * Se colle au vaisseau du joueur s'il le touche
     */
    public void stick()
    {
        if(getWorld()!= null)
        {
            if(this.isTouching(Gears.class))
            {
                Gears gear = (Gears) getWorld().getObjects(Gears.class).get(0);
                this.setLocation(gear.getX(), gear.getY());
            }
        }
    }

    /**
     * Inflige au joueur des dégât peu élevés
     */
    public void dealDamage()
    {
        if(counter%10 == 0)
        {
            damage = 5;
        }
    }

    /**
     * Disparaît après deux secondes et libère le vaisseau du joueur
     */
    public void disappear()
    {
        if(counter>=120)
        {
            getWorld().removeObject(this);
        }
    }
    
}
