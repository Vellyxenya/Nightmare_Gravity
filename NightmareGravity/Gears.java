import greenfoot.*;
import java.util.List;

public class Gears extends SmoothMover
{
    /** Retourne si le vaisseau est bougé ou pas */
    public static boolean isMoved = false;
    /** Point de vie des vaisseaux */
    public static int Life;
    /** Maximum d'énergie des vaisseaux */
    public static int maxEnergy;
    /** Point d'énergie des vaisseuax */
    public static int Henergy;
    /** variable stockant le monde tel qu'il est actuellement */
    public static World currentWorld;
    /** taux de restauration d'énergie */
    public static final int restoredEnergy = 10;
    /** renseigne si le vaisseau vient de perdre de l'énergie ou pas*/
    public static boolean lostEnergy = false;
    /** compteur pour le temps écoulé depuis la dernière perte d'énergie */
    public static int energyCounter = 0;
    /** lettre assignée à la compétence ShieldSpell */
    public static String letter;
    /** Masse du vaisseau */
    public static final int MASS = 50;
    /** Vitesse du vaisseau*/
    public static int speed;
    /** Indique si le vaisseau est entré en collision avec un boss et est donc repoussé */
    public static boolean react;
    /** Indique la distance sur laquelle le vaisseua doit réagir. */
    public static int distance;
    /** devient invisible (vaisseau 3) */
    public boolean getInvisible;
    Block block = new Block();

    public Gears()
    {
        react = false;
        distance = 0;
    }

    /**
     * Comportement général de tous les vaisseaux
     */
    public void act() 
    {
        if(isPaused() == false)
        {
            if(getWorld()!= null)
            {
                try
                {
                    try
                    {
                        enterNightmare();
                        if(Gear4.power<60)
                        {
                            applyGravity(MASS);
                        }
                        getStunned(speed, StunningOrbe.class);
                        getStunned(speed, Domination.class);
                        getStunned(speed, OrbeBoss8.class);
                        getStunned(speed, RotationCenter2.class);
                        getStunned(speed, Web.class);
                        if(getInvisible == false)
                        {
                            takeLaserDamage();
                            takeDamageBeta(RaptorWeapon.dDamage, RaptorWeapon.class);
                            takeDamageBeta(Missile.damage, Missile.class);
                            takeDamageBeta(BossBullet.damage, BossBullet.class);
                            takeDamageBeta(Orbe.damage, Orbe.class);
                            takeDamageBeta(MegaLightning.damageLight, MegaLightning.class);
                            takeDamageBeta(Electricity.damage, Electricity.class);
                            takeDamageBeta(AsteroidExplosion.damage, AsteroidExplosion.class);
                            takeDamageBeta(Hurting.damage, Hurting.class);
                            takeDamageBeta(BlueHit.damage, BlueHit.class);
                            takeDamageBeta(Burst.damage, Burst.class);
                            takeDamageBeta(Lightning2.damage, Lightning2.class);
                            takeDamageRift();
                            takeDamage();
                            takeDamageSpecial(Gaz.damage, Gaz.class);
                            takeDamageSpecial(Gaz2.damage, Gaz2.class);
                            takeDamageSpecial(Circle.damage, Circle.class);
                        }
                        finalTraps();
                        shield(letter);
                        speed();
                        regenHealth();
                        gainCredit();
                        restoreEnergy();
                        getMovements();
                        getPunished();
                        getPunishedBoss3();
                        if(Game.Level>6 && !getWorld().getObjects(Magnet.class).isEmpty())
                        {
                            getWorld().removeObjects(getWorld().getObjects(Magnet.class));
                        }
                    }catch(IllegalStateException e){}
                }catch(NullPointerException ex){}
            }
        }
    }
    
    public void getDamaged()
    {
        Life-=5;
    }

    /**
     * Centre le vaisseau avant le début du combat contre un boss
     */
    public void prepareToBattle()
    {
        if(!getWorld().getObjects(Halo.class).isEmpty())
        {
            if(getX() > 200) setLocation(getX()-1, getY());
            else if(getX()<200) setLocation(getX()+1, getY());
            if(getY() > getWorld().getHeight()/2) setLocation(getX(), getY()-1);
            else if(getY() < getWorld().getHeight()/2) setLocation(getX(), getY()+1);
        }
    }

    /**
     * Le vaisseau est rejeté s'il entre en collision avec un boss
     */
    public void getPunished()
    {
        if(isTouching(Bosses.class))
        {
            Life = Life - Bosses.damage;
            if(Life <=0)
            {
                getWorld().removeObject(this);
                Store.wealth += Game.credit;
            }
            react = true;
        }
        if(react == true)
        {
            if(Greenfoot.isKeyDown("Up")||Greenfoot.isKeyDown("W"))
            {
                mySetLocation(this.getX(),this.getY()+speed);
            }
            if(Greenfoot.isKeyDown("Down")||Greenfoot.isKeyDown("S"))
            {
                mySetLocation(this.getX(),this.getY()-speed);
            }
            if(Greenfoot.isKeyDown("Right")||Greenfoot.isKeyDown("D"))
            {
                mySetLocation(this.getX()-speed ,this.getY());
            }
            if(Greenfoot.isKeyDown("Left") ||Greenfoot.isKeyDown("A"))
            {
                mySetLocation(this.getX()+speed ,this.getY());
            }
            try
            {
                Bosses boss = (Bosses) getWorld().getObjects(Bosses.class).get(0);
                turnTowards(boss.getX(), boss.getY());
            }catch(IndexOutOfBoundsException e){}
            move(-15);
            distance++;
        }
        if(distance >= 15)
        {
            react = false;
            setRotation(0);
            distance = 0;
        }
    }

    /**
     * Le vaisseau est repoussé vers le centre s'il s'approche trop des bords (contre le 3e boss)
     */
    public void getPunishedBoss3()
    {
        if(!getWorld().getObjects(Boss3.class).isEmpty() && (getX()<50 || getY()<50 || getY()>513))
        {
            Life = Life - 1;
            if(Life <=0)
            {
                getWorld().removeObject(this);
                Store.wealth += Game.credit;
                Greenfoot.setWorld(new GameOver());
            }
            react = true;
        }
        if(react == true)
        {
            if(Greenfoot.isKeyDown("Up")||Greenfoot.isKeyDown("W"))
            {
                mySetLocation(this.getX(),this.getY()+speed);
            }
            if(Greenfoot.isKeyDown("Down")||Greenfoot.isKeyDown("S"))
            {
                mySetLocation(this.getX(),this.getY()-speed);
            }
            if(Greenfoot.isKeyDown("Right")||Greenfoot.isKeyDown("D"))
            {
                mySetLocation(this.getX()-speed ,this.getY());
            }
            if(Greenfoot.isKeyDown("Left") ||Greenfoot.isKeyDown("A"))
            {
                mySetLocation(this.getX()+speed ,this.getY());
            }
            turnTowards(getWorld().getWidth()/2, getWorld().getHeight()/2);
            move(12);
            distance++;
        }
        if(distance >= 10)
        {
            react = false;
            setRotation(0);
            distance = 0;
        }
    }

    /**
     * Obtient des vies supplémentaires pour le labyrinthe
     */
    public void gainLives()
    {
        if(isTouching(Life.class))
        {
            removeTouching(Life.class);
            Game.lives++;
        }
    }

    /**
     * Indique si le vaisseau est en mouvement ou pas
     */
    public void getMovements()
    {
        if(
        Greenfoot.isKeyDown("Up")||Greenfoot.isKeyDown("W")||
        Greenfoot.isKeyDown("Down")||Greenfoot.isKeyDown("S")||
        Greenfoot.isKeyDown("Right")||Greenfoot.isKeyDown("D")||
        Greenfoot.isKeyDown("Left") ||Greenfoot.isKeyDown("A")||
        getWorld().getObjects(Traps.class).isEmpty() == false  //car trap engendre un mouvement sans les touches directionnelles
        )
        {
            isMoved = true;
        }
        else{isMoved = false;}
    }

    /**
     * Mouvement des vaisseaux
     */
    public void animate(int speed)
    {
        if(getWorld()!= null)
        {
            Obstacle obstacle = new Obstacle();
            if(Greenfoot.isKeyDown("Up")||Greenfoot.isKeyDown("W"))
            {
                mySetLocation(this.getX(),this.getY()-speed);
            }
            if(Greenfoot.isKeyDown("Down")||Greenfoot.isKeyDown("S"))
            {
                mySetLocation(this.getX(),this.getY()+speed);
            }
            if(Greenfoot.isKeyDown("Right")||Greenfoot.isKeyDown("D"))
            {
                mySetLocation(this.getX()+speed ,this.getY());
            }
            if(Greenfoot.isKeyDown("Left") ||Greenfoot.isKeyDown("A"))
            {
                mySetLocation(this.getX()-speed ,this.getY());
            }
        }
    }

    /**
     * Méthode setLocation qui en prend en compte les obstacles
     */
    public void mySetLocation(int x, int y)
    {
        int currentX = getX();
        int currentY = getY();
        super.setLocation(x, y);
        if(!getIntersectingObjects(Obstacle.class).isEmpty())
        {
            super.setLocation(currentX, currentY);
        }
    }

    /**
     * Méthode animate pour le vaisseau 3 qui a un mode de propulsion spécial
     */
    public void animate2()
    {
        if(Greenfoot.isKeyDown("Up") || Greenfoot.isKeyDown("W")) {move(4);}
        if(Greenfoot.isKeyDown("Down") || Greenfoot.isKeyDown("S")) {move(-3);}
        if(Greenfoot.isKeyDown("D") && Greenfoot.isKeyDown("space")){setRotation(getRotation()+1);}
        else if(Greenfoot.isKeyDown("D")||Greenfoot.isKeyDown("Right")) {setRotation(getRotation()+5);}
        if(Greenfoot.isKeyDown("A") && Greenfoot.isKeyDown("space")){setRotation(getRotation()-1);}
        else if(Greenfoot.isKeyDown("A")||Greenfoot.isKeyDown("Left")) {setRotation(getRotation()-5);}
    }

    /**
     * Le vaisseau perd des points de vie s'il entre en collision avec des ennemis
     */
    public void takeDamage()
    {
        if(getWorld()!= null)
        {
            if(this.isTouching(Enemies.class))
            {
                Life = Life - Enemies.damage;
                if(Life <=0)
                {
                    getWorld().removeObject(this);
                    Store.wealth += Game.credit;
                }
            }
        }
    }

    /**
     * Le vaisseau perd des points de vie s'il entre en collision avec les failles générées par le 4e Boss
     */
    public void takeDamageRift()
    {
        if(this.isTouching(Rift.class))
        {
            Life = Life - Rift.damage;
            if(Life <=0)
            {
                getWorld().removeObject(this);
                Store.wealth += Game.credit;
            }
        }
    }

    /**
     * Le 3e Vaisseau est vulnérable aux dégâts s'il touche des ennemis en n'étant pas invisible
     */
    public void takeDamageGear3()
    {
        if(this.isTouching(Enemies.class) && Gear3.isInvisible() == false)
        {
            Life = Life - Enemies.damage;
            if(Life <=0)
            {
                getWorld().removeObject(this);
                Store.wealth += Game.credit;
            }
        }
    }

    /**
     * Le vaisseau reçoit des dégâts s'il touche le laser des objets de la classe Devices
     */
    public void takeLaserDamage()
    {
        if(getWorld() !=null)
        {
            if(this.isTouching(DeviceLaser.class) && Game.aimingCounter>150)
            {
                Life = Life - DeviceLaser.damage;
                if(Life <=0)
                {
                    getWorld().removeObject(this);
                    if(getWorld() != null)
                    {
                        List<DeviceLaser> weapons = getWorld().getObjects(DeviceLaser.class);
                        int length = weapons.size();
                        if(length >=1)
                        {
                            getWorld().removeObjects(weapons);
                        }
                    }
                    Store.wealth += Game.credit;
                }
            }
        }
    }

    /**
     * Le vaisseau reçoit des dégâts en touchant n'importe quelle arme ennemie
     */
    public void takeDamageBeta(int thatDamage, Class classe)
    {
        if(getWorld() !=null)
        {
            if(this.isTouching(classe))
            {
                Life = Life - thatDamage;
                if(Life <=0)
                {
                    getWorld().removeObject(this);
                    Store.wealth += Game.credit;
                }
            }
        }
    }

    /**
     * Le vaisseau reçoit des dégâts s'il y a collision (pixel par pixel)
     */
    public void takeDamageSpecial(int thatDamage, Class classe)
    {
        if(getWorld() !=null)
        {
            if(!getTouchedObjects(classe).isEmpty())
            {
                Life = Life - thatDamage;
                if(Life <=0)
                {
                    getWorld().removeObject(this);
                    Store.wealth += Game.credit;
                    //Greenfoot.setWorld(new GameOver());
                }
            }
        }
    }

    /**
     * Reçoit des dégâts s'il touche les trous noirs du dernier Boss
     */
    public void finalTraps()
    {
        if(getWorld()!= null)
        {
            if(isTouching(FinalTraps.class))
            {
                Life -= 100;
                if(Life <=0)
                {
                    getWorld().removeObject(this);
                    Store.wealth += Game.credit;
                }
            }
        }
    }

    /**
     * Applique la formule de Newton de la gravitation à cet acteur
     */
    public void applyGravity(int mass)
    {
        if(getWorld()!= null)
        {
            List<Traps> traps = getWorld().getObjects(Traps.class);
            int size = traps.size();
            if(size>0)
            {
                for(Traps trap : traps)
                {
                    double dx = trap.getExactX() - this.getExactX();
                    double dy = trap.getExactY() - this.getExactY();
                    Vector force = new Vector (dx, dy);
                    double distance = Math.sqrt (dx*dx + dy*dy);
                    double strength = GRAVITY * mass * trap.mass / (distance * distance);
                    double acceleration = strength / mass;
                    force.setLength (acceleration);
                    myAddForce (force);
                    move();
                }
            }
        }
    }

    /**
     * Si le vaisseau entre en collision avec un trou-noir il a une certaine chance (qui dépend du niveau) d'affronter 
     * un Nightmare ou de perdre directement
     */
    public void enterNightmare()
    {
        if(getWorld()!=null)
        {
            if(this.isTouching(Trap.class))
            {
                currentWorld = getWorld();
                List<Trap> traps = getWorld().getObjects(Trap.class);
                int length = traps.size();
                if(length >=1)
                {
                    getWorld().removeObjects(traps);
                }
                Greenfoot.setWorld(new Nightmare(10-Game.Level));
            }
        }
    }

    /**
     * Restaure de l'énergie si aucune compétence n'a utilisée pendant un certain temps
     */
    public void restoreEnergy()
    {
        if(Gears.lostEnergy == false)
        {
            energyCounter++;
            if(energyCounter >= 240)
            {
                if(Henergy < 500)
                {
                    Henergy += 1;
                }
            }
        }
        else if(Gears.lostEnergy == true)
        {
            energyCounter = 0;
        }
    }

    /**
     * le vaisseau est immobilisé et subit des secousses s'il est touchée par un objet de la classe StunningOrbe
     */
    public void getStunned(int speed, Class classe)
    {
        if(this.isTouching(classe))
        {
            if(Greenfoot.isKeyDown("Up")||Greenfoot.isKeyDown("W"))
            {
                mySetLocation(this.getX(),this.getY()+speed);
            }
            if(Greenfoot.isKeyDown("Down")||Greenfoot.isKeyDown("S"))
            {
                mySetLocation(this.getX(),this.getY()-speed);
            }
            if(Greenfoot.isKeyDown("Right")||Greenfoot.isKeyDown("D"))
            {
                mySetLocation(this.getX()-speed ,this.getY());
            }
            if(Greenfoot.isKeyDown("Left") ||Greenfoot.isKeyDown("A"))
            {
                mySetLocation(this.getX()+speed ,this.getY());
            }
        }
    }

    /**
     * Compétence qui invoque un bouclier dans la direction de la souris.
     */
    public void shield(String letter)
    {
        if(getWorld()!=null)
        {
            MouseInfo mouse = Greenfoot.getMouseInfo();
            if(mouse != null)
            {
                if(ShieldIcon.used == true)
                {
                    if(Greenfoot.isKeyDown(letter))
                    {
                        int xMouse = mouse.getX();
                        int yMouse = mouse.getY();
                        int xGear = this.getX();
                        int yGear = this.getY();
                        double dx = xMouse-xGear;
                        double dy = yMouse-yGear;
                        int rotation = (int) Math.toDegrees(Math.atan(dy/dx));
                        getWorld().addObject(block, getX(), getY());
                        if(mouse.getX()<this.getX())
                        {
                            block.setRotation(rotation+180);
                        }
                        else if(mouse.getX()>= this.getX())
                        {
                            block.setRotation(rotation);
                        }
                        Henergy-=2;
                        Gears.lostEnergy = true;
                    }
                    else
                    {
                        getWorld().removeObject(block);
                    }
                }
            }
        }
    }

    /**
     * Compétence augmentant la vitesse du vaisseau
     */
    public void speed()
    {
        if(SpeedIcon.used == true)
        {
            if(Greenfoot.isKeyDown("Up")||Greenfoot.isKeyDown("W"))
            {
                mySetLocation(this.getX(),this.getY()-3);
            }
            if(Greenfoot.isKeyDown("Down")||Greenfoot.isKeyDown("S"))
            {
                mySetLocation(this.getX(),this.getY()+3);
            }
            if(Greenfoot.isKeyDown("Right")||Greenfoot.isKeyDown("D"))
            {
                mySetLocation(this.getX()+3 ,this.getY());
            }
            if(Greenfoot.isKeyDown("Left") ||Greenfoot.isKeyDown("A"))
            {
                mySetLocation(this.getX()-3 ,this.getY());
            }
        }
    }

    /**
     * Compétence de régénération de vie
     */
    public void regenHealth()
    {
        if(RegenIcon.used == true)
        {
            if(Game.time%2 == 0 && Life<3000)
            {
                Life+=2;
            }
        }
    }

    /**
     * Compétence générant des crédits périodiquement
     */
    public void gainCredit()
    {
        if(CreditIcon.used == true)
        {
            if(Game.time>6 && Game.time%10 == 0)
            {
                Game.credit++;
            }
        }
    }

}