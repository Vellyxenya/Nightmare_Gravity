import greenfoot.*;
import java.awt.Color;

/**
 * Objet affichant à l'écran le montant de crédits gagnés durant la partie en cours.
 */
public class Crediter extends Interface
{
    
    public void act() 
    {
        showCrediter(Game.credit);
    }

    /**
     * dessine le nombre de crédits actuellement en possession du joueur
     */
    public void showCrediter(int credit)
    {
        GreenfootImage text = new GreenfootImage("Credits :  "+credit, 24, Color.CYAN, new Color(0, 0, 0, 0));
        setImage(text);
    }
    
}
