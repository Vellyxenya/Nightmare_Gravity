import greenfoot.*;
import java.awt.Color;

/**
 * Compteur à rebours utilisé dans EasyNightmare (puzzle).
 */
public class CountDown extends Interface
{
    
    public void act() 
    {
        showCounter(EasyNightmare.MAX_TIME , EasyNightmare.time);
    }

    /**
     * Dessine le temps restant en secondes
     */
    public void showCounter(int maxTime, int time)
    {
        GreenfootImage text = new GreenfootImage("Time :  "+ (maxTime-time), 24, Color.WHITE, new Color(0, 0, 0, 0));
        setImage(text);
    }
    
}
