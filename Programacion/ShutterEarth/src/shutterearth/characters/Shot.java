/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shutterearth.characters;

import org.newdawn.slick.geom.Rectangle;
import shutterearth.Game;

/**
 *
 * @author mr.blissfulgrin
 */
public class Shot
{
    private int delay;
    private float x;
    private float y;
    private final float w;
    private final float h;
    private boolean first;
    private final Charact hero;
    private final int updateRate;
    private boolean face;
    
    private float maxR;
    private float maxL;
    
    private final Rectangle box;
    
    private final int offset;
    private final int damage;
    private int borderRoom;
    
    private float counter;
    private int hited;
    
    public Shot (Gun gun, Charact hero, int offset)
    {
        this.offset = offset;
        this.updateRate = 4;
        this.delay = gun.getDelay();
        this.y = hero.getY();
        first = true;
        this.hero = hero;
        
        this.w = Game.getX()/50;
        this.h = Game.getY()/50;
        
        maxR = Game.getX();
        maxL = 0;
        
        this.box = new Rectangle(0,0,w,h);
        this.damage = gun.getDamage();
        this.counter = 40;
    }
    
    public void setHited(int hited)
    {
        this.hited = hited;
    }
    public int getHited()
    {
        return hited;
    }
    
    public int getDamage()
    {
        return damage;
    }
    
    public float getW()
    {
        return w;
    }
    public float getH()
    {
        return h;
    }
    
    public void count (float delta)
    {
        this.counter -= 1*delta;
    }
    public boolean remove()
    {
        return counter <0;
    }
    
    public void update (float delta)
    {
        if (delay > 0)
        {
            delay -= 1*delta;
        }
        else
        {
            if (first)
            {
                    first = false;
                    hero.doShotAnimation();
                    y = hero.getBox().getY()+offset-h/2;
                    x = hero.getBox().getCenterX();
                    borderRoom = hero.getBorder();
                    maxR = hero.getColum().getMaxX();
                    maxL = hero.getColum().getX();
                    face = hero.getFace();
            }
            else
            {
                if (face)
                    x += updateRate*delta;
                else
                    x -= updateRate*delta;
            }
        }
        box.setX(x);
        box.setY(y);
    }
    
    public Rectangle getBox ()
    {
        return box;
    }
    
    public float getX()
    {
        return x;
    }
    public float getY()
    {
        return y;
    }
    public boolean getFace()
    {
        return face;
    }
    public boolean isDwable()
    {
        return (delay <= 0) && !first;
    }
    public boolean ended ()
    {
        switch (borderRoom)
        {
            case 0:
                return x<0 || x+w>maxR; 
            case 1:
                return x<maxL || x+w>Game.getX(); 
            case 2:
                return x<0 || x+w>Game.getX(); 
            default:
                return x<maxL || x+w>maxR;
        }
    }
}
