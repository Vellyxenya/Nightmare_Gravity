import greenfoot.*;
import java.awt.Color;

/**
 * Sorte de rectange donnant des informations sur l'objet sélectionné.
 */
public class Informations extends Interface
{
    /**
     * Largeur du cadre
     */
    int WIDTH;
    /**
     * Hauteur du cadre
     */
    int HEIGHT;
    /**
     * Image de l'écran d'informtion
     */
    GreenfootImage image;
    /**
     * Variable délimitant la courbure aux coins de l'écran d'information
     */
    int width = 10;
    int counter;
    String text;

    /**
     * Construit l'écran d'information
     */
    public Informations(String text, int width, int height)
    {
        this.text = text;
        this.WIDTH = width;
        this.HEIGHT = height;
        image = new GreenfootImage(width, height);
        counter = 0;
        image.setTransparency(250);
        drawBackground(0, this.width, new Color(0, 85, 17, 255));
        drawBackground(4, this.width, Color.BLACK);
        image.setColor(Color.GREEN);
        setImage(image);
    }

    /**
     * Dessine des lignes verticales et horizontales sur l'écran d'information
     */
    public void act()
    {
        counter++;
        if(counter == 1 || getWorld().getObjects(Point.class).isEmpty() == true)
        {
            drawBackground(0, this.width, Color.GREEN);
            drawBackground(4, this.width, Color.BLACK);
            image.setColor(new Color(0, 255, 0, 100));
            for(int i = 1; i<Math.floor(HEIGHT/30)+1; i++)
            {
                int x = 0+Greenfoot.getRandomNumber(100);
                int y = 30*i;
                int X = WIDTH-Greenfoot.getRandomNumber(100);
                image.drawLine(x, y, X, y);
                int duration = X-x;
                getWorld().addObject(new Point(0, duration), this.getX()-this.getImage().getWidth()/2+x, this.getY()-this.getImage().getHeight()/2+y);
            }
            for(int i = 1; i<Math.floor(WIDTH/30); i++)
            {
                int x = 30*i;
                int y = 0+Greenfoot.getRandomNumber(50);
                int Y = HEIGHT+Greenfoot.getRandomNumber(200);
                image.drawLine(x, y, x, Y);
                int duration = Y-y;
                getWorld().addObject(new Point(1, duration), this.getX()-this.getImage().getWidth()/2+x, this.getY()-this.getImage().getHeight()/2+y);
            }
            writeText(text);
            setImage(image);
        }
    }

    /**
     * Dessine un rectangle avec des coins arrondis
     */
    public void drawBackground(int space, int width, Color color)
    {
        image.setColor(color);
        image.fillOval(space+0, space+0, width, width);
        image.fillOval(space+0, image.getHeight()-width-space, width, width);
        image.fillOval(image.getWidth()-width-space, space+0, width, width);
        image.fillOval(image.getWidth()-width-space, image.getHeight()-width-space, width, width);
        image.fillRect(space+width/2, space+0, WIDTH-width-2*space, HEIGHT-2*space);
        image.fillRect(space+0, space+width/2, WIDTH-2*space, HEIGHT-width-2*space);
    }
    
    /**
     * Dessine le texte à afficher sur l'écran d'information
     */
    public void writeText(String text)
    {
        GreenfootImage write = new GreenfootImage(text, 24, new Color(10, 250, 160), new Color(0,0,0,0));
        image.drawImage(write, 15, 10);
    }

}
