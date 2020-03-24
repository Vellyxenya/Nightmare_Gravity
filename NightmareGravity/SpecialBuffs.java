import greenfoot.*;
import java.awt.Color;
import javax.swing.JOptionPane;

/**
 * Compétences acquises (achetées)
 */
public class SpecialBuffs extends SmoothMover
{
    //Boutons pour chaque compétence
    public SpellButtons button1 = new SpellButtons();
    public SpellButtons button2 = new SpellButtons();
    public SpellButtons button3 = new SpellButtons();
    public SpellButtons button4 = new SpellButtons();
    public SpellButtons button5 = new SpellButtons();
    //Indique si les compétences sont utilisées ou pas.
    public static boolean ShieldSpell = false;
    public static boolean SpeedSpell = false;
    public static boolean RegenSpell = false;
    public static boolean CreditSpell = false;
    public static boolean CollectSpell = false;
    public boolean on;
    public GreenfootSound sound = new GreenfootSound("ButtonSound.wav");
    
    public SpecialBuffs()
    {
        on = false;
    }
    
    /**
     * Vérifie si la souris est dessus
     */
    public void track()
    {
        if(mouseOn() == false)
        {
            on = false;
        }
    }
    
    /**
     * Produit du son lorsque la souris passe dessus
     */
    public void makeSound() 
    {
        if(mouseOn() == true && on == false)
        {
            sound.play();
            on = true;
        }
    }
    
    /**
     * Indique à l'utilisateur qu'il possède déjà tel objet
     */
    public void alreadyOwned()
    {
        JOptionPane.showMessageDialog(null, "You already have mastered this spell!");
    }
    
    /**
     * Indique à l'utilisateur qu'il n'a pas assez de crédits pour acquérir tel objet
     */
    public void noMoney()
    {
        JOptionPane.showMessageDialog(null, "You don't have enough money to master this spell!");
    }
    
}
