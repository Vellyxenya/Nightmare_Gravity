import greenfoot.*;
import java.awt.Color;

/**
 * Fenêtre affichant des cinématiques.
 */
public class TheEnd extends TheEdge
{
    /**
     * Tableau d'images affichées après la fin de la mission mais avant le labyrinthe
     */
    GreenfootImage images0[] = {new GreenfootImage("A.png"), new GreenfootImage("B.png"),
            new GreenfootImage("C.png"), new GreenfootImage("D.png")};
    /**
     * Tableau d'images affichées à la toute fin de la partie (après le labyrinthe)
     */
    GreenfootImage images1[] = {new GreenfootImage("E.png"), new GreenfootImage("F.png"),
            new GreenfootImage("G.png"), new GreenfootImage("H.png"),
            new GreenfootImage("I.png"), new GreenfootImage("J.png"),
            new GreenfootImage("K.png"), new GreenfootImage("L.png")};
    /**
     * paramètre indiquant quel tableau d'images afficher
     */
    int i;
    /**
     * Tableau affichant les sous-titres
     * @see #images0[]
     */
    String subtitles0[] = {"I've been betrayed", "caught and tortured,:used as a coboye.", "But one day, a stranger came:and took my chains off", "I finally had my chance:to escape this hell!"};
    /**
     * Tableau affichant les sous-titres
     * @see #images0[]
     */
    String subtitles1[] = {"No one were going to:stop me", "I unleashed the pain of all:these years", "On everyone who stood in:my way to...", "FREEDOM",
            "It's time to fly away", "But I'll never forget:this place", "I may come again to take:my revenge", "but for now, I'm just:happy I'm alive"};

    /**
     * Choisit les images à afficher 
     * @param i : indice indiquant quel tableau d'images choisir
     */
    public TheEnd(int i)
    {
        getBackground().setColor(Color.WHITE);
        getBackground().fill();
        this.i = i;
        if(i == 0) addObject(new Yay(images0, i, subtitles0), getWidth()/2, getHeight()/2);
        else if(i == 1) addObject(new Yay(images1, i, subtitles1), getWidth()/2, getHeight()/2);
        Game.gameMusic.stop();
    }

}