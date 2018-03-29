/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AliensDriveMeCrazy;

import java.util.ArrayList;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author mr.blissfulgrin
 */
public abstract class Character
{
    private final ArrayList <Image> img;
    protected final Inventory inventory;
    protected int healthMax;
    protected int healthCurrent;
    protected float x;
    protected float y;
    protected float xVel;
    protected float yVel;
    protected final float gravity;
    protected boolean jump;
    
    public Character (String [] img, Inventory inventory, int healthMax, int x, int y)
    {
        this.img = new ArrayList<>();
        for (String s : img)
        {
            try
            {
                this.img.add(new Image(s));
            } 
            catch (SlickException ex)
            {
                System.out.println("ERROR LOADING CHARACTER WEARPONS");
            }
        }
        this.x = x;
        this.y = y;
        this.xVel = 0;
        this.yVel = 0;
        this.gravity = -1;
        this.jump = false;
        
        this.inventory = inventory;
        this.healthMax = healthMax;
        this.healthCurrent = healthMax;
    }
    
    public int getHealthMax ()
    {
        return healthMax;
    }
    
    public int getHealthCurrent ()
    {
        return healthCurrent;
    }
    
    public void setHealthMax(int health)
    {
        healthMax = health;
    }
    
    public void setHealthCurrent(int health)
    {
        healthCurrent = health;
    }
    
    public Image getWearpon ()
    {
        return inventory.getWearpon();
    }
    
    public Image getBullets()
    {
        return inventory.getBullets();
    }
    
    public int getBulletsAmount ()
    {
        return inventory.getAmount();
    }
    
    public void draw ()
    {
        img.get(inventory.getCurrent()).draw(x,y,100,100);
    }
    
    public abstract void move(int control);
    public abstract void shot(int control);
}
