import greenfoot.*;
import java.awt.Color;
import java.io.*;

/**
 * Pseudo du joueur affiché au-dessus du vaisseau du joueur pendant la partie
 */
public class Pseudo extends Interface
{
    /**
     * Image de l'acteur
     */
    GreenfootImage text;
    /**
     * Variable contenant le pseudo de l'utilisateur
     */
    String ID;

    /**
     * Récupère le pseudo depuis le compte de l'utilisateur stocké dans un fichier .txt
     */
    public Pseudo()
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
        text = new GreenfootImage(""+ID, 24, Color.WHITE, new Color(0, 0, 0, 0), Color.BLUE);
        setImage(text);
    }

    /**
     * Suit les mouvements du vaisseau
     */
    public void act() 
    {
        try
        {
            Gears gear = (Gears) getWorld().getObjects(Gears.class).get(0);
            setLocation(gear.getX(), gear.getY()-25);
        }
        catch(IndexOutOfBoundsException e)
        {
            getWorld().removeObject(this);
        }
    }

}