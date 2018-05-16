/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shutterearth.map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
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
    private boolean go;
    private Circle [] circles;
    
    public BB ()
    {
        bb = null;
        go = false;
    }
    
    public void setBB (Rectangle bb)
    {
        if (this.bb == null)
        {
            this.bb = bb;
            Game.addScene(this);
            go = true;
        }
        this.circles = new Circle[4];
        for (int x = 0; x < circles.length; x++)
        {
            circles[x] = new Circle (0,0,Game.getY()/(x+2));
            circles[x].setCenterX(bb.getCenterX());
            circles[x].setCenterY(bb.getCenterY());
        }
    }
    public Rectangle getBB ()
    {
        return bb;
    }
    
    @Override
    public void Render(GameContainer gc, Graphics g) throws SlickException
    {
        g.setLineWidth(5);
        if (go)
        {
            for (Circle circle : circles)
            {
                g.draw(circle);
            }
        } 
        g.setLineWidth(1);
        Game.getMedia().getImage(Media.IMAGE.BB).draw(bb.getX(),bb.getY(),bb.getHeight(),bb.getWidth());
    }

    @Override
    public void Update(GameContainer gc, float t) throws SlickException
    {
        for (Circle circle : circles)
        {
            if (circle.getRadius()>0)
            {
                circle.setRadius(circle.getRadius() - 1*t);
                circle.setCenterX(bb.getCenterX());
                circle.setCenterY(bb.getCenterY());
            }
        }
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
