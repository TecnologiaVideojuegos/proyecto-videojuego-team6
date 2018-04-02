/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AliensDriveMeCrazy;

import AliensDriveMeCrazy.Guns.Inventory;
import AliensDriveMeCrazy.Guns.Bullets;
import AliensDriveMeCrazy.Guns.Wearpon;
import AliensDriveMeCrazy.Characters.BadGuy;
import AliensDriveMeCrazy.Characters.Hero;
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
import org.newdawn.slick.command.MouseButtonControl;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author mr.blissfulgrin
 */
public class StartMenu extends Scene implements InputProviderListener
{
    private InputProvider provider;
    private final Command click;
    private final Rectangle game;
    private final Rectangle statistics;
    private final Rectangle store;
    private final int w;
    private final int h;
    private final int x;
    private boolean clicked;
    private int xMouse;
    private int yMouse;
    private Input input;
    
    public StartMenu ()
    {
        this.w = 500;
        this.h = 150;
        this.x = 600;
        game = new Rectangle (x,300, w, h);
        statistics = new Rectangle (x,500, w, h);
        store = new Rectangle (x,700, w, h);
        click = new BasicCommand("click");
        clicked = false;
    }
            
    @Override
    public void Render(GameContainer gc, Graphics g) throws SlickException
    {
        g.setColor(Color.yellow);
        g.fill(game);
        g.fill(statistics);
        g.fill(store);
        g.setColor(Color.lightGray);
        g.drawString("GAME", x+w/7*3+15, 375);
        g.drawString("STORE", x+w/7*3+10, 575);
        g.drawString("STATISTICS", x+w/7*3-2, 775);
    }

    @Override
    public void Update(GameContainer gc, int t) throws SlickException
    {
        if (clicked)
        {
            if (game.contains(xMouse, yMouse))
            {
                Hero hero = SavingStation.load();
                
                HUD hud = new HUD (hero);

                Bullets bulletsb = new Bullets(0,"./src/img/BULLET.jpg",2, 500);
                Wearpon wearponb = new Wearpon("./src/img/WEARPON.jpg",bulletsb,0);
                Inventory inventoryb = new Inventory();
                inventoryb.addWearpon(wearponb);
                BadGuy bad = new BadGuy (new String [] {"./src/img/CHARACTER.png","./src/img/CHARACTER.png"}, inventoryb, 5, 1000,100);

                Bullets bulletsb2 = new Bullets(0,"./src/img/BULLET.jpg",1, 200);
                Wearpon wearponb2 = new Wearpon("./src/img/WEARPON.jpg",bulletsb2,0);
                Inventory inventoryb2 = new Inventory();
                inventoryb2.addWearpon(wearponb2);
                BadGuy bad2 = new BadGuy (new String [] {"./src/img/CHARACTER.png","./src/img/CHARACTER.png"}, inventoryb2, 5, 900,400);
                
                Bullets bulletsb3 = new Bullets(0,"./src/img/BULLET.jpg",3, 1500);
                Wearpon wearponb3 = new Wearpon("./src/img/WEARPON.jpg",bulletsb3,0);
                Inventory inventoryb3 = new Inventory();
                inventoryb3.addWearpon(wearponb3);
                BadGuy bad3 = new BadGuy (new String [] {"./src/img/CHARACTER.png","./src/img/CHARACTER.png"}, inventoryb3, 2, 1200,-89999);

                ArrayList <BadGuy> b = new ArrayList <>();
                b.add(bad);
                b.add(bad2);
                b.add(bad3);

                Field fiel = new Field (hero,b, hud);

                Game.removeSence(this);
                Game.addScene(fiel);
                Game.addScene(hud);
            }
        }
        clicked = false;
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
