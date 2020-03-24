import greenfoot.*;
import greenfoot.core.WorldHandler;  
import java.awt.event.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Font;

/**
 * Classe qui contient une aide pour le joueur en plus d'informations de l'ordre de la conception du jeu
 */
public class About extends TheEdge
{
    int location = 0;
    ScrollingListener scroll = new ScrollingListener();
    JPanel panel = WorldHandler.getInstance().getWorldCanvas();
    GreenfootImage base = getBackground();
    GreenfootImage image = new GreenfootImage(750, 563);

    public About()
    {
        Font f = new Font("creepygirl", Font.BOLD, 84);
        FontMetrics fm = base.getAwtImage().createGraphics().getFontMetrics(f);
        base.setFont(f);
        base.setColor(Color.RED);
        String title1 = "N i g h t m a r e";
        String title2 = "G r a v i t y";
        base.drawString(title1, getWidth()/2-fm.stringWidth(title1)/2, 100);
        base.drawString(title2, getWidth()/2-fm.stringWidth(title2)/2, 190);
        Font f2 = new Font("calvin", Font.BOLD, 20);
        Font f3 = new Font("cracked johnnie", Font.BOLD, 16);
        base.setFont(f2);
        base.drawString("Welcome to Nightmare Gravity, a game where you\nwill defy Gravity and face your Nightmares", 25, 285);
        base.drawString("Your first steps :", 25, 360);
        base.drawString("Here is the main menu, from whence you can access\nall the other menus :", 25, 650);
        base.drawString("The ships : ", 25, 1150);
        base.drawString("Purchase abilities :", 25, 1290);
        base.drawString("Nightmares :", 25, 1390);
        base.drawString("Final Nightmare :", 25, 1760);
        base.drawString("More :", 25, 2300);
        base.drawString("About :", 25, 2420);
        base.drawString("The Robot :", 25, 1980);
        base.drawString("History :", 25, 2120);
        base.drawString("             Press ESCAPE if you are done reading", 25, 2820);
        base.setColor(Color.WHITE);
        base.setFont(f3);
        base.drawString("A Game is composed of 9 levels. At the end of each one, you\nhave to face a Boss and defeat him to get to the next level.", 25, 390);
        base.drawString("Be careful, some Blackholes spawn from\nto time and try to attract you.", 25, 450);
        base.drawString("Colliding with a Blackhole may make you face a Nightmare or\nsimply lead you to death!.\nThe higher your level is, the lesser is your chance\nto survive a collision with a blackhole.\nCompleting a Nightmare increases your score!", 25, 510);
        base.drawString("As you start, only the first ship is available.\nEvery ship has its own abilities.\nTo acquire a new one, you have to successfully complete a\nmission and collect all the ship's components in the maze.", 25, 1180);
        base.drawString("You can purchase new abilities in the store by spending your\ncredits. Credits are droped by enemies during games", 25, 1320);
        base.drawString("In the Nightmares, you must solve a randomly generated puzzle.\nYou can rotate each piece by clicking on it.\nTo complete the puzzle, every segment of each disc\nmust be binded to the otherss", 25, 1420);
        base.drawString("In the Final Nightmare, you'll find yourself stuck in a maze.\nTo escape, you must collect all the pieces\nthat you can find in the map.\nYou must collect all of them and go\nto the exit door to escape.\nBe careful, each time you touch an enemy,\nyou lose a life... losing all of them leads to...\na beautiful screen with GAME OVER.", 25, 1790);
        base.drawString("If you encounter any problem, a bug, or if you want to suggest\nmore options and amelirations, please e-mail me at:\nnoureddach@gmail.com", 25, 2330);
        base.drawString("You are not alone, before facing each boss, Mr. R-Bot will\ngive you precious advices to help you win and continue your\nmission. Be careful, never go too close to a boss, touching it\nwill severely damage your ship!", 25, 2010);
        base.drawString("R-Bot is a poor little robot who has been taken away from\nhis planet for an unknown reason. Fortunately, he knows\nthe path and can help you in your battle.\nHowever, the kind person you are will soon regret such\na deicision. Will you survive the Gravity and be back\nhome safe?", 25, 2150);
        base.drawString("Developed by : Noureddine Gueddach\nTeacher: Laurent Bardy\nBeta Tester and adviser: Paul Bureth\nRealised within the framework of the maturity work\nCoded in Java within Greenfoot\nYear: 2015-2016\nMost of the images are from the Internet.\nSound effects are from: http://www.universal-soundbank.com/\nBackground music was realised using Rythmik Ultimate\n\nA big thank you to all who contributed in a way or the other\nto the realization of Nightmare Gravity!!!", 25, 2450);
        base.drawImage(new GreenfootImage("Trap.png"), 620, 520);
        base.drawImage(new GreenfootImage("True.png"), 420, 1510);
        base.drawImage(new GreenfootImage("False.png"), 120, 1510);
        GreenfootImage ss = new GreenfootImage("MenuSs.png");
        base.drawImage(ss, getWidth()/2-ss.getWidth()/2, 710); 
        panel.addMouseWheelListener(scroll);
        image.drawImage(base, 0, location);
        setBackground(image);
    }

    /**
     * Fait défiler l'image de cette scène de haut en bas selon le mouvement de la roue de la souris
     * Revient au menu principal en cliquant sur "escape"
     */
    public void act()
    {
        ChangeMouseImage(img1, 1, 1);
        location += scroll.getScroll();
        if(location<0)
        {
            location = 0;
        }
        if(location>160)
        {
            location = 160;
        }
        image.drawImage(base, 0, -15*location);
        setBackground(image);
        if(Greenfoot.isKeyDown("escape"))
        {
            Greenfoot.setWorld(new Menu());
        }
    }

}

/**
 * Méthodes pour détecter le mouvement de la roulette de la souris
 */
class ScrollingListener implements MouseWheelListener
{
    int amount = 0;

    public void mouseWheelMoved(MouseWheelEvent e){
        amount += e.getWheelRotation();  
        e.consume();
    }

    public int getScroll(){
        int t = amount;
        amount = 0;
        return t;
    }

}
