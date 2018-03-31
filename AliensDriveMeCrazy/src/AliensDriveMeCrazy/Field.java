/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AliensDriveMeCrazy;

import java.util.ArrayList;
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
    private final float step;
    private Image img;
    
    private final Hero hero;
    private final ArrayList <BadGuy> badGuy;
    
    public Field (Hero hero, ArrayList <BadGuy> badGuy)
    {
        this.step = 130;
        this.hero = hero;
        this.badGuy = badGuy;
        
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
        if (!badGuy.isEmpty()&&hero.isAlive())
        {
            img.draw(0,0,Game.getX(),Game.getY());
            hero.draw();
            badGuy.forEach((b) ->
            {
                b.draw();
            });
        }
        else
        {
            if (hero.isAlive())
            {
                g.drawString("YOU WIN", 830, 500);
            }
            else
            {
                g.drawString("YOU LOSE", 830, 500);
            }
        }
    }

    @Override
    public void Update(GameContainer gc, int t) throws SlickException
    {
        if (!badGuy.isEmpty()&&hero.isAlive())
        {
            hero.move(t);
            hero.shot(t);
            badGuy.forEach((b) ->
            {
                b.IA(t,hero.getX(),hero.getY(), step);
            });
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
            hero.LEFT();
        }
        else if (command.equals(RIGHT))
        {
            hero.RIGHT();
        }
        else if (command.equals(SHOT))
        {
            hero.SHOT();
        }
    }
    
    @Override
    public void controlReleased(Command command) {}
    
}
