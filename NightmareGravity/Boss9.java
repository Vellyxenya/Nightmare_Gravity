import greenfoot.*;
import java.awt.Color;
import java.util.List;

/**
 * Dernier Boss, pluie de balles
 */
public class Boss9 extends Bosses
{
    public static int health;
    public static final int MAX_HEALTH = 30000;
    private int counter;
    private GreenfootImage baseImage;
    int[] x = {616, 605, 595, 603, 586, 566, 579, 551, 527, 639, 649, 656};
    int[] y = {221, 185, 147, 342, 379, 414, 268, 262, 256, 297, 309, 322};
    TP2 tp = new TP2();

    public Boss9()
    {
        counter = 0;
        health = 30000;
        
        baseImage = getImage();
        setImage(2-baseImage.getWidth()%2, baseImage);
    }

    public void act() 
    {
        if(counter == 2) getWorld().addObject(new TP(), 470, getWorld().getHeight()/2);
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
            fire();
        }
        movements(610);
        int imageWidth = getImage().getWidth();
        if (imageWidth < baseImage.getWidth())
        {
            setImage(imageWidth+2, baseImage);
        }
    }

    /**
     * Projette des balles dans toutes les directions depuis les 12 pores du boss
     */
    public void fire()
    {
        if(isPaused() == false)
        {
            if(!getWorld().getObjects(TP2.class).isEmpty() && counter%89 == 0) getWorld().removeObjects(getWorld().getObjects(TP2.class));
            if(counter%90 == 0)
            {
                for(int i = 0; i<12; i++)
                {
                    getWorld().addObject(new Shoots(), x[i], y[i]);
                }
                getWorld().addObject(tp, Greenfoot.getRandomNumber(750), Greenfoot.getRandomNumber(563));
                Gears g = (Gears) getWorld().getObjects(Gears.class).get(0);
                tp.turnTowards(g.getX(), g.getY());
            }
        }
    }

}
