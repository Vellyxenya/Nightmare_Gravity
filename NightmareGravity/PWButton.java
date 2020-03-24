import greenfoot.*;

/**
 * Bouton où est écrit le mot de passe
 */
public class PWButton extends DATA
{
    /**
     * Retourne true si cet objet est actuellement sélectionné
     */
    public static boolean available = false;
    /**
     * Compteur utile pour l'effet de la souris
     */
    private int counter;
    public static boolean ready;

    public PWButton()
    {
        super();
        ready = false;
        on = false;
    }

    /**
     * Déetecte si l'utilisateur vient de cliquer sur ce bouton
     */
    public void act()
    {
        super.act();
        if(Greenfoot.mouseClicked(this))
        {
            try
            {
                String pw = Background.PW;
                String pwf = Background.PWFake;
                if(Greenfoot.getKey() != null && pw.length() == 0)
                {
                    pw = pw.substring(0, pw.length()-1);
                    pwf = pwf.substring(0, pwf.length()-1);
                }
            }catch(StringIndexOutOfBoundsException e){}
            this.available = true;
            IDButton.available = false;
        }
        changeImage();
        counter++;
        effect();
    }

    /**
     * Effet de la souris et sélection du bouton
     */
    public void effect()
    {
        if(!Greenfoot.isKeyDown("enter")) ready = true;
        if(this.available == true)
        {
            if(counter%10 == 0)
            {
                getWorld().addObject(new Effect(), this.getX(), this.getY());
            }
            if(Greenfoot.isKeyDown("enter") && ready == true)
            {
                this.available = false;
                ready = false;
                Login.login();
            }
        }
    }

}
