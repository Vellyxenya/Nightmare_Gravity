import greenfoot.*;
import java.awt.Color;

/**
 * Barre de vie du joueur. Objet enlevé et rajouté au monde à chaque act.
 */
public class Health extends Interface
{

    public Health()
    {
        drawHealthBar();
    }

    /**
     * Dessine la barre de vie du joueur (en rouge).
     */
    public void drawHealthBar()
    {
        GreenfootImage i = new GreenfootImage(getImage()); //Image de base
        GreenfootImage healthBar = new GreenfootImage(i.getWidth(),20); //Image de la barre à proprement parler
        healthBar.setColor(Color.BLACK);
        healthBar.fill();
        switch(RightArrow.imageNumber) //Cela correspond au vaisseau sélectionner. Chaque vaisseau a un numéro allant de 0 à 3
        {
            case 0: //Vaisseau 1
            int healthPixels1 = (int)(200*Gear1.Life/Gear1.MAX_HEALTH); //calcule le nombre de pixels correspondants à la vie.
            if(healthPixels1 <= 0) //dessine au moins un pixel pour éviter des bugs
            { 
                healthPixels1 = 1;
            }
            GreenfootImage filledHealth = new GreenfootImage(healthPixels1,20); 
            filledHealth.setColor(Color.RED);
            filledHealth.fill();
            healthBar.drawImage(filledHealth, 0, 0);  
            i.drawImage(healthBar,0,0);
            setImage(i);
            break;
            
            case 1: //idem, vaisseau 2
            int healthPixels2 = (int)(200*Gear2.Life/Gear2.MAX_HEALTH);
            if(healthPixels2 <= 0)
            { 
                healthPixels2 = 1;
            }
            GreenfootImage filledHealth2 = new GreenfootImage(healthPixels2,20);
            filledHealth2.setColor(Color.RED);
            filledHealth2.fill();
            healthBar.drawImage(filledHealth2, 0, 0);  
            i.drawImage(healthBar,0,0);
            setImage(i);
            break;
            
            case 2: //idem, vaisseau 3
            int healthPixels3 = (int)(200*Gear3.Life/Gear3.MAX_HEALTH);
            if(healthPixels3 <= 0)
            { 
                healthPixels3 = 1;
            }
            GreenfootImage filledHealth3 = new GreenfootImage(healthPixels3,20);
            filledHealth3.setColor(Color.RED);
            filledHealth3.fill();
            healthBar.drawImage(filledHealth3, 0, 0);  
            i.drawImage(healthBar,0,0);
            setImage(i);
            break;
            
            case 3: //idem, vaisseau 4
            int healthPixels4 = (int)(200*Gear4.Life/Gear4.MAX_HEALTH);
            if(healthPixels4 <= 0)
            { 
                healthPixels4 = 1;
            }
            GreenfootImage filledHealth4 = new GreenfootImage(healthPixels4,20);
            filledHealth4.setColor(Color.RED);
            filledHealth4.fill();
            healthBar.drawImage(filledHealth4, 0, 0);  
            i.drawImage(healthBar,0,0);
            setImage(i);
            break;
        }
    }
}
