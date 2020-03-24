import greenfoot.*;
import java.awt.Color;
import java.util.List;

/**
 * Boss très passif: il orbite diamétralement avec le joueur et c'est celui qui se prend le plus de dégâts qui perd.
 */
public class Boss7 extends Bosses
{
    /**
     * Points de vie actuels
     */
    public static int health;
    /**
     * Points de vie max
     */
    public static final int MAX_HEALTH = 4000;
    private int counter;
    private GreenfootImage baseImage;
    /**
     * Rotation actuelle du boss
     */
    public static int rotationAngle = 0;

    public Boss7()
    {
        counter = -300;
        health = MAX_HEALTH;
        baseImage = getImage();
        setImage(2-baseImage.getWidth()%2, baseImage);
    }

    public void act() 
    {
        if(isPaused() == false)
        {
            try
            {
                if(getWorld().getObjects(Informations2.class).isEmpty())
                {
                    counter++;
                    drawHealthBar(health, MAX_HEALTH);
                }
            }catch(NullPointerException e){}
            takeDamage();
            movements();
            createClones();
            swap();
            dominate();
            createBombs();
        }
        movements(580);
        int imageWidth = getImage().getWidth();
        if (imageWidth < baseImage.getWidth())
        {
            setImage(imageWidth+2, baseImage);
        }
    }

    /**
     * Méthode rendant le boss sensible aux dégâts et le faisant disparaître si sa vie tombe à zéro
     */
    public void takeDamage()
    {
        if(getWorld() != null)
        {
            if(isTouching(Bomb.class))
            {
                removeTouching(Bomb.class);
                health = health - 100;
                if(health <=0)
                {
                    List<BossStuff> objects = getWorld().getObjects(BossStuff.class);
                    getWorld().removeObjects(objects);
                    List<Obstacle> obstacles = getWorld().getObjects(Obstacle.class);
                    getWorld().removeObjects(obstacles);
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

    /**
     * Le boss décrit un pentagone dans son mouvement
     */
    public void movements()
    {
        if(counter == 120)
        {
            getWorld().addObject(new RotationCenter(), getWorld().getWidth()/2, getWorld().getHeight()/2);
            getWorld().addObject(new RotationCenter2(), getWorld().getWidth()/2, getWorld().getHeight()/2);
        }
        if(counter >= 160)
        {
            if(getWorld()!= null)
            {
                RotationCenter rotationCenter = (RotationCenter) getWorld().getObjects(RotationCenter.class).get(0);
                rotationAngle++;
                this.setLocation(rotationCenter.getX()+(int)(Math.cos(Math.toRadians(rotationAngle))*205),
                rotationCenter.getY()+(int)(Math.sin(Math.toRadians(rotationAngle))*-205));
            }
        }
    }

    /**
     * Effet visuel de défilement
     */
    public void createClones()
    {
        if(counter >= 120 && counter%5 == 0)
        {
            if(getWorld()!= null)
            {
                getWorld().addObject(new Boss7Clone(), this.getX(), this.getY());
            }
        }
    }

    /**
     * Crée l'objet qui immobilise le joueur
     */
    public void dominate()
    {
        if(getWorld()!= null)
        {
            Gears gear = (Gears) getWorld().getObjects(Gears.class).get(0);
            if(counter%360 == 0)
            {
                getWorld().addObject(new Domination(), gear.getX(), gear.getY());
            }
        }
    }

    /**
     * Permet au joueur de permuter sa position avec celle du boss pour éviter un obstacle par exemple
     */
    public void swap()
    {
        if(Greenfoot.mouseClicked(this))
        {
            rotationAngle += 180;
        }
    }

    /**
     * Crée des bombes tout autant létales pour lui que pour le joueur
     */
    public void createBombs()
    {
        if(counter >= 180 && counter%60 == 0)
        {
            int x = Greenfoot.getRandomNumber(getWorld().getWidth());
            int y = Greenfoot.getRandomNumber(getWorld().getHeight());
            getWorld().addObject(new Bomb(), x, y);
        }
    }

}
