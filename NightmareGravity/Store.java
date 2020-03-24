import greenfoot.*;
import java.awt.Color;

/**
 * Menu via lequel le joueur peut acheter des compétences grâce aux crédits gagnés en jeu.
 */
public class Store extends TheEdge
{
    GreenfootImage ground = getBackground();
    /**
     * Variable contenant les crédits dans le porte-monnaie
     */
    public static int wealth;
    /**
     * Tableau contenant la description de ce que fait chaque objet
     */
    String[] text = { 
            "_______________\nCreate\na\nprotecting\nshield",
            "_______________\nDropped\nobjects are\nattracted by\nyour ship",
            "_______________\nHigh\nincrease\nof gear's\nspeed",
            "_______________\nYou\nperiodically\nearn some\nextra credits",
            "_______________\nYour health points\nare\nregenerated\nperiodically"
        };
        /**
     * Coordonnée X de l'information affichée
     */
    int xPosition = 460;
    /**
     * Coordonnée Y de l'information affichée
     */
    int yPosition = 300;
    boolean a = false;
    boolean b = false;
    boolean c = false;
    boolean d = false;
    boolean e = false;

    public Store()
    {
        wealth = Menu.previousWealth; //redéfinit la variable contenant les crédits.
        addObjects();
        draw();
        drawCredit();
        putItems();
        drawPrices();
        drawTitle();
        setPaintOrder(Point.class, Buttons.class);
    }

    public void act()
    {
        super.act();
        ChangeMouseImage(img1, 1, 1);
        backToMenu();
        drawCredit();
        wealth = Menu.previousWealth;
        informate(200, 160);
    }
    
    /**
     * Dessine le titre de l'onglet
     */
    public void drawTitle()
    {
        String string = "Credits : ";
        GreenfootImage text = new GreenfootImage("Credits : ", 24, new Color(210, 170, 250), new Color(0, 0, 0, 0));
        ground.drawImage(text, getWidth()/2-text.getWidth()/2, 68);
    }
    
    /**
     * Rajoute le bouton servant à revenir au menu principal
     */
    public void addObjects()
    {
        addObject(back,this.getBackground().getWidth()*17/20,getBackground().getHeight()*9/10);
    }
    
    /**
     * Affiche le montant de crédits possédé
     */
    public void drawCredit()
    {
        showText(""+wealth, getWidth()/2+70, getHeight()/7);
    }
    
    /**
     * Ajoute les compétences qui peuvent être acquises
     */
    public void putItems()
    {
        addObject(new ShieldSpell(), 200, 200);
        addObject(new SpeedSpell(), 200, 300);
        addObject(new RegenSpell(), 200, 400);
        addObject(new CreditSpell(), 200, 350);
        addObject(new CollectSpell(), 200, 250);
    }
    
    /**
     * Affiche le prix de chaque compétence
     */
    public void drawPrices()
    {
        GreenfootImage text = new GreenfootImage(ShieldSpell.ShieldSpell+" Credits", 24, Color.CYAN, new Color(0,0,0,0), Color.BLACK);
        ground.drawImage(text, 600, 185);
        GreenfootImage text5 = new GreenfootImage(CollectSpell.CollectSpell+" Credits", 24, Color.CYAN, new Color(0,0,0,0), Color.BLACK);
        ground.drawImage(text5, 600, 235);
        GreenfootImage text2 = new GreenfootImage(SpeedSpell.SpeedSpell+" Credits", 24, Color.CYAN, new Color(0,0,0,0), Color.BLACK);
        ground.drawImage(text2, 600, 285);
        GreenfootImage text4 = new GreenfootImage(CreditSpell.CreditSpell+" Credits", 24, Color.CYAN, new Color(0,0,0,0), Color.BLACK);
        ground.drawImage(text4, 600, 335);
        GreenfootImage text3 = new GreenfootImage(RegenSpell.RegenSpell+" Credits", 24, Color.CYAN, new Color(0,0,0,0), Color.BLACK);
        ground.drawImage(text3, 600, 385);
    }
    
    /**
     * Crée l'Object de classe Informations qui indique à l'utilisateur l'utilité de chaque compétence
     */
    public void informate(int x, int y)
    {
        ShieldSpell shield = (ShieldSpell)getObjects(ShieldSpell.class).get(0);
        CollectSpell collect = (CollectSpell)getObjects(CollectSpell.class).get(0);
        SpeedSpell speed = (SpeedSpell)getObjects(SpeedSpell.class).get(0);
        CreditSpell credit = (CreditSpell)getObjects(CreditSpell.class).get(0);
        RegenSpell regen = (RegenSpell)getObjects(RegenSpell.class).get(0);
        if(shield.mouseOn() == true && a == false)
        {
            removeObjects(getObjects(Informations.class));
            removeObjects(getObjects(Point.class));
            addObject(new Informations(text[0], x, y), xPosition, yPosition);
            //Tous ces true/false servent à enlever les objetes Informations non concernés.
            a = true;
            b = false;
            c = false;
            d = false;
            e = false;
        }
        else if(collect.mouseOn() == true && b == false)
        {
            removeObjects(getObjects(Informations.class));
            removeObjects(getObjects(Point.class));
            addObject(new Informations(text[1], x, y), xPosition, yPosition);
            a = false;
            b = true;
            c = false;
            d = false;
            e = false;
        }
        else if(speed.mouseOn() == true && c == false)
        {
            removeObjects(getObjects(Informations.class));
            removeObjects(getObjects(Point.class));
            addObject(new Informations(text[2], x, y), xPosition, yPosition);
            a = false;
            b = false;
            c = true;
            d = false;
            e = false;
        }
        else if(credit.mouseOn() == true && d == false)
        {
            removeObjects(getObjects(Informations.class));
            removeObjects(getObjects(Point.class));
            addObject(new Informations(text[3], x, y), xPosition, yPosition);
            a = false;
            b = false;
            c = false;
            d = true;
            e = false;
        }
        else if(regen.mouseOn() == true && e == false)
        {
            removeObjects(getObjects(Informations.class));
            removeObjects(getObjects(Point.class));
            addObject(new Informations(text[4], x, y), xPosition, yPosition);
            a = false;
            b = false;
            c = false;
            d = false;
            e = true;
        }
        else if(shield.mouseOn() == false && collect.mouseOn() == false && speed.mouseOn() == false && credit.mouseOn() == false && regen.mouseOn() == false)
        {    //Enlève tous les objets de la classe Informations si aucun objet n'est sélectionné
            removeObjects(getObjects(Informations.class));
            removeObjects(getObjects(Point.class));
            a = false;
            b = false;
            c = false;
            d = false;
            e = false;
        }
    }

}
