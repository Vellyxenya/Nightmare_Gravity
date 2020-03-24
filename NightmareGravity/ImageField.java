import greenfoot.*;

/**
 * Objet affichant 1 des 4 images des vaisseaux dans le menu sélection
 */
public class ImageField extends Interface
{
    /**
     * Tableau contenant les images des 4 vaisseaux
     */
    String[] gears = {"Vaisseau1.png", "Vaisseau2.png", "Vaisseau3.png", "Vaisseau4.png"};
    
    public void act() 
    {
        showGear();
    }
    
    /**
     * Sélectionne l'image à afficher
     */
    public void showGear()
    {
        setImage(gears[RightArrow.imageNumber]);
    }
    
}
