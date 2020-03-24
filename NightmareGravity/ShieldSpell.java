import greenfoot.*;
import java.awt.Color;
import java.awt.Font;

/**
 * Compétence pour générer un bouclier.
 */
public class ShieldSpell extends SpecialBuffs
{
    GreenfootImage image = new GreenfootImage(200, 50);
    GreenfootImage icon = new GreenfootImage("ShieldSpell.png");
    GreenfootImage bigImage = new GreenfootImage(250, 50);
    GreenfootImage text = new GreenfootImage("Shield Spell", 20, Color.RED, new Color(0, 0, 0, 0));
    /**
     * Prix de la compétence
     */
    public static int ShieldSpell = 1500;
    GreenfootSound sound = new GreenfootSound("Buy.wav");

    public ShieldSpell()
    {
        image.setColor(Color.black);
        image.fillRect(50, 5, 150, 40);
        image.drawImage(icon, 0, 0);
        image.setColor(Color.RED);
        text.setFont(new Font("Arial", Font.BOLD+Font.ITALIC, 20));
        image.drawImage(text, 60, 15);
        setImage(image);
    }

    public void act() 
    {
        show();
        track();
        makeSound();
    }

    public void show() //Dessine la compétence et permet à l'utilisateur d'intéragir avec, de l'acheter etc..
    {
        if(mouseOn()== true)
        {
            getWorld().addObject(button1, 325, 200);
            bigImage.clear();
            bigImage.drawImage(image, 0, 0);
            setImage(bigImage);
            if(this.getX()<225)
            {
                this.setLocation(this.getX()+25, getY());
            }
            button1.getImage().scale(50, 50);
            GreenfootImage text = new GreenfootImage("BUY", 24, Color.RED, new Color(0,0,0,0), Color.BLACK);
            button1.getImage().drawImage(text, 10, 10);
        }
        else if(mouseOn() == false)
        {
            getWorld().removeObject(button1);
            if(this.getX()>200)
            {
                this.setLocation(this.getX()-25, getY());
            }
            setImage(image);
            getWorld().showText("", getWorld().getWidth()/2, 500);
        }
        try
        {
            if(Greenfoot.mouseClicked(null) && button1.mouseOn())
            {
                if(Menu.previousWealth >= ShieldSpell && SpecialBuffs.ShieldSpell == false)
                {
                    Menu.previousWealth -= ShieldSpell;
                    SpecialBuffs.ShieldSpell = true;
                    DATA.storeInfos(4);
                    sound.play();
                }
                else if(Menu.previousWealth >= ShieldSpell && SpecialBuffs.ShieldSpell == true) alreadyOwned();
                else noMoney();
            }
        }catch(IllegalStateException e){}
    }

    //Les autres compétences ont le même mode de fonctionnement.
}
