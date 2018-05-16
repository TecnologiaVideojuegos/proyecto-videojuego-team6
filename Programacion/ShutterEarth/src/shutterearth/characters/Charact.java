/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shutterearth.characters;

import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import shutterearth.Game;
import shutterearth.map.Field;
import shutterearth.screens.Scene;

/**
 *
 * @author mr.blissfulgrin
 */
public abstract class Charact extends Scene
{
    protected int healthCurrent;
    protected float w;
    protected float h;
    protected float xVel;
    protected float yVel;
    protected float xPos;
    protected float yPos;
    protected Inventory inventory;
    protected int healthMax;
    
    protected float floor;
    protected boolean over;
    protected boolean jumpUp;
    protected boolean jumpDown;
    
    protected Rectangle line;
    protected Rectangle colum;
    protected Rectangle box;
    protected boolean animation;
    protected int counterAnimation;
    protected int animationTime;
    
    protected boolean active;
    protected Field field;
    ArrayList<Charact> enemy;
    
    protected boolean called;
    protected int borderRoom;
    protected int room;
    
    public Charact ()
    {
        counterAnimation = 0;
        animation = false;
        active = false;
        called = false;
    }

    @Override
    public abstract void Render(GameContainer gc, Graphics g) throws SlickException;

    @Override
    public abstract void Update(GameContainer gc, float t) throws SlickException;

    @Override
    public abstract void init(GameContainer gc) throws SlickException;

    public abstract void goUp();

    public abstract void goLeft();

    public abstract void goRight();

    public abstract void goDown();

    public abstract void shot();

    public abstract void place(float x, float y, float floor, float left, float right, int borderRoom, int room);

    public abstract void setBounds (float floor, float left, float right, int borderRoom, int room);
    
    protected void boundSetter(float[] bounds)
    {
        setBounds(bounds[0],bounds[1],bounds[2],(int)bounds[3],(int)bounds[4]);
    }
        
    protected abstract void setX(float x);
    protected abstract void setY(float y);
    
    public abstract void doShotAnimation();
    
    public int getCurrentHealth()
    {
        return this.healthCurrent;
    }
    
    public void getDamage(int damage)
    {
        this.healthCurrent -= damage;
    }
    
    public boolean isAlive()
    {
        return healthCurrent > 0;
    }
    
    public Rectangle getLine()
    {
        return line;
    }

    public Rectangle getColum()
    {
        return colum;
    }

    public Rectangle getBox()
    {
        return box;
    }

    public boolean isInLine(Rectangle rect)
    {
        return line.intersects(rect);
    }

    public boolean isInRoom(Rectangle rect)
    {
        return line.intersects(rect) && colum.intersects(rect);
    }

    public boolean isHited(Rectangle rect)
    {
        return box.intersects(rect);
    }

    public float getH()
    {
        return h;
    }

    public float getW()
    {
        return w;
    }

    public Rectangle[] debug()
    {
        return new Rectangle[] {line,colum,box};
    }

    public Inventory getInventory()
    {
        return inventory;
    }

    public float getY()
    {
        return yPos;
    }

    public float getX()
    {
        return xPos;
    }

    public boolean getFace()
    {
        return xVel > 0;
    }

    public void start()
    {
        Game.addScene(this);
    }
    
    public void startI()
    {
        Game.addScene(inventory);
    }

    public void end()
    {
        Game.removeSence(this);
        Game.removeSence(inventory);
    }

    public void pause()
    {
        this.setState(Scene.STATE.FREEZE);
        inventory.setState(Scene.STATE.FREEZE);
    }

    public void wake()
    {
        this.setState(Scene.STATE.ON);
        inventory.setState(Scene.STATE.ON);
    }

    public void addEnemys(ArrayList<Charact> enemy)
    {
        this.enemy = enemy;
    }
    
    public ArrayList<Charact> getEnemys()
    {
        return enemy;
    }
    
    public void activate()
    {
        active = true;
    }
    
    public int getHealthMax ()
    {
        return healthMax;
    }
    public void hasKilled(int money){}
    
    public abstract int getInfo();
    
    public void setHudAlien (Charact enemy, int LastLive){}
    
    public boolean jumping ()
    {
        return jumpUp || jumpDown;
    }
    
    public int getBorder()
    {
        return borderRoom;
    }
    
    public float[] bulletControl (float x,float y)
    {
        return field.bulletControl(x, y);
    }
}
