import greenfoot.*;

public class RoamerHalo extends SmoothMover
{
    
    public void act() 
    {
        Roamer r = (Roamer) getWorld().getObjects(Roamer.class).get(0);
        setLocation(r.getX(), r.getY());
    }
    
}
