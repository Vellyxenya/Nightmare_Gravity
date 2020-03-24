import greenfoot.*;

public class Gear3 extends Gears
{
    /**
     * Maximum des points de vie
     */
    public static final int MAX_HEALTH = 5000;
    /**
     * Maximum des points d'énergie
     */
    public static final int MAX_ENERGY = 500;
    /**
     * Vitesse d'attaque de la mitrailleuse.
     */
    private static int attackSpeed = 6;
    /**
     * Compteur avant la prochaine attaque
     */
    private int attackTimer;
    /**
     * Masse du vaisseau
     */
    public static final int MASS = 50;
    /**
     * Vitesse du vaisseau
     */
    public int speed = 3;
    /**
     * Retourne si la souris est pressée ou pas
     */
    private boolean isPressed = false;
    Gray gray = new Gray();
    GreenfootSound sound = new GreenfootSound("invisible.wav");

    public Gear3(Vector movement)
    {
        Life = MAX_HEALTH;
        Henergy = MAX_ENERGY;
        getInvisible = false;
    }

    public void act() 
    {
        prepareToBattle();
        if(isPaused() == false)
        {
            super.act();
            adjustLife();
            animate2();
            if(isInvisible() == true) animate2();
            fire();
            attackTimer++;
            loseEnergy();
            inv();
        }
    }

    /**
     * Tire des balles avec une certaine dispersion
     */
    public void fire()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse != null)
        {
            try{
                int xMouse = mouse.getX();
                int yMouse = mouse.getY();
                int xGear = this.getX();
                int yGear = this.getY();
                double dx = xMouse-xGear;
                double dy = yMouse-yGear;
                int rotation = (int) Math.toDegrees(Math.atan(dy/dx));
                if(Game.bossSpawned == 0)
                {
                    if(Greenfoot.mousePressed(null))
                    {
                        if(mouse.getX()<this.getX())
                        {
                            Weapon2 weapon = new Weapon2(getRotation()-5+Greenfoot.getRandomNumber(10)+180, 15, 0);
                            getWorld().addObject(weapon, this.getX(), this.getY());
                            attackTimer = 0;
                        }
                        else if(mouse.getX()>= this.getX())
                        {
                            Weapon2 weapon = new Weapon2(getRotation()-5+Greenfoot.getRandomNumber(10), 15, 0);
                            getWorld().addObject(weapon, this.getX(), this.getY());
                            attackTimer = 0;
                        }
                        isPressed = true;
                    }
                    if(Greenfoot.mouseClicked(null))
                    {
                        isPressed = false;
                    }
                    if(isPressed == true && attackTimer >= attackSpeed)
                    {
                        if(mouse.getX()<this.getX())
                        {
                            Weapon2 weapon = new Weapon2(getRotation()-5+Greenfoot.getRandomNumber(10)+180, 15, 0);
                            getWorld().addObject(weapon, this.getX(), this.getY());
                            attackTimer = 0;
                        }
                        else if(mouse.getX()>= this.getX())
                        {
                            Weapon2 weapon = new Weapon2(getRotation()-5+Greenfoot.getRandomNumber(10), 15, 0);
                            getWorld().addObject(weapon, this.getX(), this.getY());
                            attackTimer = 0; 
                        }
                    }
                }
                else if(Game.bossSpawned == 1)
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
            }catch(IllegalStateException ise){}
        }
    }

    /**
     * Perd de l'énergie quand il est invisible
     */
    public void loseEnergy()
    {
        if(isInvisible() == true && isPaused() == false)
        {
            Henergy--;
            Gears.lostEnergy = true;
            //setImage("Orbe.png");
            Greenfoot.setSpeed(40);
            speed = 6;
            attackSpeed = 3;
            Weapon2.speed = 30;
            getWorld().addObject(gray, getWorld().getWidth()/2, getWorld().getHeight()/2);
            sound.playLoop();
            Game.gameMusic.pause();
        }
        else
        {
            setImage("Gear3.png");
            Gears.lostEnergy = false;
            Greenfoot.setSpeed(50);
            speed = 3;
            attackSpeed = 6;
            Weapon2.speed = 15;
            sound.stop();
            if(Volume. volumeOn == true) Game.gameMusic.playLoop();
            if(gray != null)
            {
                try
                {
                    getWorld().removeObject(gray);
                }catch(NullPointerException e){}
            }
        }
    }

    /**
     * Devient invisible
     */
    public void inv()
    {
        if(Greenfoot.isKeyDown("space")&& Gear3.Henergy >1)getInvisible = true;
        else getInvisible = false;
    }

    /**
     * Retourne si le vaisseau est invisible ou pas
     */
    public static boolean isInvisible()
    {
        if(Greenfoot.isKeyDown("space")&& Gear3.Henergy >1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Méthode empêchant que la barre de vie se "surcharge"
     */
    public void adjustLife()
    {
        if(Life>MAX_HEALTH)
        {
            Life = MAX_HEALTH;
        }
    }

}
