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
    private final Command UP = new BasicCommand("UP");
    private final Command DOWN = new BasicCommand("DOWN");
    private final Command LEFT = new BasicCommand("LEFT");
    private final Command RIGHT = new BasicCommand("RIGHT");
    private final Command I_LEFT = new BasicCommand("I_LEFT");
    private final Command I_RIGHT = new BasicCommand("I_RIGHT");
    private final Command SHOT = new BasicCommand("SHOT");
    
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
        
        hero.place(400, 100, 500);
    }
    
    @Override
    public void Render(GameContainer gc, Graphics g) throws SlickException
    {
        if (battle)
            Game.getMedia().getImage(Media.IMAGE.BATTLE).draw(0,0,Game.getX(),Game.getY());
        else
            Game.getMedia().getImage(Media.IMAGE.GAME).draw(0,0,Game.getX(),Game.getY());
    }

    @Override
    public void Update(GameContainer gc, float t) throws SlickException
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
        provider.bindCommand(new KeyControl(Input.KEY_UP), UP);
        provider.bindCommand(new KeyControl(Input.KEY_DOWN), DOWN);
        provider.bindCommand(new KeyControl(Input.KEY_RIGHT), RIGHT);
        provider.bindCommand(new KeyControl(Input.KEY_LEFT), LEFT);
        provider.bindCommand(new KeyControl(Input.KEY_E), I_RIGHT);
        provider.bindCommand(new KeyControl(Input.KEY_Q), I_LEFT);
        provider.bindCommand(new KeyControl(Input.KEY_SPACE), SHOT);
    }

    @Override
    public void controlPressed(Command command)
    {
        if (!this.isFreezed())
        {
            if (command.equals(CONTROL))
            {
                this.setState(STATE.FREEZE);
                hud.pause();
                Game.addScene(new Pause(this,hud));
            }
            else if (command.equals(UP))
            {
                //hero.setBounds(stage, stage, stage);
                hero.goUp();
            }
            else if (command.equals(DOWN))
            {
                //hero.setBounds(stage, stage, stage);
                hero.goDown();
            }
            else if (command.equals(RIGHT))
            {
                hero.goRight();
            }
            else if (command.equals(LEFT))
            {
                hero.goLeft();
            }
            else if (command.equals(I_RIGHT))
            {
                hero.inventoryRight();
            }
            else if (command.equals(I_LEFT))
            {
                hero.inventroyLeft();
            }
            else if (command.equals(SHOT))
            {
                hero.shot();
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
