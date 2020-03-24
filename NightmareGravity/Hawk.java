import greenfoot.*;
import java.util.List;

public class Hawk extends Weapons
{
    /**
     * Compteur du missile
     */
    private int timer;
    /**
     * Direction de propulsion du missile
     */
    private int direction;
    /**
     * Coordonnée X de la souris
     */
    public int xMouse;
    /**
     * Coordonnée Y de la souris
     */
    public int yMouse;
    /**
     * Coordonnée X du vaisseau
     */
    public int xGear;
    /**
     * Coordonnée Y du vaisseau
     */
    public int yGear;
    /**
     * Rotation initiale
     */
    public int firstRotation;
    /**
     * Deuxième compteur pour le missile
     */
    private int counter;
    /**
     * Indique si le missile doit tourner ou pas
     */
    private boolean noRotation = false;
    /**
     * Rotation du vaisseau
     */
    private int gearRotation;
    /**
     * Mode de tir
     */
    private int style;
    Actor actor;
    /**
     * Son joué pendant le lancement du missile
     */
    GreenfootSound sound = new GreenfootSound("Hawk.wav");
    /**
     * Son joué à l'impact du missile
     */
    GreenfootSound sound2 = new GreenfootSound("Hawk2.wav");
    /**
     * Durée de vie du missile
     */
    private int lifeDurationCounter;
    private final int MAX_LIFE_DURATION = 100;

    /**
     * Initialise un missile en mode rafale
     */
    public Hawk(int xMouse, int yMouse, int xGear, int yGear, int rotation)
    {
        this.xMouse = xMouse;
        this.yMouse = yMouse;
        this.xGear = xGear;
        this.yGear = yGear;
        getImage().scale(50, 20);
        setRotation(Greenfoot.getRandomNumber(360));
        this.firstRotation = getRotation();
        this.gearRotation = rotation;
        style = 0;
        sound.setVolume(40);
        sound.play();
    }

    /**
     * Initialise un missile en mode ciblage
     */
    public Hawk(int rotation, Actor actor)
    {
        getImage().scale(50, 20);
        setRotation(rotation);
        style = 1;
        this.actor = actor;
        sound.setVolume(80);
        sound.play();
    }

    /**
     * Gère quel mode de tir utiliser, quand il faut tourner, quand il ne faut plus, les sons etc...
     */
    public void act() 
    {
        if(isPaused() == false)
        {
            try{
                timer++;
                lifeDurationCounter++;
                getWorld().addObject(new HawkEffect(), this.getX(), this.getY());
                if(style == 0)
                {
                    try{
                        counter++;
                        move(8);
                        target();
                        noRotation();
                    }catch(IllegalStateException exep){}
                }
                else if(style == 1)
                {
                    move(12);
                    if(timer>3)
                    {
                        try
                        {
                            int random = Greenfoot.getRandomNumber(30);
                            turnTowards(actor.getX()-15+random, actor.getY()-15+random);
                        }catch(IllegalStateException e){}
                    }
                }
                if(isTouching(Enemies.class))
                {
                    removeTouching(Enemies.class);
                    Game.killedEnemies++;
                    getWorld().removeObject(this);
                    sound2.setVolume(80);
                    sound2.play();
                }
                try
                {
                    if(isAtEdge())
                    {
                        getWorld().removeObject(this);
                    }
                }catch(IllegalStateException e){}
                if(lifeDurationCounter >= MAX_LIFE_DURATION){
                    getWorld().removeObject(this);
                }
            }catch(NullPointerException exe){}
        }
    }

    /**
     * Ne tourne plus au bout d'un certain moment
     */
    public void noRotation()
    {
        double distance = Math.hypot(this.getX()-xMouse, this.getY()-yMouse);
        if(counter == 40 && noRotation == false  && distance<300)
        {
            int rot = getRotation();
            turnTowards(xMouse, yMouse);
            int newRot = getRotation();
            if(Math.abs(rot-newRot)%360<40)
            {
                noRotation = true;
            }
            else
            {
                setRotation(rot);
            }
        }
    }

    /**
     * Choisit la direction à prendre selon la position relative de la souris et du vaisseau
     */
    public int direction()
    {
        try
        {
            Gear4 a = (Gear4) getWorld().getObjects(Gear4.class).get(0);
            Opponent b = (Opponent) getWorld().getObjects(Opponent.class).get(0);
        }catch(IndexOutOfBoundsException e){}
        int position = (xMouse-xGear)*(this.getY()-yGear)-(yMouse-yGear)*(this.getX()-xGear);
        if(position<=0)
        {
            return 1;
        }
        else
        {
            return -1;
        }
    }

    /**
     * Cible un ennemi en particulier
     */
    public void target()
    {
        if(noRotation == false)
        {
            if(timer % 3 == 0)
            {
                int currentRotation = getRotation();
                turnTowards(xMouse, yMouse);
                int newRotation = getRotation();
                if (Math.abs(currentRotation-newRotation) > 180)
                {
                    if (currentRotation < 180) currentRotation += 360;
                    else newRotation += 360;
                }
                if(currentRotation != newRotation)
                {  
                    setRotation(currentRotation+direction()*8);
                }
            }
        }
    }

}
