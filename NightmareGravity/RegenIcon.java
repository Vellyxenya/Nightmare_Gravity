import greenfoot.*;
import java.awt.Color;

public class RegenIcon extends SpecialBuffs
{
    int baseX;
    int baseY;
    public static int n;
    public static boolean used = false;
    public static String letter = "";
    GreenfootImage image = new GreenfootImage("RegenSpell.png");
    public static GreenfootImage image2 = new GreenfootImage("RegenSpell.png");
    public static boolean manipulate = true;
    public static boolean ready = true;

    public RegenIcon()
    {
        
    }

    public RegenIcon(int x)
    {
        manipulate = false;
    }

    public void act() 
    {
        if(manipulate == true)
        {
            movements();
            //attribute();
            //movements(n, used, ready, image, baseX, baseY, letter);
            //attribute(used, letter, ready, image2, image, "letter2");
        }
    }

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
                    //ready = false;
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
