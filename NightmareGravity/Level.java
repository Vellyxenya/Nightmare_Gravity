import greenfoot.*;
import java.awt.Color;

/**
 * Classe qui définit le niveau actuel du jeu. L'affiche à l'écran.
 */
public class Level extends Interface
{
    /**
     * Niveau de rouge
     */
    private int r;
    /**
     * Niveau de vert
     */
    private int g;
    /**
     * Niveau de bleu
     */
    private int b;
    /**
     * Indice de la phase de transition de couleur
     */
    private int phase;
    
    /**
     * Initialise l'acteur
     */
    public Level()
    {
        r = 255;
        g = 0;
        b = 0;
        phase = 1;
    }

    public void act() 
    {
        showLevel(Game.Level);
    }

    /**
     * Change graduellement la couleur de l'affichage du niveau actuel
     */
    public void showLevel(int level)
    {
        switch(phase)
        {
            case 1: b++; if(b==255)phase = 2; break;

            case 2: r--; if(r==0)phase = 3; break;

            case 3: g++; if(g==255)phase = 4; break;

            case 4: b--; if(b==0)phase = 5; break;

            case 5: r++; if(r== 255)phase = 6; break;

            case 6: g--; if(g== 0)phase = 1; break;

        }
        GreenfootImage text = new GreenfootImage("Level :  "+level, 24, new Color(r, g, b), new Color(0, 0, 0, 0));
        setImage(text);
    }

}

