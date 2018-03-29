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
    
    private final float xWearpon;
    private final float yWearpon;
    private final float wWearpon;
    private final float hWearpon;
    private final float xBullets;
    private final float yBullets;
    private final float wBullets;
    private final float hBullets;
    private final float xText;
    private final float yText;
    
    public HUD(Character character)
    {
        this.xWearpon = 1560;
        this.yWearpon = 20;
        this.wWearpon = 100;
        this.hWearpon = 100;
        this.wBullets = 30;
        this.hBullets = 30;
        this.xBullets = 1576;
        this.yBullets = 20 + hWearpon + 5;
        this.xText = 1630;
        this.yText = yBullets + 7;
                
        this.character = character;
        this.lives = new ArrayList <>();
        generateLives();
    }

    @Override
    public void Render(GameContainer gc, Graphics g) throws SlickException
    {
        g.setColor(Color.red);
        for (int x = 0; x < character.getHealthMax(); x++)
        {
            if (x < character.getHealthCurrent())
            {
                g.fill(lives.get(x));
            }
            else
            {
                g.draw(lives.get(x));
            }
        }
        character.getWearpon().draw(xWearpon,yWearpon,wWearpon,hWearpon);
        character.getBullets().draw(xBullets,yBullets,wBullets,hBullets);
        g.setColor(Color.yellow);
        g.drawString(String.valueOf(character.getBulletsAmount()), xText, yText);
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
        for (int i = 0; i < character.getHealthMax(); i++)
        {
            lives.add(new Circle(20+ 40*i, 20 , 11));
        }
    }
}
