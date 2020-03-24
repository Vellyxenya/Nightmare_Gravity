import greenfoot.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

/**
 * Donne au joueur des informations sur le monstre à affronter. Le texte est affiché lettre après lettre.
 */
public class Informations2 extends Interface
{
    /**
     * Largeur de la fenêtre d'affichage
     */
    int WIDTH;
    /**
     * Hauteur de la fenêtre d'affichage
     */
    int HEIGHT;
    /**
     * Courbure des coins du rectangle d'affichage
     */
    int width = 10;
    /**
     * Texte à afficher
     */
    String text;
    /**
     * Compteur gérant l'affichage des lettres
     */
    int counter;
    /**
     * Indice de la lettre en cours de traitement
     */
    int i = 0;
    /**
     * Image du rectangle d'affichage
     */
    GreenfootImage image;
    /**
     * Tableau de tous les caractères du message
     */
    char dimension[][];
    /**
     * Nombre de caractères maximum par ligne
     */
    int x;
    int a;
    int b;
    int j;
    int c;
    int y;
    int h;
    
    /**
     * Initialise l'image en prenant en compte la longueur du message et des retours à la ligne forcés
     */
    public Informations2(String text)
    {
        counter = 0;
        char table[] = text.toUpperCase().toCharArray();
        dimension = new char[30][(int)Math.ceil(table.length/30)+2];
        int a = 0;
        int b = 0;
        int j = 0;
        int c = 0;
        x = 0;
        y = 0;
        h = 0;
        for(int i = 0; i<table.length; i++)
        {
            if(table[i] == ' ')
            {   
                dimension[a][b] = table[i];
                a++;
            }
            else if(table[i] != ' ')
            {
                c = i;
                j = 0;
                while(table[c] != ' ')
                {
                    if(c<table.length)
                    {
                        c++;
                        j++;
                    }
                }
                if(j<30-a)
                {
                    dimension[a][b] = table[i];
                    a++;
                }
                else
                {
                    b++;
                    a = 0;
                    dimension[a][b] = table[i];
                    a++;
                }
            }
            if(a==30)
            {
                a = 0;
                b++;
            }
        }
        h = b;
        image = new GreenfootImage(390, 18*b+36);
        drawBackground(0, 10, Color.GREEN);
        drawBackground(4, 10, Color.BLACK);
        setImage(image);
    }

    public void act() 
    {
        if(isPaused() == false)
        {
            disappear();
            write();
        }
    }

    /**
     * Dessine le pourtour du rectangle avec des coins arrondis
     */
    public void drawBackground(int space, int width, Color color)
    {
        image.setColor(color);
        image.fillOval(space+0, space+0, width, width);
        image.fillOval(space+0, image.getHeight()-width-space, width, width);
        image.fillOval(image.getWidth()-width-space, space+0, width, width);
        image.fillOval(image.getWidth()-width-space, image.getHeight()-width-space, width, width);
        image.fillRect(space+width/2, space+0, 390-width-2*space, 36+18*(h)-2*space);
        image.fillRect(space+0, space+width/2, 390-2*space, 36+18*(h)-width-2*space);
    }

    /**
     * Ecrit la prochaine lettre du message à chaque act
     */
    public void write()
    {
        try
        {
            GreenfootImage letter = new GreenfootImage(12, 18);
            letter.setColor(Color.GREEN);
            Font f = new Font("SANS_SERIF", Font.BOLD, 13);
            letter.setFont(f);
            FontMetrics fm = image.getAwtImage().getGraphics().getFontMetrics(f);
            letter.drawString(String.valueOf(dimension[x][y]), 6-fm.stringWidth(String.valueOf(dimension[x][y]))/2, 10);
            image.drawImage(letter, 15+x*12, 13+y*18);
            x++;
            if(x== 30)
            {
                y++;
                x=0;
            }
            setImage(image);
        }catch(ArrayIndexOutOfBoundsException e){}
    }

    /**
     * l'écran disparaît quand l'utilisateur presse sur "Enter"
     */
    public void disappear()
    {
        if(Greenfoot.isKeyDown("enter"))
        {
           getWorld().removeObject(this);
        }
    }

}
