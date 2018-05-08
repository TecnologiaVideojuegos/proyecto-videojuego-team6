
package shutterearth.characters;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author mr.blissfulgrin
 */
public class SavedHero implements Serializable
{
    private final String user;
    private final String pswd;
    private final Boolean permission;
    private int healthMax;
    private final int stage;
    private int bullets;
    private final int kills;
    private final int numberOfGuns;
    private final ArrayList <int[]> inventory;
    
    public SavedHero(String user, String pswd, Boolean permission)
    {
        this.user = user;
        this.pswd = pswd;
        this.permission = permission;
        this.healthMax = 20;
        this.stage = 1;
        this.bullets = 0;
        this.kills = 0;
        this.numberOfGuns = 1;
        this.inventory = new ArrayList<>();
        inventory.add(new int[]{0,1});
        inventory.add(new int[]{1,0});
        inventory.add(new int[]{2,0});
        inventory.add(new int[]{3,0});
        inventory.add(new int[]{4,0});
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
        this.numberOfGuns = hero.getNumberOfGuns();
        this.inventory = hero.saveInventory();
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
    
    public int getNumberOfGuns ()
    {
        return numberOfGuns;
    }
    
    public Boolean getPermission ()
    {
        return permission;
    }
    
    public ArrayList <int[]> getInventory()
    {
        return inventory;
    }
    public void sold (int money)
    {
        bullets -= money;
    }
        public void setHealth(int health)
    {
        this.healthMax = health;
    }
}
