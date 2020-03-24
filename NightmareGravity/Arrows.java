import greenfoot.*;
import java.awt.Color;

/**
 * Flèches se trouvant dans le menu sélection
 */
public class Arrows extends Interface
{
    /**
     * Numéro du vaisseau par défaut, c'est à dire le premier
     */
    public static int imageNumber = 0;
    public GreenfootSound sound = new GreenfootSound("Arrow.wav");
    
    public Arrows()
    {
        drawArrow(new Color(0, 0, 120));
    }
    
    /**
     * Produit un son si l'utilisateur clique sur ce bouton
     */
    public void makeSound()
    {
        if(Greenfoot.mouseClicked(this))
        {
            sound.play();
        }
    }
    
    /**
     * Dessine une flèche avec la couleur donnée
     */
    public void drawArrow(Color color)
    {
        GreenfootImage image = new GreenfootImage(100, 30);
        image.setColor(color);
        image.fillRect(20, 5, 50, 20);
        int[] x = {60, 89, 60};
        int[] y = {0, 15, 29};
        image.fillPolygon(x, y, 3);
        setImage(image);
    }
    
}
