import greenfoot.*;

/**
 * Tourelle qui cible le personnage dans MediumNightmare
 */
public class Turret extends Obstacle
{
    private int angle;
    private int counter;

    public Turret(int angle)
    {
        this.angle = angle;
    }

    public void act() 
    {
        counter++;
        rotate();
        transparent();
        shoot();
    }

    /**
     * Se tourne vers le vaisseau
     */
    public void rotate() //se tourne vers le vaisseau
    {
        Roamer roamer = (Roamer) getWorld().getObjects(Roamer.class).get(0);
        int xRoamer = roamer.getX();
        int yRoamer = roamer.getY();
        int xGear = this.getX();
        int yGear = this.getY();
        double dx = xRoamer-xGear;
        double dy = yRoamer-yGear;
        int rotation = (int) Math.toDegrees(Math.atan(dy/dx));
        if(roamer.getX()<this.getX())
        {
            this.setRotation(rotation+180);
        }
        if(roamer.getX()>= this.getX())
        {
            this.setRotation(rotation);
        }
    }
    
    /**
     * Disparaît si la rotation sort du domaine de définition
     */
    public void transparent()
    {
        if(angle == 0)
        {
            if(getRotation()>180)
            {
                getImage().setTransparency(0);
            }
            else if(getRotation()<=180)
            {
                getImage().setTransparency(255);
            }
        }
        else if(angle == 90)
        {
            if(getRotation()>90 && getRotation()<270)
            {
                getImage().setTransparency(0);
            }
            else if(getRotation()>=270 || getRotation()<=90)
            {
                getImage().setTransparency(255);
            }
        }
    }
    
    /**
     * Tire sur le personnage
     */
    public void shoot()
    {
        if(getImage().getTransparency() == 255)
        {
            if(counter%240 == 0)
            {
                getWorld().addObject(new Bullet2(this.getRotation()), this.getX(), this.getY());
            }
        }
    }
    
}
