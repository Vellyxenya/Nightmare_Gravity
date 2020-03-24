import greenfoot.*;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JOptionPane;
import java.io.*;

/**
 * Tout premier écran de la partie, contient les champs de pseudo/mot de passe ainsi que les boutons
 * Login et Register
 */
public class Background extends TheEdge
{
    GreenfootImage background = getBackground();
    /**
     * Pseudo de l'utilisateur
     */
    public static String ID;
    /**
     * Mot de passe de l'utilisateur, stocké mais non affiché
     */
    public static String PW;
    /**
     * Mot de passe de l'utilisateur affiché en astérisques
     */
    public static String PWFake;
    /**
     * Maximum de caractères autorisés pour le pseudo
     */
    private final int MAX_SIZE_ID = 12;
    /**
     * Maximum de caractères autorisés pour le mot de passe
     */
    private final int MAX_SIZE_PW = 12;
    /**
     * Variable stockant la dernière touche invoquée
     */
    public static String keyName;

    /**
     * Construit les objets et initialise les valeurs des pseudo/mot de passe
     * Initialise également les images pour l'animation de début de partie (ceci afin que
     * l'initialisation ne se fasse qu'une seule fois.
     */
    public Background()
    {
        ID = "";
        PW = "";
        PWFake = "";
        Title1 title = new Title1();
        addObject(title, getWidth()/2, getHeight()/5);
        background.fillRect(300, getHeight()/2-15, 150, 30);
        background.fillRect(300, getHeight()/2+25, 150, 30);
        background.setFont(new Font("NasalizationRg-Regular", Font.PLAIN, 28));
        background.setColor(Color.BLACK);
        background.drawString("Username ", 140, 290);
        background.drawString("Password ", 140, 330);
        addObject(new IDButton(), getWidth()/2, getHeight()/2);
        addObject(new PWButton(), getWidth()/2, getHeight()/2+40);
        addObject(new Register(), getWidth()/2, 440);
        addObject(new Login(), getWidth()/2, 380);
        setActOrder(this.getClass());
        try
        {
            File file = new File(System.getProperty("user.home")+"/datas.txt");
            if(!file.exists())
            {
                file.createNewFile();
                BufferedWriter printWriter = new BufferedWriter(new FileWriter(System.getProperty("user.home")+"/datas.txt"));
                printWriter.write("Bugged account 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0");
                printWriter.close();
            }
        }catch(IOException e){System.out.println("problem");}
    }

    public void act()
    {
        trackMovements();
        show();
    }

    /**
     * Méthode permettant à l'utilisateur d'interagir avec l'interface: cliques, mouvements, souris, 
     * effets visuels...
     */
    public void trackMovements()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        IDButton id = (IDButton) getObjects(IDButton.class).get(0);
        PWButton pw = (PWButton) getObjects(PWButton.class).get(0);
        Register reg = (Register) getObjects(Register.class).get(0);
        Login log = (Login) getObjects(Login.class).get(0);
        if(mouse != null && id.mouseOn() == false && pw.mouseOn() == false && reg.mouseOn() == false && log.mouseOn() == false)
        {
            MouseEffect effect = new MouseEffect(Greenfoot.getRandomNumber(360));
            addObject(effect, mouse.getX(), mouse.getY());
            ChangeMouseImage(img1, 1, 1);
        }
        else
        {
            ChangeMouseImage(img2, 1, 1);
        }
        if(Greenfoot.mouseClicked(this)) // détecte si l'utilisateur a cliqué sur le champ dédié au mot de passe/pseudo
        {
            IDButton.available = false;
            PWButton.available = false;
        }
        if(IDButton.available == true)
        {
            writeID();
        }
        else if(PWButton.available == true)
        {
            writePW();
        }
        if(id.mouseOn() == false && pw.mouseOn() == false && Greenfoot.mouseClicked(null))
        {
            IDButton.available = false;
            PWButton.available = false;
        }
    }

    /**
     * Méthode affichant le pseudo ainsi que les astérisques à l'écran
     */
    public void show()
    {
        showText(ID, getWidth()/2, getHeight()/2);
        showText(PWFake, getWidth()/2, getHeight()/2+40);
    }

    /**
     * Méthode qui écrit le pseudo et le stocke dans une variable,
     * certains caractères sont interdits
     * et la longueur du pseudo est limitée
     */
    public void writeID()
    {
        keyName = Greenfoot.getKey();
        if(keyName == null) return;
        if (keyName != null)
        {
            if(keyName.equals("backspace"))
            {
                if(ID.length()>0)
                {
                    ID = ID.substring(0, ID.length()-1);
                }
                else if(ID.length() == 0)
                {
                    return;
                }
            }
            else if(keyName.length()<2)
            {
                if(ID.length()<MAX_SIZE_ID)
                {
                    ID+=keyName;
                }
            }
        }
    }

    /**
     * Méthode qui écrit le mot de passe et le stocke dans une variable, certains caractères sont interdits
     * et la longueur du mot de passe est limitée. Seul un code en astérisques est affiché
     * à l'écran
     */
    public void writePW()
    {
        String keyName = Greenfoot.getKey();
        if(keyName == null) return;
        if (keyName != null)
        {
            if(keyName.equals("backspace"))
            {
                if(PW.length()>0)
                {
                    PW = PW.substring(0, PW.length()-1);
                    PWFake = PWFake.substring(0, PWFake.length()-1);
                }
                else if(PW.length() == 0)
                {
                    return;
                }
            }
            else if(keyName.length()<2)
            {
                if(PW.length()<MAX_SIZE_PW)
                {
                    PWFake+="*";
                    PW+=keyName;
                }
            }
        }
    }

}
