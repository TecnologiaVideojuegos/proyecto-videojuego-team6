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
import shutterearth.screens.Scene;

/**
 *
 * @author mr.blissfulgrin
 */
public class Ship extends Scene implements Charact
{
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
    
    public Ship (int type,int stage)
    {
        this.w = Game.getX()/9;
        this.h = Game.getX()/9;
        this.animationTime = 60;
        this.inventory = new Inventory(new int[]{type+2,stage/2},this);
    }
    
    @Override
    public int getCurrentHealth()
    {
        return this.healthCurrent;
    }

    @Override
    public void Render(GameContainer gc, Graphics g) throws SlickException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Update(GameContainer gc, float t) throws SlickException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void init(GameContainer gc) throws SlickException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void goUp()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void goLeft()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void goRight()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void goDown()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void shot()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void place(float floor, int left, int right)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setBounds(float left, float right, float floor)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rectangle getColum()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rectangle getBox()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isInLine(Rectangle rect)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isInRoom(Rectangle rect)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isHited(Rectangle rect)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float getH()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float getW()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rectangle[] debug()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Inventory getInventory()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float getY()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float getX()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doShotAnimation()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean getFace()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        this.setState(STATE.FREEZE);
        inventory.setState(STATE.FREEZE);
    }

    @Override
    public void wake()
    {
        this.setState(STATE.ON);
        inventory.setState(STATE.ON);
    }

    @Override
    public void addEnemys(ArrayList<Charact> enemy)
    {
        inventory.addEnemys(enemy);
    }
    
}
