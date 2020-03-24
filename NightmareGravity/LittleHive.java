import greenfoot.*;

/**
 * Objet généré par Hive et infligeant des dégâts au joueur si le Boss est attaqué.
 */
public class LittleHive extends Rift
{
    /**
     * Durée de vie
     */
    private int duration;
    /**
     * Rotation aléatoire de cet objet
     */
    private int randomTurn;
    /**
     * Mouvement aléatoire de cet objet
     */
    private int randomMove;

    /**
     * Initialisation
     */
    public LittleHive()
    {
        GreenfootImage baseImage = getImage();
        int size = Greenfoot.getRandomNumber(6);
        baseImage.scale(4+size, 4+size);
        int random = Greenfoot.getRandomNumber(360);
        setRotation(random);
        duration = 360;
        randomTurn = 1+Greenfoot.getRandomNumber(5);
        randomMove = 1+Greenfoot.getRandomNumber(3);
    }

    /**
     * Suit un mouvement aléatoire et disparaît au bout d'un moment
     */
    public void act() 
    {
        if(isPaused() == false)
        {
            try
            {
                Gears gear = (Gears) getWorld().getObjects(Gears.class).get(0);
                if(Boss4.vengeance == false)
                {
                    move(randomMove);
                    turn(randomTurn);
                }
                else if(Boss4.vengeance == true)
                {
                    this.turnTowards(gear.getX(), gear.getY());
                    move(10);
                }
            }catch(IndexOutOfBoundsException e){}
            duration--;
            if(duration<=0)
            {
                getWorld().removeObject(this);
            }
            vanish();
        }
    } 

}
