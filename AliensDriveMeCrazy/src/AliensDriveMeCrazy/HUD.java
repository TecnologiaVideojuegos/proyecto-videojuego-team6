/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AliensDriveMeCrazy;

import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

/**
 *
 * @author mr.blissfulgrin
 */
public class HUD extends Scene
{
    private int livesMax;
    private int livesCurrent;
    private final ArrayList <Circle> lives;
    
    public HUD(int x, int y, int livesMax)
    {
        this.livesMax = livesMax;
        this.livesCurrent = livesMax;
        this.lives = new ArrayList <>();
        generateLives();
    }

    @Override
    public void Render(GameContainer gc, Graphics g) throws SlickException
    {
        g.setColor(Color.red);
        for (int x = 0; x < livesMax; x++)
        {
            if (x < livesCurrent)
            {
                g.fill(lives.get(x));
            }
            else
            {
                g.draw(lives.get(x));
            }
        }
    }

    @Override
    public void Update(GameContainer gc, int t) throws SlickException
    {
        
    }

    @Override
    public void init(GameContainer gc) throws SlickException
    {
    } 
    
    public void setLivesCurrent (int lives)
    {
        livesCurrent = lives;
        trunkLives();
    }
    
    public void setLivesMax (int lives)
    {
        livesMax = lives;
        generateLives();
    }
    
    private void generateLives ()
    {
        this.lives.clear();
        for (int i = 0; i < livesMax; i++)
        {
            lives.add(new Circle(20+ 40*i, 20 , 11));
        }
    }
     
    private void trunkLives()
    {
        if (livesCurrent > livesMax)
        {
            livesCurrent = livesMax;
        }
        else if (livesCurrent < 0)
        {
            livesCurrent = 0;
        }
    }
}
