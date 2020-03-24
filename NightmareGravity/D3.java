import greenfoot.*;

/**
 * Animation d'atterrissage du vaisseau après la destruction du dernier boss
 */
public class D3 extends SmoothMover
{
    private int counter;
    /**
     * Initialise l'image
     */
    GreenfootImage image = new GreenfootImage("3D.png");
    /**
     * Indique au joueur qu'il est arrivé à bon port
     */
    Informations2 info = new Informations2("Youpiii, finally!!! Thank you so much Captain! You're the best!!! To thank you..., I've prepared one more test... FAREWELL!!! ");
    PressEnter p = new PressEnter();

    /**
     * Mouvements de l'animation
     */
    public void act() 
    {
        counter++;
        if(counter%2 == 0 && counter<=90)
        {
            double newWidth = image.getWidth()+(counter/5);
            double newHeight = image.getHeight()+(counter/8);
            image.scale((int)(newWidth), (int)(newHeight));
            setImage(image);
        }
        else if(counter>90 && counter<150)
        {
            this.setLocation(getX(), getY()-1);
        }
        else if(counter == 150)
        {
            getWorld().addObject(info, getWorld().getWidth()/2, getWorld().getHeight()/2);
            getWorld().addObject(p, getWorld().getWidth()/2, 460);
        }
        if(Greenfoot.isKeyDown("enter") && counter >= 150)
        {
            getWorld().removeObject(info);
            getWorld().removeObject(p);
            Greenfoot.setWorld(new TheEnd(0));
        }
    }

}
