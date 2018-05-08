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
    private final int x;
    private final int y;
    private final int w;
    private final int h;
    private int index;
    private final int[][] prices;
    
    public Store (SavedHero hero)
    {
        this.prices = new int[][]
        {
            {
                50,100,160,225,400  //HEALTH
            },
            {
                10,20,30,40         //Arma Minima
            },
            {
                60,70,80,90         //Arma Base
            },
            {
                120,130,140,150     //Arma Fuerte
            },
            {
                170,180,190,200     //Arma Rápida
            },
            {
                250,260,270,300     //Arma Final
            },
        };
        exit = new Rectangle (Game.getX()/14,Game.getY()/14,Game.getX()/16,Game.getY()/20);
        click = new BasicCommand("click");
        clicked = false;
        this.hero = hero;
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
        Game.getImages().getImage(Images.MENU).draw(0,0,Game.getX(),Game.getY());
        g.fill(exit);
        g.fill(left);
        g.fill(right);
        g.fill(upgrade);
        if (index == 0)
        {
            Game.getImages().getSprit(Images.BASE_DER).draw(x,y,w,h);
        }
        else
        {
            Game.getImages().getImage(Images.getGun(index-1)).draw(x,y,w,h);
        }
        if (index > 0)
        {
            for (int j = 0; j < status.length; j++)
            {
                if (hero.getInventory().get(index-1)[1] > j)
                    g.fill(status[j]);
                else
                    g.draw(status[j]);
            }
        }
        else
        {
            for (int j = 0; j < status.length; j++)
            {
                if (hero.getHealthMax()/20>j)
                    g.fill(status[j]);
                else
                    g.draw(status[j]);
            }
        }
        g.drawString("Bullets: "+hero.getBullets(), upgrade.getX(), upgrade.getMaxY()+1);
        g.drawString("Cost: "+prices[index][index>0?hero.getInventory().get(index-1)[1]:hero.getHealthMax()/20], upgrade.getX(), upgrade.getMaxY()+15);
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
                if ((index < prices.length-1))
                {
                    index ++;
                }
            }
            else if (upgrade.contains(xMouse, yMouse))
            {
                if (index > 0)
                {
                    if (hero.getBullets() >= prices[index][hero.getInventory().get(index-1)[1]])
                    {
                        hero.sold(prices[index][hero.getInventory().get(index-1)[1]]);
                        hero.getInventory().get(index-1)[1]++;
                    }
                }
                else
                {
                    if (hero.getBullets() >= prices[index][hero.getHealthMax()/20])
                    {
                        hero.sold(prices[index][hero.getHealthMax()/20]);
                        hero.setHealth(((hero.getHealthMax()/20)+1)*20);
                    }
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
