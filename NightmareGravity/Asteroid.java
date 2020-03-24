import greenfoot.*;
import java.util.List;

/**
 * Astéroïdes invoqués par le Boss5.. Ils servent de protection mais infligent des dégâts s'ils entrent en collision avec le vaisseau.
 * Ils peuvent être capurés par le vaisseau si celui-ci reste près d'eux assez longtemps
 */
public class Asteroid extends SmoothMover
{
    private int counter = 0;
    private int speed = 2;
    private int stickCounter;
    private int number;
    private int trueRotation;
    private int Life;
    /**
     * A la possibilité d'être collé au vaiseau ou pas
     */
    private boolean stickable;
    /**
     * Points de vie de l'astéroïde
     */
    private int MAX_LIFE = 30000;
    // les 6 positions disponibles pour les astéroïdes.
    private boolean p0;
    private boolean p1;
    private boolean p2;
    private boolean p3;
    private boolean p4;
    private boolean p5;
    /**
     * Accroché ou pas au vaisseau
     */
    public boolean stick;

    public Asteroid()
    {
        setRotation(Greenfoot.getRandomNumber(360));
        stickCounter = 0;
        stick = false;
        stickable = false;
        Life = 30000;
        p0 = true;
        p1 = true;
        p2 = true;
        p3 = true;
        p4 = true;
        p5 = true;
    }

    public void act() 
    {
        if(stick == false)
        {   
            counter++;
            move(speed);
            bounceAtEdge(speed, counter);
            justBounce();
            detectZones();
            stick();
            explode();
            takeDamageNew(Weapon1.littleDamage, Weapon1.class);
            takeDamageNew(Weapon2.weapon2Damage, Weapon2.class);
            takeDamageNew(Lightning.damage, Lightning.class);
            changeImage();
        }
        else if (stick == true)
        {
            follow();
        }
    }

    /**
     * Subit des dégâts des tirs du vaisseau
     */
    public void takeDamageNew(int hurt, Class classe)
    {
        if(getWorld() !=null)
        {
            if(this.isTouching(classe))
            {
                Life = Life - hurt;
                if(Life <=0)
                {
                    getWorld().removeObject(this);
                }
            }
        }
    }

    /**
     * Rebondit contre les autres astéroïdes
     */
    public void justBounce()
    {
        if(getWorld() != null)
        {
            List<Asteroid> asteroids = (List<Asteroid>) getWorld().getObjects(Asteroid.class);
            for(Asteroid asteroid : asteroids)
            {
                if(asteroid != this)
                {
                    int dx = this.getX()-asteroid.getX();
                    int dy = this.getY()-asteroid.getY();
                    int angle = Math.abs((int) Math.toDegrees(Math.atan((double)dx/(double)dy)));
                    if(!this.getObjectsInRange(this.getImage().getWidth(), asteroid.getClass()).isEmpty())
                    {
                        if(this.getY()>asteroid.getY() && this.getX()<asteroid.getX()) //Sud-West
                        {
                            this.setRotation(360-this.getRotation()+2*angle);
                            adjust();
                            move(speed);
                        }
                        else if(this.getY()<asteroid.getY() && this.getX()<asteroid.getX()) //Nord-West
                        {
                            this.setRotation(360-this.getRotation()-2*angle);
                            adjust();
                            move(speed);
                        }
                        else if(this.getY()>asteroid.getY() && this.getX()>asteroid.getX()) //Sud-Est
                        {
                            this.setRotation(180-this.getRotation()-2*angle);
                            adjust();
                            move(speed);
                        }
                        else if(this.getY()<asteroid.getY() && this.getX()>asteroid.getX()) //Nord-Est
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

    /**
     * Se colle au vaisseau s'il a moins de la moitié de ses points de vie et s'il reste à une certaine distance du vaisseau pendant un certain moment
     */
    public void stick()
    {
        if(this.stickable == true)
        {
            Gears gear = (Gears) getWorld().getObjects(Gears.class).get(0);
            int dx = Math.abs(gear.getX()-this.getX());
            int dy = Math.abs(gear.getY()-this.getY());
            int rotation = (int)Math.toDegrees(Math.atan((double)dy/(double)dx));
            if(this.getX()>gear.getX() && this.getY()>gear.getY())
            {
                trueRotation = rotation;
            }
            else if(this.getX()>gear.getX() && this.getY()<gear.getY())
            {
                trueRotation = 360-rotation;
            }
            else if(this.getX()<gear.getX() && this.getY()>gear.getY())
            {
                trueRotation = 180-rotation;
            }
            else if(this.getX()<gear.getX() && this.getY()<gear.getY())
            {
                trueRotation = 180+rotation;
            }
            if(!this.getObjectsInRange(this.getImage().getWidth()/2+gear.getImage().getWidth()/2+80, Gears.class).isEmpty())
            {
                stickCounter++;
                if(stickCounter>= 120)
                {
                    stick = true;
                    if(trueRotation>=330 && trueRotation<30) number = 0;
                    else if (trueRotation>=30 && trueRotation<90) number = 1;
                    else if (trueRotation>=90 && trueRotation<150) number = 2;
                    else if (trueRotation>=150 && trueRotation<210) number = 3;
                    else if (trueRotation>=210 && trueRotation<270) number = 4;
                    else if (trueRotation>=270 && trueRotation<330) number = 5;
                }
            }
        }
    }

    /**
     * Une fois collé au vaisseau, il le suit dans tous ses déplacements
     */
    public void follow()
    {
        if(getWorld()!= null)
        {
            Gears gear = (Gears) getWorld().getObjects(Gears.class).get(0);
            if(this.stick == true)
            {
                switch(number)
                {
                    case 0 :
                    if(p0 == false)
                    {
                        this.setLocation(gear.getX()+50, gear.getY());
                        Boss5.protection0 = true;
                    }
                    else    stickCounter = 0;
                    break;

                    case 1 :
                    if(p1 == false)
                    {
                        this.setLocation(gear.getX()+25, gear.getY()+30);
                        Boss5.protection1 = true;
                    }
                    else    stickCounter = 0;
                    break;

                    case 2 :
                    if(p2 == false)
                    {
                        this.setLocation(gear.getX()-25, gear.getY()+30);
                        Boss5.protection2 = true;
                    }
                    else    stickCounter = 0;
                    break;

                    case 3 :
                    if(p3 == false)
                    {
                        this.setLocation(gear.getX()-50, gear.getY());
                        Boss5.protection3 = true;
                    }
                    else    stickCounter = 0;
                    break;

                    case 4 :
                    if(p4 == false)
                    {
                        this.setLocation(gear.getX()-25, gear.getY()-30);
                        Boss5.protection4 = true;
                    }
                    else    stickCounter = 0;
                    break;

                    case 5 :
                    if(p5 == false)
                    {
                        this.setLocation(gear.getX()+25, gear.getY()-30);
                        Boss5.protection5 = true;
                    }
                    else stickCounter = 0;
                    break;
                }
            }
        }
    }

    /**
     * Explose s'il entre en collision avec le vaisseau, infligeant des dégâts
     */
    public void explode()
    {
        if(stick == false)
        {
            Gears gear = (Gears) getWorld().getObjects(Gears.class).get(0);
            if(!this.getObjectsInRange(this.getImage().getWidth()/2+gear.getImage().getWidth()/2, Gears.class).isEmpty())
            {
                getWorld().addObject(new AsteroidExplosion(), this.getX(), this.getY());
                getWorld().removeObject(this);
            }
        }
    }

    /**
     * Change d'image s'il a moins de la moitié de ses pv max
     */
    public void changeImage()
    {
        if(Life <= MAX_LIFE/2)
        {
            setImage("Asteroid2.png");
            stickable = true;
        }
    }

    /**
     * Renvoie si les 6 zones de protection du vaisseau sont couvertes par un astéroïde ou pas
     */
    public void detectZones()
    {
        if(getWorld()!= null)
        {
            Gears gear = (Gears) getWorld().getObjects(Gears.class).get(0);
            if(getWorld().getObjectsAt(gear.getX()+50, gear.getY(), Asteroid.class).isEmpty()) p0 = false;
            if(getWorld().getObjectsAt(gear.getX()+25, gear.getY()+30, Asteroid.class).isEmpty()) p1 = false;
            if(getWorld().getObjectsAt(gear.getX()-25, gear.getY()+30, Asteroid.class).isEmpty()) p2 = false;
            if(getWorld().getObjectsAt(gear.getX()-50, gear.getY(), Asteroid.class).isEmpty()) p3 = false;
            if(getWorld().getObjectsAt(gear.getX()-25, gear.getY()-30, Asteroid.class).isEmpty()) p4 = false;
            if(getWorld().getObjectsAt(gear.getX()+25, gear.getY()-30, Asteroid.class).isEmpty()) p5 = false;
        }
    }

}
