import greenfoot.*;
import java.util.List;
import java.awt.Color;

/**
 * Défi que le joueur doit affronter s'il entre en collision avec un trou noir et qu'il y survit. 
 */
public class EasyNightmare extends TheEdge
{
    /**
     * Durée maximale du défi
     */
    public static final int MAX_TIME = 240;
    /**
     * Variable stockant le temps écoulé
     */
    public static int time;
    public static int counter2;
    GreenfootImage image;
    public static int counter;
    /**
     * tableau contenant le bon arrangement donnant la bonne solution au puzzle
     */
    private int[][] general;
    /**
     * Tableau contenant un arrangement désordonné du puzzle
     */
    private int[][] mazed;
    /**
     * Variable contenant le nombre de la pièce en cours de traitement
     */
    private int fill;
    GreenfootSound sound = new GreenfootSound("Marble.wav");

    /**
     * Initialise les 7 premières pièces centrales du puzzle
     */
    public EasyNightmare()
    {
        Greenfoot.setSpeed(50);
        if(Volume.volumeOn == true)
        {
            sound.play();
        }
        Game.gameMusic.pause();
        counter2 = 0;
        time = 0;
        drawTime();
        image = getBackground();
        image.setColor(Color.RED);
        int[] x = {getWidth()/2-200, getWidth()/2-100, getWidth()/2+100, getWidth()/2+200, getWidth()/2+100, getWidth()/2-97};
        int[] y = {getHeight()/2, getHeight()/2-172, getHeight()/2-172, getHeight()/2, getHeight()/2+172, getHeight()/2+172};
        image.drawPolygon(x, y, 6);
        setPaintOrder(PressEnter.class, Informations2.class, Hexa.class, Marker.class);
        general = new int[39][6];
        mazed = new int[39][6];
        fill = 0;
        counter = 0;
        addObject(new Hexa(binary(), binary(), binary(), binary(), binary(), binary()), getWidth()/2, getHeight()/2);
        for(int i = 0; i<6; i++)
        {
            int datas[] = new int[6];
            for( int j = 0; j<6; j++)
            {
                try
                {
                    Hexa h = (Hexa) getObjectsAt(getWidth()/2+(int)(Math.cos(Math.toRadians(60+(i-1)*60))*50)+(int)(Math.cos(Math.toRadians(60+(j-1)*60))*50), getHeight()/2+(int)(Math.sin(Math.toRadians(60+(i-1)*60))*50)+(int)(Math.sin(Math.toRadians(60+(j-1)*60))*50), Hexa.class).get(0);
                    if(h.array[(j+3)%6] == 1)
                    {
                        datas[j] = 1;
                    }
                    else if(h.array[(j+3)%6] == 0)
                    {
                        datas[j] = 0;
                    }
                }
                catch(IndexOutOfBoundsException e)
                {
                    datas[j] = binary();
                }
            }
            addObject(new Hexa(datas[0], datas[1], datas[2], datas[3], datas[4], datas[5]),
                getWidth()/2+(int)(Math.cos(Math.toRadians(60+(i-1)*60))*50),
                getHeight()/2+(int)(Math.sin(Math.toRadians(60+(i-1)*60))*50));
            general[fill][0] = datas[0];
            general[fill][1] = datas[1];
            general[fill][2] = datas[2];
            general[fill][3] = datas[3];
            general[fill][4] = datas[4];
            general[fill][5] = datas[5];
            fill++;
        }
        addObject(new PressEnter(), getWidth()/2, 500);
        addObject(new Informations2("The only way out is to solve this puzzle. To do so, the extremities of each segment of all circles must be connected. You can rotate each circle by clicking on it... HURRY UP! "), getWidth()/2, getHeight()/2);
    }

    /**
     * Empêche le joueur de stopper la partie pour réfléchir!
     */
    public void stopped()
    {
        Greenfoot.start();
    }

    /**
     * Empêche entre-autre que le joueur mette le jeu en pause pour réfléchir.
     */
    public void act()
    {
        if(counter == 0)
        {
            autoGenerate();
        }
        if(counter == 1)
        {
            autoGenerate();
        }
        if(getObjects(Informations.class).isEmpty())
        {
            counter++;
            win();
            counter2++;
            timer();
            ChangeMouseImage(img1, 1, 1);
            fail();
        }
    }

    /**
     * Temps écoulé en secondes
     */
    public void timer()
    {
        if(counter2 % 60 == 0)
        {
            time++;
        }
    }

    /**
     * Affiche le temps restant à l'écran
     */
    public void drawTime()
    {
        List<CountDown> countdowns = getObjects(CountDown.class);
        removeObjects(countdowns);
        CountDown countdown = new CountDown();
        addObject(countdown, getWidth()*11/12, getHeight()/20);
    }

    /**
     * Méthode qui renvoie le joueur à la partie s'il a réussi à résoudre le puzzle 
     */
    public void win()
    {
        if(time <= MAX_TIME)
        {
            int verify = 0;
            List<Hexa> hexas = getObjects(Hexa.class);
            for(Hexa hexa : hexas)
            {
                if(hexa.solved == false)
                {
                    verify++;
                }
            }
            if(verify == 0)
            {
                TheEdge.nightmares++;
                DATA.storeInfos2(13, Integer.toString(TheEdge.nightmares));
                Greenfoot.delay(120);
                Game.completedNightmares++;
                Greenfoot.setWorld(Gears.currentWorld);
                sound.stop();
                if(Volume.volumeOn == true)
                {
                    Game.gameMusic.setVolume(100);
                    Game.gameMusic.playLoop();
                }
                Gears gear = (Gears) Gears.currentWorld.getObjects(Gears.class).get(0);
                gear.setLocation(Gears.currentWorld.getWidth()/6, Gears.currentWorld.getHeight()/2);
            }
        }
    }

    /**
     * Méthode servant à générer une combinaison aléatoire du puzzle
     */
    public void autoGenerate()
    {
        List<Hexa> hexas = getObjects(Hexa.class);
        for(Hexa hexa : hexas)
        {
            for(int i = 0; i<6; i++)
            {
                int datas[] = new int[6];
                for(int j = 0; j<6; j++)
                {
                    try
                    {
                        Hexa h = (Hexa) getObjectsAt(hexa.getX()+(int)(Math.cos(Math.toRadians(60*i))*50)+(int)(Math.cos(Math.toRadians(60+(j-1)*60))*50), hexa.getY()+(int)(Math.sin(Math.toRadians(60*i))*50)+(int)(Math.sin(Math.toRadians(60+(j-1)*60))*50), Hexa.class).get(0);
                        if(h.array[(j+3)%6] == 1)
                        {
                            datas[j] = 1;
                        }
                        else if(h.array[(j+3)%6] == 0)
                        {
                            datas[j] = 0;
                        }
                    }
                    catch(IndexOutOfBoundsException e)
                    {
                        datas[j] = binary();
                    }
                }
                if(getObjectsAt(hexa.getX()+(int)(Math.cos(Math.toRadians(60+(i-1)*60))*50), hexa.getY()+(int)(Math.sin(Math.toRadians(60+(i-1)*60))*50), Hexa.class).isEmpty())
                {
                    addObject(new Hexa(datas[0], datas[1], datas[2], datas[3], datas[4], datas[5]),
                        hexa.getX()+(int)(Math.cos(Math.toRadians(60+(i-1)*60))*50),
                        hexa.getY()+(int)(Math.sin(Math.toRadians(60+(i-1)*60))*50));
                    general[fill][0] = datas[0];
                    general[fill][1] = datas[1];
                    general[fill][2] = datas[2];
                    general[fill][3] = datas[3];
                    general[fill][4] = datas[4];
                    general[fill][5] = datas[5];
                    fill++;
                }
            }
        }
    }

    /**
     * Méthode renvoyant une valeur binaire aléatoire
     */
    public int binary()
    {
        int random = Greenfoot.getRandomNumber(2);
        if(random == 0) return 0;
        return 1;
    }

    /**
     * Méthode mettant fin à la partie si le temps est écoulé
     */
    public void fail()
    {
        if(time > MAX_TIME)
        {
            sound.stop();
            Game.gameMusic.stop();
            Greenfoot.setWorld(new GameOver());
        }
    }

}
