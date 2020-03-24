import greenfoot.*;
import java.awt.Color;

/**
 * Hexagone constituant les pièces du puzzle de EasyNightmare
 */
public class Hexa extends SmoothMover
{
    /**
     * Son joué quand ce cercle est tourné
     */
    GreenfootSound sound = new GreenfootSound("ButtonSound.wav");
    GreenfootImage image = new GreenfootImage(50, 50);
    /**
     * Tableau en 6 bits de la bonne rotation de l'hexagone
     * 1: il y aun branche
     * 0: il n'y a pas de branche
     */
    public int array[] = new int[6];
    /**
     * Tableau en 6 bits d'une mauvaise rotation de l'hexagone
     * 1: il y aun branche
     * 0: il n'y a pas de branche
     */
    public int array2[] = new int[6];
    /**
     * Indique si l'hexagone est dans le bon sens
     */
    public boolean solved;

    /**
     * Construit l'hexagone
     */
    public Hexa(int a, int b, int c, int d, int e, int f)
    {
        array[0] = a;
        array[1] = b;
        array[2] = c;
        array[3] = d;
        array[4] = e;
        array[5] = f;
        image.setColor(Color.WHITE);
        image.fillOval(0, 0, 49, 49);
        image.setColor(Color.BLACK);
        image.drawOval(0, 0, 49, 49);
        image.setColor(Color.RED);
        image.fillOval(20, 20, 10, 10);
        for (int i = 0; i<6; i++)
        {
            if(array[i] == 1)
            {
                image.drawLine(25, 25, (int)(25+Math.cos(Math.toRadians(60+(i-1)*60))*25),
                    (int)(25+Math.sin(Math.toRadians(60+(i-1)*60))*25));
            }
        }
        setImage(image);
    }

    /**
     * Prolonge les lignes des hexagone périphérique pour faciliter le puzzle
     */
    public void act() 
    {
        if(EasyNightmare.counter == 4)
        {
            for (int i = 0; i<6; i++)
            {
                if(array[i] == 1)
                {
                    getWorld().addObject(new Marker(60*i), getX(), getY());
                }
            }
        }
        if(EasyNightmare.counter == 5)
        {
            int random = Greenfoot.getRandomNumber(6);
            for(int i = 0; i<6; i++)
            {
                array2[i] = array[(i+random)%6];
            }
            update();
            solved = false;
        }
        rotate();
        if(EasyNightmare.counter>3)
        {
            verify();
        }
    }

    /**
     * Vérifie si l'hexagone est dans le bon sens
     */
    public void verify()
    {
        int verify = 0;
        for(int i = 0; i<6; i++)
        {
            if(array2[i] == array[i])
            {
                verify++;
            }
        }
        if(verify<6)
        {
            solved = false;
        }
        else
        {
            solved = true;
        }
    }

    /**
     * Change l'image de l'hexagone après sa rotation
     */
    public void update()
    {
        image.clear();
        image.setColor(Color.WHITE);
        image.fillOval(0, 0, 49, 49);
        image.setColor(Color.BLACK);
        image.drawOval(0, 0, 49, 49);
        image.setColor(Color.RED);
        image.fillOval(20, 20, 10, 10);
        for (int i = 0; i<6; i++)
        {
            if(array2[i] == 1)
            {
                image.drawLine(25, 25, (int)(25+Math.cos(Math.toRadians(60+(i-1)*60))*25),
                    (int)(25+Math.sin(Math.toRadians(60+(i-1)*60))*25));
            }
        }
        setImage(image);
    }

    /**
     * Fait tourner les hexagones à droite ou à gauche selon que l'utilisateur fait un clique droit ou gauche.
     */
    public void rotate()
    {
        MouseInfo info = Greenfoot.getMouseInfo();
        if(info != null)
        {
            if(Greenfoot.mouseClicked(this))
            {
                if(info.getButton() == 1)
                {
                    int[] pro = new int[6];
                    for(int i = 0; i<6; i++)
                    {
                        pro[i] = array2[(i+1)%6];
                    }
                    for(int i = 0; i<6; i++)
                    {
                        array2[i] = pro[i];
                    }
                }
                if(info.getButton() == 3)
                {
                    int[] pro = new int[6];
                    pro[0] = array2[5];
                    pro[1] = array2[0];
                    pro[2] = array2[1];
                    pro[3] = array2[2];
                    pro[4] = array2[3];
                    pro[5] = array2[4];
                    for(int i = 0; i<6; i++)
                    {
                        array2[i] = pro[i];
                    }
                }
                update();
                sound.play();
            }
        }
    }

}
