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
import shutterearth.screens.Scene;
import shutterearth.screens.StartMenu;


public class Game extends BasicGame implements SavingStationInterface
{
    private static AppGameContainer app;
    private static ArrayList<Scene> scenes ;
    private static int X;
    private static int Y;
    private static SavingStation savingStation;
    
    public Game (String gamename)
    {
        super(gamename);
        scenes = new ArrayList<>();
        savingStation = new SavingStation();
    }
    
    // Add a scene to the list and call the init method
    public static void addScene (Scene sence)
    {
        scenes.add(sence);
        try 
        {
            sence.init(app);
        } 
        catch (SlickException e) 
        {
            System.out.println("ERROR DE ESCENA " + e.toString());
        }
        Collections.sort(scenes);
    }

    // Removes a scene
    public static void removeSence ( Scene sence )
    {
        scenes.remove(sence);
    }

    @Override
    public void init(GameContainer gc) throws SlickException 
    {
        StartMenu startMenu = new StartMenu();
        scenes.add(startMenu);
        
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
        setX(app.getScreenWidth());
        setY(app.getScreenHeight());
        app.setDisplayMode(X,Y, true);
        app.setShowFPS(false);
        app.setTargetFrameRate(45);
        app.start();
    }

    @Override
    public SavedHero load(String user, String pswd)
    {
        return savingStation.load(user, pswd);
    }

    @Override
    public boolean add(Hero hero)
    {
        return savingStation.add(hero);
    }

    @Override
    public void save()
    {
        savingStation.save();
    }
}