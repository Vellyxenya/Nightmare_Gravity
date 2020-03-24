import greenfoot.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.io.*;

/**
 * Grande version stylisée du pseudo de l'utilisateur et apparaissant juste après que ce dernier se soit logué
 * en même temps que les objets de classe Names
 */
public class Name extends Interface
{
    GreenfootImage image;
    /**
     * Son produit à l'apparition du nom
     */
    GreenfootSound sound = new GreenfootSound("Hawk.wav");
    /**
     * Pseudo de l'utilisateur
     */
    String ID;
    /**
     * police utilisée
     */
    Font f;

    /**
     * Largeur initiale de l'image
     */
    int width = 3000;
    /**
     * Hauteur initiale de l'image
     */
    int height = 1200;
    /**
     * Compteur réduisant petit à petit la taille de l'image
     */
    private int counter;
    FontMetrics fm;
    int i;

    /**
     * Construit l'image du pseudo en le récupérant dans le fichier texte de stockage
     */
    public Name()
    {
        try
        {
            i = 0;
            f = new Font("Creepygirl", Font.BOLD, 1000);
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
            try
            {
                image = new GreenfootImage(width, height);
            }catch(OutOfMemoryError e){System.gc(); Greenfoot.setWorld(new Menu());}
            image.setColor(Color.BLACK);
            image.setFont(f);
            fm = image.getAwtImage().getGraphics().getFontMetrics(f);
            image.drawString(""+ID, width/2-fm.stringWidth(""+ID)/2, height/2);
            setImage(image);
        }catch(NullPointerException e){Greenfoot.setWorld(new Menu());}
    }

    /**
     * Réduit la taille de l'image jusqu'à ce qu'elle tienne sur l'écran (Effet visuel)
     */
    public void act() 
    {
        try
        {
            if(getImage().getWidth()>500)
            {
                getImage().scale((int)(getImage().getWidth()/1.1), (int)(getImage().getHeight()/1.1));
                setImage(image);
            }
            else if(Intro.ready == false)
            {
                Intro.ready = true;
            }
            if(getImage().getWidth()>800) sound.play();
            if(getWorld().getObjects(Names.class).isEmpty())
            {
                counter++;
                if(counter == 30)
                {
                    System.gc();
                    Greenfoot.setWorld(new Menu());
                }
            }
        }catch(OutOfMemoryError e){Greenfoot.setWorld(new Menu()); System.gc();}
    }

}
