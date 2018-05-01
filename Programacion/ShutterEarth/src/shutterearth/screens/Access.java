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
import shutterearth.Images;
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
        Game.getImages().getImage(Images.MENU).draw(0, 0, Game.getX(), Game.getY());
        g.setColor(Color.yellow);
        g.fill(exit);
        g.fill(go);
        g.fill(register);
        g.setColor(Color.lightGray);
        user.render(gc, g);
        pswd.render(gc, g);
        user.setBackgroundColor(Color.gray);
        user.setBorderColor(Color.black);
        user.setTextColor(Color.white);
        pswd.setBackgroundColor(Color.gray);
        pswd.setBorderColor(Color.black);
        pswd.setTextColor(Color.white);
        g.setColor(Color.yellow);
        g.drawString("User: ",x-(Game.getX()/6),y+(Game.getY()/14));
        g.drawString("Password: ",x+(Game.getX()/6),y+(Game.getY()/14));
        if (ok != null)
        {
            g.drawString("WRONG INPUT",x+(Game.getX()/15),y+(Game.getY()/5));
        }
    }

    @Override
    public void Update(GameContainer gc, int t) throws SlickException
    {
        if (clicked)
        {
            if (go.contains(xMouse, yMouse))
            {
                SavedHero hero = Game.load(user.getText(), pass);
                if(hero != null)
                {
                    StartMenu startMenu = new StartMenu(hero);
                    Game.addScene(startMenu);
                    Game.removeSence(this);
                }
                else
                {
                    user.setText("");
                    pswd.setText("");
                    ok = false;
                }
            }
            else if (register.contains(xMouse, yMouse))
            {
                Game.addScene(new Register());
                Game.removeSence(this);
            }
            else if (exit.contains(xMouse, yMouse))
            {
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
        this.user = new TextField(gc, gc.getDefaultFont(), x, y, w, h);
        this.pswd = new TextField(gc, gc.getDefaultFont(), x, y+Game.getY()/14, w, h);
        provider = new InputProvider(gc.getInput());
        provider.addListener(this);
        provider.bindCommand(new MouseButtonControl(0), click);
        provider.bindCommand(new KeyControl(Input.KEY_TAB), tab);
        for (int x = 30; x < 45; x++)
        {
            provider.bindCommand(new KeyControl(x), any);
        }
        input = gc.getInput();  
        this.user = new TextField(gc, gc.getDefaultFont(), x-(Game.getX()/6), y+(Game.getY()/14)*2, w, 30);
        this.pswd = new TextField(gc, gc.getDefaultFont(), x+(Game.getX()/6), y+(Game.getY()/14)*2, w, 30);
        user.setText("");
        pswd.setText("");
        pswd.setAcceptingInput(true);
        user.setAcceptingInput(true);
        pswd.setFocus(true);
        user.setFocus(true);
        user.setConsumeEvents(true);
        pswd.setConsumeEvents(true);
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
}
