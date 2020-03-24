import greenfoot.*;
import java.awt.Color;

/**
 * Objet créé dans le monde lorsque la touche "P" est cliquée, met le jeu temporairement en pause.
 */
public class Pause extends Interface
{
    private int width = 150;
    private int height = 40;
    /**
     * Titres affichés sur le menu pause
     */
    String[] titles = {"Resume", "Restart", "Leave Game"};

    public Pause()
    {
        addButtons();
    }

    /**
     * Ajoute les différents boutons au menu pause
     */
    private void addButtons()
    {
        GreenfootImage image = this.getImage();
        for(int i=0; i<3; i++)
        {
            image.drawRect(image.getWidth()/2-width/2, image.getHeight()*3/10+70*i, width, height);
            image.fillRect(image.getWidth()/2-width/2, image.getHeight()*3/10+70*i, width, height);
            GreenfootImage textImage = new GreenfootImage(titles[i], 24, new Color(100, 255, 128), new Color(0, 0, 0, 0));
            image.drawImage(textImage, image.getWidth()/2-(textImage.getWidth()/2), image.getHeight()*3/10+70*i+8);
        }
    }

}
