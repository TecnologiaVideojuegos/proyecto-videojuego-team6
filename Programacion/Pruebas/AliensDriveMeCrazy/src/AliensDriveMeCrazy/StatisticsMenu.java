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
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author mr.blissfulgrin
 */
public class StatisticsMenu extends Scene implements InputProviderListener
{
    private final Circle [] stage;
    private final float y;
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
    
    public StatisticsMenu (Hero hero)
    {
        this.hero = hero;
        this.y = 800;
        this.stage = new Circle [10];
        for (int i = 0; i < stage.length; i++)
        {
            stage [i] = new Circle(100*i + 400,y,20);
        }
        this.back = new Rectangle (100,100,100,50);
        this.right = new Rectangle (900,400,50,50);
        this.left = new Rectangle (1400,400,50,50);
        click = new BasicCommand("click");
        clicked = false;
    }
    
    @Override
    public void Render(GameContainer gc, Graphics g) throws SlickException
    {
        Game.getImages().getImage(Images.MENU).draw(0, 0,Game.getX(),Game.getY());
        g.setColor(Color.yellow);
        for (int i = 0; i < stage.length; i++)
        {
            if (i <= hero.getStage())
                g.fill(stage[i]);
            else
                g.draw(stage[i]);
            
            if (i < stage.length-1)
                g.drawLine(stage[i].getCenterX()+stage[i].getRadius(), y,stage[i+1].getCenterX()-stage[i+1].getRadius() , y);
        }
        hero.getBullets().draw(1230, 600,20,20);
        hero.getWearpon().draw(1020, 280, 300, 300);
        hero.getImg().draw(500,300,400,400);
        g.drawString(String.valueOf("Ammo: "+((hero.getBulletsMax()==0)? "inf":hero.getBulletsMax())), 1140, 600);
        g.drawString(String.valueOf("Kills: "+hero.getKills()), 400, 300);
        g.drawString(String.valueOf("HealthMax: "+hero.getHealthMax()), 400, 320);
        g.drawString(String.valueOf("Money: "+hero.getMoney()), 400, 340);
        g.fill(back);
        g.fill(right);
        g.fill(left);
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
                hero.goRightWearpon();
            else if (left.contains(xMouse, yMouse))
                hero.goLeftWearpon();
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
