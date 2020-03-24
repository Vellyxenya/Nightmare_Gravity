import greenfoot.*;
import java.awt.Color;

/**
 * Scène affichant une animation du nombre de l'utilisateur avant de rentrer dans le jeu
 */
public class Intro extends TheEdge
{
    GreenfootImage image = getBackground();
    public static boolean ready;
    
    public Intro()
    {
        ready = false;
        image.setColor(Color.BLACK);
        image.fill();
        setBackground(image);
        addObject(new PseudoAnim(), getWidth()/2, getHeight()/2);
    }
    
    public void act()
    {
        ChangeMouseImage(img1, 1, 1);
    }
    
}
