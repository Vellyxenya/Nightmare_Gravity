import greenfoot.*;
import java.awt.Color;

/**
 * Ennemis qui se déplacent aléatoirement dans le cadre du labyrinthe.
 */
public class Ghost extends Creatures
{
    private int counter;
    private int turn = 0;
    private int i0 = -1;
    private int i90 = -1;
    private int i180 = -1;
    private int i270 = -1;

    public Ghost()
    {
        counter = 0;
    }

    public void act() 
    {
        if(getWorld().getObjects(Informations2.class).isEmpty())
        {
            movements();
            int r = Greenfoot.getRandomNumber(60);
            if(r == 0)
            {
                turn = 0;
                forTheFun();
            }
            counter++;
            i0--;
            i90--;
            i180--;
            i270--;
            finish();
            debug();
        }
    }

    public void finish() //counters pour qu'elles ne tournent pas chaque act.
    {
        if(i0 == 0)
        {
            setRotation(0);
        }
        if(i90 == 0)
        {
            setRotation(90);
        }
        if(i180 == 0)
        {
            setRotation(180);
        }
        if(i270 == 0)
        {
            setRotation(270);
        }
    }

    public void movements() //tourne à l'intersection d'un mur.
    {
        try{
            move(1);
            if(getRotation() == 0)
            {
                if(!getWorld().getColorAt(this.getX()+this.getImage().getWidth()/2, this.getY()).equals(Color.WHITE))
                {
                    directions();
                }
            }
            else if(getRotation() == 90)
            {
                if(!getWorld().getColorAt(this.getX(), this.getY()+this.getImage().getHeight()/2).equals(Color.WHITE))
                {
                    directions();
                }
            }
            else if(getRotation() == 180)
            {
                if(!getWorld().getColorAt(this.getX()-this.getImage().getWidth()/2, this.getY()).equals(Color.WHITE))
                {
                    directions();
                }
            }
            else if(getRotation() == 270)
            {
                if(!getWorld().getColorAt(this.getX(), this.getY()-this.getImage().getHeight()/2).equals(Color.WHITE))
                {
                    directions();
                }
            }
        }catch(IndexOutOfBoundsException e){}
    }

    public void directions() //les 3 directions possible après une collision.
    {
        int random = Greenfoot.getRandomNumber(3);
        switch(random)
        {
            case 0 :
            setRotation(getRotation()+90);
            break;
            case 1 :
            setRotation(getRotation()+180);
            break;
            case 2 :
            setRotation(getRotation()-90);
            break;
        }
    }

    public void forTheFun() //Des fois ces créatures peuvent tourner à droite/gauche s'il y a un long couloir.
    {
        if(getRotation() == 0 || getRotation() == 180)
        {
            int random = Greenfoot.getRandomNumber(2);
            if(random == 0)
            {
                for(int i = 10; i<100; i++)
                {
                    try
                    {
                        if(getWorld().getColorAt(this.getX(), this.getY()-i).equals(Color.WHITE))
                        {
                            turn++;
                        }
                    }catch(IndexOutOfBoundsException e){}
                }
                if(turn >= 90)
                {
                    i270 = 20; 
                }
            }
            else if(random == 1)
            {
                for(int i = 10; i<100; i++)
                {
                    try
                    {
                        if(getWorld().getColorAt(this.getX(), this.getY()+i).equals(Color.WHITE))
                        {
                            turn++;
                        }
                    }catch(IndexOutOfBoundsException e){}
                }
                if(turn >= 90)
                {
                    i90 = 20;
                }
            }
        }
        else if(getRotation() == 90 || getRotation() == 270)
        {
            int random = Greenfoot.getRandomNumber(2);
            if(random == 0)
            {
                for(int i = 10; i<100; i++)
                {
                    try
                    {
                        if(getWorld().getColorAt(this.getX()+i, this.getY()).equals(Color.WHITE))
                        {
                            turn++;
                        }
                    }catch(IndexOutOfBoundsException e){}
                }
                if(turn >= 90)
                {
                    i0 = 20;
                }
            }
            else if(random == 1)
            {
                for(int i = 10; i<100; i++)
                {
                    try
                    {
                        if(getWorld().getColorAt(this.getX()-i, this.getY()).equals(Color.WHITE))
                        {
                            turn++;
                        }
                    }catch(IndexOutOfBoundsException e){}
                }
                if(turn >= 90)
                {
                    i180 = 20;
                }
            }
        }
    }

}
