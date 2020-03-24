import greenfoot.*;
import java.util.List;
import java.awt.Color;
import java.util.Collections;
import java.util.ArrayList;

/**
 * Capture d'écran qui se désature en quelques secondes et marquant ainsi la fin de la partie
 */
public class Screenshot extends Interface
{
    GreenfootImage image;
    private int counter;
    GreenfootSound sound = new GreenfootSound("Finish.wav");
    
    public Screenshot(GreenfootImage image, List actors)
    {
        this.image = image;
        List<Actor> actorss = actors;
        for(Actor actor : actorss)
        {
            GreenfootImage img = actor.getImage();
            int rotation = actor.getRotation();
            img.rotate(rotation);
            image.drawImage(img, actor.getX()-img.getWidth()/2, actor.getY()-img.getHeight()/2);
        }
        setImage(image);
        sound.play();
    }
    
    /**
     * Passe à la fenêtre GameOver après un petit moment
     */
    public void act() 
    {
        if(counter == 0)
        {
            Game.gameMusic.stop();
        }
        grayScale();
        counter++;
        if(counter == 60)
        {
            Greenfoot.setWorld(new GameOver());
        }
    }
    
    /**
     * Algorithme pour désaturer une image
     */
    public void grayScale()
    {
        for(int i = 0; i<750; i++)
        {
            for(int j = 0; j<563; j++)
            {
                ArrayList<Integer> arrayList = new ArrayList<Integer>();
                arrayList.add(this.getImage().getColorAt(i, j).getRed());
                arrayList.add(this.getImage().getColorAt(i, j).getGreen());
                arrayList.add(this.getImage().getColorAt(i, j).getBlue());
                Integer max = Collections.max(arrayList);
                int r = arrayList.get(0);
                int g = arrayList.get(1);
                int b = arrayList.get(2);
                int difR = max-r;
                int difG = max-g;
                int difB = max-b;
                r = r + (int)(difR*counter/60);
                g = g + (int)(difG*counter/60);
                b = b + (int)(difB*counter/60);
                this.getImage().setColorAt(i, j, new Color(r, g, b));
            }
        }
    }
    
}
