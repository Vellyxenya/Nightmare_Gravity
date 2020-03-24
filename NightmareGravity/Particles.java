import greenfoot.*;

/**
 * Tirs créés par le Boss8 et infligeant des dégâts.
 */
public class Particles extends NotHurting
{
    private int x;
    private int y;
    /**
     * Vitesse de déplacement de cette particule
     */
    private int speed = 8;
    /**
     * Permet d'être propulsé parle boss
     */
    public boolean launchable;

    /**
     * N'infligent pas de dégâts quand il sont en train d'être collectés par le boss
     */
    public Particles(int x, int y)
    {
        launchable = false;
        this.x = x;
        this.y = y;
    }

    /**
     * Infligent des dégâts quand ils sont propulsés par le boss
     */
    public Particles()
    {
        launchable = true;
    }

    public void act() 
    {
        if(isPaused() == false)
        {
            move(speed);
            if(this.launchable == false)
            {
                turnTowards(x, y);
                disappear();
            }
            else if(this.launchable == true)
            {
                if(isTouching(Gears.class))
                {
                    Gears g = (Gears) getWorld().getObjects(Gears.class).get(0);
                    g.getDamaged();
                }
                vanish();
            }
        }
    }

    /**
     * Disparaît si elle touche le 8e Boss
     */
    public void disappear()
    {
        if(this.isTouching(Boss8.class)||isTouching(Boss8Stuff.class))
        {
            getWorld().removeObject(this);
        }
    }

    /**
     * Se dirige vers le 8e boss
     */
    public void follow()
    {
        if(this.getX() == x && this.getY() == y)
        {
            Boss8 boss = (Boss8) getWorld().getObjects(Boss8.class).get(0);
            this.setLocation(boss.getX(), boss.getY());
        }
    }

}
