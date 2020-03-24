import greenfoot.*;

/**
 * Ennemi de type Seeker (vaisseau qui tire des missiles).
 */
public class Seeker extends Enemies
{
    private int timer;
    private int attackTimer;
    private final int ATTACK_TIMER = 200;
    private int speed = 2;

    public Seeker()
    {
        timer = 0;
        Life = 3000;
    }

    public void act() 
    {
        if(isPaused() == false)
        {
            super.act();
            move(-speed);
            timer++;
            attackTimer++;
            fire();
            vanish();
        }
    }

    /**
     * Périodiquement, ce vaisseau tire des missiles à tête chercheuse sur le joueur
     */
    private void fire()
    {
        if(timer>= 100 && attackTimer>= ATTACK_TIMER)
        {
            Missile missile = new Missile();
            try
            {
                Actor gear = (Actor)getWorld().getObjects(Gears.class).get(0);
                getWorld().addObject(missile, this.getX(), this.getY());
                int xGear = gear.getX();
                int yGear = gear.getY();
                missile.turnTowards(xGear, yGear);
                attackTimer = 0;
            }catch(NullPointerException e){}
        }
    }

}
