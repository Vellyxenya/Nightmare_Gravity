import greenfoot.*;
import javax.swing.JOptionPane;

/**
 * Bouton permettant de sélectionner un vaisseau.
 */
public class Select extends Arrows
{
    
    public Select()
    {
        setImage("Select.png");
    }

    public void act() 
    {
        select();
        makeSound();
        if(mouseOn() == true)
        {
            setImage("Select2.png");
        }
        else
        {
            setImage("Select.png");
        }
    }

    /**
     * Sélectionne le vaisseau actuel pour la prochaine partie
     */
    public void select()
    {
        if(Greenfoot.mouseClicked(this))
        {
            Greenfoot.playSound("ButtonSound.wav");
            if(getWorld().getObjects(Locked.class).isEmpty())
            {
                Greenfoot.setWorld(new Menu());
            }
            else
            {
                JOptionPane.showMessageDialog(null, "You haven't unlocked this ship yet. You must collect its components after accomplishing your mission!");
            }
        }
    }
    
}
