import greenfoot.*;

public class Gear4 extends Gears
{
    /**
     * Points de vie maximum
     */
    public static final int MAX_HEALTH = 7000;
    /**
     * Points d'énergie maximum
     */
    public static final int MAX_ENERGY = 5000;
    /**
     * Vitesse du vaisseau
     */
    private int speed = 3;
    /**
     * Masse du vaisseau
     */
    public static final int MASS = 50;
    /**
     * Puissance accumulée par le vaisseau
     */
    public static int power;
    /**
     * Si charge est true, le vaisseau peut se déplacer à haute vitesse
     */
    private boolean charge = false;
    /**
     * Si propulse est true, le vaisseau peut se déplacer à une vitesse assez élevée
     */
    private boolean propulse = false;
    /**
     * Indique si la souris est pressée ou pas
     */
    private boolean isPressed = false;
    /**
     * Compteur pour les tirs
     */
    private int attackTimer = 0;
    private boolean again = false;
    private int cooldown;
    private int focusTime;
    Focus focus = new Focus();

    public Gear4(Vector movement)
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
            attackTimer++;
            try
            {
                movements();
                rotate();
                focus();
                propulse();
                charge();
                fury();
                aim();
            }catch(IllegalStateException e){}
        }
    }

    /**
     * Tourne dans la direction de la souris
     */
    public void rotate()
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
                if(mouse.getX()<this.getX())
                {
                    setRotation(rotation+180);
                }
                else if(mouse.getX()>= this.getX())
                {
                    setRotation(rotation);
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
        }
    }

    /**
     * Mouvements du vaisseau
     */
    public void movements()
    {
        if(Greenfoot.isKeyDown("S"))
        {
            setLocation(getX(), getY()+speed-speed*power/60);
        }
        if(Greenfoot.isKeyDown("W"))
        {
            setLocation(getX(), getY()-speed+speed*power/60);
        }
        if(Greenfoot.isKeyDown("D"))
        {
            setLocation(getX()+speed-speed*power/60, getY());
        }
        if(Greenfoot.isKeyDown("A"))
        {
            setLocation(getX()-speed+speed*power/60, getY());
        }
    }

    /**
     * Gère la puissance accumulée par le vaisseau
     */
    public void focus()
    {
        if(Greenfoot.isKeyDown("space"))
        {
            if(power<60 && again == false)
            {
                power++;
            }
            else if(power<60 && again == true)
            {
                if(power>0)
                {
                    power--;
                }
            }
        }
        else if(!Greenfoot.isKeyDown("space"))
        {
            if(again == false && power>0)
            {
                again = true;
                if(power<=15)
                {
                    propulse = true;
                    power = 15;
                }
                else if(power>15 && power<60)
                {
                    charge = true;
                    power = 10;
                }
            }
            if(power>0)
            {
                power--;
            }
            else if(power == 0)
            {
                again = false;
                propulse = false;
                charge = false;
                speed = 3;
            }
        }
    }

    /**
     * Tire une rafale de missile dans toutes les directions
     */
    public void fury()
    {
        if(power == 60 && Greenfoot.isKeyDown("space"))
        {
            if(Henergy>0)
            {
                int random = Greenfoot.getRandomNumber(3);
                if(random == 0)
                {
                    MouseInfo mouse = Greenfoot.getMouseInfo();
                    if(mouse != null)
                    {
                        getWorld().addObject(new Hawk(mouse.getX(), mouse.getY(), this.getX(),
                        this.getY(), this.getRotation()), this.getX(), this.getY());
                    }
                }
                Henergy -= 2;
            }
            Gears.lostEnergy = true;
        }
        else
        {
            Gears.lostEnergy = false;
        }
    }

    /**
     * Le vitesse peut se déplacer à haute vitesse
     */
    public void propulse()
    {
        if(propulse == true)
        {
            speed = 10;
        }
    }

    /**
     * Le vaisseau charge dans une direction
     */
    public void charge()
    {
        if(charge == true)
        {
            speed = 12;
        }
    }

    /**
     * Vise un ennemi et lui tire dessus après un petit délai
     */
    public void aim()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse != null)
        {
            try
            {
                Opponent e = (Opponent) getWorld().getObjectsAt(mouse.getX(), mouse.getY(), Opponent.class).get(0);
                if(getWorld().getObjects(Focus.class).isEmpty())
                {
                    getWorld().addObject(focus, e.getX(), e.getY());
                    focus.setImage("Focus.png");
                }
                if(e.mouseOn() == true)
                {
                    focus.setLocation(e.getX(), e.getY());
                    focusTime++;
                    if(focusTime>=60)
                    {
                        getWorld().removeObject(focus);
                        int random = Greenfoot.getRandomNumber(2);
                        if(random == 0) getWorld().addObject(new Hawk(this.getRotation()+60, e), this.getX(), this.getY());
                        else getWorld().addObject(new Hawk(this.getRotation()-60, e), this.getX(), this.getY());
                        focusTime = 0;
                    }
                }
            }
            catch(IndexOutOfBoundsException e)
            {
                getWorld().removeObject(focus);
                focusTime = 0;
            }
        }
    }

    /**
     * Méthode ajustant les points de vie pour qu'ils ne dépassent pas la limite max.
     */
    public void adjustLife()
    {
        if(Life>MAX_HEALTH)
        {
            Life = MAX_HEALTH;
        }
    }

}

