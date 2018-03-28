/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AliensDriveMeCrazy;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author mr.blissfulgrin
 */
public abstract class Wearpon extends Scene implements WearponInterface
{
    private final int identifer;
    private Image img;
    private Image bullet;
    private final int damage;
    private int bullets;
    private final int x;
    private final int y;
    private final int w;
    private final int h;
    private final boolean infiniteBullets;

    public Wearpon(String source, int damage, int bullets, String bullet, boolean infiniteBullets, int identifer)
    {
        this.x = 1560;
        this.y = 20;
        this.w = 100;
        this.h = 100;
        
        this.identifer = identifer;
        this.damage = damage;
        this.bullets = bullets;
        this.infiniteBullets = infiniteBullets;
        try
        {
            this.img = new Image(source);
            this.bullet = new Image(bullet);
        } 
        catch (SlickException e)
        {
            System.out.println("ERROR WEARPON LOADING IMG");
        }
    }

    @Override
    public void Render(GameContainer gc, Graphics g) throws SlickException
    {
        img.draw(x,y,w,h);
        bullet.draw(x+(w/6), y+h+3, 30,30);
        g.setColor(Color.yellow);
        g.drawString(String.valueOf(bullets), x+(w/6)*4, y+h+10);
    }

    @Override
    public void Update(GameContainer gc, int t) throws SlickException
    {
        
    }

    @Override
    public void init(GameContainer gc) throws SlickException
    {
        
    }
    
    @Override
    public boolean isShotable()
    {
        return (bullets > 0)||infiniteBullets;
    }
    
    @Override
    public int shot()
    {
        bullets --;
        return ((bullets > 0)||infiniteBullets)? damage:0;
    }
}
