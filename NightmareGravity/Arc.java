import greenfoot.*;

/**
 * Champ électrique créé par le Boss6 et qui relie tous les Magnet, inflige des dégâts.
 */
public class Arc extends Hurting
{
    private int counter;

    /**
     * Initialise l'arc qui relie 2 Magnet
     */
    public Arc()
    {
        /**
         * N'a une durée de vie que d'un act
         */
        counter = 1;
        int random = Greenfoot.getRandomNumber(2);
        if(random == 0)
        {
            this.getImage().setTransparency(0);
        }
    }

    public void act() 
    {
        if(isPaused() == false)
        {
            counter--;
            disappear();
        }
    }

    /**
     * Disparaît juste après sa disparition afin de donner un effet électrique
     */
    public void disappear()
    {
        if(counter<= 0)
        {
            getWorld().removeObject(this);
        }
    }

}
