import greenfoot.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

/**
 * Cinématique de fin.
 */
public class Yay extends Interface
{
    /**
     * Tableau des images utilisés dans la cinématique
     */
    GreenfootImage images[];
    /**
     * Tableau de sous-titres utilisés dans la cinématique
     */
    String subtitles[];
    /**
     * Indice de l'image/sous-titre actuel
     */
    private int number;
    /**
     * Renvoie true si l'opacité de l'image est en train d'augmenter
     */
    boolean up;
    /**
     * Définit la durée d'affichage de chaque image
     */
    private int upCounter;
    /**
     * Renvoie true si l'opacité est en trian de diminuer
     */
    boolean lighten;
    /**
     * Indique de quelle cinématique il s'agit
     */
    int i;

    /**
     * Initialise la première image de la cinématique et initialise les variables
     */
    public Yay(GreenfootImage images[], int i, String subtitles[])
    {
        number = 0;
        this.images = images;
        this.subtitles = subtitles;
        images[0].setTransparency(12);
        drawSubtitles(subtitles[number]);
        up = true;
        upCounter = 0;
        lighten = false;
        this.i = i;
    }

    /**
     * Passe fluidement d'une image à l'autre
     */
    public void act()
    {
        try
        {
            if(lighten == true)
            {
                getImage().setTransparency(getImage().getTransparency()-4);
            }
            if(getImage().getTransparency()<11)
            {
                number++;
                int trans = getImage().getTransparency();
                images[number].setTransparency(trans);
                drawSubtitles(subtitles[number]);
                up = true;
                lighten = false;
            }
            if(up == true)
            {
                getImage().setTransparency(getImage().getTransparency()+4);
            }
            if(getImage().getTransparency()>230)
            {
                up = false;
            }
            if(up == false && upCounter<300 && lighten == false)
            {
                upCounter++;
            }
            if(upCounter == 180)
            {
                lighten = true;
                upCounter = 0;
            }
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            switch(i)
            {
                case 0 : Greenfoot.setWorld(new MediumNightmare()); break;
                case 1 : Greenfoot.setWorld(new GameOver(true)); break;
            }
        }
    }

    /**
     * Centre les sous-titres en bas de l'image et dessine un contour noir autour de chaque lettre
     */
    public void drawSubtitles(String subtitles)
    {
        String parts[] = subtitles.split(":");
        Font f = new Font("BN Jinx"/*"Nasalization"*/, Font.PLAIN, 40);
        FontMetrics fm = images[number].getAwtImage().createGraphics().getFontMetrics(f);
        for(int i = 0; i<parts.length; i++)
        {
            int width = fm.stringWidth(parts[i]);
            int height = fm.getHeight();
            String text = parts[i];
            GreenfootImage img = new GreenfootImage(width+10, height+10);
            img.setFont(f);
            img.setColor(Color.WHITE);
            img.drawString(text, 5, height);
            for(int x = 1; x<width+10; x++)
            {
                for(int y = 1; y<height+10; y++)
                {
                    try
                    {
                        if((img.getColorAt(x+1, y).equals(Color.WHITE) ||
                        img.getColorAt(x-1, y).equals(Color.WHITE)||
                        img.getColorAt(x, y+1).equals(Color.WHITE)||
                        img.getColorAt(x, y-1).equals(Color.WHITE)||
                        
                        img.getColorAt(x+1, y+1).equals(Color.WHITE)||
                        img.getColorAt(x-1, y+1).equals(Color.WHITE)||
                        img.getColorAt(x+1, y-1).equals(Color.WHITE)||
                        img.getColorAt(x-1, y-1).equals(Color.WHITE) ) && !img.getColorAt(x, y).equals(Color.WHITE)
                        )
                        {
                            img.setColorAt(x, y, Color.BLACK);
                        }
                    }catch(IndexOutOfBoundsException e){}
                }
            }
            images[number].drawImage(img, images[number].getWidth()/2-img.getWidth()/2, 420+50*i);
        }
        setImage(images[number]);
    }

}
