import greenfoot.*;
import java.util.List;

/**
 * Inflige des dégâts aux ennemis
 */
public class Animations extends SmoothMover
{

    /**
     * Elimine les ennemis touchés par la vague
     */
    public void damageEnemies()
    {
        if(getWorld() !=null)
        {
            GreenfootImage image = this.getImage();
            if(isTouching(Enemies.class))
            {
                List<Enemies> monsters = getObjectsInRange(image.getWidth()/2, Enemies.class);
                for(Enemies monster : monsters)
                {
                    getWorld().removeObject(monster);
                }
            }
        }
    }

}
