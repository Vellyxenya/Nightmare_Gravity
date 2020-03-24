import greenfoot.*;
import java.util.List;

/**
 * Ennemis de classe Predator. Cet Ennemi a un mouvement spécial... en effet, lors qu'il est présent,
 * un objet du même type que Trap est créé aléatoirement dans le monde et déplacé dans le World
 * et l'attire, ce qui lui donne un mouvement très désordonné et rapide(la vitesse reste à régler).
 */
public class Predator extends Enemies
{
    private int counter;
    /**
     * Masse de Predator
     */
    private final int MASS = 20;
    /**
     * Gravité spécifique à cette classe, elle lui permet de bouger
     */
    private int GRAVITY2;
    /**
     * Vecteur initial
     */
    Vector force;

    public Predator()
    {
        counter = 0;
        addForce(new Vector(180, 0));
        Life = 3000;
    }

    /**
     * Crée des trous noirs invisible qui lui servent de directeur de déplacement
     */
    public void act() 
    {
        if(isPaused() == false)
        {
            super.act();
            counter++;
            move();
            if(counter == 10)
            {
                try
                {
                    Trap2 trap = new Trap2();
                    getWorld().addObject(trap, 600, 400);
                }catch(NullPointerException e){}
            }
            if(counter == 0)
            {
                getWorld().addObject(new Trap2(), Greenfoot.getRandomNumber(getWorld().getWidth()), Greenfoot.getRandomNumber(getWorld().getHeight()));
            }
            applyGravity(MASS);
            detectTrap2();
        }
    }

    /**
     * Applique la formule de Newton à cet objet
     */
    public void applyGravity(int mass)
    {
        if(getWorld()!= null)
        {
            List<Trap2> traps = getWorld().getObjects(Trap2.class);
            int size = traps.size();
            if(size>0)
            {
                GRAVITY2 = 200+Greenfoot.getRandomNumber(100);
                Trap2 trap = (Trap2) getWorld().getObjects(Trap2.class).get(0);
                double dx = trap.getExactX() - this.getExactX();
                double dy = trap.getExactY() - this.getExactY();
                force = new Vector (dx, dy);
                double distance = Math.sqrt (dx*dx + dy*dy);
                double strength = GRAVITY2 * mass * trap.mass / (distance * distance);
                double acceleration = strength / mass;
                force.setLength (acceleration);
                myAddForce (force);
                move();
            }
        }
    }

    /**
     * Détecte et enlève le trou noir s'il s'en approche trop et le recrée plus loin
     */
    public void detectTrap2()
    {
        if(getWorld()!= null)
        {
            List<Trap2> traps = getObjectsInRange(120, Trap2.class);
            int length = traps.size();
            if(length>=1)
            {
                getWorld().removeObjects(traps);
                Trap2 trap = new Trap2();
                getWorld().addObject(trap, Greenfoot.getRandomNumber(getWorld().getWidth()), Greenfoot.getRandomNumber(getWorld().getHeight()));
            }
        }
    }

}
