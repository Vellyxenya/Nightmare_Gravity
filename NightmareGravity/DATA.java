import greenfoot.*;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.io.*;

/**
 * Contient les méthodes permettant de stocker les crédits et le mot de passe/pseudo
 */
public class DATA extends Actor
{
    /**
     * Indique si la souris est au-dessus de tel objet ou pas
     */
    public boolean on;
    GreenfootImage image = new GreenfootImage(150, 30);
    GreenfootImage image2 = new GreenfootImage("TransparentButtons.png");
    /**
     * Son joué quand la souris passe sur un bouton
     */
    GreenfootSound sound = new GreenfootSound("ButtonSound.wav");
    /**
     * Nombre maximum d'utilisateurs
     */
    public static final int MAX_USERS = 1000;
    /**
     * Nombre d'informations par utilisateur en plus du pseudo et du mot de passe
     */
    public static final int NUMBER_OF_INFOS = 15;
    /**
     * ligne actuellement traitée
     */
    public static int lineNumber;
    /**
     * Tableau contenant toutes les informations de tous les utilisateurs
     */
    public static String[][] table = new String[MAX_USERS][NUMBER_OF_INFOS+2];

    public DATA()
    {
        image.setColor(Color.WHITE);
        image.drawRect(0, 0, 149, 29);
        image2.scale(150, 30);
    }

    public void act()
    {
        track();
        makeSound();
    }

    /**
     * Stocke les crédits actuellement en possession du joueur
     */
    public static void storeWealth(int x, String input)
    {
        Menu.previousWealth += Game.credit;
        storeInfos2(x, input);
    }

    /**
     * Stocke le pseudo et le mot de passe de l'utilisateur
     */
    public static void storeIDandPW()
    {
        try
        {
            FileInputStream fIn = new FileInputStream(System.getProperty("user.home")+"/datas.txt");
            BufferedReader textFile = new BufferedReader(new InputStreamReader(fIn));
            String line = "";
            lineNumber = 0;
            while ((line = textFile.readLine()) != null)
            {
                String[] datas = line.split(" ");
                if(datas[0].equals(Background.ID))
                {
                    JOptionPane.showMessageDialog(null, "An account with this pseudo already exists,"+
                    "please choose another one");
                    textFile.close();
                    return;
                }
                lineNumber++;
            }

            BufferedWriter printWriter = new BufferedWriter(new FileWriter(System.getProperty("user.home")+
            "/datas.txt", true));
            printWriter.newLine();
            printWriter.write(Background.ID+" "+Background.PW);
            for(int j = 0; j<NUMBER_OF_INFOS; j++)
            {
                printWriter.write(" "+0);
            }
            printWriter.close();
            Menu.previousWealth = 0;
            Menu.account = lineNumber+1;
            Greenfoot.setWorld(new Intro());
            TheEdge.topScore = 0;
            TheEdge.kills = 0;
            TheEdge.nightmares = 0;
            TheEdge.deaths = 0;
        }
        catch(IOException e)
        {
            try
            {
                BufferedWriter printWriter = new BufferedWriter(new FileWriter(System.getProperty("user.home")+"/datas.txt"));
                printWriter.write("Bugged account 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0");
                printWriter.newLine();
                printWriter.write(Background.ID+" "+Background.PW);
                for(int j = 0; j<NUMBER_OF_INFOS; j++)
                {
                    printWriter.write(" "+0);
                }
                printWriter.close();
                Menu.previousWealth = 0;
                Menu.account = lineNumber+1;
                Greenfoot.setWorld(new Intro());
            }catch(IOException ex){}
        }
    }

    /**
     * Stocke les données booléennes de l'utilisateur
     */
    public static void storeInfos(int x)
    {
        try
        {
            FileInputStream fIn = new FileInputStream(System.getProperty("user.home")+"/datas.txt");
            BufferedReader textFile = new BufferedReader(new InputStreamReader(fIn));
            String line = "";
            lineNumber = 0;
            while ((line = textFile.readLine()) != null)
            {
                String[] datas = line.split(" ");
                for(int i = 0; i<NUMBER_OF_INFOS+2; i++)
                {
                    table[lineNumber][i] = datas[i];
                }
                lineNumber++;
            }
            table[Menu.account-1][x] = 1+"";
            table[Menu.account-1][2] = Menu.previousWealth+"";
            textFile.close();

            BufferedWriter printWriter = new BufferedWriter(new FileWriter(System.getProperty("user.home")+"/datas.txt"));
            for(int i = 0; i<lineNumber; i++)
            {
                for(int j = 0; j<NUMBER_OF_INFOS+2; j++)
                {
                    printWriter.write(table[i][j]+" ");
                }
                if(i<lineNumber-1)printWriter.newLine();
            }
            printWriter.close();
        }
        catch(IOException e)
        {
            System.out.println("problem with the file...");
        }
    }

    /**
     * stocke les données numériques de l'utilisateur
     */
    public static void storeInfos2(int x, String input)
    {
        try
        {
            FileInputStream fIn = new FileInputStream(System.getProperty("user.home")+"/datas.txt");
            BufferedReader textFile = new BufferedReader(new InputStreamReader(fIn));
            String line = "";
            lineNumber = 0;
            try
            {
                while ((line = textFile.readLine()) != null)
                {
                    String[] datas = line.split(" ");
                    for(int i = 0; i<NUMBER_OF_INFOS+2; i++)
                    {
                        table[lineNumber][i] = datas[i];
                    }
                    lineNumber++;
                }
            }catch(IndexOutOfBoundsException e){}
            table[Menu.account-1][x] = input;
            textFile.close();

            BufferedWriter printWriter = new BufferedWriter(new FileWriter(System.getProperty("user.home")+"/datas.txt"));
            for(int i = 0; i<lineNumber; i++)
            {
                for(int j = 0; j<NUMBER_OF_INFOS+2; j++)
                {
                    printWriter.write(table[i][j]+" ");
                }
                if(i<lineNumber-1)printWriter.newLine();
            }
            printWriter.close();
        }
        catch(IOException e)
        {
            System.out.println("problem with the file...");
        }
    }

    /**
     * indique si la souris est sur tel objet ou pas
     */
    public void track()
    {
        if(mouseOn() == false)
        {
            on = false;
        }
    }

    /**
     * Produit un son si la souris passe dessus
     */
    public void makeSound()
    {
        if(mouseOn() == true && on == false)
        {
            sound.play();
            on = true;
        }
    }

    /**
     * Retourne si la souris est sur tel objet ou pas
     */
    public boolean mouseOn()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse != null)
        {
            if( (mouse.getX() < (getX()+ this.getImage().getWidth()/2)) &&
            (mouse.getX() > (getX()- this.getImage().getWidth()/2)) &&
            (mouse.getY() < (getY()+ this.getImage().getHeight()/2)) &&
            (mouse.getY() > (getY()- this.getImage().getHeight()/2)))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Change l'image de tel acteur si la souris est dessus
     */
    public void changeImage()
    {
        if(mouseOn() == false)
        {
            setImage(image2);
        }
        else if(mouseOn() == true)
        {
            setImage(image);
        }
    }

    /**
     * Change l'image du curseur de la souris selon si elle est sur un acteur ou pas
     */
    public void changeImage(GreenfootImage i1, GreenfootImage i2)
    {
        if(mouseOn() == true) setImage(i2);
        else setImage(i1);
    }

}
