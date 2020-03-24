import greenfoot.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.util.List;
import java.io.*;

/**
 * Objet apparaissant dans l'introduction et sur lequel s'imprime le pseudo de l'utilisateur
 */
public class PseudoAnim extends Interface
{
    GreenfootImage image;
    /**
     * Nombre de l'utilisateur
     */
    String ID;
    private int counter;
    /**
     * Variable contenant la coordonnée X potentielle pour chaque inscription
     */
    int focusX;
    /**
     * Variable contenant la coordonnée Y potentielle pour chaque inscription
     */
    int focusY;
    Name name = new Name();
    
    /**
     * Trouve le pseudo de l'utilisateur dans le fichier texte de stockage et initialise les variables
     */
    public PseudoAnim()
    {
        focusX = 0;
        focusY = 0;
        image = new GreenfootImage(750, 563);
        counter = 0;
        try
        {
            FileInputStream fIn = new FileInputStream(System.getProperty("user.home")+"/datas.txt");
            BufferedReader textFile = new BufferedReader(new InputStreamReader(fIn));
            String line = "";
            for(int i = 0; i<Menu.account-1; i++)
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
        image.setTransparency(50);
        image.setColor(Color.RED);
        int random = Greenfoot.getRandomNumber(7);
        Font f = new Font("Creepygirl", Font.BOLD, 30);
        image.setFont(f);
        FontMetrics fm = image.getAwtImage().getGraphics().getFontMetrics(f);
        setImage(image);
    }

    /**
     * L'impression répétitive du pseudo est de plus en plus centrée
     */
    public void act() 
    {
        counter++;
        if(counter%5 == 0)
        {
            for(int i=0; i<5; i++)
            {
                animate2();
            }
            if(focusX<375)
                focusX += 5;
            if(focusY<282)
                focusY += 4;
        }
    }

    /**
     * Invoque des représentations du pseudo à chaque act
     */
    public void animate2()
    {
        try
        {
            getWorld().addObject(new Names(), focusX+50+Greenfoot.getRandomNumber(650-2*focusX), focusY+10+Greenfoot.getRandomNumber(550-2*focusY));
        }

        catch(IllegalArgumentException e)
        {
            getWorld().addObject(name, getWorld().getWidth()/2, getWorld().getHeight()/2);
        }
    }
    
}