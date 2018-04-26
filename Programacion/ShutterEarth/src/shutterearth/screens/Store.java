/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shutterearth.screens;

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
import shutterearth.Game;
import shutterearth.characters.SavedHero;

/**
 *
 * @author mr.blissfulgrin
 */
public class Store extends Scene implements InputProviderListener
{
    private InputProvider provider;
    private final Command click;
    private final Rectangle exit;
    private boolean clicked;
    private int xMouse;
    private int yMouse;
    private Input input;
    private final SavedHero hero;
    
    public Store (SavedHero hero)
    {
        exit = new Rectangle (Game.getX()/14,Game.getY()/14,Game.getX()/16,Game.getY()/20);
        click = new BasicCommand("click");
        clicked = false;
        this.hero = hero;
    }
    
    @Override
    public void Render(GameContainer gc, Graphics g) throws SlickException
    {
        g.draw(exit);
    }

    @Override
    public void Update(GameContainer gc, int t) throws SlickException
    {
        if (clicked)
        {  
            if (exit.contains(xMouse, yMouse))
            {
                Game.save(hero);
                Game.addScene(new StartMenu(hero));
                Game.removeSence(this);
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
