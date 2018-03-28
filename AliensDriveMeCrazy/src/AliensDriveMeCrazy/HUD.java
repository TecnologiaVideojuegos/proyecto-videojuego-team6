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
    private final Character character;
    private final ArrayList <Circle> lives;
    
    public HUD(int x, int y, Character character)
    {
        this.character = character;
        this.lives = new ArrayList <>();
        generateLives();
    }

    @Override
    public void Render(GameContainer gc, Graphics g) throws SlickException
    {
        g.setColor(Color.red);
        for (int x = 0; x < character.healthMax; x++)
        {
            if (x < character.healthCurrent)
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
    
    
    private void generateLives ()
    {
        this.lives.clear();
        for (int i = 0; i < character.healthMax; i++)
        {
            lives.add(new Circle(20+ 40*i, 20 , 11));
        }
    }
}
