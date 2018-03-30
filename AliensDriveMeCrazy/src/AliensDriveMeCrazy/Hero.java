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
public class Hero extends Character
{
    private boolean alive;
    private ArrayList <BadGuy> enemy;
    public Hero(Inventory inventory)
    {
        super(new String [] {"./src/img/CHARACTER.png","./src/img/CHARACTER.png"}, inventory, 3, 100,100);
        this.alive = true;
    }
    
    public void setEnemy (ArrayList <BadGuy> enemy)
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
            shots.add(new ShotHero(inventory.getBullets(),x,y+h/4,xVel>0,enemy));
            shotable = false;
        }
    }
    
    public void die()
    {
        alive = false;
    }
    
    public boolean isAlive ()
    {
        return alive;
    }
}
