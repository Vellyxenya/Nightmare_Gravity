import greenfoot.*;

/**
 * Eclair généré par VellocatusFront et infligeant des dégâts au joueur.
 */
public class Lightning2 extends Boss8Stuff
{
    private GreenfootImage baseImage;
    private int speed = 8;
    private final GreenfootImage image = new GreenfootImage("Lightning2.png");
    private int duration = (int)((double)image.getWidth()/(double)speed);
    public static int damage = 10;
    
    public Lightning2()
    {
        baseImage = getImage();
        setImage(speed-baseImage.getWidth()%speed, baseImage);
    }

    public void act() 
    {
        if(isPaused() == false)
        {
            duration--;
            if(duration<= 0)
            {
                getWorld().removeObject(this);
            }
            move((int)speed/2+VellocatusFront.speed);
            vanish();
            // Fonction pour faire apparaître l'éclair en dégradé
            int imageWidth = getImage().getWidth();
            if (imageWidth < baseImage.getWidth())
            {
                setImage(imageWidth+speed, baseImage);
            }
        }
    }
    
    public void setImage(int width, GreenfootImage image) //Permet de faire apparaître l'éclair pixel par pixel
    {
        GreenfootImage setter = new GreenfootImage(width, image.getHeight());
        setter.drawImage(image, 0, 0);
        setImage(setter);
    }
    
}
