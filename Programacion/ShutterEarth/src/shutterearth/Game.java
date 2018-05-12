package shutterearth;

import java.util.ArrayList;
import java.util.Collections;
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
    private static Dev developer;
    
    public Game (String gamename)
    {
        super(gamename);
        scenes = new ArrayList<>();
        savingStation = new SavingStation();
        Game.gravity = 0.1f;
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
        c *= 0.1;
        for( int i = 0 ; i < scenes.size() ; i++ )
        {
            scenes.get(i).update(gc,c);
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
        setX(app.getScreenWidth()-50);
        setY(app.getScreenHeight()-50);
        app.setDisplayMode(X,Y, false);
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
    
    private static void resetDeveloper()
    {
        if (developer != null)
        {
            developer.end();
            try
            {
                developer.join();
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
        System.exit(0);
    }
    
    public static void develop(SavedHero hero)
    {
        if (developer == null)
        {
            developer = new Dev(hero);
            developer.start();
        }
        else 
        {
            developer.setHero(hero);
        }
    }
}