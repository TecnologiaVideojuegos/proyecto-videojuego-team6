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
import org.newdawn.slick.command.MouseButtonControl;
import org.newdawn.slick.geom.Rectangle;
import shutterearth.Game;
import shutterearth.Media;
import shutterearth.screens.Scene;

/**
 *
 * @author mr.blissfulgrin
 */
public class Pause extends Scene implements InputProviderListener
{
    private InputProvider provider;
    private final Command click;
    private final Field field;
    private Input input;
    private boolean clicked;
    private int xMouse;
    private int yMouse;
    
    private final int w;
    private final int h;
    private final int x;
    private final int y;
    private final Rectangle resume;
    private final Rectangle exit;
    
    /**
     * MENU DE PAUSA, PERMITE CONTINUAR O SALIR DEL JUEGO
     * @param field 
     */
    public Pause (Field field)
    {
        this.field = field;
        click = new BasicCommand("click");
        
        int step = Game.getY()/6;
        this.w = Game.getX()/3;
        this.h = Game.getY()/7;
        this.x = Game.getX()/2-w/2;
        this.y = Game.getY()/3;
        resume = new Rectangle (x,y, w, h);
        exit = new Rectangle (x,y+step, w, h);
    }
    
    @Override
    public void Render(GameContainer gc, Graphics g) throws SlickException
    {
        Game.getMedia().getImage(Media.IMAGE.GREY).draw(0,0,Game.getX(),Game.getY());
        g.fill(resume);
        Game.getMedia().getImage(Media.IMAGE.RESUME).draw(resume.getX(),resume.getY(),resume.getWidth(),resume.getHeight());
        g.fill(exit);
        Game.getMedia().getImage(Media.IMAGE.END_GAME).draw(exit.getX(),exit.getY(),exit.getWidth(),exit.getHeight());
    }

    @Override
    public void Update(GameContainer gc, float t) throws SlickException
    {
        if (clicked)
        {
            if (resume.contains(xMouse, yMouse))
            {
                Game.getMedia().getSound(Media.SOUND.ALIEN2).play();
                Game.removeSence(this);
                field.wake();
            }
            else if (exit.contains(xMouse, yMouse))
            {
                Game.getMedia().getSound(Media.SOUND.SHOT).play();
                Game.removeSence(this);
                field.exit();
            }
        }
    }

    @Override
    public void init(GameContainer gc) throws SlickException
    {
        Game.getMedia().getMusic(Media.MUSIC.CANCION_MENU).loop();
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
    
    @Override
    public String toString()
    {
        return "PAUSE";
    }
}
