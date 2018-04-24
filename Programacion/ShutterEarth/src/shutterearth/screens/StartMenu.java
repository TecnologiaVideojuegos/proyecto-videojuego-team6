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

/**
 *
 * @author mr.blissfulgrin
 */
public class StartMenu extends Scene implements InputProviderListener
{
    private InputProvider provider;
    private final Command click;
    private final Rectangle game;
    private final Rectangle statistics;
    private final Rectangle store;
    private final Rectangle exit;
    private final int w;
    private final int h;
    private final int x;
    private boolean clicked;
    private int xMouse;
    private int yMouse;
    private Input input;
    
    public StartMenu ()
    {
        this.w = 500;
        this.h = 150;
        this.x = 600;
        game = new Rectangle (x,300, w, h); 
        store = new Rectangle (x,500, w, h);
        statistics = new Rectangle (x,700, w, h);
        exit = new Rectangle (100,100,100,50);
        click = new BasicCommand("click");
        clicked = false;
    }
            
    @Override
    public void Render(GameContainer gc, Graphics g) throws SlickException
    {
        g.setColor(Color.yellow);
        g.fill(game);
        g.fill(statistics);
        g.fill(store);
        g.fill(exit);
        g.setColor(Color.lightGray);
        g.drawString("GAME", x+w/7*3+15, 375);
        g.drawString("STORE", x+w/7*3+10, 575);
        g.drawString("STATISTICS", x+w/7*3-2, 775);
    }

    @Override
    public void Update(GameContainer gc, int t) throws SlickException
    {
        if (clicked)
        {
            if (game.contains(xMouse, yMouse))
            {
                
            }
            else if (statistics.contains(xMouse, yMouse))
            {
                
            }
            else if (store.contains(xMouse, yMouse))
            {
                
            }
            else if (exit.contains(xMouse, yMouse))
                System.exit(0);
        }
        clicked = false;
    }

    @Override
    public void init(GameContainer gc) throws SlickException
    {
        provider = new InputProvider(gc.getInput());
        provider.addListener(this);
        provider.bindCommand(new MouseButtonControl(0), click);
        input = gc.getInput();      
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
