import greenfoot.*;

/**
 * Trait qui permet de construire un éclair composé de plusieurs autres traits
 */
public class Lightning extends Weapons
{
    /**
     * Dégâts infligés par cet objet
     */
    public static final int damage = 1000;

    public Lightning(int rotation)
    {
        setRotation(rotation);
    }

    /**
     * Mouvement des branches de l'éclair pour l'animation
     */
    public void act() 
    {
        if(isPaused() == false)
        {
            move(2);
            vanish();
        }
    }
    
}
