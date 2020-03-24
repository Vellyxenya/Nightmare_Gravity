import greenfoot.*;
import java.util.List;

/**
 * Ennemi de type Devices
 */
public class Devices extends Enemies
{
    private int Life = 2000;
    private int speed = 1;
    GreenfootSound dead = new GreenfootSound("EnemyDead.wav");

    public Devices()
    {

    }

    public void act() 
    {
        if(isPaused() == false)
        {
            move(-speed);
            stopMove();
            takeDamageNew(Weapon1.littleDamage, Weapon1.class);
            takeDamageNew(Weapon2.weapon2Damage, Weapon2.class);
            takeDamageNew(Lightning.damage, Lightning.class);
            vanish();
            effect();
        }
    }

    /**
     * S'immobilise pour viser le joueur
     */
    public void stopMove()
    {
        if(Game.aimingCounter>100 && Game.aimingCounter<170)
        {
            move(1); // avec le move(-1) de base, le movement total sera de 0.
        }
    }

    /**
     * Méthode permettant de le rendre sensible aux dégâts et d'exploser, pour autant qu'il ne soit pas en train de cibler le joueur
     */
    public void takeDamageNew(int hurt, Class classe)
    {
        if(getWorld() !=null)
        {
            if(this.isTouching(classe))
            {
                List<DeviceLaser> laseres = getWorld().getObjects(DeviceLaser.class);
                int length = laseres.size();
                if(length == 0)
                {
                    Life = Life - hurt;
                    if(Life <=0)
                    {
                        drop(20);
                        int random = Greenfoot.getRandomNumber(30);
                        int random2 = Greenfoot.getRandomNumber(100);
                        if(Greenfoot.getRandomNumber(10) == 0)
                        {
                            for(int i = 0; i<4; i++)
                            {
                                getWorld().addObject(new Web(random+120*i), this.getX(), this.getY());
                            }
                        }
                        for(int i = 0; i<3; i++)
                        {
                            getWorld().addObject(new Burst(random2+120*i), this.getX(), this.getY());
                        }
                        dead.play();
                        getWorld().removeObject(this);
                        Game.killedEnemies++;
                    }
                }
            }
        }
    }

}

