import greenfoot.*;
import java.util.List;

/**
 * Une fois les tirs du 9e Boss interceptés par un objet TP, ceux-ci ressortent de cet objet là dans
 * la direction du joueur.
 * Le joueur ne peut infliger des dégâts au boss qu'en tirant sur cet objet
 */
public class TP2 extends Traps
{
    private int counter;
    GreenfootImage i1 = new GreenfootImage("Boss10.png");
    GreenfootImage i2 = new GreenfootImage("Boss9_2.png");

    public TP2()
    {
        getImage().setTransparency(0);
    }
    
    /**
     * Le boss subit des dégâts quand cet objet est touché (Cet objet est invisible)
     */
    public void takeDamgeForBoss()
    {
        if(isPaused() == false)
        {
            Boss9 boss = (Boss9) getWorld().getObjects(Boss9.class).get(0);
            if(isTouching(Weapon2.class))
            {
                removeTouching(Weapon2.class);
                boss.health -= 50;
                if(counter%2 == 0)boss.setImage(i1);
                else if(counter%2 == 1)boss.setImage(i2);
                if(boss.health <=0)
                {
                    getWorld().addObject(new Revenger(30000, new GreenfootImage("Device.png")), boss.getX(), boss.getY());
                    getWorld().addObject(new Informations2("Your weapons are useless against him, the only way of beating him is to try to propulse the gravitational orbs towards him by shooting them! It's the last one, good luck. "), getWorld().getWidth()/2, getWorld().getHeight()/2);
                    getWorld().addObject(new PressEnter(), getWorld().getWidth()/2, 460);
                    getWorld().removeObjects(getWorld().getObjects(Weapon1.class));
                    Game.bossCreated = true;
                    getWorld().removeObject(boss);
                    List<BossStuff> objects = getWorld().getObjects(BossStuff.class);
                    getWorld().removeObjects(objects);
                    getWorld().removeObject(this);
                    Game.killedEnemies++;
                    Game.Level++;
                    return;
                }
            }
            else
            {
                boss.setImage(i1);
            }
        }
    }

    /**
     * Récolte les tirs de l'objet TP et les génère à partir de sa position actuelle
     */
    public void act() 
    {
        if(isPaused() == false)
        {
            if(TP.number>0)
            {
                Shoots s = new Shoots();
                getWorld().addObject(s, getX(), getY());
                s.setRotation(this.getRotation());
                s.setImage("Hawk.png");
            }
            takeDamgeForBoss();
            counter++;
        }
    }

}
