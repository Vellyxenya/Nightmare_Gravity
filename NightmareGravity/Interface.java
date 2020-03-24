import greenfoot.*;
import java.util.List;

/**
 * Classe mère de tous les composants de l'interface.
 */
public class Interface extends Actor
{
    /**
     * Largeur de la scène
     */
    public int width = 750;
    /**
     * Hauteur de la scène
     */
    public int height = 563;
    /**
     * Coordonnée X exacte
     */
    private double exactX;
    /**
     * Coordonnée Y exacte
     */
    private double exactY;
    
    /**
     * Détecte si la souris est sur tel objet
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
     * Renvoie si le jeu est pause ou en exécution
     */
    public boolean isPaused()
    {
        List<Pause> pause = getWorld().getObjects(Pause.class);
        int length = pause.size();
        if (length==1)
        {
            return true;
        }
        return false;
    }
    
    /**
     * Définit les coordonnées précises d'un acteur
     */
    public void setLocation(double x, double y) 
    {
        exactX = x;
        exactY = y;
        super.setLocation((int) (x + 0.5), (int) (y + 0.5));
    }
    
    /**
     * Fait bouger l'acteur dans la direction actuelle en utilisant des coordonnées exactes
     */
    public void move(double distance)
    {
        double radians = Math.toRadians(getRotation());
        double dx = Math.cos(radians) * distance;
        double dy = Math.sin(radians) * distance;
        setLocation(exactX + dx, exactY + dy);
    }
    
}
