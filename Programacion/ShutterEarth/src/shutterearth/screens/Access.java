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
public class Access extends Scene implements InputProviderListener
{
    private TextField user;
    private TextField pswd;
    private final int x;
    private final int y;
    private final int w;
    private final int h;
    private final int ry;
    private final int rx;
    private final int tx;
    private final int ty;
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
    private final Rectangle register;
    private String pass;
    private boolean focus;
    private Boolean ok;
    
    public Access ()
    {
        this.w = Game.getX()/5;
        this.h = Game.getY()/9;
        this.x = Game.getX()/2-w/2;
        this.y = Game.getY()/3;
        this.rx = (Game.getX()/6);
        this.ry = y+(Game.getY()/14)*2;
        this.tx = x+(Game.getX()/15);
        this.ty = (Game.getY()/5);
        exit = new Rectangle (Game.getX()/14,Game.getY()/14,Game.getX()/16,Game.getY()/20);
        register = new Rectangle (Game.getX()-Game.getX()/14-Game.getX()/16,Game.getY()/14,Game.getX()/16,Game.getY()/20);
        go = new Rectangle (x,y+(Game.getY()/14)*4,w,h);
        click = new BasicCommand("click");
        tab = new BasicCommand("tab");
        any = new BasicCommand("any");
        clicked = false;
        this.pass = "";
        focus = true;
        ok = null;
    }
    
    @Override
    public void Render(GameContainer gc, Graphics g) throws SlickException
    {
        Game.getMedia().getImage(Media.IMAGE.MENU).draw(0, 0, Game.getX(), Game.getY());
        g.setColor(Color.yellow);
        g.fill(exit);
        Game.getMedia().getImage(Media.IMAGE.EXIT).draw(exit.getX(),exit.getY(),exit.getWidth(),exit.getHeight());
        g.fill(go);
        Game.getMedia().getImage(Media.IMAGE.LOG_IN).draw(go.getX(),go.getY(),go.getWidth(),go.getHeight());
        g.fill(register);
        Game.getMedia().getImage(Media.IMAGE.NEW).draw(register.getX(),register.getY(),register.getWidth(),register.getHeight());
        g.setColor(Color.lightGray);
        user.render(gc, g);
        pswd.render(gc, g);       
        g.setColor(Color.yellow);
        g.drawString("User: ",x-rx,ry-20);
        g.drawString("Password: ",x+rx,ry-20);
        if (ok != null)
        {
            g.drawString("WRONG INPUT",tx,y+ty);
        }
    }

    @Override
    public void Update(GameContainer gc, float t) throws SlickException
    {
        if (clicked)
        {
            if (go.contains(xMouse, yMouse))
            {
                SavedHero hero = Game.load(user.getText(), pass);
                if(hero != null)
                {
                    Game.getMedia().getSound(Media.SOUND.SHOT).play();
                    StartMenu startMenu = new StartMenu(hero);
                    Game.addScene(startMenu);
                    Game.removeSence(this);
                }
                else
                {
                    Game.getMedia().getSound(Media.SOUND.BAD).play();
                    user.setText("");
                    pswd.setText("");
                    ok = false;
                }
            }
            else if (register.contains(xMouse, yMouse))
            {
                Game.getMedia().getSound(Media.SOUND.SHOT).play();
                Game.addScene(new Register());
                Game.removeSence(this);
            }
            else if (exit.contains(xMouse, yMouse))
            {
                Game.getMedia().getSound(Media.SOUND.SHOT).play();
                Game.exit();
                user.deactivate();
                pswd.deactivate();
            }
            clicked = false;
        }

        if (pass.length() < pswd.getText().length())
        {
            pass = pass.concat(pswd.getText().substring(pass.length()));
            fill ();
        }
        else if (pass.length() > pswd.getText().length())
        {
            pass = pass.substring(0, pswd.getText().length());
            fill ();
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
        this.user = new TextField(gc, gc.getDefaultFont(), x-rx, ry, w, 23);
        this.pswd = new TextField(gc, gc.getDefaultFont(), x+rx, ry, w, 23);
        user.setText("");
        pswd.setText("");
        pswd.setAcceptingInput(true);
        user.setAcceptingInput(true);
        pswd.setFocus(true);
        user.setFocus(true);
        user.setConsumeEvents(true);
        pswd.setConsumeEvents(true);
        user.setBackgroundColor(Color.gray);
        user.setBorderColor(Color.black);
        user.setTextColor(Color.white);
        pswd.setBackgroundColor(Color.gray);
        pswd.setBorderColor(Color.black);
        pswd.setTextColor(Color.white);
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
        user.setFocus(focus);
        pswd.setFocus(!focus);
    }

    @Override
    public void controlReleased(Command cmnd){}
    
    private void fill ()
    {
        String txt = "";
        for (int q = 0; q < pass.length(); q++)
        {
            txt = txt.concat("*");
        }
        pswd.setText(txt);
    }
    
    @Override
    public String toString()
    {
        return "Access ";
    }
}
