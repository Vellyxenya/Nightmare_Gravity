import greenfoot.*;
import java.util.List;

/**
 * Accessoire défensif du 8e Boss
 */
public class Boss8Stuff extends SmoothMover
{
    public static boolean attack = false;
    public int frequency = 20;
    public int speed = 10;

    /**
     * Génère un éclair infligeant des dégâts si les parties protectrice du Boss8 sont touchées.
     */
    public void protect()
    {
        if(!this.getTouchedObjects(Weapons.class).isEmpty())
        {
            if(!getWorld().getObjects(Boss8.class).isEmpty())
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
