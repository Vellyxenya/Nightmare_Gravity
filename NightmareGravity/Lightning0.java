import greenfoot.*;

/**
 * Premier Lightning qui n'est pas enlevé à la fin de l'éclair mais caché derrière la barre de vie...
 * L'enlever engendre un bug que je ne vois pas comment résoudre autrement^^
 */
public class Lightning0 extends Weapons
{

    public Lightning0(int rotation)
    {
        setRotation(rotation);
    }

    public void act() 
    {
        if(isPaused() == false)
        {
            vanish();
        }
    }
    
}
