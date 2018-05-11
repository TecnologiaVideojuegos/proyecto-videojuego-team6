/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shutterearth.map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.command.BasicCommand;
import org.newdawn.slick.command.Command;
import org.newdawn.slick.command.InputProvider;
import org.newdawn.slick.command.InputProviderListener;
import org.newdawn.slick.command.KeyControl;
import shutterearth.Game;
import shutterearth.characters.Hero;
import shutterearth.screens.Scene;
import shutterearth.screens.StartMenu;

/**
 *
 * @author mr.blissfulgrin
 */
public class Field extends Scene implements InputProviderListener
{
    private InputProvider provider;
    private final Command CONTROL = new BasicCommand("CONTROL");
    
    private final HUD hud;
    private final Hero hero;
    
    public Field (Hero hero)
    {
        this.hero = hero;
        this.hud = new HUD(hero);
        Game.addScene(new Pause(this,hud));
    }
    
    @Override
    public void Render(GameContainer gc, Graphics g) throws SlickException
    {
        
    }

    @Override
    public void Update(GameContainer gc, int t) throws SlickException
    {
        
    }

    @Override
    public void init(GameContainer gc) throws SlickException
    {
        provider = new InputProvider(gc.getInput());
        provider.addListener(this);
        
        provider.bindCommand(new KeyControl(Input.KEY_BACK), CONTROL);
    }

    @Override
    public void controlPressed(Command command)
    {
        if (command.equals(CONTROL))
        {
            this.setState(STATE.FREEZE);
            hud.setState(STATE.FREEZE);
            Game.addScene(new Pause(this,hud));
        }
    }

    @Override
    public void controlReleased(Command cmnd) {}
 
    public void exit ()
    {
        Game.removeSence(this);
        Game.removeSence(hud);
        Game.addScene(new StartMenu(hero.save()));   
    }
    
}
