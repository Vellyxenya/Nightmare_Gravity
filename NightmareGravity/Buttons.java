import greenfoot.*;
import java.awt.Color;

/**
 * Objet transparent sur lequel on peut cliquer.
 */
public class Buttons extends Interface
{
    /**
     * Renvoie si la souris est sur cet objet ou pas
     */
    private boolean on;
    /**
     * Son produit quand la souris passe sur un bouton
     */
    GreenfootSound sound = new GreenfootSound("ButtonSound.wav");
    /**
     * Image du bouton
     */
    GreenfootImage image = new GreenfootImage(151, 41);
    
    /**
     * Construit l'image du bouton
     */
    public Buttons()
    {
        on = false;
        image.setColor(Color.WHITE);
        image.drawRect(0, 0, 150, 40);
    }
    
    /**
     * Change l'image du curseur de la souris si celle-ci se trouve sur un bouton
     */
    public void act()
    {
        track();
        makeSound();
        changeImage();
        if(mouseOn() == true)
        {
            Background.ChangeMouseImage(TheEdge.img2, 1, 1);
        }
    }
    
    /**
     * Change l'image du bouton si la souris est dessus
     */
    public void changeImage()
    {
        if(mouseOn() == false)
        {
            setImage("TransparentButtons.png");
        }
        else if(mouseOn() == true)
        {
            setImage(image);
        }
    }
    
    /**
     * Retourne si la souris est sur cet objet ou pas
     */
    public void track()
    {
        if(mouseOn() == false)
        {
            on = false;
        }
    }
    
    /**
     * Produit un son lorsque la souris passe sur cet objet
     */
    public void makeSound()
    {
        if(mouseOn() == true && on == false)
        {
            sound.play();
            on = true;
        }
    }
    
}

