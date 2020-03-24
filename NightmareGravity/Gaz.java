import greenfoot.*;
import java.awt.Color;
import java.util.List;

/**
 * Image qui recouvre tout l'écran avec un effet de feu et qui inflige des dégâts au joueur (Boss 5).
 */
public class Gaz extends SpecialEffects
{
    GreenfootImage baseImage;
    /**
     * Dégâts infligés (ils peuvent varier)
     */
    public static int damage;
    private int counter = 0;
    /**
     * Durée de l'effet
     */
    private int duration;

    public Gaz()
    {
        baseImage = new GreenfootImage(750, 563);
        setImage(baseImage);
        duration = 90;
        damage = 6;
    }

    public void act() 
    {
        if(isPaused() == false)
        {
            counter++;
            duration--;
            heatWave();
            disappear();
            modifyDamage();
        }
    }

    /**
     * Dessine un écran avec plein de petits points rougeâtres
     */
    public void heatWave()
    {
        if(getWorld()!= null)
        {
            baseImage = new GreenfootImage(750, 563);
            int r = Greenfoot.getRandomNumber(255);
            for(int i=0; i<750; i++)
            {
                for(int j=0; j<563; j++)
                {
                    int choose = Greenfoot.getRandomNumber(2);
                    if(choose == 0)
                    {
                        if(i%12 == 0)
                        {
                            if(j%12 == 0)
                            {
                                int random = Greenfoot.getRandomNumber(2);
                                if(random == 0)
                                {
                                    baseImage.setColor(new Color(255, r, 100));
                                    baseImage.fillRect(i, j, 1, 1);
                                    setImage(baseImage);
                                }
                            }
                        }
                    }
                    else if(choose == 1)
                    {
                        if(i%11 == 0)
                        {
                            if(j%11 == 0)
                            {
                                int random = Greenfoot.getRandomNumber(2);
                                if(random == 0)
                                {
                                    baseImage.setColor(new Color(255, r, 100));
                                    baseImage.fillRect(i-2+Greenfoot.getRandomNumber(5), j-2+Greenfoot.getRandomNumber(5), 1, 1);
                                    setImage(baseImage);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Enlève les astéroïdes non collés au vaisseau.
     */
    public void disappear()
    {
        if(getWorld().getObjects(Boss5.class).isEmpty() || duration <= 0)
        {
            List<Asteroid> asteroids = getWorld().getObjects(Asteroid.class);
            for(Asteroid ast :asteroids)
            {
                if(ast.stick != true)
                {
                    getWorld().removeObject(ast);
                }
            }
            Boss5.counter = 0;
            getWorld().removeObject(this);
        }
    }
    
    /**
     * Plus il y a d'astéroïdes autour du vaisseau, moins celui-ci subit de dégâts
     */
    public void modifyDamage()
    {
        damage = 6-Boss5.protectionCounter;
    }
    
}
