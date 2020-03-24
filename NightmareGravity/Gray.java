import greenfoot.*;
import java.awt.Color;

/**
 * Objet apparaissant quand le 3e vaisseau active sa compétence d'invisibilité
 */
public class Gray extends Interface
{
    GreenfootImage image = new GreenfootImage(750, 563);
    
    public Gray()
    {
        image.setColor(new Color(100, 100, 100));
        image.fill();
        image.setTransparency(100);
        setImage(image);
    }
    
}
