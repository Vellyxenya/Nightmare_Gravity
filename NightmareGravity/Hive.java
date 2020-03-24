import greenfoot.*;

/**
 * Sorte de Ruche créant des petittes particules. Si le Boss est touché et qu'une ruche est présente dans le monde, le joueur est attaqué.
 */
public class Hive extends Rift
{
    private int counter;
    /**
     * Durée de vie
     */
    private int duration;
    /**
     * Transparence de l'iamge de cet objet
     */
    private int transparency;
    /**
     * Fait que la transparence augmente et diminue
     */
    private int again;
    GreenfootImage image;
    private int maxHive;
    /**
     * Points de vie
     */
    private int Life = 2000;

    public Hive()
    {
        image = new GreenfootImage("Hive.png");
        int size = Greenfoot.getRandomNumber(40);
        image.scale(40+size, 40+size);
        counter = 0;
        duration = 480;
        transparency = 100;
        again = 1;
        image.setTransparency(transparency);
        setImage(image);
        maxHive = 300;
    }

    /**
     * Gère la création des petites particules et l'enchaînement de la transparence
     */
    public void act() 
    {
        if(isPaused() == false)
        {
            counter++;
            duration--;
            maxHive--;
            if(counter%10 == 0 && maxHive>0)
            {
                getWorld().addObject(new LittleHive(), this.getX(), this.getY());
            }
            turn(5);
            if(duration<=0)
            {
                getWorld().removeObject(this);
            }
            transparency = transparency + (5*again);
            if(transparency>=240 || transparency <10)
            {
                again = -again;
            }
            image.setTransparency(transparency);
            setImage(image);
            vanish();
            
            takeDamageNew(Weapon1.littleDamage, Weapon1.class);
            takeDamageNew(Weapon2.weapon2Damage, Weapon2.class);
            takeDamageNew(Lightning.damage, Lightning.class);
        }
    }
    
    /**
     * Subit des dégâts. Si ses points de vie tombent à zéro, elle disparaît
     */
    public void takeDamageNew(int hurt, Class classe)
    {
        if(getWorld() !=null)
        {
            if(this.isTouching(classe))
            {
                Life = Life - hurt;
                if(Life <=0)
                {
                    getWorld().removeObject(this);
                }
            }
        }
    }
    
}
