import greenfoot.*;

/**
 * Regroupe les vaisseaux ennemis.
 */
public class Enemies extends Opponent
{
    /**
     * Dégâts infligés au vaisseau du joueur s'il touche des objets de cette classe
     */
    public static final int damage = 4;
    public int Life;
    private int counter;
    GreenfootSound dead = new GreenfootSound("EnemyDead.wav");

    public void act() 
    {
        if(isPaused() == false)
        {
            try
            {
                counter++;
                effect();
                takeDamageNew(Weapon1.littleDamage, Weapon1.class);
                takeDamageNew(Weapon2.weapon2Damage, Weapon2.class);
                takeDamageNew(Lightning.damage, Lightning.class);
            }catch(NullPointerException e){}
        }
    }

    /**
     * Effet visuel s'ils sont touchés par le laser du vaisseau 1
     */
    public void effect()
    {
        try
        {
            if(isTouching(Weapon1.class))
            {
                if(counter%3 == 0)
                {
                    MouseEffect effect = new MouseEffect(Greenfoot.getRandomNumber(360));
                    getWorld().addObject(effect, this.getX(), this.getY());
                }
            }
        }catch(IllegalStateException e){}
    }

    /**
     * Tous les vaisseaux ennemis ont une certaine chance de laisser des potions/crédits à leur disparition
     */
    public void drop(int x)
    {
        int random = Greenfoot.getRandomNumber(100);
        if(random>=20 && random < 70)
        {
            Credit credit = new Credit(x);
            getWorld().addObject(credit, this.getX() ,this.getY());
        }
        else if(random >= 70 && random<90)
        {
            Heal heal = new Heal();
            getWorld().addObject(heal, this.getX() ,this.getY());
        }
        else if(random >=97)
        {
            BonusLife life = new BonusLife();
            getWorld().addObject(life, this.getX(), this.getY());
        }
    }

    /**
     * Subit des dégâts des armes des vaisseaux
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
                    drop(50);
                    if(getWorld().getObjects(Revenger.class).isEmpty())
                    {
                        int random = Greenfoot.getRandomNumber(30);
                        int random2 = Greenfoot.getRandomNumber(100);
                        if(Greenfoot.getRandomNumber(10) == 0)
                        {
                            for(int i = 0; i<3; i++)
                            {
                                getWorld().addObject(new Web(random+120*i), this.getX(), this.getY());
                            }
                        }
                        for(int i = 0; i<3; i++)
                        {
                            getWorld().addObject(new Burst(random2+120*i), this.getX(), this.getY());
                        }
                    }
                    dead.play();
                    getWorld().removeObject(this);
                    Game.killedEnemies++;
                }
            }
        }
    }

}