
package shutterearth.characters;

import java.util.ArrayList;
import shutterearth.Game;
import shutterearth.Media;

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
    private int kills;
    private final Media images;
    private float xVel;
    private float yVel;
    private float xPos;
    private float yPos;
    private float gravity;
    private final Inventory inventory;
    
    public Hero(SavedHero hero)
    {
        this.user = hero.getUser();
        this.pswd = hero.getPswd();
        this.permission = hero.getPermission();
        this.bullets = hero.getBullets();
        this.healthMax = hero.getHealthMax();
        this.stage = hero.getStage();
        this.kills = hero.getKills();
        this.images = Game.getMedia();
        this.gravity = Game.getGravity();
        this.inventory = new Inventory (hero.getInventory(),this);
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
    
    public int getKills()
    {
        return kills;
    }
    
    public SavedHero save()
    {
        return new SavedHero(this);
    }
    
    public Boolean getPermission ()
    {
        return permission;
    }
    
    public float getY()
    {
        return yPos;
    }
    public float getX()
    {
        return xPos;
    }
    public void doShotAnimation()
    {
        
    }
    public boolean getFace()
    {
        return xVel > 0;
    }
    public ArrayList<int[]> saveInventory()
    {
        return inventory.save();
    }
    public int getNumberOfGuns()
    {
        return inventory.getNumberOfGuns();
    }
}
