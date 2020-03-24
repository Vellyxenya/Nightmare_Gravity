import greenfoot.*;
import java.awt.Color;
import java.util.List;

/**
 * Effets visuels pour les boules lancés par le Boss. 
 */
public class Gaz2 extends SpecialEffects
{
    GreenfootImage baseImage;
    public static int damage = 2;
    private int counter;

    public Gaz2()
    {
        counter = 0;
        baseImage = new GreenfootImage(750, 563);
        setImage(baseImage);
    }

    public void act() 
    {
        counter++;
        if(counter >= 120){
            getWorld().removeObject(this);
        }
        createPixels();
    }

    /**
     * Dessine un effet visuel violancé autour des boules lancées par le Boss
     */
    public void createPixels()
    {
        if(getWorld()!= null)
        {
            baseImage = new GreenfootImage(750, 563);
            List<Ash> ashes = getWorld().getObjects(Ash.class);
            int size = ashes.size();
            if(size>0)
            {
                for(Ash ash : ashes)
                {
                    for(int i = 0; i<2000; i++)
                    {
                        int x = ash.getX();
                        int y = ash.getY();
                        double r1 = Greenfoot.getRandomNumber(40);
                        double r2 = Greenfoot.getRandomNumber(40);
                        int beam = 1+Greenfoot.getRandomNumber(2);
                        int r = Greenfoot.getRandomNumber(255);
                        int g = Greenfoot.getRandomNumber(255);
                        int b = Greenfoot.getRandomNumber(255);
                        double position = Math.toRadians(Greenfoot.getRandomNumber(360));
                        baseImage.setColor(new Color(r, 0, b));
                        if(counter%2 == 0)
                        {
                            baseImage.fillOval(x+(int)(40*Math.cos(position)), y+(int)(r1*Math.sin(position)), beam, beam);
                        }
                        else if (counter%1 == 0)
                        {
                            baseImage.fillOval(x+(int)(r1*Math.cos(position)), y+(int)(40*Math.sin(position)), beam, beam);
                        }
                    }
                    setImage(baseImage);
                }
            }
        }
    }
}
