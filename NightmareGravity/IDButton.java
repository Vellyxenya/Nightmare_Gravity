import greenfoot.*;

/**
 * Bouton où est écrit le mot de passe
 */
public class IDButton extends DATA
{
    /**
     * Retourne true si cet objet est sélectionné
     */
    public static boolean available = false;
    /**
     * Compteur utile à l'effet spécial de la souris
     */
    private int counter;

    /**
     * Initialise le bouton
     */
    public IDButton()
    {
        super();
        on = false;
    }

    /**
     * Détecte si l'utilisateur vient de cliquer sur ce bouton
     */
    public void act()
    {
        super.act();
        if(Greenfoot.mouseClicked(this))
        {
            try
            {
                String id = Background.ID;
                if(Greenfoot.getKey() != null && id.length() == 0)
                {
                    id = id.substring(0, id.length()-1);
                }
            }catch(StringIndexOutOfBoundsException e){}
            this.available = true;
            PWButton.available = false;
        }
        changeImage();
        counter++;
        effect();
    }
    
    /**
     * Effet visuel de la souris et sélection de ce bouton
     */
    public void effect()
    {
        if(this.available == true)
        {
            if(counter%10 == 0)
            {
                getWorld().addObject(new Effect(), this.getX(), this.getY());
            }
            if(Greenfoot.isKeyDown("enter"))
            {
                this.available = false;
                PWButton.ready = false;
                PWButton.available = true;
            }
        }
    }

}
