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
import shutterearth.Images;
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
    private final Rectangle left;
    private final Rectangle right;
    private final Rectangle upgrade;
    private final Rectangle [] status;
    private boolean clicked;
    private int xMouse;
    private int yMouse;
    private Input input;
    private final SavedHero hero;
    private final Images images;
    private final int x;
    private final int y;
    private final int w;
    private final int h;
    private int index;
    
    public Store (SavedHero hero)
    {
        exit = new Rectangle (Game.getX()/14,Game.getY()/14,Game.getX()/16,Game.getY()/20);
        click = new BasicCommand("click");
        clicked = false;
        this.hero = hero;
        this.images = Game.getImages();
        this.x = Game.getX()/3;
        this.y = Game.getY()/3 - Game.getY()/9;
        this.w = Game.getX()/3;
        this.h = Game.getY()/3;
        this.index =0;
        
        this.left = new Rectangle (x-Game.getX()/20-Game.getX()/50,y+h/2-Game.getY()/40,Game.getX()/20,Game.getY()/20);
        this.right = new Rectangle (x+w+Game.getX()/50,y+h/2-Game.getY()/40,Game.getX()/20,Game.getY()/20);
        this.upgrade = new Rectangle (x+w/2-(Game.getX()/10),y+h+Game.getY()/22+Game.getX()/50,Game.getX()/5,Game.getY()/12);
        
        status = new Rectangle [5];
        int step = Game.getY()/60;
        int wr = Game.getX()/20;
        int hr = Game.getY()/18;
        int yr = Game.getY() - hr*3;
        int xr = Game.getX()/2 - ((wr*5)/2) - step*2;
        for (int j = 0; j < status.length; j++)
        {
            status[j] = new Rectangle (xr + step*j +wr*j, yr, wr, hr);
        }
    }
    
    @Override
    public void Render(GameContainer gc, Graphics g) throws SlickException
    {
        g.fill(exit);
        g.fill(left);
        g.fill(right);
        g.fill(upgrade);
        images.getSprit(0).draw(x,y,w,h);
        for (Rectangle rectangle : status)
        {
            g.fill(rectangle);
        }
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
            else if (left.contains(xMouse, yMouse))
            {
                if (index > 0)
                {
                    index --;
                }
            }
            else if (right.contains(xMouse, yMouse))
            {
                if (index > hero.getNumberOfGuns())
                {
                    index --;
                }
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
