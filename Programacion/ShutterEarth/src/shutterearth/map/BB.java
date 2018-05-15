/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shutterearth.map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import shutterearth.Game;
import shutterearth.Media;
import shutterearth.screens.Scene;

/**
 *
 * @author mr.blissfulgrin
 */
public class BB extends Scene
{
    private Rectangle bb;
    
    public BB ()
    {
        bb = null;
    }
    
    public void setBB (Rectangle bb)
    {
        if (this.bb == null)
        {
            this.bb = bb;
            Game.addScene(this);
        }
    }
    public Rectangle getBB ()
    {
        return bb;
    }
    
    @Override
    public void Render(GameContainer gc, Graphics g) throws SlickException
    {
        Game.getMedia().getImage(Media.IMAGE.BB).draw(bb.getX(),bb.getY(),bb.getHeight(),bb.getWidth());
    }

    @Override
    public void Update(GameContainer gc, float t) throws SlickException
    {
    }

    @Override
    public void init(GameContainer gc) throws SlickException{}

    @Override
    public String toString()
    {
        return "BB";
    }
    public void exit ()
    {
        Game.removeSence(this);
    }
}
