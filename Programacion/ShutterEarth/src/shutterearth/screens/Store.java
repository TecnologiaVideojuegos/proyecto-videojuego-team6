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
import shutterearth.map.LiveDisplayer;

/**
 *
 * @author mr.blissfulgrin
 */
public class Store extends Scene implements InputProviderListener
{
    private InputProvider provider;
    private final Command click;
    Control mouse = new MouseButtonControl(0);
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
    private final LiveDisplayer lives;
    
    public Store (SavedHero hero)
    {
        this.prices = new int[][]
        {
            {
                50,100,160,225,300,400,500,800,900,1000,0       //HEALTH
            },
            {
                30,60,90,120,0                                  //Arma Minima
            },
            {
                140,180,200,220,0                               //Arma Base
            },
            {
                240,300,350,450,0                               //Arma Fuerte
            },
            {
                400,450,500,550,0                               //Arma Rápida
            },
            {
                500,600,700,800,0                               //Arma Final
            },
        };
        exit = new Rectangle (Game.getX()/14,Game.getY()/14,Game.getX()/16,Game.getY()/20);
        click = new BasicCommand("click");
        clicked = false;
        this.hero = hero;
        this.x = Game.getX()/3 + Game.getX()/16;
        this.y = Game.getY()/3 - Game.getY()/9;
        this.w = Game.getX()/3 - Game.getX()/8;
        this.h = Game.getY()/3;
        this.index =0;
        
        this.left = new Rectangle (Game.getX()/3-Game.getX()/20-Game.getX()/50,y+h/2-Game.getY()/40,Game.getX()/20,Game.getY()/20);
        this.right = new Rectangle ((Game.getX()/3)*2+Game.getX()/50,y+h/2-Game.getY()/40,Game.getX()/20,Game.getY()/20);
        this.upgrade = new Rectangle (Game.getX()/2-Game.getX()/10,y+h+Game.getY()/22+Game.getX()/50,Game.getX()/5,Game.getY()/12);
        
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
        this.lives = new LiveDisplayer(xr,yr,hr,10,hero.getHealthMax());
        this.lives.center();
    }
    
    @Override
    public void Render(GameContainer gc, Graphics g) throws SlickException
    {
        Game.getMedia().getImage(Media.IMAGE.MENU).draw(0,0,Game.getX(),Game.getY());
        g.setColor(Color.yellow);
        g.fill(exit);
        Game.getMedia().getImage(Media.IMAGE.BACK).draw(exit.getX(),exit.getY(),exit.getWidth(),exit.getHeight());
        g.fill(left);
        Game.getMedia().getImage(Media.IMAGE.BACK).draw(left.getX(),left.getY(),left.getWidth(),left.getHeight());
        g.fill(right);
        Game.getMedia().getImage(Media.IMAGE.FORWARD).draw(right.getX(),right.getY(),right.getWidth(),right.getHeight());
        if (index == 0)
        {
            switch (hero.getStage()/2)
            {
                case 0:
                    Game.getMedia().getSprit(Media.SPRITE.HERO_1_DER).draw(x,y,w,h);
                    break;
                case 1:
                    Game.getMedia().getSprit(Media.SPRITE.HERO_2_DER).draw(x,y,w,h);
                    break;
                case 2:
                    Game.getMedia().getSprit(Media.SPRITE.HERO_3_DER).draw(x,y,w,h);
                    break;
                case 3:
                    Game.getMedia().getSprit(Media.SPRITE.HERO_4_DER).draw(x,y,w,h);
                    break;
                default:
                    Game.getMedia().getSprit(Media.SPRITE.HERO_5_DER).draw(x-20,y,w+40,h);
                    break;
            }
        }
        else
        {
            Game.getMedia().getImage(Media.getGun(index-1)).draw(x,y,w,h);
        }
        
        g.drawString("Kills: "+hero.getKills(), x, y+h+5);
        if ((hero.getStage()/2)>=(index-1))
        {
            g.setColor(Color.yellow);
                    g.fill(upgrade);
            Game.getMedia().getImage(Media.IMAGE.UPGRADE).draw(upgrade.getX(),upgrade.getY(),upgrade.getWidth(),upgrade.getHeight());
            g.drawString("Cost: "+prices[index][index>0?(hero.getInventory().get(index-1)[1]>0?hero.getInventory().get(index-1)[1]-1:0):(hero.getHealthMax()/20<11?hero.getHealthMax()/20:10)], upgrade.getX(), upgrade.getMaxY()+15);
        }
        else
        {
            g.setColor(Color.red);
            g.fill(upgrade);
        }
        
        if (index > 0)
        {
            Game.removeSence(lives);
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
            lives.setHealth(hero.getHealthMax(),true);
            this.lives.center();
            Game.addScene(lives);
        }
        g.drawString("Bullets: "+hero.getBullets(), upgrade.getX(), upgrade.getMaxY()+1);
    }

    @Override
    public void Update(GameContainer gc, float t) throws SlickException
    {
        if (clicked)
        {  
            if (exit.contains(xMouse, yMouse))
            {
                Game.getMedia().getSound(Media.SOUND.SHOT).play();
                Game.save(hero);
                provider.unbindCommand(mouse);
                provider.removeListener(this);
                Game.addScene(new StartMenu(hero));
                Game.removeSence(this);
                Game.removeSence(lives);
            }
            else if (left.contains(xMouse, yMouse))
            {
                if (index > 0)
                {
                    Game.getMedia().getSound(Media.SOUND.SHOT).play();
                    index --;
                }
                else
                {
                    Game.getMedia().getSound(Media.SOUND.BAD).play();
                }
            }
            else if (right.contains(xMouse, yMouse))
            {
                if ((index < prices.length-1))
                {
                    Game.getMedia().getSound(Media.SOUND.SHOT).play();
                    index ++;
                }
                else
                {
                    Game.getMedia().getSound(Media.SOUND.BAD).play();
                }
            }
            else if (upgrade.contains(xMouse, yMouse))
            {
                if (index > 0)
                {
                    if ((hero.getInventory().get(index-1)[1]-1 < 4)&&(hero.getInventory().get(index-1)[1]>0) &&(hero.getBullets() >= prices[index][hero.getInventory().get(index-1)[1]-1]) && ((hero.getStage()/2)>=(index-1)))
                    {
                        Game.getMedia().getSound(Media.SOUND.CASH).play();
                        hero.sold(prices[index][hero.getInventory().get(index-1)[1] - 1]);
                        hero.getInventory().get(index-1)[1]++;
                    }
                    else
                    {
                        Game.getMedia().getSound(Media.SOUND.BAD).play();
                    }
                }
                else
                {
                    if ((hero.getHealthMax() < 200) && (hero.getBullets() >= prices[index][hero.getHealthMax()/20]))
                    {
                        Game.getMedia().getSound(Media.SOUND.CASH).play();
                        hero.sold(prices[index][hero.getHealthMax()/20]);
                        hero.setHealth(((hero.getHealthMax()/20)+1)*20);
                    }
                    else
                    {
                        Game.getMedia().getSound(Media.SOUND.BAD).play();
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
        return "Store "+this.hero.toString();
    }
}
