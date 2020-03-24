import greenfoot.*;
import java.awt.Color;

/**
 * Crée la barre d'énergie de tous les vaisseaux. Les méthodes sont les même que la classe Health,
 * si ce n'est la couleur bleue...
 */
public class Energy extends Interface
{

    public Energy()
    {
        drawEnergyBar();
    }

    public void drawEnergyBar()
    {
        GreenfootImage e= new GreenfootImage(getImage());
        GreenfootImage energyBar = new GreenfootImage(e.getWidth(),20);
        energyBar.setColor(Color.BLACK);
        energyBar.fill();
        switch(RightArrow.imageNumber)
        {
            case 0:
            int energyPixels1 = (int)(200*Gear1.Henergy/Gear1.MAX_ENERGY);
            if(energyPixels1 <= 0)
            { 
                energyPixels1 = 1;
            }
            GreenfootImage filledEnergy = new GreenfootImage(energyPixels1,20);
            filledEnergy.setColor(Color.BLUE);
            filledEnergy.fill();
            energyBar.drawImage(filledEnergy, 0, 0);  
            e.drawImage(energyBar,0,e.getHeight()-e.getHeight());
            setImage(e);
            break;
            
            case 1:
            int energyPixels2 = (int)(200*Gear2.Henergy/Gear2.MAX_ENERGY);
            if(energyPixels2 <= 0)
            { 
                energyPixels2 = 1;
            }
            GreenfootImage filledEnergy2 = new GreenfootImage(energyPixels2,20);
            filledEnergy2.setColor(Color.BLUE);
            filledEnergy2.fill();
            energyBar.drawImage(filledEnergy2, 0, 0);  
            e.drawImage(energyBar,0,e.getHeight()-e.getHeight());
            setImage(e);
            break;
            
            case 2:
            int energyPixels3 = (int)(200*Gear3.Henergy/Gear3.MAX_ENERGY);
            if(energyPixels3 <= 0)
            { 
                energyPixels3 = 1;
            }
            GreenfootImage filledEnergy3 = new GreenfootImage(energyPixels3,20);
            filledEnergy3.setColor(Color.BLUE);
            filledEnergy3.fill();
            energyBar.drawImage(filledEnergy3, 0, 0);  
            e.drawImage(energyBar,0,e.getHeight()-e.getHeight());
            setImage(e);
            break;
            
            case 3:
            int energyPixels4 = (int)(200*Gear4.Henergy/Gear4.MAX_ENERGY);
            if(energyPixels4 <= 0)
            { 
                energyPixels4 = 1;
            }
            GreenfootImage filledEnergy4 = new GreenfootImage(energyPixels4,20);
            filledEnergy4.setColor(Color.BLUE);
            filledEnergy4.fill();
            energyBar.drawImage(filledEnergy4, 0, 0);  
            e.drawImage(energyBar,0,e.getHeight()-e.getHeight());
            setImage(e);
            break;
        }
    }
}
