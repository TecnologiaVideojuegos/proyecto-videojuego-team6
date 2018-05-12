/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shutterearth.characters;

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
    private final Hero hero;
    private final int updateRate;
    private boolean face;
    
    private float maxR;
    private float maxL;
    
    public Shot (Gun gun, Hero hero)
    {
        this.updateRate = 4;
        this.delay = gun.getDelay()*2;
        this.y = hero.getY();
        first = true;
        this.hero = hero;
        
        this.w = Game.getX()/50;
        this.h = Game.getY()/50;
        
        maxR = Game.getX();
        maxL = 0;
    }
    
    public float getW()
    {
        return w;
    }
    public float getH()
    {
        return h;
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
                y = hero.getBox().getCenterY();
                x = hero.getBox().getCenterX();
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
        return x<maxL || x+w>maxR;
    }
}
