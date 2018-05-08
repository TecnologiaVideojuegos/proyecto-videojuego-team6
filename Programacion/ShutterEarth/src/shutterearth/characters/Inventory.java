/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shutterearth.characters;

import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import shutterearth.Game;
import shutterearth.screens.Scene;

/**
 *
 * @author mr.blissfulgrin
 */
public class Inventory extends Scene
{
    private final int maxGun;
    private final ArrayList <Gun> inventory;
    private int index;
    private final ArrayList <Shot> shots;
    private final Hero hero;
    
    public Inventory (ArrayList <int[]> guns, Hero hero)
    {
        Game.addScene(this);
        this.hero = hero;
        this.shots = new ArrayList <>();
        maxGun = guns.size() -1;
        index = 0;
        this.inventory = new ArrayList<>();
        guns.forEach((gun) ->
        {
            inventory.add(new Gun(gun[0],gun[1],0));
        });
    }
    
    public void rightGun()
    {
        if (index < maxGun)
            index++;
    }
    public void lefttGun()
    {
        if (index < maxGun)
            index--;
    }
    public int getGunID()
    {
        return index;
    }
    public void shot()
    {
        shots.add(new Shot (inventory.get(index),hero));
    }
    public void end ()
    {
        Game.removeSence(this);
    }

    @Override
    public void Render(GameContainer gc, Graphics g) throws SlickException
    {
        shots.forEach((s) ->
        {
            //if(s.isDwable())
                //DRAW
        });
    }

    @Override
    public void Update(GameContainer gc, int t) throws SlickException
    {
        shots.forEach((s) ->
        {
            s.update(t);
        });
        shots.forEach((s) ->
        {
            //HIT OVER ENEMIES
        });
    }

    @Override
    public void init(GameContainer gc) throws SlickException
    {
        
    }
    
    public ArrayList<int[]> save()
    {
        ArrayList <int[]> data = new ArrayList<>();
        inventory.forEach((gun)->
        {
            data.add(new int[]{gun.getID(),gun.getLevel()});
        });
        return data;
    }
    public int getNumberOfGuns()
    {
        return maxGun;
    }
}
