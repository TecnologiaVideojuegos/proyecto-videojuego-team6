/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AliensDriveMeCrazy;

import java.util.ArrayList;

/**
 *
 * @author mr.blissfulgrin
 */
public class BadGuy extends Character
{
    private Hero enemy;
    
    public BadGuy(String[] img, Inventory inventory, int healthMax, int x, int y)
    {
        super(img, inventory, healthMax, x, y);
    }
    
    public void IA (int t, float x, float y, float step)
    {
        move(t);
        shot(t);
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
            boolean hit = s.hit(this);
            if ((s.getX() > right)||hit)
                toRemove.add(s);
            else if ((s.getX() < left)||hit)
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
     
    public void SHOT()
    {
        if (!(jumpUP||jumpDOWN) && shotable && inventory.isShotable())
        {
            inventory.shot();
            shots.add(new ShotBadGuy(inventory.getBullets(),x,y+h/4,xVel>0,enemy));
            shotable = false;
        }
    }
}
