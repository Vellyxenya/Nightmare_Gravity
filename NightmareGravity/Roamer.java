import greenfoot.*;
import java.awt.Color;
import java.util.List;

/**
 * Personnage acteur de MediumNightmare.
 */
public class Roamer extends Gears
{
    /**
     * Vitesse du personnage
     */
    int speed = 2;
    /**
     * Rotation du personnage
     */
    int rotation = 180;
    /**
     * Renvoei true si le joueur a collecté toutes les pièces du labyrinthe
     */
    public static boolean powered;
    /**
     * Nombre de pièces collectées
     */
    public static int collect;
    /**
     * Compteur pour l'animation de déplacement du personnage
     */
    private int counter;
    /**
     * Image contenant toutes les icônes d'animation pour le personnage
     */
    GreenfootImage image = new GreenfootImage("Animations.png");
    /**
     * Image de l'acteur
     */
    GreenfootImage base = new GreenfootImage(24, 36);
    /**
     * Indice de l'image utilisée
     */
    int number = 0;

    public Roamer()
    {
        base.drawImage(image, -number*24, -36*1);
        setImage(base);
        collect = 0;
    }

    public void act() 
    {
        if(getWorld().getObjects(Informations2.class).isEmpty())
        {
            counter++;
            movements();
            trackMovements();
            hunted();
            shoot();
            getPowered();
            collect();
            carryOn();
            die();
        }
    }

    /**
     * Meurt si le nombre de vies tombe à zéro
     */
    public void die()
    {
        if(isTouching(Creatures.class))
        {
            removeTouching(Creatures.class);
            Game.lives--;
            if(Game.lives==0)
            {
                MediumNightmare.sound.stop();
                Greenfoot.setWorld(new GameOver());
            }
        }
    }

    /**
     * Continue vers la cinématique
     */
    public void carryOn()
    {
        if(powered == true && isTouching(Neutrality.class))
        {
            if(Selection.gear2 == false)
            {
                Selection.gear2 = true;
                DATA.storeInfos(9);
            }
            else if(Selection.gear3 == false)
            {
                Selection.gear3 = true;
                DATA.storeInfos(10);
            }
            else if(Selection.gear4 == false)
            {
                Selection.gear4 = false;
                DATA.storeInfos(11);
            }
            Greenfoot.setWorld(new TheEnd(1));
            MediumNightmare.sound.stop();
        }
    }

    /**
     * Une fois toutes les pièces collectées, le personnage peut sortir du labyrinthe
     */
    public void getPowered()
    {
        if(collect >= 6 && powered == false)
        {
            powered = true;
            getWorld().addObject(new RoamerHalo(), getX(), getY());
        }
    }

    /**
     * Commandes de mouvement en intime relation avec la méthode moveMap de MediumNightmare, prenant en compte
     * la position du personnage, les murs, les bords etc...
     */
    public void movements()
    {
        try
        {
            if((Greenfoot.isKeyDown("Up")||Greenfoot.isKeyDown("W")) && (-MediumNightmare.yPosition<=0 || this.getY()>getWorld().getHeight()/2) && !getWorld().getColorAt(this.getX(), getY()-this.getImage().getHeight()/2).equals(Color.BLACK) && !getWorld().getColorAt(this.getX()+getImage().getHeight()/4, getY()-this.getImage().getHeight()/2).equals(Color.BLACK) && !getWorld().getColorAt(this.getX()-getImage().getHeight()/4, getY()-this.getImage().getHeight()/2).equals(Color.BLACK))
            {
                setLocation(this.getX(),this.getY()-speed);
            }
            if((Greenfoot.isKeyDown("Down")||Greenfoot.isKeyDown("S")) && (-MediumNightmare.yPosition>=900-563 || this.getY()<=getWorld().getHeight()/2) && !getWorld().getColorAt(this.getX(), getY()+this.getImage().getHeight()/2).equals(Color.BLACK) && !getWorld().getColorAt(this.getX()+getImage().getHeight()/4, getY()+this.getImage().getHeight()/2).equals(Color.BLACK) && !getWorld().getColorAt(this.getX()-getImage().getHeight()/4, getY()+this.getImage().getHeight()/2).equals(Color.BLACK))
            {
                setLocation(this.getX(),this.getY()+speed);
            }
            if((Greenfoot.isKeyDown("Right")||Greenfoot.isKeyDown("D")) && (-MediumNightmare.xPosition>= 1400-750 ||  this.getX()<=getWorld().getWidth()/2) && !getWorld().getColorAt(this.getX()+this.getImage().getWidth()/2, getY()).equals(Color.BLACK) && !getWorld().getColorAt(this.getX()+this.getImage().getWidth()/2, getY()+getImage().getWidth()/4).equals(Color.BLACK) && !getWorld().getColorAt(this.getX()+this.getImage().getWidth()/2, getY()-getImage().getWidth()/4).equals(Color.BLACK))
            {
                setLocation(this.getX()+speed ,this.getY());
            }
            if((Greenfoot.isKeyDown("Left") || Greenfoot.isKeyDown("A")) && (-MediumNightmare.xPosition<=0 ||  this.getX()>getWorld().getWidth()/2) && !getWorld().getColorAt(this.getX()-this.getImage().getWidth()/2, getY()).equals(Color.BLACK) && !getWorld().getColorAt(this.getX()-this.getImage().getWidth()/2, getY()+getImage().getWidth()/4).equals(Color.BLACK) && !getWorld().getColorAt(this.getX()-this.getImage().getWidth()/2, getY()-getImage().getWidth()/4).equals(Color.BLACK))
            {
                setLocation(this.getX()-speed ,this.getY());
            }
        }
        catch(IndexOutOfBoundsException e){}
    }

    public void trackMovements()
    {
        if(counter%8 == 0)
        {
            number = (number+1)%4;
        }
        if(Greenfoot.isKeyDown("Up")||Greenfoot.isKeyDown("W"))
        {
            base.clear();
            base.drawImage(image, -number*24, -36*3);
            setImage(base);
        }
        if(Greenfoot.isKeyDown("Down")||Greenfoot.isKeyDown("S"))
        {
            base.clear();
            base.drawImage(image, -number*24, 0);
            setImage(base);
        }
        if(Greenfoot.isKeyDown("Right")||Greenfoot.isKeyDown("D"))
        {
            base.clear();
            base.drawImage(image, -number*24, -36*2);
            setImage(base);
        }
        if(Greenfoot.isKeyDown("Left") ||Greenfoot.isKeyDown("A"))
        {
            base.clear();
            base.drawImage(image, -number*24, -36*1);
            setImage(base);
        }
    }

    /**
     * Informe l'araignée si le personnage est à sa portée
     */
    public void hunted()
    {
        if(getWorld()!= null)
        {
            if(isTouching(Beam.class))
            {
                try
                {
                    Spider spider = (Spider) getWorld().getObjects(Spider.class).get(0);
                    spider.hunt();
                    List<Opened> opens = getWorld().getObjects(Opened.class);
                    for(Opened o : opens)
                    {
                        o.hurt = false;
                        o.locked = false;
                    }
                }catch(IndexOutOfBoundsException e){}
            }
        }
    }

    /**
     * Tire sur les ennemis en cliquant dans la direction désirée
     */
    public void shoot()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse != null)
        {
            if(Greenfoot.mouseClicked(null))
            {
                int xMouse = mouse.getX();
                int yMouse = mouse.getY();
                int xGear = this.getX();
                int yGear = this.getY();
                double dx = xMouse-xGear;
                double dy = yMouse-yGear;
                int rotation = (int) Math.toDegrees(Math.atan(dy/dx));
                if(mouse.getX()<this.getX())
                {
                    Bullet w = new Bullet(rotation+180, 5);
                    getWorld().addObject(w, this.getX(), this.getY());
                }
                else if(mouse.getX()>= this.getX())
                {
                    Bullet w = new Bullet(rotation, 5);
                    getWorld().addObject(w, this.getX(), this.getY());
                }
            }
        }
    }

    /**
     * Permet de collecter les pièces trouvées dans le labyrinthe
     */
    public void collect()
    {
        try
        {
            Pieces p = (Pieces) getOneIntersectingObject(Pieces.class);
            if(p.generous == true)
            {
                p.generous = false;
                collect++;
                p.getImage().setTransparency(0);
            }
        }catch(NullPointerException e){}
    }

}
