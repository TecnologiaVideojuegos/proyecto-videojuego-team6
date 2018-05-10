
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
    private int stage;
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
    
    public synchronized String getUser ()
    {
        return user;
    }
    
    public synchronized int getHealthMax ()
    {
        return healthMax;
    }
    
    public synchronized int getStage ()
    {
        return stage;
    }
    
    public synchronized int getBullets ()
    {
        return bullets;
    }
    
    public synchronized int getKills()
    {
        return kills;
    }
    
    public synchronized String getPswd ()
    {
        return pswd;
    }
    
    public synchronized int getNumberOfGuns ()
    {
        return numberOfGuns;
    }
    
    public synchronized Boolean getPermission ()
    {
        return permission;
    }
    
    public synchronized ArrayList <int[]> getInventory()
    {
        return inventory;
    }
    public synchronized void sold (int money)
    {
        bullets -= money;
    }
    public synchronized void setHealth(int health)
    {
        this.healthMax = health;
    }
    public synchronized void setBullets(int bullets)
    {
        this.bullets = bullets;
    }
    public synchronized void setStage(int stage)
    {
        this.stage = stage;
    }
}
