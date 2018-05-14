/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shutterearth.map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import shutterearth.Game;
import shutterearth.Media;
import shutterearth.screens.Scene;

/**
 *
 * @author mr.blissfulgrin
 */
public class LiveDisplayer extends Scene
{
    private int x;
    private final int y;
    private final int radix;
    private final int space;
    private int displayingHealth;
    private int actualHealth;
    private int full;
    private int other;
    private final int maxRow;
    
    public LiveDisplayer (int x, int y, int radix, int maxRow, int health)
    {
        this.displayingHealth = health;
        this.actualHealth = health;
        this.calculate(health);
        this.x = x;
        this.y = y;
        this.radix = radix;
        this.space = radix/12;
        this.maxRow = maxRow;
    }
    
    public void setHealth (int health, boolean animation)
    {
        this.actualHealth = health;
        if (!animation)
        {
            this.displayingHealth = health;
        }
    }
    
    private void calculate (int health)
    {
        full = health/10;
        other = (health - full*10);
    }
    
    public void center ()
    {
        x = Game.getX()/2 - (radix*((full>maxRow?maxRow:full)+(other<2?0:1))+space*(full>maxRow?maxRow:full))/2;
    }
    
    @Override
    public void Render(GameContainer gc, Graphics g) throws SlickException
    {
        for (int j = 0; j < full; j++)
        {
            Game.getMedia().getImage(Media.IMAGE.FULL_LIVE).draw(x+(radix*(j%maxRow))+(space*(j%maxRow)),y+(radix*(j/maxRow))+(space*(j/maxRow)),radix,radix);
        }
        if (other > 6)
        {
            Game.getMedia().getImage(Media.IMAGE.TQUARTERS_LIVE).draw(x+(radix*(full%maxRow)+space*(full%maxRow)),y+(radix*(full/maxRow)+space*(full/maxRow)),radix,radix);
        }
        else if (other > 4)
        {
            Game.getMedia().getImage(Media.IMAGE.HALF_LIVE).draw(x+(radix*(full%maxRow)+space*(full%maxRow)),y+(radix*(full/maxRow)+space*(full/maxRow)),radix/2,radix);
        }
        else if (other > 1)
        {
            Game.getMedia().getImage(Media.IMAGE.QUARTER_LIVE).draw(x+radix*(full%maxRow)+space*(full%maxRow),y+(radix*(full/maxRow)+space*(full/maxRow)),radix/2,radix/2);
        }
    }

    @Override
    public void Update(GameContainer gc, float t) throws SlickException
    {
        if (displayingHealth < actualHealth)
        {
            displayingHealth ++;
            calculate(displayingHealth);
        }
        else if (displayingHealth > actualHealth)
        {
            displayingHealth --;
            calculate(displayingHealth);
        }
    }

    @Override
    public void init(GameContainer gc) throws SlickException{} 
    
    @Override
    public String toString()
    {
        return "LiveDisplayer "+this.displayingHealth+" "+this.actualHealth;
    }
}
