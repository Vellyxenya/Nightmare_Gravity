import greenfoot.*;

/**
 * Effet visuel de translation du Boss
 */
public class Boss7Clone extends Hurting
{
    private GreenfootImage baseImage = new GreenfootImage("Boss7.png");
    private int transparency;

    public Boss7Clone()
    {
        transparency = 200;
    }

    /**
     * Devient de plsu en plus transparent jusqu'à dispar
     */
    public void act() 
    {
        transparency -= 6;
        if(transparency% 10 == 0)
        {
            baseImage.setTransparency(transparency);
            setImage(baseImage);
        }
        else if(transparency<=0)
        {
            getWorld().removeObject(this);
        }
        change();
    }
    
    /**
     * Si le joueur clique sur cet objet, il change de place avec le boss
     */
    public void change()
    {
        if(Greenfoot.mouseClicked(this))
        {
            Boss7 boss = (Boss7) getWorld().getObjects(Boss7.class).get(0);
            boss.swap();
            Boss7.rotationAngle += 180;
        }
    }
    
}
