import greenfoot.*;
import java.util.List;

/**
 * Ennemi de classe Reflector
 */
public class Reflector extends Enemies
{
    /**
     * Points de vie
     */
    private int Life = 3000;
    private int counter1;
    private int counter2;
    private int speed = 2;
    GreenfootSound dead = new GreenfootSound("EnemyDead.wav");

    public Reflector()
    {
        counter1 = -90;
        counter2 = 0;
    }

    public void act() 
    {
        if(isPaused() == false)
        {
            movements();
            counter1++;
            counter2++;
            protect();
            vanish();
            effect();
            if(getWorld()!= null)
            {
                if(!this.isTouching(Shield.class))
                {
                    takeDamageNew(Weapon1.littleDamage, Weapon1.class);
                }
            }
            takeDamageNew(Weapon2.weapon2Damage, Weapon2.class);
            takeDamageNew(Lightning.damage, Lightning.class);
        }
    }

    /**
     * Déplacement horizontal de droite à gauche quelle que soit la direction de l'acteur.
     */
    public void movements()
    {
        this.setLocation(this.getX()-speed, this.getY());
    }

    /**
     * Le rend sensibles aux armes du vaisseau
     */
    public void takeDamageNew(int hurt, Class classe)
    {
        if(getWorld() !=null)
        {
            if(this.isTouching(classe))
            {
                Life = Life - hurt;
                if(Life <=0)
                {
                    drop(50);
                    int random = Greenfoot.getRandomNumber(30);
                    int random2 = Greenfoot.getRandomNumber(100);
                    if(Greenfoot.getRandomNumber(10) == 0)
                    {
                        for(int i = 0; i<3; i++)
                        {
                            getWorld().addObject(new Web(random+120*i), this.getX(), this.getY());
                        }
                    }
                    for(int i = 0; i<3; i++)
                    {
                        getWorld().addObject(new Burst(random2+120*i), this.getX(), this.getY());
                    }
                    List<Shield> shield = getWorld().getObjectsAt(this.getX(), this.getY(), Shield.class);
                    getWorld().removeObjects(shield);
                    dead.play();
                    getWorld().removeObject(this);
                    Game.killedEnemies++;
                }
            }
        }
    }

    /**
     * 3 secondes sur 6, il est protégé contre toute attaque
     */
    public void protect()
    {
        if(counter1%180 == 0)
        {
            Shield shield = new Shield();
            getWorld().addObject(shield, this.getX(), this.getY());
        }
        if(counter2%180 == 0)
        {
            List<Shield> shield = getWorld().getObjectsAt(this.getX(), this.getY(), Shield.class);
            getWorld().removeObjects(shield);
            List<Electricity> electricity = getWorld().getObjects(Electricity.class);
            if(electricity.size()>=1)
            {
                getWorld().removeObjects(electricity);
            }
        }
    }

}
