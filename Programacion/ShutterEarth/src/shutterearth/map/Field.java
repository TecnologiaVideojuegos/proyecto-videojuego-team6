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
import org.newdawn.slick.geom.Rectangle;
import shutterearth.Game;
import shutterearth.Media;
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
    private final int stage;
    private boolean battle;
    
    public Field (Hero hero, int stage, HUD hud)
    {
        this.stage = stage;
        this.hero = hero;
        this.hud = hud;
        this.battle = false;
        
        hero.place(200, 200, 300, 100, 500);
    }
    
    @Override
    public void Render(GameContainer gc, Graphics g) throws SlickException
    {
        if (battle)
            Game.getMedia().getImage(Media.IMAGE.BATTLE).draw(0,0,Game.getX(),Game.getY());
        else
            Game.getMedia().getImage(Media.IMAGE.GAME).draw(0,0,Game.getX(),Game.getY());
        
        if (Game.debug())
        {
            for (Rectangle rect : hero.debug())
            {
                Game.getMedia().getImage(Media.IMAGE.GREY).draw(rect.getX(),rect.getY(),rect.getWidth(),rect.getHeight());
            }
        } 
    }

    @Override
    public void Update(GameContainer gc, int t) throws SlickException
    {
        
    }

    @Override
    public void init(GameContainer gc) throws SlickException
    {
        Game.getMedia().getMusic(Media.MUSIC.CANCION_GAME).loop();
        provider = new InputProvider(gc.getInput());
        provider.addListener(this);
        
        provider.bindCommand(new KeyControl(Input.KEY_BACK), CONTROL);
        provider.bindCommand(new KeyControl(Input.KEY_ESCAPE), CONTROL);
    }

    @Override
    public void controlPressed(Command command)
    {
        if (command.equals(CONTROL))
        {
            if (!this.isFreezed())
            {
                this.setState(STATE.FREEZE);
                hud.pause();
                Game.addScene(new Pause(this,hud));
            }
        }
    }

    @Override
    public void controlReleased(Command cmnd) {}
 
    public void exit ()
    {
        Game.removeSence(this);
        hud.end();
        Game.addScene(new StartMenu(hero.save())); 
        Game.getMedia().getMusic(Media.MUSIC.CANCION_MENU).loop();
    }
    
}
