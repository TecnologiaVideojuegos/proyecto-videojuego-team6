/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AliensDriveMeCrazy.Characters;

import AliensDriveMeCrazy.Game;
import AliensDriveMeCrazy.Guns.Inventory;
import AliensDriveMeCrazy.Images;
import AliensDriveMeCrazy.Shots.Shot;
import AliensDriveMeCrazy.Shots.ShotBadGuy;
import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 *
 * @author mr.blissfulgrin
 */
public class BadGuy extends Character
{
    private Hero enemy;
    private final ArrayList <Hited> hited;
    
    public BadGuy(String[] img, Inventory inventory, int healthMax, int x, int y)
    {
        super(img, inventory, healthMax, x, y);
        alive = true;
        this.hited = new ArrayList<>();
    }
    
    @Override
    public void draw (Graphics g)
    {
        if (alive)
        {
            Game.getImages().getSprit(xVel>0? ((y == floor)?(healthMax==4?Images.BASE_DER:Images.FUERTE_DER):(healthMax==4?Images.BASE_SDE:Images.FUERTE_SDE)):((y == floor)?(healthMax==4?Images.BASE_IZQ:Images.FUERTE_IZQ):(healthMax==4?Images.BASE_SIZ:Images.FUERTE_SIZ))).draw(x,y,w,h);
        }
        shots.forEach((s)->
        {
            s.getImage().draw(s.getX(),s.getY(),s.getW(),s.getH());
        });
        hited.forEach((hit)->
        {
            g.setColor(Color.yellow);
            g.drawString(hit.getLabel(), hit.getX(), hit.getY());
        });
    }
    
    public void IA (int t, float x, float y, float step)
    {  
        shot(t);
        if (alive)
        {
            move(t);
            if (this.y == y)
            {
                if (this.x < x)
                {
                    this.RIGHT();
                }
                else
                {
                    this.LEFT();
                }
                this.SHOT();
            }
            else
            {
                switch((int)(Math.random()*20))
                {
                    case 0:
                        this.UP(step);
                        break;
                    case 1:
                        this.DOWN(step);
                        break;
                }
            }
        }
    }
    
    public void setEnemy (Hero enemy)
    {
        this.enemy = enemy;
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
            boolean hit = s.hit();
            if ((s.getX() > right)||hit)
                toRemove.add(s);
            else if ((s.getX() < left)||hit)
                toRemove.add(s);
        });
        toRemove.forEach((s) ->
        {
            shots.remove(s);
        });
        counterShot++;
        if (counterShot*control > inventory.getShotSpeed())
        {
            shotable = true;
            counterShot = 0;
        }
        updateHits(control);
    }
     
    @Override
    public void SHOT()
    {
        if (!(jumpUP||jumpDOWN) && shotable && inventory.isShotable()) 
        {
            inventory.shot();
            shots.add(new ShotBadGuy(inventory.getBullets(),x,y+h/4,xVel>0,enemy,this));
            shotable = false;
        }
    }
    
    @Override 
    public void die ()
    {
        alive = false;
    }
    
    public boolean isRemovable ()
    {
        return !(alive || !shots.isEmpty() || !hited.isEmpty());
    }
    
    @Override
    public void reciveDamage (int damage)
    {
        this.healthCurrent -= damage;
        hited.add(new Hited(x,y,xVel,healthCurrent));
    }
    
    private void updateHits (int control)
    {
        ArrayList <Hited> toRemoveH = new ArrayList <>();
        hited.forEach((hit) ->
        {
            hit.update(control);
            if (!hit.isAlive())
                toRemoveH.add(hit);
        });
        toRemoveH.forEach((s) ->
        {
            hited.remove(s);
        });
    }
}
