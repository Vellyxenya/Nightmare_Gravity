import greenfoot.*;

/**
 * Trou noir créé par le dernier Boss, infligeant des tonnes de dégâts au joueur mais
 * également létales au boss s'il s'en approche trop alors qu'ils sont chargés
 */
public class FinalTraps extends Traps
{
    /**
     * Si hurting est true, le trou noir est chargé et sera létal au boss
     */
    public boolean hurting = false;
    /**
     * Surcharge actuelle du trou noir
     */
    public int power;
    private int counter;

    public FinalTraps()
    {
        power = 0;
        counter = 0;
    }

    /**
     * Surcharge le trou noir en subissant des dégâts. Une fois surchargé se déplacement dans la
     * direction opposée à celle du joueur. S'il touche le boss, il lui inflige de lourds dégâts
     */
    public void act() 
    {
        if(isPaused() == false)
        {
            if(isTouching(Weapons.class)&& power<250)
            {
                power+= 2;
            }
            if(power == 250)
            {
                hurting = true;
                Gears gear = (Gears) getWorld().getObjects(Gears.class).get(0);
                turnTowards(gear.getX(), gear.getY());
            }
            if(hurting == true)
            {
                counter++;
                if(counter<240)
                {
                    move(-5);
                }
                else
                {
                    getWorld().removeObject(this);
                }
                vanish();
            }
        }
    }

}
