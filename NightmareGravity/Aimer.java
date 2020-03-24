import greenfoot.*;

/**
 * Objet créé par le Boss 2 qui crée une force répulsive projetant les orbes dans toutes les directions.
 */
public class Aimer extends SpecialEffects
{
    
    public void act() 
    {
        disappear();
    }
    
    private void disappear()
    {
        if(Game.bossSpawned == 0)
        {
            getWorld().removeObject(this);
        }
    }
    
}
