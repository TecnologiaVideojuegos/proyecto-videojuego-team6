
package shutterearth.characters;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author mr.blissfulgrin
 */
public class SavedHero implements Serializable
{
    private final String user;
    private final String pswd;
    private final AtomicBoolean permission;
    private final AtomicInteger healthMax;
    private final AtomicInteger stage;
    private final AtomicInteger bullets;
    private final AtomicInteger kills;
    private final AtomicInteger numberOfGuns;
    private final ArrayList <int[]> inventory;
    
    public SavedHero(String user, String pswd, Boolean permission)
    {
        this.user = user;
        this.pswd = pswd;
        this.permission = new AtomicBoolean(permission);
        this.healthMax = new AtomicInteger(20);
        this.stage = new AtomicInteger(1);
        this.bullets = new AtomicInteger(0);
        this.kills = new AtomicInteger(0);
        this.numberOfGuns = new AtomicInteger(1);
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
        this.permission = new AtomicBoolean(hero.getPermission());
        this.bullets = new AtomicInteger(hero.getBullets());
        this.healthMax = new AtomicInteger(hero.getHealthMax());
        this.stage = new AtomicInteger(hero.getStage());
        this.kills = new AtomicInteger(hero.getKills());
        this.numberOfGuns = new AtomicInteger(hero.getNumberOfGuns());
        this.inventory = hero.saveInventory();
    }
    
    public synchronized String getUser ()
    {
        return user;
    }
    
    public int getHealthMax ()
    {
        return healthMax.get();
    }
    
    public int getStage ()
    {
        return stage.get();
    }
    
    public int getBullets ()
    {
        return bullets.get();
    }
    
    public int getKills()
    {
        return kills.get();
    }
    
    public synchronized String getPswd ()
    {
        return pswd;
    }
    
    public int getNumberOfGuns ()
    {
        return numberOfGuns.get();
    }
    
    public Boolean getPermission ()
    {
        return permission.get();
    }
    
    public synchronized ArrayList <int[]> getInventory()
    {
        return inventory;
    }
    public void sold (int money)
    {
        bullets.addAndGet(-money);
    }
    public void setHealth(int health)
    {
        this.healthMax.set(health);
    }
    public void setBullets(int bullets)
    {
        this.bullets.set(bullets);
    }
    public void setStage(int stage)
    {
        this.stage.set(stage);
    }
}
