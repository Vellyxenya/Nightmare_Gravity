import greenfoot.*;
import java.awt.Color;

/**
 * Flèche permettant de passer au vaisseau d'indice i-1 dans le menu sélection
 */
public class LeftArrow extends Arrows
{
    
    public LeftArrow()
    {
        super();
        getImage().mirrorHorizontally();
    }
    
    public void act() 
    {
        next();
        makeSound();
    }
    
    /**
     * Passe à l'image 3 si l'actuelle est d'indice 0
     * et change l'image quand la souris est dessus
     */
    public void next()
    {
        if(Greenfoot.mouseClicked(this))
        {
            if(imageNumber == 0) imageNumber = 3;
            else imageNumber-- ;
        }
        if(mouseOn() == true)
        {
            drawArrow(new Color(34, 120, 174));
            getImage().mirrorHorizontally();
        }
        else
        {
            drawArrow(new Color(0, 0, 120));
            getImage().mirrorHorizontally();
        }
    }
    
}
