import greenfoot.*;
import java.util.List;
import java.awt.Color;

/**
 * 6e Boss: Crée des objets qui se déplacent dans le monde. Le joueur doit tirer sur ces objetes
 * pour infliger des dégâts à ce boss
 */
public class Boss6 extends Bosses
{
    /**
     * Vie actuelle
     */
    public static int health;
    /**
     * Points de vie max du boss
     */
    public static final int MAX_HEALTH = 30000;
    private int counter;
    private GreenfootImage baseImage;
    /**
     * compteur pour la vulnérabilité de tel objet du monde
     * et donc de la vulnérabilité du boss
     */
    public static int vulnerability = 180;

    public Boss6() 
    {
        health = MAX_HEALTH;
        counter = 0;
        baseImage = getImage();
        setImage(2-baseImage.getWidth()%2, baseImage);
    }

    public void act()
    {
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
            takeDamageNew((Weapon2.weapon2Damage/10), Weapon2.class);
            takeDamageNew(Lightning.damage, Lightning.class);
            createMagnets();
            getMagnets();
        }
        movements(620);
        int imageWidth = getImage().getWidth();
        if (imageWidth < baseImage.getWidth())
        {
            setImage(imageWidth+2, baseImage);
        }
    }

    /**
     * S'il touche des armes du joueur, il ne subit aucn dégâts mais c'est le joueur qui est puni
     */
    public void takeDamageNew(int hurt, Class classe)
    {
        if(getWorld() != null)
        {
            List<Weapons> weapons = getTouchedObjects(classe);
            int length = weapons.size();
            if(length>=1)
            {
                for(Weapons weapon : weapons)
                {
                    getWorld().removeObject(weapon);
                    if(getWorld().getObjects(MegaLightning.class).isEmpty())
                    {
                        getWorld().addObject(new MegaLightning(), getWorld().getWidth()/2, getWorld().getHeight()/2);
                    }
                }
            }
        }
    }

    /**
     * Crée des objets qui rebondissent entre eux
     */
    public void createMagnets()
    {
        if(counter >= 60 && counter< 400 && counter%60 == 0)
        {
            Magnet magnet = new Magnet();
            getWorld().addObject(magnet, this.getX(), this.getY());
        }
    }

    /**
     * Sélectionne un objet aléatoire et le rend vulnérable périodiquement
     */
    public void getMagnets()
    {
        if(counter> 400 && counter%200 == 0)
        {
            List<Magnet> magnets = getWorld().getObjects(Magnet.class);
            Magnet magnet = (Magnet)getWorld().getObjects(Magnet.class).get(Greenfoot.getRandomNumber(magnets.size()));
            magnet.counter2 = vulnerability;
        }
    }

}
