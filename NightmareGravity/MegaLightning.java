import greenfoot.*;

/**
 * Objet qui recouvre toute la map et qui inflige des dégâts quand le joueur touche un Cirlce
 * (Boss3). L'objet apparait, sa transparence augmetne jusqu'à ce qu'il disparasse
 */
public class MegaLightning extends SpecialEffects
{
    /**
     * Compteur pour la transparence de l'image
     */
    private int counter;
    /**
     * Dégâts de l'objet
     */
    public static int damageLight = 1;

    public MegaLightning()
    {
        counter = 255;
    }

    public void act() 
    {
        getImage().setTransparency(counter); 
        counter -= 5;
        if(getImage().getTransparency() <= 0)
        {
            getWorld().removeObject(this);
        }
    }
    
}
