
package shutterearth.characters;

import java.util.ArrayList;
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
    private final Inventory inventory;
    
    private Rectangle line;
    private Rectangle colum;
    private Rectangle box;
    private float floor;
    private boolean over;
    private boolean jumpUp;
    private boolean jumpDown;
    
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
        this.inventory = new Inventory (hero.getInventory(),this);
        Game.addScene(inventory); 
        
        this.h = Game.getY()/10;
        this.w = (h*9)/17;
        this.jumpUp = false;
        this.jumpDown = false;
        this.over = false;
        this.xPos = Game.getxVel();
    }
    
    public void goUp()
    {
        if (!jumping())
        {
            yVel = Game.getyVelUp();
            this.setY(yPos-1);
            jumpUp = true;
        }
    }
    public void goLeft()
    {
        xVel = -Game.getxVel();
    }
    public void goRight()
    {
        xVel = Game.getxVel();
    }
    public void goDown()
    {
        if (!jumping())
        {
            yVel = Game.getyVelDown();
            this.setY(yPos-1);
            jumpDown = true;
        }
    }
    public void inventroyLeft()
    {
        
    }
    public void inventoryRight()
    {
        
    }
    public void shot()
    {
        
    }
    
    public void update (int t)
    {
        this.setX(this.xPos + this.xVel*t);
        if (yVel < Game.getGravityMax())
            yVel += Game.getGravity()*t;
        this.setY(this.yPos + this.yVel*t);
        
        if (jumpUp)
        { 
            if (over)
            {
                if (yPos >= floor)
                {
                    jumpUp = false;
                    over = false;
                    yPos = floor;
                }  
            }
            else
            {
                over = (yPos < floor);
            }
        }
        else if(jumpDown)
        {
            if (yPos >= floor)
            {
                jumpDown = false;
                yPos = floor;
            }
        }
        else if (yPos >= floor)
        {
            this.setY(floor);
        }
        
        if (xPos <= colum.getX())
        {
            this.goRight();
        }
        if (xPos+w >= colum.getX()+colum.getWidth())
        {
            this.goLeft();
        }
    }
    
    public boolean jumping ()
    {
        return jumpUp || jumpDown;
    }
    
    public void place (float floor, int left, int right)
    {
        this.xPos = left;
        this.yPos = floor - h;
        this.floor = floor - h;
        line = new Rectangle (0,yPos,Game.getX(),floor+h-yPos);
        colum = new Rectangle (left,0,right-left,Game.getY());
        box = new Rectangle (xPos,yPos,w,h);
    }
    
    public void setBounds (float left, float right, float floor)
    {
        colum.setX(left);
        colum.setWidth(right-left);
        this.floor = floor + h;
    }
    
    private void setX(float x)
    {
        this.xPos = x;
        box.setX(x);
    }
    private void setY(float y)
    {
        this.yPos = y;
        box.setY(y);
        line.setY(y);
        line.setHeight(floor+h-y);
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
