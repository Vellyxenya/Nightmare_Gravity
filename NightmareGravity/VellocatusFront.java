import greenfoot.*;
import java.util.List;

/**
 * Partie protectrice frontale du 8e Boss
 */
public class VellocatusFront extends Boss8Stuff
{
    /**
     * Points de vie de cette partie
     */
    private int health;
    private int counter;
    /**
     * Vitesse de cette partie
     */
    public static int speed = 1;

    public VellocatusFront()
    {
        health = 120;
        counter = 0;
    }

    public void act() 
    {
        if(isPaused() == false)
        {
            follow();
            takeDamage(Weapons.class);
            takeDamage();
            teleport();
            face();
            counter++;
            lighten();
            createOrbes();
            position();
        }
    }

    /**
     * Est constamment à l'avant du 8e Boss
     */
    private void follow()
    {
        if(getWorld()!= null)
        {
            if (getWorld().getObjects(Boss8.class).isEmpty()) return;
            Boss8 boss = (Boss8) getWorld().getObjects(Boss8.class).get(0);
            this.setRotation(boss.getRotation());
            adjust();
            int alpha = 360-getRotation();
            int dx = (int)(90*Math.sin(Math.toRadians(alpha+90)));
            int dy = (int)(90*Math.cos(Math.toRadians(alpha+90)));
            this.setLocation(boss.getX()-dx, boss.getY()-dy);
        }
    }

    /**
     * Protège le Boss des dégâts
     */
    public void takeDamage(Class classe)
    {
        if(getWorld() != null)
        {
            if(!getWorld().getObjects(Boss8.class).isEmpty())
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

    /**
     * Une fois le boss éliminé, cette partie survit jusqu'à ce qu'elle soit éliminée à son tour.
     */
    public void takeDamage()
    {
        if(getWorld() != null)
        {
            if(getWorld().getObjects(Boss8.class).isEmpty())
            {
                if(isTouching(Weapons.class))
                {
                    health--;
                    if(health <=0)
                    {
                        getWorld().removeObjects(getWorld().getObjects(Lightning2.class));
                        getWorld().removeObjects(getWorld().getObjects(OrbeBoss8.class));
                        getWorld().addObject(new Transition(), getWorld().getWidth()/2, getWorld().getHeight()/2);
                        Game.killedEnemies++;
                        Game.bossSpawned = 0;
                        Game.bossCreated = false;
                        Game.Level += 1;
                        Game.bossCounter = 0;
                        getWorld().removeObject(this);
                    }
                }
            }
        }
    }

    /**
     * Cet objet se téléporte instantanément s'il est touché en dehors d'une phase d'attaque.
     */
    public void teleport()
    {
        if(getWorld()!= null)
        {
            if(getWorld().getObjects(Boss8.class).isEmpty())
            {
                if(getObjectsInRange(150, Weapons.class).isEmpty() == false && getWorld().getObjects(Lightning2.class).isEmpty())
                {
                    int x = 50+Greenfoot.getRandomNumber(getWorld().getWidth()-100);
                    int y = 50+Greenfoot.getRandomNumber(getWorld().getHeight()-100);
                    setLocation(x, y);
                }
            }
        }
    }

    /**
     * Se tourne toujours vers le vaisseau du joueur
     */
    public void face()
    {
        if(getWorld()!= null)
        {
            if(getWorld().getObjects(Boss8.class).isEmpty() && getWorld().getObjects(Lightning2.class).isEmpty())
            {
                try
                {
                    Gears gear = (Gears) getWorld().getObjects(Gears.class).get(0);
                    turnTowards(gear.getX(), gear.getY());
                    this.setRotation(180+getRotation());
                }catch(IndexOutOfBoundsException e){}
            }
        }
    }

    /**
     * Envoie un éclair en direction du joueur, c'est la phase vulnérable de ce vaisseau
     */
    public void lighten()
    {
        if(getWorld()!= null)
        {
            if(getWorld().getObjects(Boss8.class).isEmpty())
            {
                if(counter%180 == 0)
                {
                    Gears gear = (Gears) getWorld().getObjects(Gears.class).get(0);
                    Lightning2 light = new Lightning2();
                    getWorld().addObject(light, this.getX(), this.getY());
                    light.turnTowards(gear.getX(), gear.getY());
                }
            }
        }
    }

    /**
     * Crée des objets qui immobilisent le vaisseau du joueur s'il les touche
     */
    public void createOrbes()
    {
        if(counter%120 == 0)
        {
            int x = Greenfoot.getRandomNumber(getWorld().getWidth());
            int y = Greenfoot.getRandomNumber(getWorld().getHeight());
            getWorld().addObject(new OrbeBoss8(), x, y);
        }
    }

    /**
     * Définit la vitesse de ce vaisseau
     */
    public void position()
    {
        if(getWorld()!= null)
        {
            if(getWorld().getObjects(Boss8.class).isEmpty())
            {
                move(-speed);
            }
        }
    }

}
