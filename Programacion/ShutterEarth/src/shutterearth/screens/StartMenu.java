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
import org.newdawn.slick.command.Control;
import org.newdawn.slick.command.InputProvider;
import org.newdawn.slick.command.InputProviderListener;
import org.newdawn.slick.command.MouseButtonControl;
import org.newdawn.slick.geom.Rectangle;
import shutterearth.Game;
import shutterearth.Media;
import shutterearth.characters.SavedHero;

/**
 *
 * @author mr.blissfulgrin
 */
public class StartMenu extends Scene implements InputProviderListener
{
    private InputProvider provider;
    private final Command click;
    private final Control mouse = new MouseButtonControl(0);
    private final Rectangle game;
    private final Rectangle store;
    private final Rectangle exit;
    private final int w;
    private final int h;
    private final int x;
    private final int y;
    private boolean clicked;
    private int xMouse;
    private int yMouse;
    private Input input;
    private final SavedHero hero;
    
    /**
     * IR A JUGAR O TIENDA
     * CONTROLA EL GUARDADO DE USUARIOS AL PASAR POR ESTA ESCENA
     * @param hero 
     */
    public StartMenu (SavedHero hero)
    {
        int step = Game.getY()/6;
        this.w = Game.getX()/3;
        this.h = Game.getY()/7;
        this.x = Game.getX()/2-w/2;
        this.y = Game.getY()/3;
        game = new Rectangle (x,y, w, h);
        store = new Rectangle (x,y+step, w, h);
        exit = new Rectangle (Game.getX()/14,Game.getY()/14,Game.getX()/16,Game.getY()/20);
        click = new BasicCommand("click");
        clicked = false;
        this.hero = hero;
        Game.develop(hero);
    }
            
    @Override
    public void Render(GameContainer gc, Graphics g) throws SlickException
    {
        Game.getMedia().getImage(Media.IMAGE.MENU).draw(0, 0, Game.getX(), Game.getY());
        Game.getMedia().getImage(Media.IMAGE.TITLE).draw(Game.getX()/3, Game.getY()/6, (Game.getX())/3, Game.getY()/12);
        g.setColor(Color.yellow);
        g.fill(game);
        Game.getMedia().getImage(Media.IMAGE.PLAY).draw(game.getX(),game.getY(),game.getWidth(),game.getHeight());
        g.fill(store);
        Game.getMedia().getImage(Media.IMAGE.STORE).draw(store.getX(),store.getY(),store.getWidth(),store.getHeight());
        g.fill(exit);
        Game.getMedia().getImage(Media.IMAGE.BACK).draw(exit.getX(),exit.getY(),exit.getWidth(),exit.getHeight());
    }

    @Override
    public void Update(GameContainer gc, float t) throws SlickException
    {
        if (clicked)
        {
            if (game.contains(xMouse, yMouse))
            {
                Game.getMedia().getSound(Media.SOUND.SHOT).play();
                provider.unbindCommand(mouse);
                provider.removeListener(this);
                Game.removeSence(this);
                Game.addScene(new Maper(hero));
            }
            else if (store.contains(xMouse, yMouse))
            {
                Game.getMedia().getSound(Media.SOUND.SHOT).play();
                provider.unbindCommand(mouse);
                provider.removeListener(this);
                Game.addScene(new Store(hero));
                Game.removeSence(this);
            }
            else if (exit.contains(xMouse, yMouse))
            {
                Game.getMedia().getSound(Media.SOUND.SHOT).play();
                provider.unbindCommand(mouse);
                provider.removeListener(this);
                Game.addScene(new Access());
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
        provider.bindCommand(mouse, click);
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
    
    @Override
    public String toString()
    {
        return "Start "+this.hero.toString();
    }
}
