import greenfoot.*;
import java.awt.Color;

/**
 * Bande noire présente dans la scène Deck et affichant des messages informatifs.
 */
public class Informations3 extends Interface
{
    GreenfootImage image = new GreenfootImage(750, 50);
    private int counter;

    /**
     * Initialise l'objet
     */
    public Informations3()
    {
        counter = 0;
        image.setColor(Color.BLACK);
        image.fill();
        setImage(image);
    }

    public void act() 
    {
        counter--;
        drawInfos();
    }

    /**
     * Informe l'utilisateur de comment utiliser l'inventaire.
     */
    public void drawInfos()
    {
        Buttons button = (Buttons) getWorld().getObjects(Buttons.class).get(0);
        if(counter<=0)
        {
            if(Deck.inventory.mouseOn() == true)
            {
                image.setColor(Color.BLACK);
                image.fill();
                GreenfootImage text = new GreenfootImage("These are your items. You can use them by dragging them into \"Equipments\"", 20, Color.WHITE, new Color(0, 0, 0, 0));
                image.drawImage(text, 375-text.getWidth()/2, 15);
            }
            else if(Deck.equipments.mouseOn() == true)
            {
                image.setColor(Color.BLACK);
                image.fill();
                GreenfootImage text = new GreenfootImage("Your equiped Items. Make sure you assign a letter (Click Shift+letter on the icon)\n to \"ShielSpell\" if it's currently used", 20, Color.WHITE, new Color(0, 0, 0, 0));
                image.drawImage(text, 375-text.getWidth()/2, 5);
            }
            else
            {
                image.setColor(Color.BLACK);
                image.fill();
                GreenfootImage text = new GreenfootImage("\\(^_^)/  Welcome!  \\(^_^)/", 20, Color.WHITE, new Color(0, 0, 0, 0));
                image.drawImage(text, 375-text.getWidth()/2, 15);
            }
            if(Greenfoot.mouseClicked(button) && ShieldIcon.ready == false)
            {
                counter = 300;
            }
        }
        else
        {
            image.setColor(Color.BLACK);
            image.fill();
            GreenfootImage text = new GreenfootImage("Don't forget to assign a custom letter to \"ShielSpell\" by hovering it \n and clicking (Shift+the letter you want)", 20, Color.WHITE, new Color(0, 0, 0, 0));
            image.drawImage(text, 375-text.getWidth()/2, 5);
        }
        setImage(image);
    }

}
