import greenfoot.*;
import java.awt.Color;

/**
 * Classe d'ennemis naissant de la métamorphose d'un ennemi déjà existant ou à la toute fin de la partie, après
 * le dernier boss
 */
public class Revenger extends Enemies
{
    private int counter;
    private boolean laser;
    private int direction;
    private int where;
    private boolean boss;

    /**
     * En tant qu'ennemi normal
     */
    public Revenger()
    {
        laser = false;
        counter = 0;
        Life = 3000;
        boss = false;
    }

    /**
     * En tant que Boss
     */
    public Revenger(int life, GreenfootImage image)
    {
        laser = false;
        counter = 0;
        this.Life = life;
        setImage(image);
        boss = true;
    }

    public void act() 
    {
        try
        {
            if(isPaused() == false)
            {
                if(getWorld()!= null)
                {
                    if(boss == false) //Si c'est un ennemi normal
                    {
                        takeDamageNew(Weapon1.littleDamage, Weapon1.class);
                        takeDamageNew(Weapon2.weapon2Damage, Weapon2.class);
                        takeDamageNew(Lightning.damage, Lightning.class);
                    }
                    else if(boss == true)
                    {
                        likeABoss();
                    }
                    effect();
                    counter++;
                    if(laser == false)
                    {
                        Gears gear = (Gears) getWorld().getObjects(Gears.class).get(0);
                        turnTowards(gear.getX(), gear.getY());
                    }
                    try
                    {
                        if(isTouching(Web.class))
                        {
                            removeTouching(Web.class);
                        }
                    }catch(IllegalStateException e){}
                    attack1();
                    attack2();
                }
            }
        }catch(NullPointerException e){}
    }

    /**
     * Lance des boules sur le joueur
     */
    public void attack1()
    {
        if(counter<320)
        {
            if(counter%20 == 0)
            {
                move(65);
                getWorld().addObject(new BlueHit(getRotation(), 0), this.getX(), this.getY());
                move(-65);
            }
            else if((counter-3)%20 == 0)
            {
                move(-8);
            }
            else if((counter-12)%20 == 0)
            {
                move(4);
            }
            else if((counter-18)%20 == 0)
            {
                move(2);
            }
        }
    }

    /**
     * Recule un moment puis crée un laser en direction du joueur
     */
    public void attack2()
    {
        if(counter == 320)
        {
            if(getY()<getWorld().getHeight()/2) direction = 0;
            else if(getY()>getWorld().getHeight()/2) direction = 1;
            if(getX()<getWorld().getWidth()/2) where = 0;
            else if(getX()>getWorld().getHeight()/2) where = 1;
        }
        if(counter> 320 && counter<380)
        {
            if(direction == 0)
            {
                setLocation(getX(), getY()+3);
            }
            else if(direction == 1)
            {
                setLocation(getX(), getY()-3);
            }
            if(where == 0)
            {
                setLocation(getX()+4, getY());
            }
            else if(where == 1)
            {
                setLocation(getX()-4, getY());
            }
        }
        else if(counter == 380)
        {
            laser = true;
            BlueHit hit = new BlueHit(getRotation(), 1);
            move(70);
            getWorld().addObject(hit, this.getX(), this.getY());
            move(-70);
        }
        else if(counter == 500)
        {
            laser = false;
            counter = 0;
        }
    }
    
    /**
     * Comportement en tant que boss
     */
    public void likeABoss()
    {
        if(getWorld().getObjects(FinalTraps.class).isEmpty())
        {
            getWorld().addObject(new FinalTraps(), Greenfoot.getRandomNumber(750), Greenfoot.getRandomNumber(563));
            getWorld().addObject(new TrapColor(), 0, 0);
        }
        if(isTouching(FinalTraps.class))
        {
            FinalTraps f = (FinalTraps) getWorld().getObjects(FinalTraps.class).get(0);
            if(f.hurting == true)
            {
                removeTouching(FinalTraps.class);
                Life -= 10001;
                if(Life<0)
                {
                    getWorld().addObject(new Transition(), getWorld().getWidth()/2, getWorld().getHeight()/2);
                    Game.end = true;
                    getWorld().removeObject(this);
                }
            }
        }
    }

}
