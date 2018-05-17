package shutterearth;

import java.util.ArrayList;
import java.util.Collections;
import org.lwjgl.openal.AL;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import shutterearth.characters.Hero;
import shutterearth.characters.SavedHero;
import shutterearth.screens.Dev;
import shutterearth.screens.Scene;


public class Game extends BasicGame
{
    private static AppGameContainer app;
    private static ArrayList<Scene> scenes ;
    private static int X;
    private static int Y;
    private static SavingStation savingStation;
    private static Media media;
    private static float gravity;
    private static float gravityMax;
    private static float xVel;
    private static float yVelUp;
    private static float yVelDown;
    private static Dev developer;
    private static boolean debug;
    private float delta;
    private static float step;
    private static float reward;
    
    public static AppGameContainer getContainer ()
    {
        return app;
    }
    
    public synchronized static void setDebug (boolean debug)
    {
        Game.debug = debug;
    }
    
    public synchronized static boolean debug ()
    {
        return Game.debug;
    }
    
    public synchronized static float step ()
    {
        return Game.step;
    }
    
    public static float getReward()
    {
        return reward;
    }
    
    public Game (String gamename)
    {
        super(gamename);
        scenes = new ArrayList<>();
        savingStation = new SavingStation();
        developer = null;
    }
    
    // Add a scene to the list and call the init method
    public static void addScene (Scene scene)
    {
        if (!scenes.contains(scene))
            scenes.add(scene);
        try 
        {
            scene.init(app);
        } 
        catch (SlickException e) 
        {
            System.out.println("ERROR DE ESCENA " + e.toString());
        }
        Collections.sort(scenes);
    }

    // Removes a scene
    public static void removeSence (Scene scene)
    {
        scenes.remove(scene);
    }

    @Override
    public void init(GameContainer gc) throws SlickException 
    {
        media = new Media();
        Game.addScene(media);

        for( int i = 0 ; i < scenes.size() ; i++ )
        {
            scenes.get(i).init(gc);
        }
    }

    @Override
    public void update(GameContainer gc, int c) throws SlickException 
    {
        delta = c * 0.1f;
        for( int i = 0 ; i < scenes.size() ; i++ )
        {
            scenes.get(i).update(gc,delta);
        }
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException
    {
        for( int i = 0 ; i < scenes.size() ; i++ )
        {
            scenes.get(i).render(gc, g);
        }
    }
    
    public static int getX()
    {
        return X;
    }
    
    public static int getY()
    {
        return Y;
    }
    public static void setX(int x)
    {
        X =x;
    }
    
    public static void setY( int y)
    {
        Y = y;
    }

    public static void main(String[] args) throws SlickException
    {
        app = new AppGameContainer(new Game ("Shutter Earth"));
        setX(app.getScreenWidth());
        setY(app.getScreenHeight());
        
        //MAC PROPORTION 1764000px
        //X 1680px
        //Y 1050px
        
        Game.gravityMax = Game.getY()*50f/1050;
        Game.xVel = Game.getX()*1.8f/1680;
        Game.yVelUp = -Game.getY()*18f/1050;
        Game.yVelDown = -Game.getY()*8f/1050;
        Game.gravity = -yVelUp/18f;
        Game.debug = false;
        Game.reward = 1.3f;
        Game.step = ((Game.getY()*12)/105f);
        /*
        System.out.println("-----------------------");
        System.out.println(Game.X);
        System.out.println(Game.Y);
        System.out.println(Game.gravity);
        System.out.println(Game.gravityMax);
        System.out.println(Game.xVel);
        System.out.println(Game.yVelUp);
        System.out.println(Game.yVelDown);
        System.out.println(Game.step);
        System.out.println("-----------------------");
        */
        app.setDisplayMode(X,Y, true);
        app.setShowFPS(false);
        app.setTargetFrameRate(45);
        app.start();
    }

    public static SavedHero load(String user, String pswd)
    {
        return savingStation.load(user, pswd);
    }
    public static SavedHero load(String user)
    {
        return savingStation.load(user);
    }

    public static boolean add(SavedHero hero)
    {
        return savingStation.add(hero);
    }
    
    public static void save (Hero hero)
    {
        savingStation.save(hero);
    }
    
    public static void save (SavedHero hero)
    {
        savingStation.save(hero);
    }
    
    public static Media getMedia ()
    {
        return media;
    }
    
    public static float getGravity ()
    {
        return gravity;
    }
    public static float getGravityMax ()
    {
        return gravityMax;
    }
    public static float getxVel ()
    {
        return xVel;
    }
    public static float getyVelDown ()
    {
        return yVelDown;
    }
    public static float getyVelUp ()
    {
        return yVelUp;
    }
    
    private static void resetDeveloper()
    {
        if (developer != null)
        {
            developer.end();
            try
            {
                developer.join(1000);
            } 
            catch (InterruptedException ex)
            {
                System.out.println("DEV ERROR");
            }
            developer = null;
        }
    }

    public static void exit()
    {
        resetDeveloper();
        savingStation.save();
        AL.destroy();
        System.exit(0);
    }
    
    public static void develop(SavedHero hero)
    {
        if (developer == null)
        {
            if (hero.getPermission())
            {
                developer = new Dev(hero);
                //developer.start();
            }
        }
        else 
        {
            developer.setHero(hero);
        }
    }
    
    public synchronized static int numberScenes ()
    {
        return scenes.size();
    }
    
    public synchronized static void sceneLog()
    {
        scenes.forEach((s) ->
        {
            System.out.println(s.toString());
        });
    }
}