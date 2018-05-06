/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AliensDriveMeCrazy;

import AliensDriveMeCrazy.Characters.Hero;
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
public class StoreMenu extends Scene implements InputProviderListener
{    
    private final Hero hero;
    private final Rectangle back;
    private final Rectangle right;
    private final Rectangle left;
    private boolean clicked;
    private int xMouse;
    private int yMouse;
    private Input input;
    private InputProvider provider;
    private final Command click;
    private int status;
    private final float x;
    private final float y;
    private final float w;
    private final float h;
    
    public StoreMenu (Hero hero)
    {
        this.hero = hero;
        this.back = new Rectangle (100,100,100,50);
        this.left = new Rectangle (400,500,50,50);
        this.right = new Rectangle (1300,500,50,50);
        click = new BasicCommand("click");
        clicked = false;
        status = 0;
        this.x = 650;
        this.y = 250;
        this.w = 500;
        this.h = 500;
    }
    
    @Override
    public void Render(GameContainer gc, Graphics g) throws SlickException
    {
        Game.getImages().getImage(Images.MENU).draw(0, 0,Game.getX(),Game.getY());
        g.setColor(Color.yellow);
        g.fill(back);
        g.fill(right);
        g.fill(left);
        g.drawString("Money: "+hero.getMoney(), 850, 100);
        if (status == 0)
        {
                hero.getImg().draw(x,y,w,h);
                g.drawString("Lives: "+hero.getHealthMax(), 500, 800);
        }
        else if((status > 0) && (status <= hero.numberWearpons()))
        {
            hero.showWearpon(status-1).draw(x, y, w, h);
            hero.showBullets(status-1).draw(500, 850, 20, 20);
        }
    }

    @Override
    public void Update(GameContainer gc, int t) throws SlickException
    {
        if (clicked)
        {
            if (back.contains(xMouse, yMouse))
            {
                StartMenu startMenu = new StartMenu();   
                Game.removeSence(this);
                Game.addScene(startMenu);
            }
            else if (right.contains(xMouse, yMouse))
            {
                if (hero.numberWearpons() > status+1)
                    status ++;
            }
            else if (left.contains(xMouse, yMouse))
            {
                if (status > 0)
                    status --;
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
