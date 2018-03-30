/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AliensDriveMeCrazy;

import org.newdawn.slick.Image;

/**
 *
 * @author mr.blissfulgrin
 */
public abstract class Shot
{
    private final Image shot;
    private float x;
    private final float y;
    private final float vel;
    private final float w;
    private final float h;

    public Shot (Image shot,float x, float y, boolean dir)
    {
        this.shot = shot;
        this.x = x;
        this.y = y;
        this.vel = dir? 5:-5;
        this.w = 20;
        this.h = 20;
    }
    public float getW()
    {
        return w;
    }
    public float getH()
    {
        return h;
    }
    public Image getImage()
    {
        return shot;
    }
    public float getY ()
    {
        return y;
    }
    public void update ( float control)
    {
        this.x = x + vel*control;
    }
    public float getX()
    {
        return x;
    }

    public abstract boolean hit (Character we);
}
