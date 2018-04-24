/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shutterearth.screens;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.command.BasicCommand;
import org.newdawn.slick.command.Command;
import org.newdawn.slick.command.InputProvider;
import org.newdawn.slick.command.InputProviderListener;
import org.newdawn.slick.command.MouseButtonControl;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.TextField;
import shutterearth.Game;

/**
 *
 * @author mr.blissfulgrin
 */
public class Access extends Scene implements InputProviderListener
{
    private TextField user;
    private TextField pswd;
    private final int x;
    private final int y;
    private final int w;
    private final int h;
    private InputProvider provider;
    private final Command click;
    private boolean clicked;
    private int xMouse;
    private int yMouse;
    private Input input;
    private final Rectangle exit;
    private final Rectangle go;
    
    public Access ()
    {
        this.w = Game.getX()/5;
        this.h = Game.getY()/9;
        this.x = Game.getX()/2-w/2;
        this.y = Game.getY()/3;
        exit = new Rectangle (Game.getX()/14,Game.getY()/14,Game.getX()/16,Game.getY()/20);
        go = new Rectangle (x,y+(Game.getY()/14)*4,w,h);
        click = new BasicCommand("click");
        clicked = false;
    }
    
    @Override
    public void Render(GameContainer gc, Graphics g) throws SlickException
    {
        g.setColor(Color.yellow);
        g.fill(exit);
        g.fill(go);
        g.setColor(Color.lightGray);
        user.render(gc, g);
        pswd.render(gc, g);
        user.setBackgroundColor(Color.gray);
        user.setBorderColor(Color.black);
        user.setTextColor(Color.white);
        pswd.setBackgroundColor(Color.gray);
        pswd.setBorderColor(Color.black);
        pswd.setTextColor(Color.white);
    }

    @Override
    public void Update(GameContainer gc, int t) throws SlickException
    {
        if (clicked)
        {
            if (go.contains(xMouse, yMouse))
            {
                
            }
            else if (exit.contains(xMouse, yMouse))
            {
                Game.exit();
            }
            clicked = false;
        }
    }

    @Override
    public void init(GameContainer gc) throws SlickException
    {
        this.user = new TextField(gc, gc.getDefaultFont(), x, y, w, h);
        this.pswd = new TextField(gc, gc.getDefaultFont(), x, y+Game.getY()/14, w, h);
        provider = new InputProvider(gc.getInput());
        provider.addListener(this);
        provider.bindCommand(new MouseButtonControl(0), click);
        input = gc.getInput();  
        this.user = new TextField(gc, gc.getDefaultFont(), x-(Game.getX()/6), y+(Game.getY()/14)*2, w, 25);
        this.pswd = new TextField(gc, gc.getDefaultFont(), x+(Game.getX()/6), y+(Game.getY()/14)*2, w, 25);
    }
    
    @Override
    public void controlPressed(Command cmnd)
    {
        if (cmnd.equals(click))
        {
            xMouse = input.getMouseX();
            yMouse = input.getMouseY();
            clicked = true;
        }
    }

    @Override
    public void controlReleased(Command cmnd){}
}
