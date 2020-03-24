import greenfoot.*;
import java.util.List;
import java.awt.Color;

/**
 * Labyrinthe dans lequel le joueur doit collecter certaines pièces et se diriger vers la sortie pour enfin finir la partie
 */
public class MediumNightmare extends TheEdge
{
    /**
     * Image entière du labyrinthe
     */
    GreenfootImage image = new GreenfootImage("Laby.png");
    /**
     * Image du labyrinthe affichée à l'écran
     */
    GreenfootImage visible = new GreenfootImage(750, 563);
    /**
     * Variable stockant le nombre de déplacements effectués sur l'axe des X
     */
    static int xPosition;
    /**
     * Variable stockant le nombre de déplacements effectués sur l'axe des Y
     */
    static int yPosition;
    /**
     * Variable stockant la vitesse de défilement de la carte
     */
    int speed = 2;
    /**
     * Le personnage
     */
    Roamer roamer = new Roamer();
    /**
     * Variable stockant la rotation du champ de vision
     */
    int rotation;
    /**
     * Variable stockant le rayon de vision du joueur
     */
    final int large = 20;
    /**
     * Tableau contenant l'orientation des portes
     */
    int param1[] = {90, 0, 90, 0, 90, 0, 0, 0, 0, 0, 90, 90, 90, 90, 90, 0, 90,
            0, 0, 0, 0, 90, 0, 0, 90, 0, 0, 90, 90, 90,
            90, 90, 0, 90, 90, 0, 0, 0, 90, 90, 0, 0, 90, 90, 90, 0, 0, 0,
            90, 90, 90, 90, 90, 0, 0};
    /**
     * Tableau contenant la largeur des portes
     */
    int param2[] = {35, 35, 35, 35, 35, 75, 35, 35, 35, 35, 35, 35, 35, 50, 35, 40, 40,
            35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35,
            40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40,
            40, 40, 40, 40, 40, 40, 40};
    /**
     * Tableau contenant les coordonnées X des portes
     */
    int xParam[] = {489, 387, 290, 386, 156, 115, 592, 592, 592, 206, 227, 339, 494, 513, 741, 720, 397,
            204, 204, 204, 204, 309, 363, 591, 659, 680, 680, 614, 703, 703,
            762, 817, 839, 895, 938, 917, 917, 1148, 941, 1294, 1318, 1318, 1294, 1124, 1173, 917, 917, 917,
            798, 851, 894, 941, 1027, 918, 1052};
    /**
     * Tableau contenant les coordonnées Y des portes
     */
    int yParam[] = {340, 313, 340, 157, 340, 180, 156, 200, 495, 494, 467, 467, 467, 126, 41, 62, 468,
            583, 583, 630, 725, 804, 781, 574, 598, 624, 670, 596, 649, 721,
            262, 262, 239, 40, 40, 62, 113, 155, 134, 43, 62, 114, 136, 136, 136, 154, 333, 380,
            720, 720, 720, 720, 720, 698, 740};
    /**
     * Initialise toutes les portes de la scène
     */
    Opened doors[]= new Opened[55];
    /**
     * Détecte si le personnage a été bougé depuis le début de la partie ou pas
     */
    static boolean moved;

    int xSpider = 0;
    int ySpider = 0;
    GG g1 = new GG();
    GG g2 = new GG();
    GG g3 = new GG();
    GG g4 = new GG();
    GG g5 = new GG();
    Turret t1 = new Turret(0);
    Turret t2 = new Turret(90);
    Pieces pieces[] = new Pieces[6];
    Neutrality neutrality = new Neutrality();
    Blind blind = new Blind();
    Spider spider = new Spider();
    public static GreenfootSound sound = new GreenfootSound("Night.wav");

    /**
     * Initialise la partie Nord-Ouest du labyrinthe
     */
    public MediumNightmare()
    {
        if(Volume.volumeOn == true)
        {
            sound.play();
        }
        Game.gameMusic.stop();
        moved = false;
        for(int i = 0; i<55; i++)
        {
            doors[i] = new Opened(param1[i], param2[i]);
        }
        for(int i = 0; i<6; i++)
        {
            pieces[i] = new Pieces();
        }
        setPaintOrder(Informations2.class, PressEnter.class, Life.class, Pieces.class, LabyBoard.class, Blind.class, Roamer.class);
        addObject(blind, 550, 300);
        setBackground(image);
        addObject(g1, 253, 275);
        addObject(g5, 700, 450);
        addObject(roamer, 725, 320);
        addObject(new LabyBoard(), 130, 48);
        addObject(new Life(), 260, 25);
        addObject(new Pieces(false), 260, 60);
        xPosition = 0;
        yPosition = 0;
        addObject(pieces[0], 180, 100);
        addObject(pieces[1], 400, 90);
        for(int i = 0; i<17; i++)
        {
            addObject(doors[i], xParam[i], yParam[i]);
        }
        setBackground(image);
        addObject(new PressEnter(), getWidth()/2, 500);
        addObject(new Informations2("Brace yourself, it's the final straight. You'll have to find and collect 6 pieces that you'll need to craft the next ship, before reaching the exit. Be careful to not be killed! Oh and you have a loaded weapon, shoot everything that moves! "), getWidth()/2, getHeight()/2);
        Greenfoot.stop();
        Greenfoot.start();
    }

    public void act()
    {
        if(getObjects(Informations2.class).isEmpty())
        {
            for(int i = 0; i<17; i++)
            {
                addAgain(doors[i], xParam[i], yParam[i]);
            }
            for(int i = 17; i<30; i++)
            {
                addExtra(doors[i], xParam[i], yParam[i]);
            }
            for(int i = 30; i<48; i++)
            {
                addOthers(doors[i], xParam[i], yParam[i]);
            }
            for(int i = 48; i<55; i++)
            {
                addPlus(doors[i], xParam[i], yParam[i]);
            }
            addAgain(pieces[0], 180, 100);
            addAgain(pieces[1], 400, 90);
            addOthers(pieces[2], 1365, 135);
            addPlus(pieces[3], 1050, 800);
            addExtra(pieces[4], 365, 650);
            addOthers(pieces[5], 1300, 500);
            addExtra(neutrality, 200, 860);
            ChangeMouseImage(img1, 1, 1);
            moveMap();
            deleteObjects();
            addOthers(t1, 1046, 401);
            addPlus(t2, 947, 620);
            addAgain(g1, 253, 275);
            addExtra(g2, 110, 610);
            addPlus(g3, 840, 610);
            addOthers(g4, 1045, 330);
            addAgain(g5, 700, 450);
            createSpiders();
            rotate();
        }
    }

    /**
     * Enlève les araignées si elles arrivent au bord de la scène
     */
    public void deleteSpider()
    {
        if(getObjects(Spider.class).isEmpty()) return;
        else
        {
            List<Spider> spiders = getObjects(Spider.class);
            for(Spider spider : spiders)
            {
                if(spider.isAtEdgePerso())
                {
                    xSpider = spider.getX();
                    ySpider = spider.getY();
                    removeObject(spider);
                }
            }
        }
    }

    /**
     * Recrée une araignée dans le monde s'il n'y en a plus
     */
    public void createSpiders()
    {
        if(getObjects(Spider.class).isEmpty())
        {
            try
            {
                GG g = (GG) getObjects(GG.class).get(Greenfoot.getRandomNumber(1+getObjects(GG.class).size())-1);
                addObject(spider, g.getX(), g.getY());
            }catch(ArrayIndexOutOfBoundsException e){}
        }
    }

    /**
     * Méthode permettant de faire défiler la carte.
     */
    public void moveMap()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        Roamer roamer = (Roamer) getObjects(Roamer.class).get(0);
        if(mouse!= null)
        {
            if(directions()==1)
            {
                if(xPosition>= -(image.getWidth()-750) && roamer.getX()>=getWidth()/2)
                {
                    visible.drawImage(image, xPosition-speed, yPosition);
                    xPosition -= speed;
                    if(getObjects(Obstacle.class).isEmpty()) return;
                    else
                    {
                        List<Obstacle> obstacles = getObjects(Obstacle.class);
                        for(Obstacle obstacle : obstacles)
                        {
                            obstacle.setLocation(obstacle.getX()-speed, obstacle.getY());
                        }
                    }
                    moved = true;
                }
            }
            if(directions()==0)
            {
                if(xPosition <=0 && roamer.getX()<= getWidth()/2)
                {
                    visible.drawImage(image, xPosition+speed, yPosition);
                    xPosition += speed;
                    if(getObjects(Obstacle.class).isEmpty()) return;
                    else
                    {
                        List<Obstacle> obstacles = getObjects(Obstacle.class);
                        for(Obstacle obstacle : obstacles)
                        {
                            obstacle.setLocation(obstacle.getX()+speed, obstacle.getY());
                        }
                    }
                    moved = true;
                }
            }
            if(directions()==3)
            {
                if(-yPosition <= image.getHeight()-563 && roamer.getY()>=getHeight()/2)
                {
                    visible.drawImage(image, xPosition, yPosition-speed);
                    yPosition -= speed;
                    if(getObjects(Obstacle.class).isEmpty()) return;
                    else
                    {
                        List<Obstacle> obstacles = getObjects(Obstacle.class);
                        for(Obstacle obstacle : obstacles)
                        {
                            obstacle.setLocation(obstacle.getX(), obstacle.getY()-speed);
                        }
                    }
                    moved = true;
                }
            }
            if(directions()==2)
            {
                if(yPosition<=0 && roamer.getY()<=getHeight()/2)
                {
                    visible.drawImage(image, xPosition, yPosition+speed);
                    yPosition += speed;
                    if(getObjects(Obstacle.class).isEmpty()) return;
                    else
                    {
                        List<Obstacle> obstacles = getObjects(Obstacle.class);
                        for(Obstacle obstacle : obstacles)
                        {
                            obstacle.setLocation(obstacle.getX(), obstacle.getY()+speed);
                        }
                    }
                    moved = true;
                }
            }
            if(moved == true)
            {
                setBackground(visible);
            }
        }
    }

    /**
     * Supprime les objets qui sont au bord de la scène
     */
    public void deleteObjects()
    {
        if(getObjects(Obstacle.class).isEmpty()) return;
        else
        {
            List<Obstacle> obstacles = getObjects(Obstacle.class);
            for(Obstacle obstacle : obstacles)
            {
                if(obstacle.isAtEdgePerso())
                {
                    removeObject(obstacle);
                }
            }
        }
    }

    /**
     * Remet sur scène les objets se trouvant au Nord-Ouest du labyrinthe
     */
    public void addAgain(Actor actor, int x, int y)
    {
        if(directions() == 0)
        {
            if(x+xPosition>0 && -yPosition<y)
            {
                addObject(actor, 1, yPosition+y);
            }
        }
        else if(directions() == 2)
        {
            if(y+yPosition>0 && -xPosition<x)
            {
                addObject(actor, xPosition+x, 1);
            }
        }
    }

    /**
     * Remet sur scène les objets se trouvant au Nord-Est du labyrinthe
     */
    public void addOthers(Actor neutral, int X, int Y)
    {
        if(directions() == 1)
        {
            if(xPosition<-(image.getWidth()-this.getWidth()-(image.getWidth()-X)) && -yPosition<Y)
            {
                addObject(neutral, getWidth()-2, Y+yPosition);
            }
        }
        else if(directions() == 2)
        {
            if(yPosition>-Y && -xPosition+getWidth()>X)
            {
                addObject(neutral, getWidth()-(image.getWidth()-X)+(image.getWidth()-getWidth()+xPosition), 1);
            }
        }
    }

    /**
     * Remet sur scène les objets se trouvant au Sud-Est du labyrinthe
     */
    public void addPlus(Actor neutrum, int x, int y)
    {
        if(directions() == 1)
        {
            if(xPosition<-(image.getWidth()-this.getWidth()-(image.getWidth()-x)) && this.getHeight()-yPosition>y)
            {
                addObject(neutrum, getWidth()-2, y+yPosition);
            }
        }
        else if(directions() == 3)
        {
            if(this.getHeight()-yPosition>y && -xPosition+getWidth()>x)
            {
                addObject(neutrum, getWidth()-(image.getWidth()-x)+
                    (image.getWidth()-getWidth()+xPosition), getHeight()-1);
            }
        }
    }

    /**
     * Remet sur scène les objets se trouvant au Sud-Ouest du labyrinthe
     */
    public void addExtra(Actor actor, int x, int y)
    {
        if(directions() == 0)
        {
            if(x+xPosition>0 && getHeight()-yPosition>y)
            {
                addObject(actor, 1, y+yPosition);
            }
        }
        else if(directions() == 3)
        {
            if(this.getHeight()-yPosition>y && -xPosition<x)
            {
                addObject(actor, getWidth()-(image.getWidth()-x)+(image.getWidth()
                        -getWidth()+xPosition), getHeight()-1);
            }
        }
    }

    /**
     * Méthode retournant la direction dans laquelle se dirige le personnage
     */
    public int directions()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        Roamer roamer = (Roamer) getObjects(Roamer.class).get(0);
        if(mouse!= null)
        {
            try
            {
                if((Greenfoot.isKeyDown("A")||Greenfoot.isKeyDown("Left"))
                && !getColorAt(roamer.getX()-roamer.getImage().getWidth()/2,
                    roamer.getY()).equals(Color.BLACK)
                && !getColorAt(roamer.getX()-roamer.getImage().getWidth()/2,
                    roamer.getY()+roamer.getImage().getHeight()/4).equals(Color.BLACK)
                && !getColorAt(roamer.getX()-roamer.getImage().getWidth()/2,
                    roamer.getY()-roamer.getImage().getHeight()/4).equals(Color.BLACK)
                && -xPosition>=0)
                {
                    return 0;
                }
                if((Greenfoot.isKeyDown("D")||Greenfoot.isKeyDown("Right"))
                && !getColorAt(roamer.getX()+roamer.getImage().getWidth()/2,
                    roamer.getY()).equals(Color.BLACK)
                && !getColorAt(roamer.getX()+roamer.getImage().getWidth()/2,
                    roamer.getY()+roamer.getImage().getHeight()/4).equals(Color.BLACK)
                && !getColorAt(roamer.getX()+roamer.getImage().getWidth()/2,
                    roamer.getY()-roamer.getImage().getHeight()/4).equals(Color.BLACK)
                && -xPosition<= 1400-750)
                {
                    return 1;
                }
                if((Greenfoot.isKeyDown("W")||Greenfoot.isKeyDown("Up"))
                && !getColorAt(roamer.getX(), roamer.getY()-roamer.getImage()
                    .getHeight()/2).equals(Color.BLACK)
                && !getColorAt(roamer.getX()+roamer.getImage().getWidth()/4,
                    roamer.getY()-roamer.getImage().getHeight()/2).equals(Color.BLACK)
                && !getColorAt(roamer.getX()-roamer.getImage().getWidth()/4,
                    roamer.getY()-roamer.getImage().getHeight()/2).equals(Color.BLACK)
                && -yPosition>=0)
                {
                    return 2;
                }
                if((Greenfoot.isKeyDown("S")||Greenfoot.isKeyDown("Down"))
                && !getColorAt(roamer.getX(), roamer.getY()+roamer.getImage()
                    .getHeight()/2).equals(Color.BLACK)
                && !getColorAt(roamer.getX()+roamer.getImage().getWidth()/4,
                    roamer.getY()+roamer.getImage().getHeight()/2).equals(Color.BLACK)
                && !getColorAt(roamer.getX()-roamer.getImage().getWidth()/4,
                    roamer.getY()+roamer.getImage().getHeight()/2).equals(Color.BLACK)
                && -yPosition<=900-563)
                {
                    return 3;
                }
            }catch(IndexOutOfBoundsException e){}
        }
        return 10001;
    }

    /**
     * Méthode faisant tourner le champ de vision du joueur selon la position de la souris.
     */
    public void rotate()
    {
        Roamer roamer = (Roamer) getObjects(Roamer.class).get(0);
        blind.setLocation(roamer.getX(), roamer.getY());
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse!= null)
        {
            int xMouse = mouse.getX();
            int yMouse = mouse.getY();
            double xThis = roamer.getExactX();
            double yThis = roamer.getExactY();
            double dx = Math.abs(xMouse-xThis);
            double dy = Math.abs(yMouse-yThis);
            double length = Math.sqrt(dx*dx+dy*dy);
            double xLength = dx/length;
            double yLength = dy/length;
            rotation = (int) Math.toDegrees(Math.atan(dy/dx));
            if(mouse.getX() == roamer.getX() && mouse.getY()<roamer.getY()) //Anti-bug
            {
                rotation = 270;
            }
            else if(mouse.getY() == roamer.getY() && mouse.getX()<roamer.getX()) //Anti-bug
            {
                rotation = 180;
            }
            else
            {
                if(mouse.getX()>roamer.getX() && mouse.getY()>roamer.getY())
                {
                    rotation = (int) Math.toDegrees(Math.atan(dy/dx));
                }
                else if(mouse.getX()<roamer.getX() && mouse.getY()>roamer.getY())
                {
                    rotation = 180-(int) Math.toDegrees(Math.atan(dy/dx));
                }
                else if(mouse.getX()<roamer.getX() && mouse.getY()<roamer.getY())
                {
                    rotation = 180+(int) Math.toDegrees(Math.atan(dy/dx));
                }
                else if(mouse.getX()>roamer.getX() && mouse.getY()<roamer.getY())
                {
                    rotation = 360-(int) Math.toDegrees(Math.atan(dy/dx));
                }
            }
        }
    }

}
