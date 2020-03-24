import greenfoot.*;
import java.awt.Color;
import java.util.List;

/**
 * 1er Boss: Attaque le joueur par 3 vagues de tirs successives et différentes.
 */
public class Boss1 extends Bosses
{
    /**
     * Vie du boss
     */
    public static int health;
    /**
     * Vie maximale du boss
     */
    public static final int MAX_HEALTH = 50000;
    /**
     * Compteur pour séquencer les tirs
     */
    private int fire;
    public static int counter;
    /**
     * Impair
     */
    public static int odd = 0;
    /**
     * 2e salve
     */
    private int round2;
    /**
     * la direction des tirs est croissantte ou décroissante
     */
    private boolean goUp = true;
    /**
     * Compteur pour la division en deux des tirs
     */
    private int divisionCounter = 30;
    private GreenfootImage baseImage;
    /**
     * Rotaiton de base
     */
    private int rotationAngle = 0;

    public Boss1()
    {
        health = MAX_HEALTH;
        fire = 0;
        counter = -200;
        round2 = 0;

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
                fire();
                roam();
            }catch(NullPointerException e){}
            movements(580);
        }
        int imageWidth = getImage().getWidth();
        if (imageWidth < baseImage.getWidth())
        {
            setImage(imageWidth+2, baseImage);
        }
    }

    /**
     * Tourne autour du centre de la scène
     */
    public void roam()
    {
        if(getWorld() != null && counter>0)
        {
            if(counter == 1)
            {
                getWorld().addObject(new RotationCenter(), getWorld().getWidth()/2, getWorld().getHeight()/2);
            }
            if(counter >= 30)
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
     * Crée trois vagues de tir différentes.
     */
    public void fire()
    {
        if(getWorld()!= null)
        {
            if(counter>0)
            {
                if(counter%50 == 0 && counter<421) //1ère vague de tirs
                {
                    for(int i = 0; i<30; i++)
                    {
                        BossBullet bossbullet = new BossBullet();
                        getWorld().addObject(bossbullet, this.getX(), this.getY());
                        bossbullet.setRotation(160+12*i +6*(odd % 2));
                    }
                    odd++;
                }
                if(counter%6 == 0 && counter>= 421 && counter<1081) //2e vague de tirs
                {
                    BossBullet bossbullet = new BossBullet();
                    getWorld().addObject(bossbullet, this.getX(), this.getY());
                    bossbullet.setRotation(160+15*round2 +7*(odd % 2));
                    if(bossbullet.getRotation()<360 && goUp == true)
                    {
                        round2++;
                    }
                    else if(bossbullet.getRotation()>=60)
                    {
                        goUp = false;
                        round2--;
                        odd++;
                        if(round2<0)
                        {
                            goUp = true;
                        }
                    }
                }
                if(counter%60 == 0 && counter>=1081 && counter<1600) //3e vague de tirs
                {
                    for(int i = 0; i<20; i++)
                    {
                        BossBullet bossbullet = new BossBullet();
                        getWorld().addObject(bossbullet, this.getX(), this.getY()+150);
                        bossbullet.setRotation(160+20*i +6*(odd % 2));
                    }
                    odd++;
                }
                if(counter>=1081 && counter<1600)
                {
                    divisionCounter++;
                    if(divisionCounter%60 == 0) //3e vague de tirs, les tirs se divisent.
                    {
                        List<BossBullet> bullets = getWorld().getObjects(BossBullet.class);
                        for(BossBullet bullet : bullets)
                        {
                            BossBullet bossbullet1 = new BossBullet();
                            bossbullet1.setRotation(bullet.getRotation()+15);
                            getWorld().addObject(bossbullet1, bullet.getX(), bullet.getY());
                            BossBullet bossbullet2 = new BossBullet();
                            bossbullet2.setRotation(bullet.getRotation()-15);
                            getWorld().addObject(bossbullet2, bullet.getX(), bullet.getY());
                            getWorld().removeObject(bullet);
                        }
                    }
                }
                if(counter>1600) //Le cycle recommence.
                {
                    counter = 30;
                    divisionCounter = 30;
                }
            }
        }
    }
}
