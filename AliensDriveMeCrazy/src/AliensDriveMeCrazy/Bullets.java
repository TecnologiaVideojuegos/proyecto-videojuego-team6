/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AliensDriveMeCrazy;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author mr.blissfulgrin
 */
public class Bullets extends Scene
{
    private final int identifer;
    private final int amount;
    private Image img;
    private int x;
    private int y;
    
    public Bullets (int identifer, int amount, String source, int x, int y)
    {
        this.identifer = identifer;
        this.amount = amount;
        this.x = x;
        this.y = y;

        try
        {
            this.img = new Image(source);
        } 
        catch (SlickException ex)
        {
            System.out.println("ERROR DE IMAGEN BULLETS");
        }
    }
    
    public int recollect ()
    {
        return amount;
    }

    @Override
    public void Render(GameContainer gc, Graphics g) throws SlickException
    {
        img.draw(x,y,20,20);
    }

    @Override
    public void Update(GameContainer gc, int t) throws SlickException
    {
    }

    @Override
    public void init(GameContainer gc) throws SlickException
    {
    }
}
