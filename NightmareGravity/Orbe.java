import greenfoot.*;

/**
 * Orbes crées(en grande quantité par le 2e Boss) et projetés dans toutes les directions
 */
public class Orbe extends EW
{
    /**
     * Dégâts infligés par cet objet
     */
    public static int damage = 200;
    /**
     * Distance parcourue avant d'être violememnt projeté
     */
    private int distance;
    /**
     * Compteur pour la distance parcourue
     */
    private int i;
    /**
     * Compteur pour permettre aux orbes d'infliger des dégâts avant de disparaître
     */
    private int counter = 0;

    public Orbe()
    {
        distance = 30+Greenfoot.getRandomNumber(50);
        i = 0;
    }

    public void act() 
    {
        if(isPaused() == false)
        {
            if(i<distance)
            {
                move(2);
                i++;
            }
            go();
            vanish();
            count();
            delete();
            disappear();
        }
    }

    /**
     * Déplacement des orbes
     */
    public void go()
    {
        if(Boss2.readyToGo == true)
        {
            move(-6);
        }
    }

    /**
     * Déclenche le compteur s'il entre en collision avec le vaisseau du joueur
     */
    private void count()
    {
        if(getWorld() != null)
        {
            if(this.isTouching(Gears.class))
            {
                counter++;
            }
        }
    }

    /**
     * Disparaît après 2 act
     */
    private void delete()
    {
        if(counter == 2)
        {
            getWorld().removeObject(this);
        }
    }

    /**
     * Disparaît si le boss a été éliminé
     */
    private void disappear()
    {
        if(Game.bossSpawned == 0)
        {
            if(getWorld()!= null)
            {
                getWorld().removeObject(this);
            }
        }
    }
    
}
