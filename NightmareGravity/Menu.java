import greenfoot.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.List;

/**
 * Menu Principal.
 */
public class Menu extends TheEdge
{
    /**
     * Coordonnée milieu de l'axe des X
     */
    private int x = getBackground().getWidth()/2;
    /**
     * Coordonnée de début d'écriture
     */
    private int y = 120;
    /**
     * Largeur des boutons
     */
    private int width = 150;
    /**
     * Hauteur des boutons
     */
    private int height = 40;
    public static Buttons button0 = new Buttons();
    public static Buttons button1 = new Buttons();
    public static Buttons button2 = new Buttons();
    public static Buttons button3 = new Buttons();
    public static Buttons button4 = new Buttons();
    public static Buttons button5 = new Buttons();
    /**
     * Tableau contenant tous les boutons
     */
    public static Buttons[] buttons = {button0,button1,button2, button3, button4, button5};
    /**
     * Tableau contenant le titre affiché par les boutons
     */
    String[] titles = {"Play", "Selection", "Store", "Stats", "Inventory", "About"};
    /**
     * Variable stockant les crédits avant le début d'une partie
     */
    public static int previousWealth;
    /**
     * Variable stockant le nombre du compte (chaque compte est référencé par un nombre)
     */
    public static int account;
    
    /**
     * Initialise le monde
     */
    public Menu()
    {
        Greenfoot.setSpeed(50);
        mainDraw();
        prepare();
        if(Game.gameMusic.isPlaying())
        {
            Game.gameMusic.stop();
        }
        addObject(volumeObject, getWidth()*29/30-3, getHeight()*1/20);
        setPaintOrder(Buttons.class, Volume.class);
    }

    public void act()
    {
        super.act();
        changeGround();
        ChangeMouseImage(img1, 1, 1);
    }
    
    /**
     * Méthode dessinant les boutons
     */
    private void mainDraw()
    {
        for(int i=0; i<6; i++)
        {
            GreenfootImage newground = this.getBackground();
            newground.drawRect(x-width/2, y+60*i, width, height);
            newground.fillRect(x-width/2, y+60*i, width, height);
            GreenfootImage textImage = new GreenfootImage(titles[i], 24, new Color(0, 255, 128), new Color(0, 0, 0, 0));
            newground.drawImage(textImage, x-(textImage.getWidth()/2), y+60*i+8);
        }
    }

    /**
     * Rajoute les boutons sur la scène
     */
    private void prepare()
    {
        for(int i=0;i<6;i++)
        {
            addObject(buttons[i],x,y+60*i+height/2);
        }
    }
    
    /**
     * Méthode permettant de passer aux autres menus
     */
    public void changeGround()
    {
        if(Greenfoot.mouseClicked(button0))
        {
            Greenfoot.setWorld(new Game());
        }
        if(Greenfoot.mouseClicked(button1)){Greenfoot.setWorld(new Selection());}
        if(Greenfoot.mousePressed(button2)){Greenfoot.setWorld(new Store());}
        if(Greenfoot.mouseClicked(button3)){Greenfoot.setWorld(new Stats());}
        if(Greenfoot.mouseClicked(button4))
        {
            if(Deck.currentWorld != null)//seulement si le joueur a déjà accédé à l'inventaire.
            {
                Greenfoot.setWorld(Deck.currentWorld);
                ShieldIcon.manipulate = true;
                SpeedIcon.manipulate = true;
                RegenIcon.manipulate = true;
                CreditIcon.manipulate = true;
                CollectIcon.manipulate = true;
                Inventory inventory = (Inventory) Deck.currentWorld.getObjects(Inventory.class).get(0);
                if(SpecialBuffs.ShieldSpell == true && ShieldIcon.used == false) //affiche le logo de la compétence si elle est utilisée
                {
                    ShieldIcon shieldIcon = (ShieldIcon)Deck.currentWorld.getObjects(ShieldIcon.class).get(0);
                    shieldIcon.setLocation(inventory.getX()-inventory.getImage().getWidth()/2+25, inventory.getY());
                    shieldIcon.baseX = shieldIcon.getX();
                    shieldIcon.baseY = shieldIcon.getY();
                }
                if(SpecialBuffs.SpeedSpell == true && SpeedIcon.used == false) //idem
                {
                    SpeedIcon speedIcon = (SpeedIcon)Deck.currentWorld.getObjects(SpeedIcon.class).get(0);
                    speedIcon.setLocation(inventory.getX()-inventory.getImage().getWidth()/2+75, inventory.getY());
                    speedIcon.baseX = speedIcon.getX();
                    speedIcon.baseY = speedIcon.getY();
                }
                if(SpecialBuffs.RegenSpell == true && RegenIcon.used == false) //idem
                {
                    RegenIcon regenIcon = (RegenIcon)Deck.currentWorld.getObjects(RegenIcon.class).get(0);
                    regenIcon.setLocation(inventory.getX()-inventory.getImage().getWidth()/2+125, inventory.getY());
                    regenIcon.baseX = regenIcon.getX();
                    regenIcon.baseY = regenIcon.getY();
                }
                if(SpecialBuffs.CreditSpell == true && CreditIcon.used == false) //idem
                {
                    CreditIcon creditIcon = (CreditIcon)Deck.currentWorld.getObjects(CreditIcon.class).get(0);
                    creditIcon.setLocation(inventory.getX()-inventory.getImage().getWidth()/2+175, inventory.getY());
                    creditIcon.baseX = creditIcon.getX();
                    creditIcon.baseY = creditIcon.getY();
                }
                if(SpecialBuffs.CollectSpell == true && CollectIcon.used == false) //idem
                {
                    CollectIcon collectIcon = (CollectIcon)Deck.currentWorld.getObjects(CollectIcon.class).get(0);
                    collectIcon.setLocation(inventory.getX()-inventory.getImage().getWidth()/2+225, inventory.getY());
                    collectIcon.baseX = collectIcon.getX();
                    collectIcon.baseY = collectIcon.getY();
                }
            }
            else Greenfoot.setWorld(new Deck()); // ou si c'est la première fois que le joueur accède à Deck.
        }
        if(Greenfoot.mouseClicked(button5))Greenfoot.setWorld(new About());
    }
}
