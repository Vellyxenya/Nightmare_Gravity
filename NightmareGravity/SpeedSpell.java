import greenfoot.*;
import java.awt.Color;
import java.awt.Font;

public class SpeedSpell extends SpecialBuffs
{
    GreenfootImage image = new GreenfootImage(200, 50);
    GreenfootImage icon = new GreenfootImage("SpeedSpell.png");
    GreenfootImage bigImage = new GreenfootImage(250, 50);
    GreenfootImage text = new GreenfootImage("Speed Spell", 20, Color.RED, new Color(0, 0, 0, 0));
    /**
     * Prix de la compétence
     */
    public static int SpeedSpell = 2500;
    GreenfootSound sound = new GreenfootSound("Buy.wav");

    public SpeedSpell()
    {
        image.setColor(Color.black);
        image.fillRect(50, 5, 150, 40);
        icon.scale(40, 40);
        image.drawImage(icon, 0, 5);
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

    public void show()
    {
        if(mouseOn()== true)
        {
            getWorld().addObject(button3, 325, 300);
            bigImage.drawImage(image, 0, 0);
            setImage(bigImage);
            if(this.getX()<225)
            {
                this.setLocation(this.getX()+25, getY());
            }
            button3.getImage().scale(50, 50);
            GreenfootImage text = new GreenfootImage("BUY", 24, Color.RED, new Color(0,0,0,0), Color.BLACK);
            button3.getImage().drawImage(text, 10, 10);
        }
        else if(mouseOn() == false)
        {
            getWorld().removeObject(button3);
            if(this.getX()>200)
            {
                this.setLocation(this.getX()-25, getY());
            }
            setImage(image);
            getWorld().showText("", getWorld().getWidth()/2, 500);
        }
        try
        {
            if(Greenfoot.mouseClicked(null) && button3.mouseOn())
            {
                if(Menu.previousWealth >= SpeedSpell && SpecialBuffs.SpeedSpell == false)
                {
                    Menu.previousWealth -= SpeedSpell;
                    SpecialBuffs.SpeedSpell = true;
                    DATA.storeInfos(6);
                    sound.play();
                }
                else if(Menu.previousWealth >= SpeedSpell && SpecialBuffs.SpeedSpell == true) alreadyOwned();
                else noMoney();
            }
        }catch(IllegalStateException e){}
    }
}
