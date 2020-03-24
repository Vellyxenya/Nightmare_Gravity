import greenfoot.*;
import java.awt.Color;

public class CreditIcon extends SpecialBuffs
{
    int baseX;
    int baseY;
    public static int n;
    /**
     * Indique si cet objet est actuellement utilisé
     */
    public static boolean used = false;
    public static String letter = "";
    GreenfootImage image = new GreenfootImage("CreditSpell.png");
    public static GreenfootImage image2 = new GreenfootImage("CreditSpell.png");
    /**
     * Indique si cet objet peut-être changé de place
     */
    public static boolean manipulate = true;
    /**
     * Indique si cet objet est prêt à l'utilisation
     */
    public static boolean ready = true;

    public CreditIcon()
    {
        
    }

    public CreditIcon(int x)
    {
        manipulate = false;
    }

    public void act() 
    {
        if(manipulate == true)
        {
            movements();
        }
    }

    /**
     * Manipulations possible de l'icône
     */
    public void movements()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse != null)
        {
            if(Greenfoot.mouseDragged(this))
            {
                setLocation(mouse.getX(), mouse.getY());
            }
            if(Greenfoot.mouseDragEnded(this))
            {
                ItemDeck deck = (ItemDeck) getWorld().getObjects(ItemDeck.class).get(0);
                if(deck.mouseOn() == true)
                {
                    for(int i = 0; i<ItemDeck.number; i++)
                    {
                        if(getX()>deck.getX()-(ItemDeck.number*ItemDeck.side)/2 +50*i &&
                        getX()<deck.getX()-(ItemDeck.number*ItemDeck.side)/2+50*(i+1))
                        {
                            n = i;
                        }
                    }
                    setLocation(deck.getX()+25-(ItemDeck.number*ItemDeck.side)/2+50*n, deck.getY());
                    used = true;
                    setImage(image);
                }
                else
                {
                    setLocation(baseX, baseY);
                    used = false;
                    letter = "";
                    ready = true;
                    setImage(image);
                }
            }
        }
    }
    
}
