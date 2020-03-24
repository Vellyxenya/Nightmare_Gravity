import greenfoot.*;
import java.util.List;

/**
 * La partie protectrice supérieure du 8e boss
 */
public class VellocatusHigh extends Boss8Stuff
{
    /**
     * Rotation de cette partie
     */
    private int rotation;
    /**
     * Rotation de base
     */
    private int baseRotation = 45;
    public static int rotation2;
    public static int harvestRotation;
    private double ratio;
    public static int counter;

    /**
     * Initialise les variables
     */
    public VellocatusHigh()
    {
        rotation = 0;
        rotation2 = 0;
        counter = 0;
    }

    public void act() 
    {
        if(isPaused() == false)
        {
            follow();
            mvt1();
            mvt2();
            attack0();
            takeDamageNew(Weapons.class);
        }
    }

    /**
     * Animation d'apparition
     */
    private void mvt1()
    {
        if(getWorld()!= null)
        {
            if(Boss8.counter < 210 && Boss8.harvest == false)
            {
                if (getWorld().getObjects(Boss8.class).isEmpty()) return;
                Boss8 boss = (Boss8) getWorld().getObjects(Boss8.class).get(0);
                if(this.getRotation() < baseRotation)
                {
                    rotation++;
                    adjust();
                    setRotation(rotation);
                }
                if(this.getY()> boss.getY()-30)
                {
                    setLocation(boss.getX(), getY()-1);
                }
            }
        }
    }

    /**
     * s'ouvre pour collecter de l'énergie (Rend le Boss vulnérable
     */
    private void mvt2()
    {
        if(Boss8.harvest == true && Boss8Stuff.attack == false)
        {
            if(getWorld()!= null)
            {
                if (getWorld().getObjects(Boss8.class).isEmpty()) return;
                Boss8 boss = (Boss8) getWorld().getObjects(Boss8.class).get(0);
                counter++;
                if(counter == 1)
                {
                    harvestRotation = (this.getRotation());
                }
                if(rotation2<90)
                {
                    rotation2++;
                    setRotation(harvestRotation+rotation2);
                }
            }
        }
    }

    /**
     * Suit le boss dans ses mouvements
     */
    private void follow()
    {
        if(getWorld()!= null)
        {
            if(Boss8Stuff.attack == false && Boss8.harvest == false)
            {
                if (getWorld().getObjects(Boss8.class).isEmpty()) return;
                Boss8 boss = (Boss8) getWorld().getObjects(Boss8.class).get(0);
                if(Boss8.counter >= 210)
                {
                    this.setRotation(baseRotation+boss.getRotation());
                    adjust();
                    int alpha = 360-getRotation();
                    int dx = (int)(50*Math.sin(Math.toRadians(alpha)));
                    int dy = (int)(50*Math.cos(Math.toRadians(alpha)));
                    this.setLocation(boss.getX()-dx, boss.getY()-dy);
                }
            }
        }
    }

    /**
     * Attaque le joueur
     */
    private void attack0()
    {
        if(getWorld()!= null && Boss8Stuff.attack == true)
        {
            if(getWorld().getObjects(Boss8.class).isEmpty()) return;
            Gears gear = (Gears) getWorld().getObjects(Gears.class).get(0);
            if(counter == 1)
            {
                int dy = this.getY()-gear.getY();
                int dx = this.getX()-gear.getX();
                ratio = (double)dy/(double)dx;
            }
            if(gear.getX()<this.getX())this.setLocation(this.getExactX()-speed, this.getExactY()- speed*ratio);
            else if(gear.getX()>this.getX())this.setLocation(this.getExactX()+speed, this.getExactY()+ speed*ratio);
            if(counter == frequency)
            {
                follow();
                Boss8Stuff.attack = false;
                counter = 0;
            }
            else if (counter < frequency) counter++;
        }
    }

    /**
     * Si le joueur lui tire dessus, il protège le boss et inflige des dégâts aux joueurs
     */
    public void takeDamageNew(Class classe)
    {
        if(getWorld() != null)
        {
            List<Weapons> weapons = getTouchedObjects(classe);
            int length = weapons.size();
            if(length>=1)
            {
                protect();
                for(Weapons weapon : weapons)
                {
                    getWorld().removeObject(weapon);
                }
            }
        }
    }

}
