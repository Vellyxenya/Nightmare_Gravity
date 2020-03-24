import greenfoot.*;
import java.awt.Color;

/**
 * Objet indiquant à l'utilisateur de presster la touche "Enter" pour continuer
 */
public class PressEnter extends Interface
{
    /**
     * Renvoie true si le message est en train de devenir opaque
     */
    private boolean up = true;
    
    /**
     * Construit le message
     */
    public PressEnter()
    {
        GreenfootImage text = new GreenfootImage("Press \"ENTER\" Key to continue", 30, Color.CYAN, new Color(0, 0, 0, 0));
        text.setColor(Color.RED);
        text.setTransparency(0);
        setImage(text);
    }
    
    /**
     * Fait clignoter fluidement le message
     */
    public void act() 
    {
        if(up == false) getImage().setTransparency(getImage().getTransparency()-2);
        else if (up == true) getImage().setTransparency(getImage().getTransparency()+2);
        if(getImage().getTransparency()== 250 && up == true) up = false;
        else if(getImage().getTransparency() == 0 && up == false) up = true;
        if(getWorld().getObjects(Informations2.class).isEmpty()) getWorld().removeObject(this);
    }
    
}
