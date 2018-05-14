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
public class Label
{
    protected int counterDamage;
    protected float x;
    protected float y;
    protected final float xVel;
    protected final float yVel;
    protected final String label;
    
    protected Label (float x, float y, boolean face, int money)
    {
        this.counterDamage = 350;
        this.x = x;
        this.y = y;
        if (face)
            xVel = Game.getGravity()/20f;
        else
            xVel = -Game.getGravity()/20f;
        this.yVel = -Game.getGravity()/10f;
        this.label = String.valueOf(money);
    }
    
    public boolean isAlive ()
    {
        return counterDamage >= 0;
    }
    
    public void update (float control)
    {
        x += xVel*control;
        y += yVel*control;
        counterDamage -= 1*control;
    }
    
    public String getLabel()
    {
        return label;
    }
    public float getX()
    {
        return x;
    }
    public float getY()
    {
        return y;
    }
}
