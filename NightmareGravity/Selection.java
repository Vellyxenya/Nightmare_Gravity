import greenfoot.*;
import java.awt.Color;
import java.util.List;

/**
 * Menu de sélection de vaisseau
 */
public class Selection extends TheEdge
{
    /**
     * Informations sur chaque vaisseau.
     */
    String[] text = {
            "X-Gear Tiger\n________________________\nWell-balanced\nMain: Laser\n(Mouse-click)\nSecondary: Shockwave\n(Spacebar)",
            "C26-Gear Portal\n________________________\nVery slow\nMain: Lightning\n(Mouse-click)\nSecondary: Teleportation\n(Spacebar)",
            "AZ-Gear Hunter\n________________________\nRelentless\nMain: Burst\n(Mouse-click)\nSecondary: Invulnerability\n(Spacebar)",
            "SW-Gear Hawk\n________________________\nVery maneuverable\nMain: Missile\n(Mouse-click)\nSecondary: Rain of Hawks\n(Maintain Spacebar)"};       
    /**
     * Coordonnée X de l'affichage des informations.
     */
    int xPosition = 170;
    /**
     * Coordonnée Y de l'affichage des informations.
     */
    int yPosition = 270;
    /**
     * Objet affiché au-dessus des vaisseaux pas encore débloqués
     */
    Locked l = new Locked();
    /**
     * Variable retournant si ce vaisseau est débloqué ou pas
     */
    public static boolean gear1 = true;
    /**
     * Variable retournant si ce vaisseau est débloqué ou pas
     */
    public static boolean gear2 = true;
    /**
     * Variable retournant si ce vaisseau est débloqué ou pas
     */
    public static boolean gear3 = true;
    /**
     * Variable retournant si ce vaisseau est débloqué ou pas
     */
    public static boolean gear4 = true;

    /**
     * Initialise les objets
     */
    public Selection()
    {
        addObjects();
        draw();
        setPaintOrder(Point.class, Arrows.class);
    }

    public void act()
    {
        super.act();
        ChangeMouseImage(img1, 1, 1);
        informate(300, 220);
        locked();
    }

    /**
     * Crée les flèches, le bouton de sélection ainsi que l'image des vaisseaux
     */
    public void addObjects()
    {
        ImageField image = new ImageField();
        addObject(image, 538, 285);
        RightArrow rightArrow = new RightArrow();
        addObject(rightArrow, image.getX()+125, image.getY()+140);
        LeftArrow leftArrow = new LeftArrow();
        addObject(leftArrow, image.getX()-125, image.getY()+140);
        Select select = new Select();
        addObject(select, image.getX(), image.getY()+140);
    }

    /**
     * Affiche le titre de la fenêtre
     */
    public void draw()
    {
        GreenfootImage newground = this.getBackground();
        GreenfootImage title1 = new GreenfootImage("Select your gear", 60, Color.WHITE, new Color(0, 0, 0, 0));
        GreenfootImage title2 = new GreenfootImage("Select your gear", 59, Color.BLACK, new Color(0, 0, 0, 0));
        newground.drawImage(title1, getWidth()/2-title1.getWidth()/2, 50);
        newground.drawImage(title2, getWidth()/2-title2.getWidth()/2, 50);
    }

    /**
     * Affiche les informations relatives au vaisseau concerné
     */
    public void informate(int x, int y)
    {
        switch(RightArrow.imageNumber)
        {
            case 0:
            if(getObjects(Informations.class).isEmpty())
            {
                addObject(new Informations(text[0], x, y), xPosition, yPosition);
            }
            break;
            case 1:
            if(getObjects(Informations.class).isEmpty())
            {
                addObject(new Informations(text[1], x, y), xPosition, yPosition);
            }
            break;
            case 2:
            if(getObjects(Informations.class).isEmpty())
            {
                addObject(new Informations(text[2], x, y), xPosition, yPosition);
            }
            break;
            case 3:
            if(getObjects(Informations.class).isEmpty())
            {
                addObject(new Informations(text[3], x, y), xPosition, yPosition);
            }
            break;
        }
        if(Greenfoot.mouseClicked(getObjects(RightArrow.class).get(0))||Greenfoot.mouseClicked(getObjects(LeftArrow.class).get(0)))
        {
            removeObjects(getObjects(Informations.class));
            removeObjects(getObjects(Point.class));
        } //Passe à l'information suivante
    }
    
    /**
     * Méthode bloquant les vaisseaux pas encore acquis
     */
    public void locked()
    {
        List<Locked> list = getObjects(Locked.class);
        removeObjects(list);
        switch(RightArrow.imageNumber)
        {            
            case 0:
            if(gear1 == false)
            {                
                addObject(l, 538, 285);
            }
            break;

            case 1:
            if(gear2 == false)
            {
                addObject(l, 538, 285);
            }
            break;

            case 2:
            if(gear3 == false)
            {
                addObject(l, 538, 285);
            }
            break;

            case 3:
            if(gear4 == false)
            {
                addObject(l, 538, 285);
            }
            break;
        }
    }
    
}
