
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
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import shutterearth.Game;
import shutterearth.Media;
import shutterearth.characters.Hero;
import shutterearth.characters.SavedHero;
import shutterearth.map.Field;
import shutterearth.map.HUD;

/**
 *
 * @author mr.blissfulgrin
 */
public class Maper extends Scene  implements InputProviderListener
{
    private InputProvider provider;
    private final Command click;
    private final Control mouse = new MouseButtonControl(0);
    private final SavedHero hero;
    private Input input;
    private boolean clicked;
    private int xMouse;
    private int yMouse;
    
    private final Rectangle exit;
    private final Circle [] level;
    private final int w;
    
    private final Circle animation;
    private boolean animationStarted;
    private int stage;
    private final int diagonal;
    private float radix;
    
    public Maper (SavedHero hero)
    {
        click = new BasicCommand("click");
        exit = new Rectangle (Game.getX()/14,Game.getY()/14,Game.getX()/16,Game.getY()/20);
        
        this.level = new Circle [10];
        
        this.w = Game.getX()/20;
        int step = Game.getX()/22;
        int start = Game.getX()/2 - w*5 - step*4;
        
        for (int x = 0; x < level.length; x++)
        {
            level [x] = new Circle(start + (w*x) + (step*x), Game.getY()/2 - w/2, w/2);
        }
        
        this.hero = hero;
        
        diagonal = (Game.getX()^2+Game.getY()^2)^(1/2);
        animationStarted = false;
        radix = 0;
        animation = new Circle (0,0,0);
        stage = 0;
    }
    
    @Override
    public void Render(GameContainer gc, Graphics g) throws SlickException
    {
        Game.getMedia().getImage(Media.IMAGE.MENU).draw(0,0,Game.getX(),Game.getY());
        g.fill(exit);
        Game.getMedia().getImage(Media.IMAGE.BACK).draw(exit.getX(),exit.getY(),exit.getWidth(),exit.getHeight());
        for (int x = 0; x < level.length; x++)
        {
            if (x+1 <= hero.getStage())
            {
                g.fill(level[x]);
            }
            else
            {
                g.draw(level[x]);
            }
            
            if (x < level.length - 1)
            {
                g.drawLine(level[x].getCenterX()+(w/2), level[x].getCenterY(), level[x+1].getCenterX()-(w/2), level[x].getCenterY());
            }
        }
        g.setColor(Color.black);
        g.fill(animation);
        g.setColor(Color.yellow);
    }

    @Override
    public void Update(GameContainer gc, float t) throws SlickException
    {
        if (!animationStarted)
        {
            if (clicked)
            {
                if (exit.contains(xMouse, yMouse))
                {
                    Game.getMedia().getSound(Media.SOUND.SHOT).play();
                    provider.unbindCommand(mouse);
                    provider.removeListener(this);
                    Game.removeSence(this);
                    Game.addScene(new StartMenu(hero));
                }
                for (int x = 0; x < (level.length < hero.getStage()-1?level.length:hero.getStage()); x++)
                {  
                    if (level[x].contains(xMouse, yMouse))
                    {
                        Game.getMedia().getSound(Media.SOUND.ALIEN1).play();
                        animationStarted = true;
                        stage = x + 1;
                    }
                }
            }
        }
        else
        {
            radix = animation.getRadius() + 5f*t;
            animation.setRadius(radix);
            animation.setCenterX(Game.getX()/2);
            animation.setCenterY(Game.getY()/2);
            if (radix > diagonal)
            {
                provider.unbindCommand(mouse);
                provider.removeListener(this);
                Hero h = new Hero (hero);
                HUD hud = new HUD(h);
                Field field = new Field(h,stage,hud);
                field.start();
                Game.removeSence(this);
            }
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
        return "Maper "+this.hero.toString()+" "+this.stage;
    }
}
