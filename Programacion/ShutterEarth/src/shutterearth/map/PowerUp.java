/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shutterearth.map;

import org.newdawn.slick.Color;
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
public class PowerUp extends Scene implements Deployable
{
    private Rectangle pwup;
    private boolean go;
    private Circle [] circles;
    private final int power;
    
    public PowerUp (int n)
    {
        this.power = n;
        pwup = null;
        go = false;
    }
    
    @Override
    public void setRect (Rectangle rect)
    {
        if (this.pwup == null)
        {
            this.pwup = rect;
            Game.addScene(this);
            go = true;
        }
        this.circles = new Circle[3];
        for (int x = 0; x < circles.length; x++)
        {
            circles[x] = new Circle (0,0,Game.getY()/(x+3));
            circles[x].setCenterX(rect.getCenterX());
            circles[x].setCenterY(rect.getCenterY());
        }
    }
    @Override
    public Rectangle getRect ()
    {
        return pwup;
    }
    
    public boolean available ()
    {
        return pwup!=null;
    }
    
    public int getPower ()
    {
        return power;
    }
    
    @Override
    public void Render(GameContainer gc, Graphics g) throws SlickException
    {
        if (power > 2)
            g.setColor(Color.magenta);
        else 
            g.setColor(Color.green);
        g.setLineWidth(3);
        if (go)
        {
            for (Circle circle : circles)
            {
                g.draw(circle);
            }
        } 
        g.setLineWidth(1);
        g.setColor(Color.yellow);
        if (power > 2)
            Game.getMedia().getImage(Media.IMAGE.PWUP2).draw(pwup.getX(),pwup.getY(),pwup.getHeight(),pwup.getWidth());
        else 
            Game.getMedia().getImage(Media.IMAGE.PWUP).draw(pwup.getX(),pwup.getY(),pwup.getHeight(),pwup.getWidth());
    }

    @Override
    public void Update(GameContainer gc, float t) throws SlickException
    {
        for (Circle circle : circles)
        {
            if (circle.getRadius()>0)
            {
                circle.setRadius(circle.getRadius() - 1*t);
                circle.setCenterX(pwup.getCenterX());
                circle.setCenterY(pwup.getCenterY());
            }
        }
    }

    @Override
    public void init(GameContainer gc) throws SlickException{}

    @Override
    public String toString()
    {
        return "Power UP";
    }
    @Override
    public void exit ()
    {
        Game.removeSence(this);
        pwup = null;
    }
}
