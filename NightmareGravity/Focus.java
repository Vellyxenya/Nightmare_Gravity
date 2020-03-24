import greenfoot.*;

/**
 * Objet apparaissant sur les ennemis ciblés par le 4e Vaisseau
 */
public class Focus extends SmoothMover
{

    /**
     * rétrécit l'image de l'acteur
     */
    public void act() 
    {
        setRotation(getRotation()+5);
        if(getImage().getWidth()>100)
        {
            getImage().scale(getImage().getWidth()-1, getImage().getHeight()-1);
        }
    }

}
