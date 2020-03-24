import greenfoot.*;

/**
 * Classe regroupant toutes les armes
 * Utile pour le polymorphisme
 */
public class Weapons extends SmoothMover
{
    
    /**
     * Donne au vaisseau un mouvement vibratoire réaliste
     */
    public void stun()
    {
        if(getWorld()!= null)
        {
            if(this.isTouching(Gears.class))
            {
                Gears gear = (Gears) getWorld().getObjects(Gears.class).get(0);
                int random = Greenfoot.getRandomNumber(4);
                switch(random)
                {
                    case 0:
                    gear.setLocation(gear.getX()+3, gear.getY());
                    break;

                    case 1:
                    gear.setLocation(gear.getX()-3, gear.getY());
                    break;

                    case 2:
                    gear.setLocation(gear.getX(), gear.getY()+3);
                    break;

                    case 3:
                    gear.setLocation(gear.getX(), gear.getY()-3);
                    break;
                }
            }
        }
    }
    
}
