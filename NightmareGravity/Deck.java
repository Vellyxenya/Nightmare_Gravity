import greenfoot.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

/**
 * World permettant d'équiper les objets préalablement achetés.
 */
public class Deck extends TheEdge
{
    GreenfootImage background = getBackground();
    /**
     * Variable stockant l'état actuel de la scène
     */
    public static World currentWorld;
    /**
     * Objet affichant les compétences acquises et non équipées
     */
    static Inventory inventory = new Inventory();
    /**
     * Objet affichant les compétences acquises et équipées
     */
    static ItemDeck equipments = new ItemDeck();
    
    /**
     * Initialise les objets équipées etc...
     */
    public Deck()
    {
        Font f = new Font("creepygirl", Font.BOLD, 64);
        FontMetrics fm = background.getAwtImage().createGraphics().getFontMetrics(f);
        background.setFont(f);
        background.setColor(Color.RED);
        String title = "I N V E N T O R Y";
        background.drawString(title, getWidth()/2-fm.stringWidth(title)/2, 100);
        addObject(back,this.getBackground().getWidth()*17/20,getBackground().getHeight()*9/10);
        draw();
        putText();
        putObjects();
        putNewObjects();
        ShieldIcon icon = new ShieldIcon();
        addObject(icon, 0, 425);
        SpeedIcon idon = new SpeedIcon();
        addObject(idon, 0, 425);
        RegenIcon iron = new RegenIcon();
        addObject(iron, 0, 425);
        CreditIcon iccon = new CreditIcon();
        addObject(iccon, 0, 425);
        CollectIcon ihon = new CollectIcon();
        addObject(ihon, 0, 425);
        Informations3 infos = new Informations3();
        addObject(infos, 375, 425);
        setPaintOrder(Informations3.class, SpecialBuffs.class, Buttons.class);
    }

    public void act()
    {
        super.act();
        if(ShieldIcon.ready == true) // Une lettre doit nécessairement être assignée à ShieldIcon si cette compétence est utilisée
        {
            backToMenu();
        }
        currentWorld = this; //stock l'état du monde avant de quitter.
        ChangeMouseImage(img2, 1, 1);
    }
    
    /**
     * Affiche ce que symbolise chaque tableau de compétences
     */
    public void putText()
    {
        GreenfootImage text = new GreenfootImage("Inventory :", 20, Color.RED, new Color(0,0,0,0));
        background.drawImage(text, 100, 200);
        GreenfootImage text2 = new GreenfootImage("Equipment :", 20, Color.RED, new Color(0,0,0,0));
        background.drawImage(text2, 100, 300);
    }

    /**
     * Ajoute les tableaux de compétences à la scène
     */
    public void putObjects()
    {
        addObject(inventory, 450, 210);
        addObject(equipments, 450, 310);
    }
    
    /**
     * Ajoute les compétences concernées si elles ont été achetées
     */
    public void putNewObjects()
    {
        if(SpecialBuffs.ShieldSpell == true)
        {
            Inventory inventory = (Inventory) getObjects(Inventory.class).get(0);
            ShieldIcon shieldIcon = new ShieldIcon();
            addObject(shieldIcon, inventory.getX()-inventory.getImage().getWidth()/2+25, inventory.getY());
            shieldIcon.baseX = shieldIcon.getX();
            shieldIcon.baseY = shieldIcon.getY();
        }
        if(SpecialBuffs.SpeedSpell == true)
        {
            Inventory inventory = (Inventory) getObjects(Inventory.class).get(0);
            SpeedIcon speedIcon = new SpeedIcon();
            addObject(speedIcon, inventory.getX()-inventory.getImage().getWidth()/2+75, inventory.getY());
            speedIcon.baseX = speedIcon.getX();
            speedIcon.baseY = speedIcon.getY();
        }
        if(SpecialBuffs.RegenSpell == true)
        {
            Inventory inventory = (Inventory) getObjects(Inventory.class).get(0);
            RegenIcon regenIcon = new RegenIcon();
            addObject(regenIcon, inventory.getX()-inventory.getImage().getWidth()/2+125, inventory.getY());
            regenIcon.baseX = regenIcon.getX();
            regenIcon.baseY = regenIcon.getY();
        }
        if(SpecialBuffs.CreditSpell == true)
        {
            Inventory inventory = (Inventory) getObjects(Inventory.class).get(0);
            CreditIcon creditIcon = new CreditIcon();
            addObject(creditIcon, inventory.getX()-inventory.getImage().getWidth()/2+175, inventory.getY());
            creditIcon.baseX = creditIcon.getX();
            creditIcon.baseY = creditIcon.getY();
        }
        if(SpecialBuffs.CollectSpell == true)
        {
            Inventory inventory = (Inventory) getObjects(Inventory.class).get(0);
            CollectIcon collectIcon = new CollectIcon();
            addObject(collectIcon, inventory.getX()-inventory.getImage().getWidth()/2+225, inventory.getY());
            collectIcon.baseX = collectIcon.getX();
            collectIcon.baseY = collectIcon.getY();
        }
    }

}
