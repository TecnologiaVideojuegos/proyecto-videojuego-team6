/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shutterearth.map;

import java.util.ArrayList;
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
    private final int x;
    private final int y;
    private final int radix;
    private final int space;
    private int displayingHealth;
    private int actualHealth;
    private int full;
    private int other;
    
    public LiveDisplayer (int x, int y, int radix, int health)
    {
        this.displayingHealth = health;
        this.actualHealth = health;
        this.calculate(health);
        this.x = x;
        this.y = y;
        this.radix = radix;
        this.space = radix/12;
    }
    
    public void setHealth (int health)
    {
        this.actualHealth = health;
    }
    
    private void calculate (int health)
    {
        full = health/10;
        other = (health - full*10)/4;
    }
    
    @Override
    public void Render(GameContainer gc, Graphics g) throws SlickException
    {
        for (int j = 0; j < full; j++)
        {
            Game.getMedia().getImage(Media.FULL_LIVE).draw(x+radix*j+space*j,y,radix,radix);
        }
        switch (other)
        {
            case 1:
                Game.getMedia().getImage(Media.TQUARTERS_LIVE).draw(x+radix*full+space*full,y,radix,radix);
                break;
            case 2:
                Game.getMedia().getImage(Media.HALF_LIVE).draw(x+radix*full+space*full,y,radix/2,radix);
                break;
            case 3:
                Game.getMedia().getImage(Media.QUARTER_LIVE).draw(x+radix*full+space*full,y,radix/2,radix/2);
                break;
        }
    }

    @Override
    public void Update(GameContainer gc, int t) throws SlickException
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
    public void init(GameContainer gc) throws SlickException
    {
        
    } 
}
