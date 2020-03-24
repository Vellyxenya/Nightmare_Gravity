import greenfoot.*;

/**
 * Immobilise le vaisseau du joueur, objet créé par le Boss7
 */
public class Domination extends NotHurting
{
    /**
     * Dégâts négatifs pour compenser les dégâts naturels de ces objets
     */
    public static int damage = -2;
    private int counter;

    public Domination()
    {
        counter = 360;
    }

    /**
     * Commande le déplacement du joueur et suit le déplacement du boss
     */
    public void act() 
    {
        if(isPaused() == false)
        {
            counter--;
            if(getWorld()!= null)
            {
                try
                {
                    Gears gear = (Gears) getWorld().getObjects(Gears.class).get(0);
                    Boss7 boss = (Boss7) getWorld().getObjects(Boss7.class).get(0);
                    this.setLocation(getWorld().getWidth()-boss.getX(), getWorld().getHeight()-boss.getY());
                    gear.setLocation(this.getX(), this.getY());
                }catch(IndexOutOfBoundsException e){}
            }
            if(counter <= 0)
            {
                getWorld().removeObject(this);
            }
        }
    }

}
