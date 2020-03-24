import greenfoot.*;
import java.util.List;

/**
 * Bouclier de la classe Reflector
 */
public class Shield extends Reflector
{

    public Shield()
    {
        getImage().setTransparency(100);
        getImage().scale(80, 80);
    }

    public void act() 
    {
        if(isPaused() == false)
        {
            follow();
            vanish();
            protect();
            stun();
        }
    }  

    /**
     * Suit Reflector pour le protéger
     */
    public void follow()
    {
        move(-2);
    }

    /**
     * Enlève les balles qu'ils touche, protégeant ainsi son vaisseau
     */
    public void protect()
    {
        try
        {
            removeTouching(Weapon2.class);
        }catch(IllegalStateException e){}
    }

    /**
     * Crée un objet Electricity qui inflige des dégâts au joueur si cet objet est touché par un laser
     */
    public void stun()
    {
        if(this.isTouching(Weapon1.class))
        {
            if(getWorld()!= null)
            {
                List<Electricity> electricity = getWorld().getObjects(Electricity.class);
                if(electricity.size()>=1)
                {
                    getWorld().removeObjects(electricity);
                }
                Gear1 gear = (Gear1) getWorld().getObjects(Gear1.class).get(0);
                getWorld().addObject(new Electricity(), gear.getX(), gear.getY());
            }
        }
        if(Greenfoot.mouseClicked(null))
        {
            List<Electricity> electricity = getWorld().getObjects(Electricity.class);
            if(electricity.size()>=1)
            {
                getWorld().removeObjects(electricity);
            }
        }
    }
    
}
