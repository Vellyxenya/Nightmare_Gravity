import greenfoot.*;
import java.util.List;

/**
 * Boss qui crée des astéroides servant à protéger le joueur... ou le tuer, plus le joueur aura d'astéroïdes, plus il sera protégé.
 */
public class Boss5 extends Bosses
{
    /**
     * Vie actuelle du boss
     */
    public static int health;
    /**
     * Maximum de points de vie
     */
    public static final int MAX_HEALTH = 8000;
    private GreenfootImage baseImage;
    public static int counter;
    //Indique le nombre d'astéroïdes autour du vaisseau.
    public static boolean protection0 = false;
    public static boolean protection1 = false;
    public static boolean protection2 = false;
    public static boolean protection3 = false;
    public static boolean protection4 = false;
    public static boolean protection5 = false;
    public static int protectionCounter = 0;
    /**
     * Compteur pour le lancement de gaz mortel
     */
    private int launchGaz = 780;

    public Boss5()
    {
        health = MAX_HEALTH;
        counter = 0;
        baseImage = getImage();
        setImage(2-baseImage.getWidth()%2, baseImage);
    }

    public void act() 
    {
        if(isPaused() == false)
        {
            if(counter%120 == 0)
            {
                getWorld().addObject(new Heal(), 300, 300);
            }
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
            createAshes();
            countProtection();
            createGaz();
            createGaz2();
            createAsteroids();
        }
        movements(590);
        int imageWidth = getImage().getWidth();
        if (imageWidth < baseImage.getWidth())
        {
            setImage(imageWidth+2, baseImage);
        }
    }

    /**
     * Sensible aux dégâts
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
                    if(protection0 == true && protection1 == true && protection2 == true && protection3 == true && protection4 == true && protection5 == true)
                    {
                        health = health - hurt;
                    }
                    if(health <=0)
                    {
                        if(getWorld()!= null)
                        {
                            List<Gaz2> gaz = getWorld().getObjects(Gaz2.class);
                            int lengthgaz = gaz.size();
                            if(lengthgaz>=1)
                            {
                                getWorld().removeObjects(gaz);
                            }
                            List<Asteroid> asteroids = getWorld().getObjects(Asteroid.class);
                            int lengthast = asteroids.size();
                            if(lengthast>=1)
                            {
                                getWorld().removeObjects(asteroids);
                            }
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
     * Lance des astéroïdes en trajectoire courbe
     */
    public void createAshes()
    {
        if(counter%60 == 0)
        {
            getWorld().addObject(new Ash(), this.getX(), this.getY());
        }
    }

    /**
     * Génère un gaz infligeant des dégâts au joueur
     */
    public void createGaz()
    {
        if(counter % launchGaz == 0)
        {
            getWorld().addObject(new Gaz(), getWorld().getWidth()/2, getWorld().getHeight()/2);
        }
    }

    /**
     * Simple effet visuel
     */
    public void createGaz2()
    {
        if(counter==1)
        {
            //getWorld().addObject(new Gaz2(), getWorld().getWidth()/2, getWorld().getHeight()/2);
        }
    }

    /**
     * Génère des astéroïdes utiles au joueur
     */
    public void createAsteroids()
    {
        if(counter >= 60 && counter < 600 && counter% 30 == 0)
        {
            getWorld().addObject(new Asteroid(), getWorld().getWidth()/2, getWorld().getHeight()/2);
        }
    }

    /**
     * Détecte le nombre d'astéroïdes qui protègent le joueur
     */
    public void countProtection()
    {
        if(counter % launchGaz-1 == 0)
        {
            if(protection0 == true) protectionCounter ++;
            if(protection1 == true) protectionCounter += 1;
            if(protection2 == true) protectionCounter += 1;
            if(protection3 == true) protectionCounter += 1;
            if(protection4 == true) protectionCounter += 1;
            if(protection5 == true) protectionCounter += 1;
        }
    }

    /**
     * Retourne le nombre de protections
     */
    public int getCounter()
    {
        return protectionCounter;
    }

}
