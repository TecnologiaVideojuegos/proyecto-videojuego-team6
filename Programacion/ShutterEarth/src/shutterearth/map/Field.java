/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shutterearth.map;

import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.command.BasicCommand;
import org.newdawn.slick.command.Command;
import org.newdawn.slick.command.InputProvider;
import org.newdawn.slick.command.InputProviderListener;
import org.newdawn.slick.command.KeyControl;
import org.newdawn.slick.geom.Circle;
import shutterearth.Game;
import shutterearth.Media;
import shutterearth.characters.Hero;
import shutterearth.screens.Scene;
import shutterearth.screens.StartMenu;
import shutterearth.characters.Charact;
import shutterearth.characters.Enemy;
import shutterearth.characters.Ship;

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
    private final ArrayList <Charact> enemy;
    private final ArrayList <Enemy> en;
    private final ArrayList <Ship> sh;
    private int shipCounter;
    private float counter;
    
    private float radix;
    private Circle animation;
    private boolean animationStarted;
    private int diagonal;
    
    public Field (Hero hero, int stage, HUD hud)
    {
        this.stage = stage;
        this.hero = hero;
        this.hud = hud;
        this.battle = false;
        hero.place(450, 400, 900);
        radix = 0;
        animation = new Circle (Game.getX()/2,Game.getY()/2,radix);
        animationStarted = false;
        diagonal = (Game.getX()^2+Game.getY()^2)^(1/2);
        
        enemy = new ArrayList <>();
        en = new ArrayList <>();
        sh = new ArrayList <>();
        ArrayList <Charact> h = new ArrayList <>();
        h.add(hero);
        
        sh.add(new Ship(1,3,hero,this));
        sh.add(new Ship(1,3,hero,this));
        this.shipCounter = 200;
        sh.forEach((s) ->
        {
            enemy.add(s);
            s.addEnemys(h);
        });
        
        switch(stage)
        {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
        }
        hero.addEnemys(enemy);
    }
    
    @Override
    public void Render(GameContainer gc, Graphics g) throws SlickException
    {
        if (battle)
            Game.getMedia().getImage(Media.IMAGE.BATTLE).draw(0,0,Game.getX(),Game.getY());
        else
            Game.getMedia().getImage(Media.IMAGE.GAME).draw(0,0,Game.getX(),Game.getY());
        
        g.setColor(Color.black);
        g.fill(animation);
        g.setColor(Color.yellow);
    }

    @Override
    public void Update(GameContainer gc, float t) throws SlickException
    {
        if (counter < shipCounter)
        {
            counter += 1*t;
        }
        else
        {
            sh.forEach((s)->
            {
                s.activate();
            });
        }
        if (animationStarted)
        {
            radix = animation.getRadius() + 5f*t;
            animation.setRadius(radix);
            animation.setCenterX(Game.getX()/2);
            animation.setCenterY(Game.getY()/2);
            if (radix > diagonal)
            {
                this.exit();
            }
        }
    }

    @Override
    public void init(GameContainer gc) throws SlickException
    {
        Game.getMedia().getMusic(Media.MUSIC.CANCION_GAME).loop();
        provider = new InputProvider(gc.getInput());
        provider.addListener(this);
        
        for (int x = 0; x<2; x++)
        {
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
    }

    @Override
    public void controlPressed(Command command)
    {
        if (!this.isFreezed())
        {
            if (command.equals(CONTROL))
            {
                this.pause();
                Game.addScene(new Pause(this));
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
 
    public void pause ()
    {
        this.setState(STATE.FREEZE);
        hud.pause();
        enemy.forEach((e)->
        {
            e.pause();
        });
    }
    
    public void wake ()
    {
        this.setState(STATE.ON);
        hud.wake();
        enemy.forEach((e)->
        {
            e.wake();
        });
    }
    
    public void exit ()
    {
        Game.removeSence(this);
        hud.end();
        enemy.forEach((e)->
        {
            e.end();
        });
        Game.addScene(new StartMenu(hero.save())); 
        Game.getMedia().getMusic(Media.MUSIC.CANCION_MENU).loop();
    }
    
    public void start ()
    {
        Game.addScene(this);
        Game.addScene(hud);
        hero.setField(this);
        enemy.forEach((e)->
        {
            e.start();
        });
        hero.start();
        enemy.forEach((e)->
        {
            e.startI();
        });
        hero.startI();
    }
    
    public void setHudAlien (Charact enemy, int lastLive)
    {
        hud.addBadGuy(enemy, lastLive);
    }
    public void heroDied ()
    {
        Game.addScene(new TextDisplayer(this,11,0));
    }
    public void enemyDied (Charact enemy)
    {
        this.enemy.remove(enemy);
    }
    
    public void startAnimation ()
    {
        Game.getMedia().getSound(Media.SOUND.ALIEN1).play();
        this.animationStarted = true;
    }
    
    public void startBattle ()
    {
        Game.getMedia().getMusic(Media.MUSIC.BATTLE_SONG).loop();
        this.battle = true;
    }
}
