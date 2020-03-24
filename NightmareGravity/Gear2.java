import greenfoot.*;
import java.util.List;

public class Gear2 extends Gears
{
    /**
     * Maximum de points de vie
     */
    public static final int MAX_HEALTH = 8000;
    /**
     * Maximum d'énergie
     */
    public static final int MAX_ENERGY = 5000;
    /**
     * Vitesse du vaisseau
     */
    private int speed = 1;
    /**
     * Délai de récupération de la téléportation
     */
    private static final int MAX_COUNTER = 60;
    /**
     * Compteur du délai de récupération
     */
    private static int counter;
    /**
     * Renvoie s'il y a un éclair sur scène
     */
    private boolean isLightning;
    /**
     * Compteur avant la disparition de l'éclair
     */
    private int timer;
    /**
     * Masse du vaisseau
     */
    public static final int MASS = 20;
    /**
     * Compteur pour le mode de tir alternatif
     */
    public int attackTimer = 0;
    /**
     * Renvoie si la souris est pressée
     */
    private boolean isPressed = false;
    GreenfootSound sound = new GreenfootSound("Thunder.wav");
    GreenfootSound sound2 = new GreenfootSound("Jump.wav");
    private int soundCounter;

    public Gear2(Vector movement)
    {
        Life = MAX_HEALTH;
        Henergy = MAX_ENERGY;
        counter = 0;
        sound.setVolume(100);
        sound2.setVolume(100);
        sound.setVolume(65);
        if(Volume.volumeOn == true)
        {
            Game.gameMusic.setVolume(80);
        }
        getInvisible = false;
    }

    public void act() 
    {
        prepareToBattle();
        if(isPaused() == false)
        {
            super.act();
            attackTimer++;
            adjustLife();
            teleport();
            counter++;
            lighten();
            dispersion();
            timer++;
            animate(speed);
            if(timer>=8)
            {
                if(getWorld() != null)
                {
                    List<Lightning> lightnings = getWorld().getObjects(Lightning.class);
                    getWorld().removeObjects(lightnings);
                    List<Lightning0> lightnings0 = getWorld().getObjects(Lightning0.class);
                    getWorld().removeObjects(lightnings0);
                    Lightning0 lightning0 = new Lightning0(0);
                    getWorld().addObject(lightning0, 30, 15);
                }
            }
        }
        try
        {
            if(getWorld().getObjects(Lightning.class).isEmpty())soundCounter++;
            if(soundCounter == 100)
            {
                sound.stop();
                soundCounter = 0;
            }
        }catch(NullPointerException e){}
    }

    /**
     * Téléporte le vaisseau à l'emplacement de la souris et crée une explosion
     */
    public void teleport()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse != null)
        {
            if(Greenfoot.isKeyDown("space") && counter >= MAX_COUNTER)
            {
                this.setLocation(mouse.getX(), mouse.getY());
                counter = 0;
                Explosion explosion = new Explosion();
                getWorld().addObject(explosion, this.getX(), this.getY());
                Henergy -=180;
                Gears.lostEnergy = true;
                sound2.play();
            }
            else
            {
                Gears.lostEnergy = false;
            }
        }
    }

    /**
     * Crée le première partie de l'éclair dans la direction de la souris
     */
    public void lighten()
    {
        if(getWorld()!=null)
        {
            MouseInfo mouse = Greenfoot.getMouseInfo();
            if(mouse != null)
            {
                int xMouse = mouse.getX();
                int yMouse = mouse.getY();
                int xGear = this.getX();
                int yGear = this.getY();
                double dx = xMouse-xGear;
                double dy = yMouse-yGear;
                int rotation = (int) Math.toDegrees(Math.atan(dy/dx));
                if(Game.bossSpawned == 0)
                {
                    if(Greenfoot.mousePressed(null) && timer >=30)
                    {
                        if(sound.isPlaying())sound.stop();
                        if(mouse.getX()<this.getX())
                        {
                            Lightning0 lightning = new Lightning0(rotation +180);
                            getWorld().addObject(lightning, this.getX(), this.getY());
                        }
                        else if(mouse.getX()>= this.getX())
                        {
                            Lightning0 lightning = new Lightning0(rotation);
                            getWorld().addObject(lightning, this.getX(), this.getY());
                        }
                        isLightning = true;
                        sound.play();
                    }
                }
                else if(Game.bossSpawned == 1) //Lorsque le boss est là, le mode de tir passe de l'éclair aux projectiles (mitraillette)
                {
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
                    if(Greenfoot.mouseClicked(null))
                    {
                        isPressed = false;
                    }
                    if(isPressed == true)
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
                    }
                }
            }
        }
    }

    /**
     * Ajoute les prochains traits de l'éclair
     */
    public void addLightning()
    {
        Actor lightning = (Actor)getWorld().getObjects(Lightning0.class).get(1); 
        Lightning lightningNext = new Lightning(lightning.getRotation());
        getWorld().addObject(lightningNext, lightning.getX(), lightning.getY());
        lightningNext.move(16);
        lightningNext.setRotation(lightning.getRotation()-20 +Greenfoot.getRandomNumber(40));
    }

    /**
     * Ajoute les traits de l'éclair suivants
     */
    public void addLightning2()
    {
        for(int i = 1; i<3; i++)
        {
            Actor lightning = (Actor)getWorld().getObjects(Lightning.class).get(i); 
            Lightning lightningNext = new Lightning(lightning.getRotation());
            getWorld().addObject(lightningNext, lightning.getX(), lightning.getY());
            lightningNext.move(16);
            lightningNext.setRotation(lightning.getRotation()-30 +Greenfoot.getRandomNumber(60));
        }
    }

    /**
     * Ajoute encore des traits d'éclair
     */
    public void addLightning3()
    {
        for(int i = 3; i<7; i++)
        {
            Actor lightning = (Actor)getWorld().getObjects(Lightning.class).get(i); 
            Lightning lightningNext = new Lightning(lightning.getRotation());
            getWorld().addObject(lightningNext, lightning.getX(), lightning.getY());
            lightningNext.move(16);
            lightningNext.setRotation(lightning.getRotation()-30 +Greenfoot.getRandomNumber(60));
        }
    }

    /**
     * Et encore un autre afin d'augmenter la portée
     */
    public void addLightning4()
    {
        for(int i = 1; i<15; i++)
        {
            Actor lightning = (Actor)getWorld().getObjects(Lightning.class).get(i); 
            Lightning lightningNext = new Lightning(lightning.getRotation());
            getWorld().addObject(lightningNext, lightning.getX(), lightning.getY());
            lightningNext.move(16);
            lightningNext.setRotation(lightning.getRotation()-30 +Greenfoot.getRandomNumber(60));
        }
    }

    /**
     * Continue d'augmenter la taille de l'éclair
     */
    public void addLightning5()
    {
        for(int i = 1; i<31; i++)
        {
            Actor lightning = (Actor)getWorld().getObjects(Lightning.class).get(i); 
            Lightning lightningNext = new Lightning(lightning.getRotation());
            getWorld().addObject(lightningNext, lightning.getX(), lightning.getY());
            lightningNext.move(16);
            lightningNext.setRotation(lightning.getRotation()-30 +Greenfoot.getRandomNumber(60));
        }
    }

    /**
     * Regroupe la création de chaque groupe de segments de l'éclair. Impose la création d'uniquement 2 branches filles par branche mère
     */
    public void dispersion()
    {
        if(getWorld() !=null)
        {
            if(isLightning == true)
            {
                for(int i = 0; i<2; i++)
                {
                    addLightning();
                }
                for(int i = 0; i<2; i++)
                {
                    addLightning2();
                }
                for(int i = 0; i<2; i++)
                {
                    addLightning3();
                }
                for(int i = 0; i<2; i++)
                {
                    addLightning4();
                }
                for(int i = 0; i<2; i++)
                {
                    addLightning5();
                }
                isLightning = false;
                timer = 0;
            }
        }
    }

    /**
     * Empêche que les points de vie dépassent la limite maximale
     */
    public void adjustLife()
    {
        if(Life>MAX_HEALTH)
        {
            Life = MAX_HEALTH;
        }
    }

}
