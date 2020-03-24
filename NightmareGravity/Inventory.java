import greenfoot.*;
import java.awt.Color;

/**
 * Tableau contenant les objets acquis.
 */
public class Inventory extends Interface
{
    /**
     * Largeur du tableau
     */
    int width = 250;
    /**
     * Hauteur du tableau
     */
    int height = 50;
    GreenfootImage image = new GreenfootImage(width, height);
    
    /**
     * Dessine les cases du tableau
     */
    public Inventory()
    {
        image.setColor(new Color(160, 180, 200));
        image.fillRect(0, 0, width-1, height-1);
        image.setTransparency(150);
        image.setColor(Color.BLACK);
        image.drawRect(0, 0, width-1, height-1);
        for(int i = 0; i<5; i++)
        {
            image.drawRect(i*height, 0, height, height);
        }
        setImage(image);
    }
    
}
