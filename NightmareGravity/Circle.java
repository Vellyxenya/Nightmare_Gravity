import greenfoot.*;
import java.util.List;

/**
 * Objet généré lors de la présence du Boss3 soit aléatoirement soit autour du vaisseau du joueur, interdit de le 
 * toucher avec le vaisseau ou de tirer dessus au risque de se prendre de sévères dégâts.
 */
public class Circle extends SmoothMover
{
    private int counter;
    private int x;
    private int y;
    private int duration;
    /**
     * Vitesse de déplacement des cercles
     */
    private int speed = 1;
    public static int damage;
    /**
     * Dégâts infligés
     */
    public static GreenfootImage[] images;
    public int numberImages = 0;
    /**
     * Détermine la taille maximale de l'image
     */
    public static int range = 10;
    /**
     * Indique si l'objet peut-être supprimé ou pas
     */
    private boolean removable;

    /**
     * Constructeur qui crée des cercles qui apparaissent autour du joueur
     */
    public Circle(int x)
    {
        setRotation(Greenfoot.getRandomNumber(360)); //le cercle a une rotation aléatoire, ce qui le fait partir dans une direction aléatoire
        counter = 0;
        duration = x; //la durée de vie du cercle
        initializeImages("Circle.png");
        setImage(images[0]);
        removable = false; //cet objet ne disparaît pas s'il touche d'autres cercles lors de sa propagation
        damage = 0; //les dégâts ne deviennent effectifs qu'une fois la taille max atteinte
    }

    /**
     * Constructeur qui crée les Circles qui spawn aléatoirement
     */
    public Circle()
    {
        setRotation(Greenfoot.getRandomNumber(360));
        counter = 0;
        duration = 300; //la durée de vie du cercle
        initializeImages("Circle.png");
        setImage(images[0]);
        removable = true; //cet objet disparaît s'il touche d'autres cercles lors de sa propagation
        damage = 0; //les dégâts ne deviennent effectifs qu'une fois la taille max atteinte
    }

    public void act() 
    {
        if(isPaused() == false)
        {
            duration--;
            propagate();
            if(duration<=0) //disparaît au bout d'un moment
            {
                getWorld().removeObject(this);
            }
            movements();
            justBounce();
            bounceAtEdge(speed, counter);
            damageGear();
            detectWeapons();
        }
    }

    /**
     * Initialise les images du grossissement du cercle
     */
    private static void initializeImages(String fileName)
    {
        if(images == null)
        {
            GreenfootImage firstImage = new GreenfootImage(fileName);
            images = new GreenfootImage[range];
            for (int i=0; i<range; i++)
            {
                int size = (i+1)*(firstImage.getWidth()/range);
                images[i] = new GreenfootImage(firstImage);
                images[i].scale(size, size);
            }
        }
    }

    /**
     * Fait grandir le Cercle (l'objet est très petit et grandit en une fraction de seconde)
     * Pour éviter des Cercle qui apparaissent où il y a déjà d'autres Circles.
     */
    public void propagate()
    {
        if (numberImages < range) //Le cercle grandit
        {
            setImage(images[numberImages]);
            numberImages++;
            if(numberImages <= (range-1)) //Si ce cercle touche un autre cercle alors qu'il n'est pas au maximum de sa taille, il est enlevé...
            {
                if(this.isTouching(Circle.class))
                {
                    if(this.removable == true) // ...si c'est un cercle qui apparaît aléatoirement...
                    {
                        getWorld().removeObject(this);
                    }
                    else if (this.removable == false) //... ou enlève les cercles qu'il touche si c'est un cercle qui apparaît autour du joueur.
                    {
                        this.removeTouching(Circle.class);
                    }
                }
            }
        }
    }

    /**
     * Ne fait des dégâts que s'il est au maximum de sa taille
     */
    public void damageGear()
    {
        if(this.numberImages == range)
        {
            damage = 1;
        }
    }

    public void movements()
    {
        move(speed);
    }

    /**
     * Rebondit contre les autres cercles
     */
    public void justBounce()
    {
        if(getWorld() != null)
        {
            List<Circle> circles = (List<Circle>) getWorld().getObjects(Circle.class);
            for(Circle circle : circles)
            {
                if(circle != this)
                {
                    int dx = this.getX()-circle.getX();
                    int dy = this.getY()-circle.getY();
                    int angle = Math.abs((int) Math.toDegrees(Math.atan((double)dx/(double)dy)));
                    int distance = (int)Math.sqrt((double)(dx*dx)+(double)(dy*dy));
                    if(!this.getObjectsInRange(this.getImage().getWidth(), circle.getClass()).isEmpty())
                    {
                        if(this.getY()>circle.getY() && this.getX()<circle.getX()) //Sud-West
                        {
                            this.setRotation(360-this.getRotation()+2*angle);
                            adjust();
                            move(speed);
                        }
                        else if(this.getY()<circle.getY() && this.getX()<circle.getX()) //Nord-West
                        {
                            this.setRotation(360-this.getRotation()-2*angle);
                            adjust();
                            move(speed);
                        }
                        else if(this.getY()>circle.getY() && this.getX()>circle.getX()) //Sud-Est
                        {
                            this.setRotation(180-this.getRotation()-2*angle);
                            adjust();
                            move(speed);
                        }
                        else if(this.getY()<circle.getY() && this.getX()>circle.getX()) //Nord-Est
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
     * Si le cercle est touché par un tir, un éclair infligeant des dégâts est créé sur la scène comme punition
     */
    public void detectWeapons()
    {
        if(getWorld()!= null)
        {
            if(!this.getTouchedObjects(Weapon2.class).isEmpty())
            {
                List<MegaLightning> lightning = getWorld().getObjects(MegaLightning.class);
                int length = lightning.size();
                if(length<1)
                {
                    getWorld().addObject(new MegaLightning(), getWorld().getWidth()/2, getWorld().getHeight()/2);
                }
            }
        }
    }

}
