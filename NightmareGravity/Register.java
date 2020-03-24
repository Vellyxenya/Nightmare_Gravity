import greenfoot.*;
import javax.swing.JOptionPane;

/**
 * Bouton pour créer un compte.
 */
public class Register extends DATA
{
    GreenfootImage i1 = new GreenfootImage("Register.png");
    GreenfootImage i2 = new GreenfootImage("Register2.png");

    public Register()
    {
        on = false;
    }

    /**
     * Invoque les méthodes de stockage de données de la classe-mère DATA
     */
    public void act()
    {
        super.act();
        if(Greenfoot.mouseClicked(this))
        {
            if(Background.ID.length()>=3 && Background.PW.length()>=6)
            {
                DATA.storeIDandPW();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "ID must contain at least 3 characters, Password must contain at least 6 characters");
            }
        }
        changeImage(i1, i2);
    }

}
