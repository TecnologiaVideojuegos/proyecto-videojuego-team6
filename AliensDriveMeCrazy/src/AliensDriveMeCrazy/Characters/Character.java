/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AliensDriveMeCrazy.Characters;

import AliensDriveMeCrazy.Game;
import AliensDriveMeCrazy.Guns.Inventory;
import AliensDriveMeCrazy.Shots.Shot;
import java.io.Serializable;
import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author mr.blissfulgrin
 */
public abstract class Character implements Serializable
{   
    protected final ArrayList <Image> img;
    protected final Inventory inventory;
    protected int healthMax;
    protected int healthCurrent;
    protected float x;
    protected float y;
    protected float xVel;
    protected float yVel;
    protected final float gravity;
    protected boolean jumpUP;
    protected boolean jumpDOWN;
    protected final float w;
    protected final float h;
    protected float floor;
    protected boolean over;
    protected boolean shotable;
    protected float left;
    protected float right;
    protected int counterShot;
    protected final ArrayList <Shot> shots;
    protected boolean alive;
    
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
        this.w = 50;
        this.h = 70;
        this.floor = Game.getY() - h;
        this.xVel = 2;
        this.yVel = 0;
        this.gravity = 1;
        this.jumpUP = false;
        this.jumpDOWN = false;
        this.over = false;
        this.right = Game.getX() - w;
        this.left = 0;
        this.shotable = true;
        
        
        this.shots = new ArrayList<>();
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
    
    public int getBulletsMax ()
    {
        return inventory.getBulletsMax();
    }
    
    public void draw (Graphics g)
    {
        if (alive)
        {
            img.get(inventory.getCurrent()).draw(x,y,w,h);
        }
        shots.forEach((s)->
        {
            s.getImage().draw(s.getX(),s.getY(),s.getW(),s.getH());
        });
    }
    
    public void move (int control)
    {
        x += xVel*control;
        if (yVel < 50)
            yVel += gravity*control;
        y += yVel*control;
        
        if (jumpUP)
        { 
            if (over)
            {
                if (y >= floor)
                {
                    jumpUP = false;
                    over = false;
                    y = floor;
                    shotable = true;
                    counterShot = 0;
                }  
            }
            else
            {
                over = (y < floor);
            }
        }
        else if(jumpDOWN)
        {
            if (y >= floor)
            {
                jumpDOWN = false;
                y = floor;
                shotable = true;
                counterShot = 0;
            }
        }
        else if (y >= floor)
        {
            y = floor;
        }
        
        if (x <= left)
        {
            this.RIGHT();
        }
        if (x > right)
        {
            this.LEFT();
        }
    }
    
    public boolean UP (float step)
    {
        boolean done = false;
        if (!(jumpUP||jumpDOWN))
        {
            if (floor > step)
                floor -= step;
            yVel = -19;
            y--;
            jumpUP = true;
            done = true;
        }
        return done;
    }
    public boolean DOWN (float step)
    {
        boolean done = false;
        if (!(jumpUP||jumpDOWN))
        {
            if (floor < (Game.getY() - h))
                floor += step;
            yVel = -8;
            y--;
            jumpDOWN = true;
            done = true;
        }
        return done;
    }
    public void LEFT (float left)
    {
        this.left = left;
        xVel = -2;
    }
    public void RIGHT (float right)
    {
        this.right = right;
        xVel = 2;
    }
    public void LEFT ()
    {
        xVel = -2;
    }
    public void RIGHT ()
    {
        xVel = 2;
    }
    
    public void setFloor (float floor)
    {
        if (!(jumpUP||jumpDOWN))
            this.floor = floor;
    }
    public float getFloor ()
    {
        return this.floor ;
    }
    public float getH()
    {
        return h;
    }
    public float getW()
    {
        return w;
    }
    public float getX()
    {
        return x;
    }
    public float getY()
    {
        return y;
    }
    public void setLeft (float left)
    { 
        this.left = left;
    }
    public void setRigth (float right)
    {
        this.right = right;
    }
    
    public void reciveDamage (int damage)
    {
        this.healthCurrent -= damage;
    }
    
    public int getDamage ()
    {
        return inventory.getDamage();
    }
    
    public abstract void die();
    public abstract void SHOT();
    public boolean isAlive()
    {
        return alive;
    }
}
