import greenfoot.*;
import java.awt.Color;

/**
 * Objet sur lequel sont peints les barres de vie et d'énergie
 */
public class Board extends Interface
{
    
    /**
     * Dessiné deux rectangles noirs destinés à contenir les barres de vie et d'énergie
     */
    public Board()
    {
        GreenfootImage background = getImage();
        background.setColor(Color.BLACK);
        background.fillRect(25, 10, 200, 20);
        background.fillRect(25, 40, 200, 20);
    }
    
}
