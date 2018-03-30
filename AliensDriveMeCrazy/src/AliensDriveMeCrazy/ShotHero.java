/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AliensDriveMeCrazy;

import java.util.ArrayList;
import org.newdawn.slick.Image;

/**
 *
 * @author mr.blissfulgrin
 */
public class ShotHero extends Shot
{
    ArrayList <BadGuy> enemy;
    public ShotHero (Image shot,float x, float y, boolean dir, ArrayList <BadGuy> enemy)
    {
        super (shot,x,y,dir);
        this.enemy = enemy;
    }
    
    @Override
    public boolean hit (Character we)
    {
        boolean result = false;
        BadGuy e;
        int counter = 0;
        ArrayList <BadGuy> toEliminate = new ArrayList <>();
        while (!result && counter<enemy.size())
        {
            e = enemy.get(counter);
            result = (e.getX()<super.getX()+super.getW()&&e.getX()+e.getW()>super.getX()&&e.getY()<super.getY()+super.getH()&&e.getY()+e.getH()>super.getY());
            if (result)
            {
                e.reciveDamage(we.getDamage());
                if (e.getHealthCurrent()<=0)
                {
                    toEliminate.add(e);
                }
            }
            counter++;
        }
        toEliminate.forEach((c) ->
        {
            enemy.remove(c);
        });
        return result;
    }
}
