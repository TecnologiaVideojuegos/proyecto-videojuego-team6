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
    private boolean first;
    private final Hero hero;
    private final int updateRate;
    private boolean face;
    
    public Shot (Gun gun, Hero hero)
    {
        this.updateRate = 2;
        this.delay = gun.getDamage();
        this.y = hero.getY();
        first = true;
        this.hero = hero;
    }
    
    public void update (int delta)
    {
        if (delay > 0)
        {
            delay --;
        }
        else
        {
            if (first)
            {
                first = false;
                hero.doShotAnimation();
                y = hero.getY();
                x = hero.getX();
                face = hero.getFace();
            }
            else
            {
                if (face)
                    x += x + updateRate*delta;
                else
                    x -= x + updateRate*delta;
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
    public boolean isDwable()
    {
        return delay <= 0;
    }
    public boolean ended ()
    {
        return x>Game.getX() || x<0;
    }
}
