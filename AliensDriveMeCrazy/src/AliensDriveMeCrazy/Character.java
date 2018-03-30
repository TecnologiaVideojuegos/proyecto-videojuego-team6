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
    private class Shot
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
        protected float getW()
        {
            return w;
        }
        protected float getH()
        {
            return h;
        }
        protected Image getImage()
        {
            return shot;
        }
        protected float getY ()
        {
            return y;
        }
        protected void update ( float control)
        {
            this.x = x + vel*control;
        }
        protected float getX()
        {
            return x;
        }
    }
    
    private final ArrayList <Image> img;
    private final Inventory inventory;
    private int healthMax;
    private int healthCurrent;
    private float x;
    private float y;
    private float xVel;
    private float yVel;
    private final float gravity;
    private boolean jumpUP;
    private boolean jumpDOWN;
    private final float w;
    private final float h;
    private float floor;
    private boolean over;
    private boolean shotable;
    private float left;
    private float right;
    private int counter;
    private final ArrayList <Shot> shots;
    
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
    
    public void draw ()
    {
        img.get(inventory.getCurrent()).draw(x,y,w,h);
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
    
    public void shot(int control)
    {
        ArrayList <Shot> toRemove = new ArrayList <>();
        shots.stream().map((s) ->
        {
            s.update(control);
            return s;
        }).forEachOrdered((s) ->
        {
            if (s.getX() > right)
                toRemove.add(s);
            else if ((s.getX() < left))
                toRemove.add(s);
        });
        toRemove.forEach((s) ->
        {
            shots.remove(s);
        });
        counter++;
        if (counter*control > inventory.getShotSpeed())
        {
            shotable = true;
            counter = 0;
        }
    }
    
    public void UP (float step)
    {
        if (!(jumpUP||jumpDOWN))
        {
            if (floor > step)
                floor -= step;
            yVel = -19;
            y--;
            jumpUP = true;
        }
    }
    public void DOWN (float step)
    {
        if (!(jumpUP||jumpDOWN))
        {
            if (floor < (Game.getY() - h))
                floor += step;
            yVel = -8;
            y--;
            jumpDOWN = true;
        }
    }
    public void LEFT ()
    {
        xVel = -2;
    }
    public void RIGHT ()
    {
        xVel = 2;
    }
    
    public void SHOT()
    {
        if (!(jumpUP||jumpDOWN) && shotable)
        {
            inventory.shot();
            shots.add(new Shot(inventory.getBullets(),x,y+h/4,xVel>0));
            shotable = false;
        }
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
    public void setLeft (float left)
    { 
        this.left = left;
    }
    public void setRigth (float right)
    {
        this.right = right;
    }
}
