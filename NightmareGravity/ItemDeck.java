import greenfoot.*;
import java.awt.Color;

/**
 * Objet contenant les compétences équipées
 */
public class ItemDeck extends Interface
{
    /**
     * Longueur du côté de chaque case
     */
    public static int side = 50;
    /**
     * Nombre de cases
     */
    public static int number = 5;
    GreenfootImage image = new GreenfootImage(side, side);
    GreenfootImage base = new GreenfootImage(side*number, side);
    
    /**
     * Crée un tableau avec "number" cases de "side" de côté
     */
    public ItemDeck()
    {
        for(int i = 0; i<number; i++)
        {
            image.setColor(new Color(160, 180, 200));
            image.fillRect(0, 0, side-1, side-1);
            image.setTransparency(150);
            image.setColor(Color.BLACK);
            image.drawRect(0, 0, side-1, side-1);
            base.drawImage(image, i*side, 0);
        }
        setImage(base);
    }

}
