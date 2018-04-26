/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shutterearth.characters;

import java.io.Serializable;

/**
 *
 * @author mr.blissfulgrin
 */
public class SavedHero implements Serializable
{
    private final String user;
    private final String pswd;
    private final Boolean permission;
    private final int healthMax;
    private final int stage;
    private final int bullets;
    private final int kills;
    
    public SavedHero(String user, String pswd, Boolean permission)
    {
        this.user = user;
        this.pswd = pswd;
        this.permission = permission;
        this.healthMax = 12;
        this.stage = 1;
        this.bullets = 50;
        this.kills = 0;
    }
    public SavedHero(Hero hero)
    {
        this.user = hero.getUser();
        this.pswd = hero.getPswd();
        this.permission = hero.getPermission();
        this.bullets = hero.getBullets();
        this.healthMax = hero.getHealthMax();
        this.stage = hero.getStage();
        this.kills = hero.getKills();
    }
    
    public String getUser ()
    {
        return user;
    }
    
    public int getHealthMax ()
    {
        return healthMax;
    }
    
    public int getStage ()
    {
        return stage;
    }
    
    public int getBullets ()
    {
        return bullets;
    }
    
    public int getKills()
    {
        return kills;
    }
    
    public String getPswd ()
    {
        return pswd;
    }
    
    public Boolean getPermission ()
    {
        return permission;
    }
}
