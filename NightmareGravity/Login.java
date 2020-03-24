import greenfoot.*;
import javax.swing.JOptionPane;
import java.io.*;

/**
 * Bouton pour se connecter à un compte en entrant mot de passe et pseudo
 */
public class Login extends DATA
{
    GreenfootImage i1 = new GreenfootImage("Login.png");
    GreenfootImage i2 = new GreenfootImage("Login2.png");
    /**
     * Indique la ligne actuellement traîtée
     */
    public static int lineNumber = 0;

    /**
     * Initialise le bouton
     */
    public Login()
    {
        on = false;
    }

    /**
     * Détecte si le pseudo et le mot de passe correspondent
     */
    public void act()
    {
        super.act();
        changeImage(i1, i2);
        if(Greenfoot.mouseClicked(this))
        {
            login();
        }
    }

    /**
     * Contient les méthodes pour se loguer au compte à la base d'un fichier texte
     */
    public static void login()
    {
        try
        {
            lineNumber = 0;
            FileInputStream fIn = new FileInputStream(System.getProperty("user.home")+"/datas.txt");
            BufferedReader textFile = new BufferedReader(new InputStreamReader(fIn));
            String line = "";
            while ((line = textFile.readLine()) != null)
            {
                lineNumber++;
                String[] datas = line.split(" ");
                if(datas[0].equals(Background.ID)) //pseudo
                {
                    if(datas[1].equals(Background.PW)) //mot de passe
                    {
                        Menu.account = lineNumber;
                        Menu.previousWealth = Integer.parseInt(datas[2]); //crédits
                        TheEdge.topScore = Integer.parseInt(datas[3]); //score
                        if(Integer.parseInt(datas[4]) == 1)SpecialBuffs.ShieldSpell = true;
                        if(Integer.parseInt(datas[5]) == 1)SpecialBuffs.CollectSpell = true;
                        if(Integer.parseInt(datas[6]) == 1)SpecialBuffs.SpeedSpell = true;
                        if(Integer.parseInt(datas[7]) == 1)SpecialBuffs.CreditSpell = true;
                        if(Integer.parseInt(datas[8]) == 1)SpecialBuffs.RegenSpell = true;
                        if(Integer.parseInt(datas[9]) == 1)Selection.gear2 = true;
                        if(Integer.parseInt(datas[10]) == 1)Selection.gear3 = true;
                        if(Integer.parseInt(datas[11]) == 1)Selection.gear4 = true;
                        try{TheEdge.kills = Integer.parseInt(datas[12]);} catch(NumberFormatException e){TheEdge.kills = 0;}
                        try{TheEdge.nightmares = Integer.parseInt(datas[13]);} catch(NumberFormatException e){TheEdge.nightmares = 0;}
                        try{TheEdge.deaths = Integer.parseInt(datas[14]);} catch(NumberFormatException e){TheEdge.deaths = 0;}
                        textFile.close();
                        Greenfoot.setWorld(new Intro());
                        return;
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Wrong password");
                        textFile.close();
                        return;
                    }
                }
            }
            textFile.close();
            JOptionPane.showMessageDialog(null, "This account doesn't exist");
        }
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(null, "This account doesn't exist (there's no account at all)");
        }
    }

}
