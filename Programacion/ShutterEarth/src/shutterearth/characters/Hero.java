/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shutterearth.characters;

/**
 *
 * @author mr.blissfulgrin
 */
public class Hero
{
    private final String user;
    private final String pswd;
    private final Boolean permission;
    private int healthMax;
    private int healthCurrent;
    private int stage;
    private int bullets;
    
    public Hero(SavedHero hero)
    {
        this.user = hero.getUser();
        this.pswd = hero.getPswd();
        this.permission = hero.getPermission();
    }
    
    public String getUser ()
    {
        return user;
    }
    
    public String getPswd ()
    {
        return pswd;
    }
    
    public int getHealthMax ()
    {
        return healthMax;
    }
    
    public int getHealthCurrent ()
    {
        return healthCurrent;
    }
    
    public int getStage ()
    {
        return stage;
    }
    
    public int getBullets ()
    {
        return bullets;
    }
    
    public SavedHero save()
    {
        return new SavedHero(this);
    }
    
    public Boolean getPermission ()
    {
        return permission;
    }
}
