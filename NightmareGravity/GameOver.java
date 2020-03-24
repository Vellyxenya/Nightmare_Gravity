import greenfoot.*;
import java.awt.Color;
import java.io.*;

/**
 * World de fin de partie, montre au joueur les statistiques de la partie et lui propose de recommencer.
 */
public class GameOver extends TheEdge
{
    /**
     * Bouton permettant de revenir au menu principal
     */
    Buttons button = new Buttons();
    /**
     * Score du joueur
     */
    public static int score;
    /**
     * Meilleur score du joueur toutes parties confendues
     */
    public static int previousScore;

    /**
     * Ecran de fin de partie.
     */
    public GameOver()
    {
        TheEdge.deaths++;
        finishAndStore();
    }

    /**
     * Ecran de fin de partie.
     * @param lost : true si le joueur a perdu la partie, false si le joueur a gagné
     */
    public GameOver(boolean win)
    {
        finishAndStore();
    }
    
    /**
     * Sauvegarde les données de la partie
     */
    public void finishAndStore()
    {
        Game.gameMusic.stop();
        score = (int)Math.floor(Game.time+Game.killedEnemies*3*(1+Game.completedNightmares)*((6-Game.usedAbilities)/6))*Game.Level;
        TheEdge.kills += Game.killedEnemies;
        drawText();
        setPaintOrder(Buttons.class);
        try
        {
            FileInputStream fIn = new FileInputStream(System.getProperty("user.home")+"/datas.txt");
            BufferedReader textFile = new BufferedReader(new InputStreamReader(fIn));
            String line = "";
            for(int i = 1; i<Menu.account; i++)
            {
                textFile.readLine();
            }
            String infos = textFile.readLine();
            String[] splittedInfos = infos.split(" ");
            String wanted = splittedInfos[3];
            previousScore = Integer.parseInt(wanted);
            textFile.close();
        }
        catch(IOException e)
        {
            return;
        }
        if(score>previousScore)
        {
            TheEdge.topScore = score;
            DATA.storeInfos2(3, Integer.toString(score));
        }
        DATA.storeInfos2(12, Integer.toString(TheEdge.kills));
        DATA.storeInfos2(14, Integer.toString(TheEdge.deaths));
        DATA.storeWealth(2, Integer.toString(Menu.previousWealth+Game.credit));
    }

    public void act()
    {
        super.act();
        replay();
        ChangeMouseImage(img1, 1, 1);
    }

    /**
     * Affiche les statistiques du joueur
     */
    public void drawText()
    {
        GreenfootImage newground = this.getBackground();
        newground.drawRect(getWidth()/2+25, getHeight()*3/4+15, 150, 40);
        newground.fillRect(getWidth()/2+25, getHeight()*3/4+15, 150, 40);
        GreenfootImage text1 = new GreenfootImage("Time :                     " +Game.time, 30, Color.WHITE, new Color(0, 0, 0, 0));
        getBackground().drawImage(text1, getWidth()/2, getHeight()/4);
        GreenfootImage text2 = new GreenfootImage("Killed Enemies :  " +Game.killedEnemies, 30, Color.WHITE, new Color(0, 0, 0, 0));
        getBackground().drawImage(text2, getWidth()/2, getHeight()/4+50);
        GreenfootImage text3 = new GreenfootImage("Score :                   " +score, 30, Color.WHITE, new Color(0, 0, 0, 0));
        getBackground().drawImage(text3, getWidth()/2, getHeight()/4+100);
        GreenfootImage text4 = new GreenfootImage("Try again", 30, Color.BLUE, new Color(0, 0, 0, 0));
        getBackground().drawImage(text4, getWidth()/2-text4.getWidth()/2+100, getHeight()*3/4+20);
        addObject(button, getWidth()/2+100, getHeight()*3/4+35);
    }

    /**
     * Propose au joueur de recommencer une nouvelle partie
     */
    public void replay()
    {
        if(Greenfoot.mouseClicked(button))
        {
            Greenfoot.setWorld(new Menu());
        }
    }

}
