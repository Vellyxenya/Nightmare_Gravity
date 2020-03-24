import greenfoot.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.io.*;

/**
 * Pseudo de l'utilisateur affiché rapidement et à plusieurs reprises juste après qu'il se soit connecté
 */
public class Names extends Interface
{
    GreenfootImage image = new GreenfootImage(280, 80);
    String ID;
    private int counter;

    /**
     * Cherche le nom de l'utilisateur dans le fichier de stockage et en construit une image rouge stylisée
     */
    public Names()
    {
        try
        {
            FileInputStream fIn = new FileInputStream(System.getProperty("user.home")+"/datas.txt");
            BufferedReader textFile = new BufferedReader(new InputStreamReader(fIn));
            String line = "";
            for(int i = 1; i<Menu.account; i++)
            {
                textFile.readLine();
            }
            String infos = textFile.readLine();
            String[] splittedInfos = infos.split(" ");
            ID = splittedInfos[0];
            textFile.close();
        }
        catch(IOException e)
        {
            System.out.println("In-Game problem, can't read the file for the pseudo...");
        }
        image.setTransparency(30+Greenfoot.getRandomNumber(80));
        image.setColor(Color.RED);
        Font f = new Font("Creepygirl", Font.BOLD, 30+Greenfoot.getRandomNumber(60));
        image.setFont(f);
        FontMetrics fm = image.getAwtImage().getGraphics().getFontMetrics(f);
        image.drawString(""+ID, 125-fm.stringWidth(""+ID)/2, 50);
        setImage(image);
        counter = 30;
    }

    /**
     * Se déplace vers le bord de l'écran et disparaît une fois l'animation terminée
     */
    public void act() 
    {
        if(Intro.ready == true)
        {
            counter++;
            if(counter >= 120)
            {
                int rotation = this.getRotation();
                int angle;
                this.turnTowards((int)(getWorld().getWidth()/2), (int)(getWorld().getHeight()/2));
                if(counter<135) move(-2);
                else if(counter>=135 && counter <175) move(-3);
                else if(counter>=175 && counter <200) move(-4);
                else move(-5);
                setRotation(0);
                if(isAtEdge())
                {
                    getWorld().removeObject(this);
                }
            }
        }
    }

}
