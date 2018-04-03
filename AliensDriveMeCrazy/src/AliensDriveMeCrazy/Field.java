/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AliensDriveMeCrazy;

import AliensDriveMeCrazy.Characters.BadGuy;
import AliensDriveMeCrazy.Characters.Hero;
import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.command.BasicCommand;
import org.newdawn.slick.command.Command;
import org.newdawn.slick.command.InputProvider;
import org.newdawn.slick.command.InputProviderListener;
import org.newdawn.slick.command.KeyControl;

/**
 *
 * @author mr.blissfulgrin
 */
public class Field extends Scene implements InputProviderListener
{
    private InputProvider provider;
    private final Command UP = new BasicCommand("UP");
    private final Command DOWN = new BasicCommand("DOWN");
    private final Command LEFT = new BasicCommand("LEFT");
    private final Command RIGHT = new BasicCommand("RIGHT");
    private final Command SHOT = new BasicCommand("SHOT");
    private final Command CONTROL = new BasicCommand("CONTROL");
    private final Command EXIT = new BasicCommand("EXIT");
    private final float step;
    private Image img;
    
    private final Hero hero;
    private final ArrayList <BadGuy> badGuy;
    private boolean start;
    private boolean pause;
    private final HUD hud;
    private int contador;
    private boolean done;
    
    public Field (Hero hero, ArrayList <BadGuy> badGuy, HUD hud)
    {
        this.hud = hud;
        this.step = 130;
        this.hero = hero;
        this.badGuy = badGuy;
        start = false;
        pause = false;
        contador = 100;
        done = false;
        
        hero.setEnemy(badGuy);
        badGuy.forEach((b) ->
        {
            b.setEnemy(hero);
        });
        
        try
        {
            this.img = new Image("./src/img/BACKGROUND.png");
        } 
        catch (SlickException e)
        {
            System.out.println("ERROR WEARPON LOADING IMG");
        }
    }

    @Override
    public void Render(GameContainer gc, Graphics g) throws SlickException
    {   
        if (start)
        {
            if (!badGuy.isEmpty()&&hero.isAlive())
            {
                img.draw(0,0,Game.getX(),Game.getY());
                hero.draw(g);
                badGuy.forEach((b) ->
                {
                    b.draw(g);
                });
                if (this.isFreezed())
                {
                    g.drawString("PAUSE, HIT ENTER TO RESUME", 740, 500);
                }
            }
            else
            {
                g.setColor(Color.yellow);
                if (hero.isAlive())
                {
                    g.drawString("YOU WIN\n<- Para salir", 830, 500);
                    if (!done)
                    {
                        hero.nextStage();
                        done = true;
                    }
                }
                else
                {
                    g.drawString("YOU LOSE\n<- Para salir", 830, 500);
                }
                this.setState(STATE.FREEZE);
            }
        }
        else
        {

            if (contador <= 0)
                start = true;
            else
            {
                g.setColor(Color.white);
                img.draw(0,0,Game.getX(),Game.getY());
                g.drawString(String.valueOf(contador), 850, 500);
            }
            contador --;
        }
    }

    @Override
    public void Update(GameContainer gc, int t) throws SlickException
    {
        if (start)
        {
            if (!badGuy.isEmpty()&&hero.isAlive())
            {
                hero.move(t);
                hero.shot(t);
                ArrayList <BadGuy> toRemove = new ArrayList <> ();
                badGuy.forEach((b) ->
                {
                    b.IA(t,hero.getX(),hero.getY(), step);
                    if (b.isRemovable())
                    {
                        toRemove.add(b);
                    }
                });
                toRemove.forEach((b) ->
                {
                    badGuy.remove(b);
                });
            }
        }
    }

    @Override
    public void init(GameContainer gc) throws SlickException
    {
        provider = new InputProvider(gc.getInput());
        provider.addListener(this);

        provider.bindCommand(new KeyControl(Input.KEY_UP), UP);
        provider.bindCommand(new KeyControl(Input.KEY_DOWN), DOWN);
        provider.bindCommand(new KeyControl(Input.KEY_LEFT), LEFT);
        provider.bindCommand(new KeyControl(Input.KEY_RIGHT), RIGHT);
        provider.bindCommand(new KeyControl(Input.KEY_SPACE), SHOT);
        provider.bindCommand(new KeyControl(Input.KEY_ENTER), CONTROL);
        provider.bindCommand(new KeyControl(Input.KEY_BACK), EXIT);
    }
    
    @Override
    public void controlPressed(Command command) 
    {
        if (command.equals(UP))
        {
            hero.UP(step);
        }
        else if (command.equals(DOWN))
        { 
            hero.DOWN(step);
        }
        else if (command.equals(LEFT))
        {
            hero.LEFT(0);
        }
        else if (command.equals(RIGHT))
        {
            hero.RIGHT(Game.getX() - hero.getW());
        }
        else if (command.equals(SHOT))
        {
            hero.SHOT();
        }
        else if (command.equals(CONTROL))
        {
            this.control();
        }
        else if (command.equals(EXIT))
        {
            this.exit();
        }
    }
    
    private void control ()
    {
        if (!badGuy.isEmpty()&&hero.isAlive())
        {
            if (start)
            {
                if (this.isFreezed())
                    this.setState(STATE.ON);
                else
                    this.setState(STATE.FREEZE);
                pause = !pause;
            }
        }
    }
    
    private void exit ()
    {
        Game.removeSence(this);
        Game.removeSence(hud);
        Game.addScene(new StartMenu());
        SavingStation.save(hero);
    }
    
    @Override
    public void controlReleased(Command command) {}
    
}
