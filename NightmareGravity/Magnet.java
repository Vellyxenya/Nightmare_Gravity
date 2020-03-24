import greenfoot.*;
import java.util.List;
import java.awt.Color;

/**
 * Objets rebondissants et infligeant des dégâts.
 */
public class Magnet extends Hurting
{
    public int counter2;
    public static int counter = 0;
    private int speed = 3;
    /**
     * Retourne true si cet objet est vulnérable et peut donc subir des dégâts et les
     * transférer au boss
     */
    private boolean vulnerable;
    private boolean react = false;
    private int distance;

    public Magnet()
    {
        vulnerable = false;
        setRotation(Greenfoot.getRandomNumber(360));
        distance = 0;
    }

    public void act()
    {
        if(isPaused() == false)
        {
            counter++;
            counter2--;
            beVulnerable();
            movements();
            bounceAtEdge(speed, counter);
            justBounce();
            unStick();
            match();
            takeDamageForBoss((Weapon2.weapon2Damage/10), Weapon2.class);
            takeDamageForBoss(Lightning.damage, Lightning.class);
        }
    }

    /**
     * Méthode qui empêche cet objet de se retrouver coincé au bord de l'écran
     */
    public void unStick()
    {
        if(getX()<25 || getY()<25 || getY()>531 || getX()>725)
        {
            react = true;
        }
        if(react == true)
        {
            turnTowards(getWorld().getWidth()/2, getWorld().getHeight()/2);
            move(5);
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
     * Mouvements ...
     */
    public void movements()
    {
        move(speed);
    }

    /**
     * Crée un réseau d'arcs électriques infligeant des dégâts entre chacun de ces objets
     */
    public void match()
    {
        if(getWorld()!= null)
        {
            List<Magnet> magnets = getWorld().getObjects(Magnet.class);
            for(Magnet magnet : magnets)
            {
                if(magnet != this)
                { 
                    int dx = (magnet.getX()-this.getX())/2;
                    int dy = (magnet.getY()-this.getY())/2;
                    int rotation = 50;
                    int length = (int)Math.sqrt(Math.pow(dx*2, 2)+Math.pow(dy*2, 2));
                    if(length == 0)
                    {
                        length = 1;
                    }
                    Arc arc = new Arc();
                    getWorld().addObject(arc, this.getX()+dx, this.getY()+dy);
                    arc.turnTowards(magnet.getX(), magnet.getY());
                    arc.getImage().scale(length, arc.getImage().getHeight()/4);
                }
            }
        }
    }

    /**
     * Si cet objet est touché en étant vulnérable, c'est le boss qui subit des dégâts.
     * Sinon c'est le joueur
     */
    public void takeDamageForBoss(int hurt, Class classe)
    {
        if(getWorld()!= null)
        {
            if(this.vulnerable == true)
            {
                try
                {
                    if(this.isTouching(classe))
                    {
                        Boss6.health = Boss6.health - hurt;
                        if(Boss6.health<= 0)
                        {
                            Boss6 boss = (Boss6) getWorld().getObjects(Boss6.class).get(0);
                            getWorld().removeObject(boss);
                        }
                    }
                }
                catch(IndexOutOfBoundsException e)
                {
                    try
                    {
                        getWorld().addObject(new Transition(), getWorld().getWidth()/2, getWorld().getHeight()/2);
                        List<Arc> arcs = getWorld().getObjects(Arc.class);
                        getWorld().removeObjects(arcs);
                        List<Magnet> magnets = getWorld().getObjects(Magnet.class);
                        Game.killedEnemies++;
                        Game.bossSpawned = 0;
                        Game.bossCreated = false;
                        Game.Level += 1;
                        Game.bossCounter = 0;
                        for(Magnet magnet : magnets)
                        {
                            getWorld().removeObject(magnet);
                        }
                        return;
                    }catch(NullPointerException ex){}
                }
            }
        }
    }

    /**
     * Périodiquement un de ces objets devient vulnérable et change d'image
     */
    public void beVulnerable()
    {
        if(this.counter2 <= Boss6.vulnerability && this.counter2 >0)
        {
            this.setImage("Magnet2.png");
            vulnerable = true;
        }
        else if(this.counter2<= 0)
        {
            this.setImage("Magnet.png");
            vulnerable = false;
        }
    }

    /**
     * Rebondit contre les auters objets de cette classe
     */
    public void justBounce()
    {
        if(getWorld() != null)
        {
            List<Magnet> magnets = (List<Magnet>) getWorld().getObjects(Magnet.class);
            for(Magnet magnet : magnets)
            {
                if(magnet != this)
                {
                    int dx = this.getX()-magnet.getX();
                    int dy = this.getY()-magnet.getY();
                    int angle = Math.abs((int) Math.toDegrees(Math.atan((double)dx/(double)dy)));
                    if(!this.getObjectsInRange(this.getImage().getWidth(), magnet.getClass()).isEmpty())
                    {
                        if(this.getY()>magnet.getY() && this.getX()<magnet.getX()) //Sud-West
                        {
                            this.setRotation(360-this.getRotation()+2*angle);
                            adjust();
                            move(speed);
                        }
                        else if(this.getY()<magnet.getY() && this.getX()<magnet.getX()) //Nord-West
                        {
                            this.setRotation(360-this.getRotation()-2*angle);
                            adjust();
                            move(speed);
                        }
                        else if(this.getY()>magnet.getY() && this.getX()>magnet.getX()) //Sud-Est
                        {
                            this.setRotation(180-this.getRotation()-2*angle);
                            adjust();
                            move(speed);
                        }
                        else if(this.getY()<magnet.getY() && this.getX()>magnet.getX()) //Nord-Est
                        {
                            this.setRotation(360-this.getRotation()+2*angle);
                            adjust();
                            move(speed);
                        }
                    }
                }
            }
        }
    }

}
