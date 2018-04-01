/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AliensDriveMeCrazy.Shots;

import AliensDriveMeCrazy.Characters.BadGuy;
import AliensDriveMeCrazy.Characters.Hero;
import java.io.Serializable;
import java.util.ArrayList;
import org.newdawn.slick.Image;

/**
 *
 * @author mr.blissfulgrin
 */
public class ShotHero extends Shot implements Serializable
{
    private final ArrayList <BadGuy> enemy;
    private final Hero we;
    public ShotHero (Image shot,float x, float y, boolean dir, ArrayList <BadGuy> enemy, Hero we)
    {
        super (shot,x,y,dir);
        this.enemy = enemy;
        this.we = we;
    }
    
    @Override
    public boolean hit ()
    {
        //sacar to eliminate al exterior y que no se elimine nadie hasta que sus balas no desaparezcan, solo no se mueve ni dispara ni se dibuja
        boolean result = false;
        int counter = 0;
        while (!result && counter<enemy.size())
        {
            result = (enemy.get(counter).getX()<super.getX()+super.getW()&&enemy.get(counter).getX()+enemy.get(counter).getW()>super.getX()&&enemy.get(counter).getY()<super.getY()+super.getH()&&enemy.get(counter).getY()+enemy.get(counter).getH()>super.getY());
            if (result)
            {
                enemy.get(counter).reciveDamage(we.getDamage());
                if (enemy.get(counter).getHealthCurrent()<=0)
                {
                    we.addKill();
                    enemy.get(counter).die();
                }
            }
            counter++;
        }
        return result;
    }
}
