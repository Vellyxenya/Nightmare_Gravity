import greenfoot.*;
import java.awt.Font;
import java.awt.Color;

/**
 * Affiche à l'écran le nombre de vies restantes et le nombre de pièces collectées
 */
public class LabyBoard extends Interface
{
    GreenfootImage image = new GreenfootImage(250, 70);

    public LabyBoard()
    {
        image.setFont(new Font("calvin", Font.BOLD, 16));
        image.setColor(Color.WHITE);
        draw();
    }

    public void act() 
    {
        draw();
    }

    /**
     * Ecrine le nb de vies restant et le nombre de pièces collectées
     */
    public void draw()
    {
        image.clear();
        image.drawString("Lives : "+Game.lives, 5, 20);
        image.drawString("Collected parts : "+Roamer.collect+"/6", 5, 55);
        setImage(image);
    }

}
