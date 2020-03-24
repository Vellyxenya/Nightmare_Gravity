import greenfoot.*;
import java.util.List;

public class Gear1 extends Gears
{
    /**
     * Indique si la souris est cliquée ou pas
     */
    private boolean isPressed = false;
    /**
     * maximum de points de vie
     */
    public static final int MAX_HEALTH = 9000;
    /**
     * maximum d'énergie
     */
    public static final int MAX_ENERGY = 1000;
    /**
     * Délai de récupération de la compétence ShockWave
     */
    private static final int maxReloadWave = 240;
    /**
     * Compteur de la compétence ShockWave
     */
    public static int reloadWave;
    /**
     * Portée maximale du laser
     */
    private final int LENGTH_MAX = 350;
    /**
     * Masse du vaisseau
     */
    public static final int MASS = 50;
    /**
     * Compteur pour le mode de tir lors de la présence d'un boss
     */
    private int attackTimer;
    /**
     * Indique si le vaisseau est immobilisé ou pas.
     */
    public static boolean stunned = false;
    GreenfootSound son = new GreenfootSound("ShockWave.wav");

    /**
     * Initialise les valeurs de Gear1
     */
    public Gear1(Vector movement)
    {
        Life = MAX_HEALTH;
        Henergy = MAX_ENERGY;
        reloadWave = 200;
        speed = 4;
        getInvisible = false;
    }

    public void act() 
    {
        prepareToBattle();
        if(isPaused() == false)
        {
            try
            {
                if(getWorld()!= null)
                {
                    super.act();
                    adjustLife();
                    laser();
                    detonate();
                    reloadWave++;
                    attackTimer++;
                    animate(speed);
                }
            }catch(NullPointerException e){}
        }
    }

    /**
     * Crée un laser allant du vaisseau jusqu'à la souris ou jusqu'à la portée maximale
     */
    private void laser()
    {
        try
        {
            MouseInfo mouse = Greenfoot.getMouseInfo();
            if(mouse != null)
            {
                //Calcule la direction et la longueur du laser
                int xMouse = mouse.getX();
                int yMouse = mouse.getY();
                double xThis = this.getExactX();
                double yThis = this.getExactY();
                double dx = xMouse-xThis;
                double dy = yMouse-yThis;
                double length = Math.sqrt(dx*dx+dy*dy);
                double xLength = dx/length;
                double yLength = dy/length;
                int rotation = (int) Math.toDegrees(Math.atan(dy/dx));
                
                if(Volume.volumeOn == true)Game.gameMusic.setVolume(70);
                if(Game.bossSpawned == 0) //Cas où il n'y a pas de boss dans le monde.
                {   //Toutes les méthodes qui suivent servent à ce que le laser soit fluide le maximum possible.
                    if(Greenfoot.mousePressed(null)|| Greenfoot.mouseDragged(null)) 
                    {
                        List<Weapon1> weapons = getWorld().getObjects(Weapon1.class);
                        getWorld().removeObjects(weapons);
                        if(length<=LENGTH_MAX)
                        {
                            for(int i=0; i<length; i++)
                            {
                                Weapon1 weapon1 = new Weapon1(); //Crée une chaîne de weapon1 (2x2 pixels) pour créer le laser
                                getWorld().addObject(weapon1, (int)(this.getX()+i*xLength), (int)(this.getExactY()+i*yLength));
                            }
                        }
                        else if(length>LENGTH_MAX)
                        {
                            for(int i=0; i<LENGTH_MAX; i++)
                            {
                                Weapon1 weapon1 = new Weapon1();
                                getWorld().addObject(weapon1, (int)(this.getX()+i*xLength), (int)(this.getExactY()+i*yLength));
                            }
                        }
                        isPressed = true; //renvoie si la souris est pressée
                    }
                    if(Greenfoot.mouseClicked(null))
                    {
                        List<Weapon1> weapons = getWorld().getObjects(Weapon1.class);
                        getWorld().removeObjects(weapons);
                        isPressed = false;
                    }
                    if((isMoved == true && isPressed == true))
                    {
                        List<Weapon1> weapons = getWorld().getObjects(Weapon1.class);
                        getWorld().removeObjects(weapons);
                        if(length<=LENGTH_MAX)
                        {
                            for(int i=0; i<length; i++)
                            {
                                Weapon1 weapon1 = new Weapon1();
                                getWorld().addObject(weapon1, (int)(this.getX()+i*xLength), (int)(this.getExactY()+i*yLength));
                            }
                        }
                        else if(length>LENGTH_MAX)
                        {
                            for(int i=0; i<LENGTH_MAX; i++)
                            {
                                Weapon1 weapon1 = new Weapon1();
                                getWorld().addObject(weapon1, (int)(this.getX()+i*xLength), (int)(this.getExactY()+i*yLength));
                            }
                        }
                    }
                }
                else if(Game.bossSpawned == 1) //Lorsque le boss est là, le mode de tir passe du laser aux projectiles (mitraillette)
                {
                    deleteWeapons();
                    if(Greenfoot.mousePressed(null))
                    {
                        if(mouse.getX()<this.getX())
                        {
                            Weapon2 weapon = new Weapon2(rotation+180, 15);
                            weapon.setImage("missile.png");
                            getWorld().addObject(weapon, this.getX(), this.getY());
                            attackTimer = 0;
                        }
                        else if(mouse.getX()>= this.getX())
                        {
                            Weapon2 weapon = new Weapon2(rotation, 15);
                            weapon.setImage("missile.png");
                            getWorld().addObject(weapon, this.getX(), this.getY());
                            attackTimer = 0; 
                        }
                        isPressed = true;
                    }
                    else if(Greenfoot.mouseClicked(null))
                    {
                        isPressed = false;
                    }
                    else if(isPressed == true)
                    {
                        if(mouse.getX()<this.getX())
                        {
                            Weapon2 weapon = new Weapon2(rotation+180, 15);
                            weapon.setImage("missile.png"); //Change l'image de l'arme utilisée pour le vaisseau 3... afin que ça ait l'air d'un pseudo-laser
                            getWorld().addObject(weapon, this.getX(), this.getY());
                            attackTimer = 0;
                        }
                        else if(mouse.getX()>= this.getX())
                        {
                            Weapon2 weapon = new Weapon2(rotation, 15);
                            weapon.setImage("missile.png");
                            getWorld().addObject(weapon, this.getX(), this.getY());
                            attackTimer = 0; 
                        }
                    }
                }
            }
        }catch(IllegalStateException e){}
    }

    /**
     * Enlève le laser de la scène
     */
    private void deleteWeapons()
    {
        if(getWorld() != null)
        {
            List<Weapon1> weapons = getWorld().getObjects(Weapon1.class);
            int length = weapons.size();
            if(length>=1)
            {
                getWorld().removeObjects(weapons);
            }
        }
    }

    /**
     * Crée une déflagration qui détruit tout sur son passage
     */
    private void detonate()
    {
        if(Greenfoot.isKeyDown("space") && reloadWave >= maxReloadWave)
        {
            ShockWave wave = new ShockWave();
            getWorld().addObject(wave, this.getX(), this.getY());
            reloadWave = 0;
            Henergy -= 100;
            Gears.lostEnergy = true;
            son.setVolume(90);
            son.play();
        }
        else
        {
            Gears.lostEnergy = false;
        }
    }

    /**
     * Empêche les points de vie de dépasser la limite maximale
     */
    public void adjustLife()
    {
        if(Life>MAX_HEALTH)
        {
            Life = MAX_HEALTH;
        }
    }

}
