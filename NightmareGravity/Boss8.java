import greenfoot.*;
import java.util.List;
import java.awt.Color;

/**
 * 8e Boss: Composé du corps principal, ci-dessous + des objets de la classe Boss8Stuff,
 * qui sont des objets de protection.
 */
public class Boss8 extends Bosses
{
    /**
     * Points de vie actuels du boss
     */
    public static int health;
    /**
     * Points de vie max
     */
    public static final int MAX_HEALTH = 20000;
    public static int counter;
    private GreenfootImage baseImage;
    private int attackTimer;
    private int speed = 1;
    /**
     * True s'il est vulnérable
     */
    public static boolean harvest;
    /**
     * Temps de vulnérabilité
     */
    private int harvestCounter;

    public Boss8()
    {
        counter = 0;
        health = MAX_HEALTH;
        attackTimer = 0;
        harvest = false;
        harvestCounter = 0;
        baseImage = getImage();
        setImage(2-baseImage.getWidth()%2, baseImage);
    }

    public void act() 
    {
        if(isPaused() == false)
        {
            attackTimer++;
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
            spread();
            movement();
            attack();
            harvest();
            begin();
        }
        movements(650);
        int imageWidth = getImage().getWidth();
        if (imageWidth < baseImage.getWidth())
        {
            setImage(imageWidth+2, baseImage);
        }
    }

    /**
     * Sensible aux dégâts. Si la vie tombe à zéro celui-ci disparaît et seule sa partie frontale subsiste
     * Il faut alors l'éliminer à son tour
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
                        List<BossStuff> objects = getWorld().getObjects(BossStuff.class);
                        getWorld().removeObjects(objects);
                        List<Obstacle> obstacles = getWorld().getObjects(Obstacle.class);
                        getWorld().removeObjects(obstacles);
                        List<VellocatusHigh> objects_1 = getWorld().getObjects(VellocatusHigh.class);
                        getWorld().removeObjects(objects_1);
                        List<VellocatusLow> objects_2 = getWorld().getObjects(VellocatusLow.class);
                        getWorld().removeObjects(objects_2);
                        getWorld().removeObject(this);
                        return;
                    }
                }
            }
        }
    }

    /**
     * Crée ses protections à son apparition
     */
    private void spread()
    {
        if(counter == 120)
        {
            getWorld().addObject(new VellocatusHigh(), this.getX(), this.getY());
            getWorld().addObject(new VellocatusLow(), this.getX(), this.getY());
            getWorld().addObject(new VellocatusFront(), this.getX(), this.getY());
        }
    }

    /**
     * Mouvement du boss
     */
    public void movement()
    {
        if(counter >= 210)
        {
            if(Boss8Stuff.attack == false && harvest == false)
            {
                if(getWorld()!= null)
                {
                    Gears gear = (Gears) getWorld().getObjects(Gears.class).get(0);
                    turnTowards(gear.getX(), gear.getY());
                    this.setRotation(180+getRotation());
                    move(-speed);
                }
            }
        }
    }

    /**
     * Ordonne à ses protections d'attaquer
     */
    public void attack()
    {
        if(attackTimer == 200)
        {
            if(getWorld()!= null)
            {
                if(!this.getObjectsInRange(300, Gears.class).isEmpty())
                {
                    Boss8Stuff.attack = true;
                    attackTimer = 0;
                }
            }
        }
    }

    /**
     * Collecte des rayons d'énergie pour ensuite les expulser, phase vulnérable
     */
    public void harvest()
    {
        if(counter%300 == 0 && Boss8Stuff.attack == false)
        {
            harvest = true;
        }
    }

    /**
     * Crée une pluie de particules après avoir eu assez d'énergie
     */
    public void begin()
    {
        if(harvest == true)
        {
            if(getWorld()!= null)
            {
                if(harvestCounter<120)
                {
                    int random = Greenfoot.getRandomNumber(4);
                    switch(random)
                    {
                        case 0:
                        getWorld().addObject(new Particles(this.getX(), this.getY()), 0,
                        Greenfoot.getRandomNumber(getWorld().getHeight()));
                        harvestCounter++;
                        break;

                        case 1:
                        getWorld().addObject(new Particles(this.getX(), this.getY()),
                        getWorld().getWidth(), Greenfoot.getRandomNumber(getWorld().getHeight()));
                        harvestCounter++;
                        break;

                        case 2:
                        getWorld().addObject(new Particles(this.getX(), this.getY()),
                        Greenfoot.getRandomNumber(getWorld().getWidth()), 0);
                        harvestCounter++;
                        break;

                        case 3:
                        getWorld().addObject(new Particles(this.getX(), this.getY()),
                        Greenfoot.getRandomNumber(getWorld().getWidth()), getWorld().getHeight());
                        harvestCounter++;
                        break;
                    }
                }
                else
                {
                    harvestCounter = 0;
                    harvest = false;
                    VellocatusHigh.counter = 0;
                    VellocatusHigh.rotation2 = 0;
                    VellocatusLow.rotation2 = 0;
                    for(int i = 0; i<3; i++)
                    {
                        getWorld().addObject(new Crater(), this.getX(), this.getY());
                    }
                }
            }
        }
    }

}
