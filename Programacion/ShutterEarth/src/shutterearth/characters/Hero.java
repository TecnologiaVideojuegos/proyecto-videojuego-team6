
package shutterearth.characters;

import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
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
    
    private final float w;
    private final float h;
    private float xVel;
    private float yVel;
    private float xPos;
    private float yPos;
    private float gravity;
    private final Inventory inventory;
    
    private Rectangle line;
    private Rectangle colum;
    private Rectangle box;
    private float floor;
    
    public Hero(SavedHero hero)
    {
        this.user = hero.getUser();
        this.pswd = hero.getPswd();
        this.permission = hero.getPermission();
        this.bullets = hero.getBullets();
        this.healthMax = hero.getHealthMax();
        this.healthCurrent = hero.getHealthMax();
        this.stage = hero.getStage();
        this.kills = hero.getKills();
        this.gravity = Game.getGravity();
        this.inventory = new Inventory (hero.getInventory(),this);
        Game.addScene(inventory); 
        
        this.h = Game.getY()/10;
        this.w = (h*9)/17;
    }
    
    public void place (float x, float y, float floor, int left, int right)
    {
        this.xPos = x;
        this.yPos = y;
        this.floor = floor;
        line = new Rectangle (0,y,Game.getX(),floor -y);
        colum = new Rectangle (left,0,right-left,Game.getY());
        box = new Rectangle (x,y,w,h);
    }
    
    public Media.SPRITE getImg()
    {
        return xVel > 0? Media.SPRITE.BASE_DER : Media.SPRITE.BASE_IZQ;
    }
    
    public Rectangle getLine ()
    {
        return line;
    }
    
    public Rectangle getColum ()
    {
        return colum;
    }
    
    public Rectangle getBox ()
    {
        return box;
    }
    
    public boolean isInLine (Rectangle rect)
    {
        return line.intersects(rect);
    }
    
    public boolean isInRoom (Rectangle rect)
    {
        return line.intersects(rect) && colum.intersects(rect);
    }
    
    public boolean isHited (Rectangle rect)
    {
        return box.intersects(rect);
    }
    
    public float getH ()
    {
        return h;
    }
    
    public float getW ()
    {
        return w;
    }
    
    public Rectangle[] debug ()
    {
        return new Rectangle[] {line,colum,box};
    }
    
    public Inventory getInventory ()
    {
        return inventory;
    }
    
    public void setStage (int stage)
    {
        if (this.stage < stage)
            this.stage = stage;
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
        SavedHero saved = new SavedHero(this);
        Game.save(saved);
        return saved;
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
