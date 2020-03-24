import greenfoot.*;
import java.awt.Color;

/**
 * Montre le temps écoulé depuis le début de la partie.
 */
public class Timer extends Interface
{
    
    public void act() 
    {
        showTimer(Game.time);
    }

    /**
     * Affiche le temps écoulé
     */
    public void showTimer(int time)
    {
        GreenfootImage text = new GreenfootImage("Time :  "+time, 24, Color.CYAN, new Color(0, 0, 0, 0));
        setImage(text);
    }
    
}
