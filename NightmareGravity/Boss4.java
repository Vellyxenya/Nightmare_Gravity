import greenfoot.*;
import java.util.List;

/**
 * 4e Boss: Crée des failles et les propulse sur le joueur
 * Attaquer ce boss est létal s'il y a des objets de type Hive sur scène
 */
public class Boss4 extends Bosses
{
    /**
     * Vie actuelle du boss
     */
    public static int health;
    /**
     * Vie maximale
     */
    public static final int MAX_HEALTH = 40000;
    private GreenfootImage baseImage;
    private int counter;
    /**
     * Renvoie si le boss vient d'être attaqué
     */
    public static boolean vengeance;
    /**
     * Durée d'attaque des objets de la classe Hive
     */
    private int exhaust;

    public Boss4()
    {
        health = MAX_HEALTH;
        counter = 0;
        vengeance = false;
        exhaust = 0;
        baseImage = getImage();
        setImage(2-baseImage.getWidth()%2, baseImage);
    }

    /**
     * Comportement du boss
     */
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
                createRifts();
                createHives();
            }catch(NullPointerException e){}
            exhaust++;
            exhaust();
        }
        movements(610);
        int imageWidth = getImage().getWidth();
        if (imageWidth < baseImage.getWidth())
        {
            setImage(imageWidth+2, baseImage);
        }
    }

    /**
     * Subit des dégâts s'il touche des armes du joueur
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
                    if(!getWorld().getObjects(Hive.class).isEmpty())
                    {
                        vengeance = true;
                    }
                    exhaust = -180;
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
     * Crée des failles létales sur scène et les propulse dans toutes les directions
     */
    private void createRifts()
    {
        if(counter% 30 == 0)
        {
            int rotation = Greenfoot.getRandomNumber(360);
            getWorld().addObject(new Fault(rotation), this.getX(), this.getY());
            getWorld().addObject(new Rift1(rotation), this.getX(), this.getY());;
        }
        if((20+counter)% 40 == 0)
        {
            int rotation = Greenfoot.getRandomNumber(360);
            getWorld().addObject(new Fault(rotation), Greenfoot.getRandomNumber(getWorld().getWidth()), Greenfoot.getRandomNumber(getWorld().getHeight()));
        }
    }

    /**
     * Crée des nids de monstres qui attaquent si le boss est attaqué
     */
    public void createHives()
    {
        if(counter%180 == 0)
        {
            getWorld().addObject(new Hive(), Greenfoot.getRandomNumber(getWorld().getWidth()), Greenfoot.getRandomNumber(getWorld().getHeight()));
        }
    }

    /**
     * Compteur pour la durée de l'attaque
     */
    private void exhaust()
    {
        if(exhaust<0)
        {
            exhaust++;
        }
        if(exhaust == 0)
        {
            vengeance = false;
        }
    }
    
}
