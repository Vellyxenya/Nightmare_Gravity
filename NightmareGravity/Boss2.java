import greenfoot.*;
import java.util.List;

/**
 * 2e Boss: S'entoure d'orbes d'énergie et les projette dans toutes les directions.
 */
public class Boss2 extends Bosses
{
    /**
     * Vie actuelle
     */
    public static int health;
    /**
     * Maximum de points de vie
     */
    public static final int MAX_HEALTH = 70000;
    public static int counter;
    /**
     * Peut commencer à tirer
     */
    public static boolean readyToGo;
    private int count;
    private int count2;
    public GreenfootImage baseImage;
    
    public Boss2()
    {
        health = MAX_HEALTH;
        counter = -150;
        readyToGo = false;
        count = 0;

        baseImage = getImage();
        setImage(2-baseImage.getWidth()%2, baseImage);
    }

    public void act() 
    {
        if(isPaused() == false)
        {
            takeDamageNew((Weapon2.weapon2Damage/10), Weapon2.class);
            takeDamageNew(Lightning.damage, Lightning.class);
            try
            {
                if(getWorld().getObjects(Informations2.class).isEmpty())
                {
                    counter++;
                    drawHealthBar(health, MAX_HEALTH);
                }
            }catch(NullPointerException e){}
            fire();
            shoot();
            readyAgain();
        }
        movements(580);
        int imageWidth = getImage().getWidth();
        if (imageWidth < baseImage.getWidth())
        {
            setImage(imageWidth+2, baseImage);
        }
    }

    /**
     * Sensible aux dégâts.
     */
    public void takeDamageNew(int hurt, Class classe)
    {
        if(getWorld() != null)
        {
            List<Weapons> weapons = getTouchedObjects(classe);
            int length = weapons.size();
            if(length>=1)
            {
                for(Weapons weapon : weapons)
                {
                    getWorld().removeObject(weapon);
                    health = health - hurt;
                    if(health <=0)
                    {
                        getWorld().addObject(new Transition(), getWorld().getWidth()/2, getWorld().getHeight()/2);
                        getWorld().removeObject(this);
                        Game.killedEnemies++;
                        Game.bossSpawned = 0;
                        Game.bossCreated = false;
                        Game.Level += 1;
                        Game.bossCounter = 0;
                        return;
                    }
                }
            }
        }
    }

    /**
     * S'entoure d'orbes puis les projette
     */
    private void fire()
    {
        if(counter%1 == 0 && counter>=50 && counter<100)
        {
            if(this != null)
            {
                if(getWorld() != null)
                {
                    Orbe orbe = new Orbe();
                    orbe.setRotation(70+Greenfoot.getRandomNumber(220));
                    getWorld().addObject(orbe, this.getX()-125, this.getY()-32);
                }
            }
        }
    }

    /**
     * Projette les orbes dans toutes les directions
     */
    public void shoot()
    {
        if(counter == 150)
        {
            Aimer aimer = new Aimer();
            getWorld().addObject(aimer, 450, 250);
        }
        if(counter == 160)
        {
            if(getWorld()!= null)
            {
                Actor aimer = (Actor)getWorld().getObjects(Aimer.class).get(0);
                List<Orbe> orbes = getWorld().getObjects(Orbe.class);
                int length = orbes.size();
                if(length>=1)
                {
                    for(Orbe orbe : orbes)
                    {
                        orbe.turnTowards(aimer.getX(), aimer.getY());
                        readyToGo = true;
                    }
                }
            }
        }
    }

    /**
     * Le cycle recommence
     */
    private void readyAgain()
    {
        if(readyToGo == true)
        {
            count++;
            if(count>=100)
            {
                readyToGo = false;
                count = 0;
                return;
            }
            count2++;
            if(count2>=50)
            {
                count2 = 0;
                counter = 30;
            }
        }
    }
    
}
