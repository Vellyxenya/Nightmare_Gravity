import greenfoot.*;
import java.awt.Color;

/**
 * Pièces que le personnage doit collecter dans le labyrinthe
 */
public class Pieces extends Obstacle
{
    /** True si collectable */
    public boolean generous = true;
    GreenfootImage base = getImage();
    boolean board = true;
    
    /**
     * constructeur pour les pièces collectables
     */
    public Pieces()
    {
        
    }
    
    /**
     * Constructeur pour l'objet affiché sur l'indicateur de nombre de pièces collectées
     */
    public Pieces(boolean board)
    {
        this.generous = false;
        this.board = board;
        int width = base.getWidth();
        int height = base.getHeight();
        GreenfootImage image = new GreenfootImage(width, height);
        for(int i = 0; i<width; i++)
        {
            for(int j = 0; j<height; j++)
            {
                if(base.getColorAt(i, j).equals(Color.BLACK))
                {
                    image.setColorAt(i, j, Color.WHITE);
                }
            }
        }
        setImage(image);
    }
    
    public void act()
    {
        if(board == false)
        {
            setLocation(260, 60);
        }
    }
    
}
