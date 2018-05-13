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
import shutterearth.Media;
import shutterearth.screens.Scene;

/**
 *
 * @author mr.blissfulgrin
 */
public abstract class CharactX extends Scene implements Charact
{
    protected int healthCurrent;
    protected float w;
    protected float h;
    protected float xVel;
    protected float yVel;
    protected float xPos;
    protected float yPos;
    protected Inventory inventory;
    
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
    
    public CharactX ()
    {
        counterAnimation = 0;
        animation = false;
        active = false;
    }

    @Override
    public abstract void Render(GameContainer gc, Graphics g) throws SlickException;

    @Override
    public abstract void Update(GameContainer gc, float t) throws SlickException;

    @Override
    public abstract void init(GameContainer gc) throws SlickException;

    @Override
    public abstract void goUp();

    @Override
    public abstract void goLeft();

    @Override
    public abstract void goRight();

    @Override
    public abstract void goDown();

    @Override
    public abstract void shot();

    @Override
    public abstract void place(float floor, int left, int right);

    @Override
    public abstract void setBounds(float left, float right, float floor);
    
        
    protected abstract void setX(float x);
    protected abstract void setY(float y);
    
    @Override
    public void doShotAnimation()
    {
        Game.getMedia().getSound(Media.SOUND.SHOT).play();
        animation = true;
    }
    
    @Override
    public int getCurrentHealth()
    {
        return this.healthCurrent;
    }
    
    @Override
    public void getDamage(int damage)
    {
        this.healthCurrent -= damage;
    }
    
    @Override
    public boolean isAlive()
    {
        return healthCurrent > 0;
    }
    
    @Override
    public Rectangle getLine()
    {
        return line;
    }

    @Override
    public Rectangle getColum()
    {
        return colum;
    }

    @Override
    public Rectangle getBox()
    {
        return box;
    }

    @Override
    public boolean isInLine(Rectangle rect)
    {
        return line.intersects(rect);
    }

    @Override
    public boolean isInRoom(Rectangle rect)
    {
        return line.intersects(rect) && colum.intersects(rect);
    }

    @Override
    public boolean isHited(Rectangle rect)
    {
        return box.intersects(rect);
    }

    @Override
    public float getH()
    {
        return h;
    }

    @Override
    public float getW()
    {
        return w;
    }

    @Override
    public Rectangle[] debug()
    {
        return new Rectangle[] {line,colum,box};
    }

    @Override
    public Inventory getInventory()
    {
        return inventory;
    }

    @Override
    public float getY()
    {
        return yPos;
    }

    @Override
    public float getX()
    {
        return xPos;
    }

    @Override
    public boolean getFace()
    {
        return xVel > 0;
    }

    @Override
    public void start()
    {
        Game.addScene(this);
    }
    @Override
    public void startI()
    {
        Game.addScene(inventory);
    }

    @Override
    public void end()
    {
        Game.removeSence(this);
        Game.removeSence(inventory);
    }

    @Override
    public void pause()
    {
        this.setState(Scene.STATE.FREEZE);
        inventory.setState(Scene.STATE.FREEZE);
    }

    @Override
    public void wake()
    {
        this.setState(Scene.STATE.ON);
        inventory.setState(Scene.STATE.ON);
    }

    @Override
    public void addEnemys(ArrayList<Charact> enemy)
    {
        inventory.addEnemys(enemy);
    }
    
    @Override
    public void activate()
    {
        active = true;
    }
}
