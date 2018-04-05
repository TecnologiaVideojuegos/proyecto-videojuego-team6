/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AliensDriveMeCrazy.Characters;

/**
 *
 * @author mr.blissfulgrin
 */
public class Hited
{
    protected int counterDamage;
    protected float x;
    protected float y;
    protected final float xVel;
    protected final float yVel;
    protected final String label;
    
    protected Hited (float x, float y, float currentVelx, int currentHealth)
    {
        this.counterDamage = 150;
        this.x = x;
        this.y = y;
        if (currentVelx > 0)
            xVel = 0.1f;
        else
            xVel = -0.1f;
        this.yVel = -0.2f;
        this.label = String.valueOf(currentHealth);
    }
    
    public boolean isAlive ()
    {
        return counterDamage >= 0;
    }
    
    public void update (int control)
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
