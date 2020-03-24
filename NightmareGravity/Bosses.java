import greenfoot.*;
import java.awt.Color;
import java.util.List;

/**
 * Création de Boss, 1 boss à la fin de chaque lvl.
 */
public class Bosses extends Opponent
{
    /**
     * Dégâts infligés au joueur s'il s'approche trop des boss
     */
    public static int damage = 100;

    /**
     * Le Boss arrive dans le monde jusqu'à une certaine distance
     */
    public void movements(int x)
    {
        try
        {
            if(this.getX() > x)
            {
                move(-1);
            }
            else if(!getWorld().getObjects(Halo.class).isEmpty())
            {
                getWorld().removeObject((Actor)getWorld().getObjects(Halo.class).get(0));
            }
        }catch(IllegalStateException e){}
    }

    /**
     * Méthode dessinant la barre de vie du Monstre.
     */
    public void drawHealthBar(int health, int maxHealth)
    {
        GreenfootImage i = new GreenfootImage(getImage());
        GreenfootImage healthBar = new GreenfootImage(i.getWidth(),5);
        healthBar.setColor(Color.WHITE);
        healthBar.fill();

        int healthPixels = (int) (healthBar.getWidth()*health/maxHealth);
        if(healthPixels <= 0)
        {
            healthPixels=1; 
        }
        GreenfootImage filledHealth = new GreenfootImage(healthPixels,5);
        filledHealth.setColor(Color.RED);
        filledHealth.fill();

        healthBar.drawImage(filledHealth,0,0);        
        i.drawImage(healthBar, 0, 0);
        setImage(i);
    }

    /**
     * Méthode faisant apparaître le boss pixel par pixel.
     */
    public void setImage(int width, GreenfootImage image)
    {
        GreenfootImage setter = new GreenfootImage(width, image.getHeight());
        setter.drawImage(image, 0, 0);
        setImage(setter);
    }

}
