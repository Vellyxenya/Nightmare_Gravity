import greenfoot.*;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Font;

/**
 * Menu affichant les statistiques de l'utilisateur.
 */
public class Stats extends TheEdge
{
    /**
     * Image de la scène
     */
    GreenfootImage image = new GreenfootImage("Stats.png");
    /**
     * Taille de la police
     */
    private final int size = 30;
    /**
     * Tableau contenant les informations affichées à l'écran
     */
    private String texts[] = {
        "Top Score :",
        "Total enemies killed :",
        "Number of Nightmares completed :",
        "Number of deaths :",
        ""+TheEdge.topScore,
        ""+TheEdge.kills,
        ""+TheEdge.nightmares,
        ""+TheEdge.deaths
    };
    
    /**
     * Initialise la scène
     */
    public Stats()
    {
        FontMetrics fms = image.getAwtImage().getGraphics().getFontMetrics(image.getFont());
        Font f = new Font("creepygirl", Font.BOLD, 64);
        FontMetrics fm = image.getAwtImage().createGraphics().getFontMetrics(f);
        image.setFont(f);
        image.setColor(Color.RED);
        String title = "S t a t i s t i c s";
        image.drawString(title, getWidth()/2-fm.stringWidth(title)/2, 100);
        for (int i = 0; i<4; i++)
        {
            image.drawImage(new GreenfootImage(texts[i], size, Color.RED, new Color(0, 0, 0, 0)),image.getWidth()/8, 200+50*i);
        }
        for (int i = 4; i<8; i++)
        {
            image.drawImage(new GreenfootImage(texts[i], size, Color.RED, new Color(0, 0, 0, 0)), image.getWidth()*7/8-2*fms.stringWidth(texts[i]), 200+50*(i-4));
        }
        setBackground(image);
        addObject(back,this.getBackground().getWidth()*17/20,getBackground().getHeight()*9/10);
        draw();
    }
    
    public void act()
    {
        backToMenu();
        ChangeMouseImage(img1, 1, 1);
    }
    
}
