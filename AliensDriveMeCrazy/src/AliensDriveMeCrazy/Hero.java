/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AliensDriveMeCrazy;

import org.newdawn.slick.GameContainer;
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
public class Hero extends Character implements InputProviderListener
{
    private InputProvider provider;
    private final Command UP = new BasicCommand("UP");
    private final Command DOWN = new BasicCommand("DOWN");
    private final Command LEFT = new BasicCommand("LEFT");
    private final Command RIGHT = new BasicCommand("RIGHT");
    private final Command SHOT = new BasicCommand("SHOT");
    
    public Hero(Inventory inventory)
    {
        super(new String [] {"./src/img/CHARACTER.png"}, inventory, 1, 100,100);
    }

    @Override
    protected void move()
    {
        x += xVel;
        //y += yVel - gravity;
        y = 400;
    }

    @Override
    protected void shot()
    {
        
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
            yVel = 20;
        }
        else if (command.equals(DOWN))
        {
            yVel = 5;
            jump = true;
        }
        else if (command.equals(LEFT))
        {
            xVel = -10;
        }
        else if (command.equals(RIGHT))
        {
            xVel = 10;
        }
        else if (command.equals(SHOT))
        {
            
        }
    }
    
    @Override
    public void controlReleased(Command command) {}
}
