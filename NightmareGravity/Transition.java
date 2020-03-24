import greenfoot.*;
import java.awt.Color;

/**
 * Ecran blanc apparaissant marquant la fin d'un niveau
 */
public class Transition extends Interface
{
    GreenfootImage image = new GreenfootImage(750, 563);
    /**
     * Son produit pendant la transition entre les niveaux
     */
    GreenfootSound sound = new GreenfootSound("Transition.wav");

    /**
     * Crée l'écran blanc
     */
    public Transition()
    {
        image.setColor(new Color(230, 230, 255));
        image.fill();
        image.setTransparency(0);
        setImage(image);
        sound.play();
    }

    /**
     * Diminue la transparence de l'écran jusqu'à ce qu'il devient opaque puis le fait disparaître
     */
    public void act() 
    {
        if(isPaused() == false)
        {
            image.setTransparency(image.getTransparency()+1);
            setImage(image);
            if(getImage().getTransparency()== 255)
            {
                if(Game.end == true)
                {
                    getWorld().addObject(new D3(), getWorld().getWidth()/2, getWorld().getHeight()/2);
                }
                getWorld().removeObject(this);
                Game.imageNumber++;
                Game.reduce = true;
            }
        }
    }

}
