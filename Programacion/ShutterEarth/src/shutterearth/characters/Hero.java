
package shutterearth.characters;

import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import shutterearth.Game;
import shutterearth.Media;
import shutterearth.screens.Scene;

/**
 *
 * @author mr.blissfulgrin
 */
public class Hero extends Scene
{
    private final String user;
    private final String pswd;
    private final Boolean permission;
    private final int healthMax;
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
    private boolean animation;
    private int counterAnimation;
    private final int animationTime;
    
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
        
        this.h = Game.getY()/11;
        this.w = (h*9)/17;
        this.jumpUp = false;
        this.jumpDown = false;
        this.over = false;
        this.xPos = Game.getxVel();
        animationTime = 20;
        counterAnimation = 0;
        animation = false;
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
        inventory.lefttGun();
    }
    public void inventoryRight()
    {
        inventory.rightGun();
    }
    public void shot()
    {
        if (!jumping())
        {
            if (bullets >= inventory.getCost())
            {
                bullets -= inventory.getCost();
                inventory.shot();
            }
        }
    }
    
    public void getDamage (int damage)
    {
        this.healthCurrent -= damage;
    }
    
    public boolean isAlive ()
    {
        return this.healthCurrent > 0;
    }
    
    public void hasKilled(int money)
    {
        bullets += money;
        kills++;
    }
    
    
    @Override
    public void Render(GameContainer gc, Graphics g) throws SlickException
    {
                
        if (Game.debug())
        {
            for (Rectangle rect : this.debug())
            {
                Game.getMedia().getImage(Media.IMAGE.GREY).draw(rect.getX(),rect.getY(),rect.getWidth(),rect.getHeight());
            }
        } 
        Game.getMedia().getSprit(xVel > 0? Media.SPRITE.BASE_DER : Media.SPRITE.BASE_IZQ).draw(xPos,yPos,w,h);
        if (animation)
        {
            if (counterAnimation > animationTime)
            {
                animation = false;
                counterAnimation = 0;
            }
            Game.getMedia().getImage(this.getFace()?Media.IMAGE.FIRE_R:Media.IMAGE.FIRE_L).draw(xPos+(this.getFace()?-10:0),yPos,w+10,h);
            counterAnimation++;
        }
    }

    @Override
    public void Update(GameContainer gc, float t) throws SlickException
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

    @Override
    public void init(GameContainer gc) throws SlickException{}
    
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
        Game.getMedia().getSound(Media.SOUND.SHOT).play();
        animation = true;
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
    
    public void start ()
    {
        Game.addScene(inventory);
        Game.addScene(this);
    }
    public void end ()
    {
        Game.removeSence(this);
        Game.removeSence(inventory);
    } 
    public void pause ()
    {
        this.setState(STATE.FREEZE);
        inventory.setState(STATE.FREEZE);
    } 
    public void wake ()
    {
        this.setState(STATE.ON);
        inventory.setState(STATE.ON);
    } 
}
