import greenfoot.*;

/**
 * Objet permettant d'enclencher/déclencer le son. Il est On par défaut.
 */
class Volume extends Interface
{
    /**
     * Retourne si le volume est enclenché ou pas.
     */
    public static boolean volumeOn = true;

    /**
     * Construit l'image de base du bouton
     */
    public Volume()
    {
        if(Volume.volumeOn == true)
        {
            setImage("musicOn.png");
            Game.gameMusic.setVolume(100);
        }
        else if(Volume.volumeOn == false)
        {
            setImage("musicOff.png");
            Game.gameMusic.setVolume(0);
        }
    }
    
    /**
     * Active/désactive la musique de fond
     */
    public void act()
    {
        if(Greenfoot.mouseClicked(this) && volumeOn == true)
        {
            volumeOn = false;
            setImage("musicOff.png");
            Game.gameMusic.setVolume(0);
        }
        else if(Greenfoot.mouseClicked(this) && volumeOn == false)
        {
            volumeOn = true;
            setImage("musicOn.png");
            Game.gameMusic.setVolume(100);
        }
    }    

}
