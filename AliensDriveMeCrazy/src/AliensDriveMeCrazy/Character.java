/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AliensDriveMeCrazy;

import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author mr.blissfulgrin
 */
public abstract class Character extends Scene
{
    private final ArrayList <Image> img;
    private final Inventory inventory;
    protected int healthMax;
    protected int healthCurrent;
    protected int x;
    protected int y;
    protected int xVel;
    protected int yVel;
    protected final int gravity;
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
        this.gravity = 1;
        this.jump = false;
        
        this.inventory = inventory;
        this.healthMax = healthMax;
        this.healthCurrent = healthMax;
    }

    @Override
    public void Render(GameContainer gc, Graphics g) throws SlickException
    {
        move();
        shot();
    }

    @Override
    public void Update(GameContainer gc, int t) throws SlickException
    {
        img.get(inventory.getCurrent()).draw(100, 100, 100,100);
    }

    @Override
    public void init(GameContainer gc) throws SlickException
    {  
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
    
    protected abstract void move();
    protected abstract void shot();
}
