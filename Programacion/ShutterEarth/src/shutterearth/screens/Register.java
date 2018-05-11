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
import org.newdawn.slick.command.KeyControl;
import org.newdawn.slick.command.MouseButtonControl;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.TextField;
import shutterearth.Game;
import shutterearth.Media;
import shutterearth.characters.SavedHero;

/**
 *
 * @author mr.blissfulgrin
 */
public class Register extends Scene implements InputProviderListener
{
    private TextField newUser;
    private TextField newPswd;
    private final int x;
    private final int y;
    private final int w;
    private final int h;
    private final int ry;
    private final int rx;
    private InputProvider provider;
    private final Command click;
    private final Command tab;
    private final Command any;
    private boolean clicked;
    private int xMouse;
    private int yMouse;
    private Input input;
    private final Rectangle exit;
    private final Rectangle go;
    private Boolean ok;
    private boolean focus;
    
    public Register ()
    {
        this.w = Game.getX()/5;
        this.h = Game.getY()/9;
        this.x = Game.getX()/2-w/2;
        this.y = Game.getY()/3;
        this.rx = (Game.getX()/6);
        this.ry = y+(Game.getY()/14)*2;
        exit = new Rectangle (Game.getX()/14,Game.getY()/14,Game.getX()/16,Game.getY()/20);
        go = new Rectangle (x,y+(Game.getY()/14)*4,w,h);
        click = new BasicCommand("click");
        tab = new BasicCommand("tab");
        any = new BasicCommand("any");
        clicked = false;
        ok = null;
        focus = true;
    }
    
    @Override
    public void Render(GameContainer gc, Graphics g) throws SlickException
    {
        Game.getMedia().getImage(Media.MENU).draw(0, 0, Game.getX(), Game.getY());
        g.setColor(Color.yellow);
        g.fill(exit);
        Game.getMedia().getImage(Media.BACK).draw(exit.getX(),exit.getY(),exit.getWidth(),exit.getHeight());
        g.fill(go);
        Game.getMedia().getImage(Media.REGISTER).draw(go.getX(),go.getY(),go.getWidth(),go.getHeight());
        g.setColor(Color.lightGray);
        newUser.render(gc, g);
        newPswd.render(gc, g);
        g.setColor(Color.yellow);
        g.drawString("User: ",x-rx,ry-20);
        g.drawString("Password: ",x+rx,ry-20);
        if (ok != null)
        {
            if (ok)
            {
                g.drawString("USER CREATED",x+(Game.getX()/15),y+(Game.getY()/5));
            }
            else
            {
                g.drawString("USER ALREADY IN USE",x+(Game.getX()/20),y+(Game.getY()/5));
            }
        }
    }

    @Override
    public void Update(GameContainer gc, int t) throws SlickException
    {
        if (clicked)
        {
            if (go.contains(xMouse, yMouse))
            {
                Game.getMedia().getSound(Media.SHOT).play();
                if (newUser.getText().length()>5 && newPswd.getText().length()>5)
                {
                    SavedHero hero = new SavedHero (newUser.getText(),newPswd.getText(),false);
                    ok = Game.add(hero);
                }
                else
                {
                    ok = false;
                }
                newUser.setText("");
                newPswd.setText("");
            }
            else if (exit.contains(xMouse, yMouse))
            {
                Game.getMedia().getSound(Media.SHOT).play();
                Game.removeSence(this);
                newUser.deactivate();
                newPswd.deactivate();
            }
            clicked = false;
        }
    }

    @Override
    public void init(GameContainer gc) throws SlickException
    {
        provider = new InputProvider(gc.getInput());
        provider.addListener(this);
        provider.bindCommand(new MouseButtonControl(0), click);
        provider.bindCommand(new KeyControl(Input.KEY_TAB), tab);
        for (int x = 30; x < 45; x++)
        {
            provider.bindCommand(new KeyControl(x), any);
        }
        input = gc.getInput();  
        this.newUser = new TextField(gc, gc.getDefaultFont(), x-rx, ry, w, 23);
        this.newPswd = new TextField(gc, gc.getDefaultFont(), x+rx, ry, w, 23);
        newUser.setText("");
        newPswd.setText("");
        newPswd.setAcceptingInput(true);
        newUser.setAcceptingInput(true);
        newPswd.setFocus(true);
        newUser.setFocus(true);
        newUser.setConsumeEvents(true);
        newPswd.setConsumeEvents(true);
        newUser.setBackgroundColor(Color.gray);
        newUser.setBorderColor(Color.black);
        newUser.setTextColor(Color.white);
        newPswd.setBackgroundColor(Color.gray);
        newPswd.setBorderColor(Color.black);
        newPswd.setTextColor(Color.white);
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
        else if (cmnd.equals(tab))
        {
            focus = !focus;
        }
        newUser.setFocus(focus);
        newPswd.setFocus(!focus);
    }

    @Override
    public void controlReleased(Command cmnd){}
    
}
