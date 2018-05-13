/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shutterearth.characters;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import shutterearth.Game;
import shutterearth.Media;

/**
 *
 * @author mr.blissfulgrin
 */
public class Enemy extends CharactX
{
    public Enemy ()
    {
        h = Game.getY()/10;
        w = (h/10)*9;
        animationTime = 40;
    }
    
    @Override
    public void Render(GameContainer gc, Graphics g) throws SlickException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Update(GameContainer gc, float t) throws SlickException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void init(GameContainer gc) throws SlickException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void goUp()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void goLeft()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void goRight()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void goDown()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void shot()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void place(float floor, int left, int right)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setBounds(float left, float right, float floor)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     @Override
    protected void setX(float x)
    {
        this.xPos = x;
        box.setX(x);
    }
    @Override
    protected void setY(float y)
    {
        this.yPos = y;
        box.setY(y);
        line.setY(y);
        line.setHeight(floor+h-y);
    }

    @Override
    public int getInfo()
    {
        return 2;
    }
    
    @Override
    public void doShotAnimation()
    {
        Game.getMedia().getSound(Media.SOUND.FIRE_ALIEN).play();
        animation = true;
    }
}
