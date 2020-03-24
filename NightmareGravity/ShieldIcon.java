import greenfoot.*;
import java.awt.Color;
//import java.*;

public class ShieldIcon extends SpecialBuffs
{
    int baseX;
    int baseY;
    public static int n;
    public static boolean used = false;
    public static String letter = "";
    GreenfootImage image = new GreenfootImage("ShieldSpell.png");
    public static GreenfootImage image2 = new GreenfootImage("ShieldSpell.png");
    public static boolean manipulate = true;
    public static boolean ready = true;

    public ShieldIcon()
    {

    }

    public ShieldIcon(int x)
    {
        manipulate = false;
    }

    public void act() 
    {
        if(manipulate == true)
        {
            movements();
            attribute();
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
                    ready = false;
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

    /**
     * Permet à l'utilisateur d'attribuer une lettre à cette compétence, afin qu'il puisse 
     * s'en servir
     */
    public void attribute()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse != null)
        {
            if(this.mouseOn() == true && used == true && Greenfoot.isKeyDown("shift"))
            {
                String keyName = Greenfoot.getKey();
                if(keyName == null) return;
                if (keyName != null)
                {
                    if(keyName.length()<2)
                    {
                        letter = keyName;
                        Gears.letter = keyName;
                        ready = true;
                        image2.clear();
                        image2.drawImage(image, 0, 0);
                        GreenfootImage text = new GreenfootImage(keyName, 40,
                        Color.BLACK, new Color(0,0,0,0));
                        image2.drawImage(text, 25-text.getWidth()/2,
                        25-text.getHeight()/2);
                        setImage(image2);
                    }
                }
            }
        }
    }
    
}
