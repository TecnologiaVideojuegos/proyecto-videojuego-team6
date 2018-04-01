/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AliensDriveMeCrazy.Characters;

import AliensDriveMeCrazy.Guns.Inventory;
import AliensDriveMeCrazy.Shots.Shot;
import AliensDriveMeCrazy.Shots.ShotHero;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author mr.blissfulgrin
 */
public class Hero extends Character implements Serializable
{
    private ArrayList <BadGuy> enemy;
    private int kills;
    private int stage;
    
    public Hero(Inventory inventory)
    {
        super(new String [] {"./src/img/CHARACTER.png","./src/img/CHARACTER.png"}, inventory, 10, 100,100);
        this.alive = true;
        this.kills = 0;
        this.stage = 0;
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
    }
    
    @Override
    public void SHOT()
    {
        if (!(jumpUP||jumpDOWN) && shotable && inventory.isShotable())
        {
            inventory.shot();
            shots.add(new ShotHero(inventory.getBullets(),x,y+h/4,xVel>0,enemy,this));
            shotable = false;
        }
    }
    
    @Override
    public void die()
    {
        alive = false;
    }
    
    public void addKill ()
    {
        kills ++;
    }
    
    public int getKills()
    {
        return kills;
    }
    
    public int getStage()
    {
        return stage;
    }
    
    public void nextStage()
    {
        this.stage++;
    }
}
