/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AliensDriveMeCrazy;

import org.newdawn.slick.Image;

/**
 *
 * @author mr.blissfulgrin
 */
public class ShotBadGuy extends Shot
{
    Hero enemy;
    public ShotBadGuy (Image shot,float x, float y, boolean dir, Hero enemy)
    {
        super (shot,x,y,dir);
        this.enemy = enemy;
    }
    
    @Override
    public boolean hit (Character we)
    {
        boolean result;

        result = (enemy.getX()<super.getX()+super.getW()&&enemy.getX()+enemy.getW()>super.getX()&&enemy.getY()<super.getY()+super.getH()&&enemy.getY()+enemy.getH()>super.getY());
        if (result)
        {
            enemy.reciveDamage(we.getDamage());
            if (enemy.getHealthCurrent()<=0)
            {
                enemy.die();
            }
        }
        return result;
    }
}
