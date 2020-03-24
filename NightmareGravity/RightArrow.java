import greenfoot.*;
import java.awt.Color;

/**
 * Flèche permettant de passer au vaisseau d'indice i+1 dans le menu sélection
 */
public class RightArrow extends Arrows
{
    
    public RightArrow()
    {
        super();
    }
    
    public void act() 
    {
        next();
        makeSound();
    }
    
    /**
     * Passage à l'image 0 si l'on est à l'image 3
     * et change l'image quand la souris est dessus
     */
    public void next()
    {
        if(Greenfoot.mouseClicked(this))
        {
            if(imageNumber == 3) imageNumber = 0;
            else imageNumber++ ;
        }
        if(mouseOn() == true)
        {
            drawArrow(new Color(34, 120, 174));
        }
        else
        {
            drawArrow(new Color(0, 0, 120));
        }
    }
    
}
