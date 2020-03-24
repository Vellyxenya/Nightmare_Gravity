import greenfoot.*;
import java.util.List;

/**
 * 3e Boss: Génère des cercles qui infligent des dégâts si touchés par le joueur ou une de ses armes
 */
public class Boss3 extends Bosses
{
    /**
     * Vie actuelle du boss
     */
    public static int health;
    /**
     * Maximum de vie du Boss
     */
    public static final int MAX_HEALTH = 20000;
    private GreenfootImage baseImage;
    private int counter;
    private int counter2;

    public Boss3()
    {
        health = MAX_HEALTH;
        counter = 0;
        counter2 = 0;
        baseImage = getImage();
        setImage(2-baseImage.getWidth()%2, baseImage);
    }

    /**
     * Comportement du Boss et réglage des phases d'attaque
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
                    counter2++;
                    drawHealthBar(health, MAX_HEALTH);
                }
            }catch(NullPointerException e){}
            jail();
            createCircles();
        }
        movements(600);
        int imageWidth = getImage().getWidth();
        if (imageWidth < baseImage.getWidth())
        {
            setImage(imageWidth+2, baseImage);
        }
    }    

    /**
     * Subit des dégâts si touché par les armes du joueur
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
                        if(getWorld() != null)
                        {
                            List<Circle> circles = getWorld().getObjects(Circle.class);
                            getWorld().removeObjects(circles);
                        }
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
     * Crée un cercle autour du joueur
     */
    public void jail()
    {
        if(counter%420 == 0)
        {
            Gears gear = (Gears) getWorld().getObjects(Gears.class).get(0);
            getWorld().addObject(new Circle(300), gear.getX(), gear.getY());
        }
    }

    /**
     * Crée des cercles aléatoires
     */
    public void createCircles()
    {
        if(counter2 >120 && counter2%30 == 0)
        {
            getWorld().addObject(new Circle(), 91+Greenfoot.getRandomNumber(567), 91+Greenfoot.getRandomNumber(375));
        }
    }
    
}
