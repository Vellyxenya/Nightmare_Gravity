import greenfoot.*;
import java.awt.List;
import java.awt.Font;
import java.awt.Color;
import java.awt.FontMetrics;

/**
 * Monde précédant le Nightmare et dans lequel le joueur a un pourcentage de chance de passer au défi
 * ou de perdre immédiatement (le pourcentage dépend du niveau)
 */
public class Nightmare extends TheEdge
{
    int[]values = new int[10];
    /**
     * 10-niveau du joueur
     */
    int level;
    GreenfootImage base = getBackground();

    /**
     * Construit la scène avec les 10 cases à choix
     */
    public Nightmare(int level)
    {
        Game.gameMusic.stop();
        this.level = level;
        while(numberOfOne(values) < this.level)
        {
            int random = Greenfoot.getRandomNumber(10);
            if(values[random] != 1) values[random] = 1;
        }
        for(int i = 0; i<10; i++)
        {
            if(values[i]==1) addObject(new Rect(1), 80+65*i, getHeight()/2);
            else addObject(new Rect(0), 80+65*i, getHeight()/2);
        }
        Font f = new Font("creepygirl", Font.BOLD, 86);
        FontMetrics fm = base.getAwtImage().createGraphics().getFontMetrics(f);
        base.setFont(f);
        base.setColor(Color.RED);
        String title = "N i g h t m a r e";
        String[] texts = {"Here are 10 squares, "+level+" of them are safe,",
                "The others will make this world forget you forever",
                "Be ready to die, you'll LOSE !"};
        base.drawString(title, getWidth()/2-fm.stringWidth(title)/2, 150);
        Font f2 = new Font("creepygirl", Font.BOLD, 44);
        base.setFont(f2);
        FontMetrics fm2 = base.getAwtImage().createGraphics().getFontMetrics(f2);
        for(int i = 0; i<texts.length; i++)
        {
            base.drawString(texts[i], getWidth()/2-fm2.stringWidth(texts[i])/2, 400+50*i);
        }
        setBackground(base);
    }

    /**
     * Compte le nombre de 1 dans un tableau de nombres entiers
     */
    public int numberOfOne(int array[])
    {
        int number = 0;
        for(int i = 0; i<array.length; i++)
        {
            if(array[i] == 1)
            {
                number++;
            }
        }
        return number;
    }

    /**
     * Change l'image du curseur de la souris
     */
    public void act()
    {
        ChangeMouseImage(img1, 1, 1);
    }

}
