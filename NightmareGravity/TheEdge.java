import greenfoot.*;
import java.awt.Color;
import java.util.List;
import greenfoot.core.WorldHandler;
import javax.swing.*;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;

/**
 * Classe contenant certaines méthodes telles que le changement du curseur de la souris et l'ordre de chevauchement des acteurs.
 */
public class TheEdge extends World
{
    /**
     * Bouton servant à augmenter/diminuer le volume
     */
    public static Volume volumeObject = new Volume();
    /**
     * Bouton servant à revenir à la scène précédente
     */
    Buttons back = new Buttons();
    private static int counter;
    /**
     * Variable stockant le meilleur score de l'utilisateur
     */
    public static int topScore;
    /**
     * Variable stockant le nombre total d'ennemis éliminés
     */
    public static int kills;
    /**
     * Variable stockant le nombre total de Nightmares accomplis
     */
    public static int nightmares;   
    /**
     * Variable stockant le nombre de fois que l'utilisateur est mort
     */
    public static int deaths;
    public static GreenfootImage img1 = new GreenfootImage("MouseOff.png");
    public static GreenfootImage img2 = new GreenfootImage("MouseOn.png");
    
    /**
     * Met en place l'ordre de chevauchement des acteurs
     */
    public TheEdge()
    {    
        super(750, 563, 1);
        setPaintOrder(Point.class, Informations3.class, Buttons.class, Health.class, Energy.class, Board.class, Buttons.class, Volume.class, Pause.class, Informations.class, Informations2.class, Gears.class, Bosses.class, Weapons.class, Enemies.class, Rift1.class, Magnet.class);
    }
    
    /**
     * Méthode dessinant le bouton "back"
     */
    public void draw()
    {
        GreenfootImage newground = this.getBackground();
        newground.setColor(Color.BLACK);
        newground.drawRect(this.getBackground().getWidth()*17/20-75,getBackground().getHeight()*9/10-20, 150, 40);
        newground.fillRect(this.getBackground().getWidth()*17/20-75,getBackground().getHeight()*9/10-20, 150, 40);
        GreenfootImage textImage = new GreenfootImage("Back", 24, new Color(0, 255, 128), new Color(0, 0, 0, 0));
        newground.drawImage(textImage, this.getBackground().getWidth()*17/20-(textImage.getWidth()/2), getBackground().getHeight()*9/10-20+8);
    }
    
    /**
     * Méthode servant à revenir au menu principal
     */
    public void backToMenu()
    {
        if(Greenfoot.mouseClicked(back))
        {
            Greenfoot.setWorld(new Menu());
        }
    }

    public void act()
    {
        counter++;
        mouse();
    }
    
    /**
     * Méthode changeant l'image du curseur de la souris
     */
    public static void ChangeMouseImage(GreenfootImage image, int XClick, int YClick)
    {
        JPanel Panel = WorldHandler.getInstance().getWorldCanvas();
        Cursor Cursor;
        Toolkit Tk = Toolkit.getDefaultToolkit();
        Point CursorPoint= new Point(XClick,YClick);
        Cursor = Tk.createCustomCursor(image.getAwtImage(),CursorPoint,"Nothing");
        Panel.setCursor(Cursor);
    }

    /**
     * Méthode qui appliqué un effet spécial vers le pointeur du curseur de la souris
     */
    public void mouse()
    {
        try
        {
            MouseInfo mouse = Greenfoot.getMouseInfo();
            if(mouse != null)
            {
                if(counter%3 == 0)
                {
                    MouseEffect effect = new MouseEffect(Greenfoot.getRandomNumber(360));
                    addObject(effect, mouse.getX(), mouse.getY());
                }
            }
        }catch(IndexOutOfBoundsException e){}
    }

}
