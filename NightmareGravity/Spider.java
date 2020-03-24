import greenfoot.*;
import java.awt.Color;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

/**
 * Créature qui traque sans relâche le personnage dans MediumNightmare.
 */
public class Spider extends Creatures
{
    private int counter;

    public Spider()
    {
        getImage().scale(25, 25);
        counter = 0;
    }

    public void act() 
    {
        if(getWorld().getObjects(Informations2.class).isEmpty())
        {
            counter++;
            headTo();
            unlock();
            sonar();
        }
    }

    /**
     * Traque le personnage.
     * Les portes activées sont indiquées par la présence d'un Objet Ariane aux coordonnées de la porte.
     */
    public void headTo()
    {
        try
        {
            List<Ariane> doors = getWorld().getObjects(Ariane.class);
            ArrayList<Integer> arrayList = new ArrayList<Integer>();
            if(getWorld().getObjects(Ariane.class).isEmpty()) return;
            else
            {
                for(Ariane door : doors)
                {
                    int distance = (int)Math.hypot(this.getX()-door.getX(), this.getY()-door.getY());
                    arrayList.add(distance); //Calcule la distance avec toutes les portes.
                }
                Integer i = Collections.min(arrayList);
                int index = arrayList.indexOf(i); //prend la plus courte distance.
                Ariane opened = (Ariane) getWorld().getObjects(Ariane.class).get(index);
                int dx = Math.abs(this.getX()-opened.getX());
                int dy = Math.abs(this.getY()-opened.getY());
                if(this.getX()-opened.getX()>0) //Se dirige vers la porte choisie.
                {
                    if(getWorld().getColorAt(this.getX()-this.getImage().getWidth()/2, getY()).equals(Color.WHITE))
                    {
                        setLocation(getX()-1, getY());
                    }
                }
                else if(this.getX()-opened.getX()<0)
                {
                    if(getWorld().getColorAt(this.getX()+this.getImage().getWidth()/2, getY()).equals(Color.WHITE))
                    {
                        setLocation(getX()+1, getY());
                    }
                }
                if(this.getY()-opened.getY()>0)
                {
                    if(getWorld().getColorAt(this.getX(), this.getY()-getImage().getHeight()/2).equals(Color.WHITE))
                    {
                        setLocation(getX(), getY()-1);
                    }
                }
                else if(this.getY()-opened.getY()<0)
                {
                    if(getWorld().getColorAt(this.getX(), this.getY()+getImage().getHeight()/2).equals(Color.WHITE))
                    {
                        setLocation(getX(), getY()+1);
                    }
                }
            }
        }catch(IndexOutOfBoundsException e){}
    }

    /**
     * Une fois la porte passée, elle est à nouveau désactivée
     */
    public void unlock()
    {
        if(!getWorld().getObjectsAt(this.getX(), this.getY(), Opened.class).isEmpty())
        {
            Opened opened = (Opened) getWorld().getObjectsAt(getX(), getY(), Opened.class).get(0);
            if(opened.locked == true)
            {
                opened.locked = false;
            }
        }
    }

    /**
     * Une fois à portée, l'araignée n'a plus besoin de portes, elle se dirige en ligne droite vers le personnage
     */
    public void hunt()
    {
        Roamer roamer = (Roamer) getWorld().getObjects(Roamer.class).get(0);
        turnTowards(roamer.getX(), roamer.getY());
        move(2);
    }

    /**
     * Sonar de l'araignée lui permettant de détecter le personnage
     */
    public void sonar()
    {
        Roamer roamer = (Roamer) getWorld().getObjects(Roamer.class).get(0);
        if(Math.hypot(this.getX()-roamer.getX(), this.getY()-roamer.getY())<200)
        {
            if(counter%15 == 0)
            {
                for(int i = 0; i<120; i++)
                {
                    getWorld().addObject(new Beam(3*i), this.getX(), this.getY());
                }
            }
        }
    }

}
