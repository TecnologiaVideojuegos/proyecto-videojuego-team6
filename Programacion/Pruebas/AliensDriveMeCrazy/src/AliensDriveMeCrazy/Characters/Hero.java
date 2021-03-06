/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AliensDriveMeCrazy.Characters;

import AliensDriveMeCrazy.Guns.Inventory;
import AliensDriveMeCrazy.Shots.Shot;
import AliensDriveMeCrazy.Shots.ShotHero;
import java.util.ArrayList;
import org.newdawn.slick.Image;

/**
 *
 * @author mr.blissfulgrin
 */
public class Hero extends Character
{
    private final int MAX_LIFE = 10;
    private ArrayList <BadGuy> enemy;
    private int kills;
    private int stage;
    private int money;
    
    public Hero(Inventory inventory)
    {
        super(new String [] {"./src/img/HERO_DER.png","./src/img/HERO_IZQ.png"}, inventory, 3, 100,100);
        this.alive = true;
        this.kills = 0;
        this.stage = 0;
        this.money = 0;
    }
    public Hero (SavedHero hero)
    {
        super(hero.getImg(), new Inventory(hero.getSavedInventory()), hero.getHeathMax(), 100,100);
        this.alive = true;
        this.kills = hero.getKills();
        this.stage = hero.getStage();
        this.money = hero.getMoney();
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
    public void addMoney (int money)
    {
        this.money += money;
    }
    public int getMoney()
    {
        return money;
    }
    
    public SavedHero save ()
    {
        String [] savedImg = new String[img.size()];
        for (int i = 0; i < img.size(); i++)
        {
            savedImg[i] = img.get(i).getResourceReference();
        }
        return new SavedHero (kills, stage, money, healthMax, inventory.save(), savedImg);
    }
    
    public void goLeftWearpon ()
    {
        inventory.goLeft();
    }
    public void goRightWearpon ()
    {
        inventory.goRight();
    }
    public Image getImg ()
    {
        return img.get(inventory.getCurrent());
    }
    public int getMAX_LIVE ()
    {
        return MAX_LIFE;
    }

    public void refill(int wearpon)
    {
        inventory.refill(wearpon);
    }

    public void setAmountMax(int amount)
    {
        inventory.setAmountMax(amount);
    }

    public int getShotSpeed()
    {
        return inventory.getShotSpeed();
    }

    public int getMAX_BULLETS()
    {
        return inventory.getMAX_BULLETS();
    }
    
    public int numberWearpons ()
    {
        return inventory.numberWearpons();
    }
    
    public Image showWearpon (int x)
    {
        return inventory.showWearpon(x);
    }
    public Image showBullets (int x)
    {
        return inventory.showBullets(x);
    }
}
