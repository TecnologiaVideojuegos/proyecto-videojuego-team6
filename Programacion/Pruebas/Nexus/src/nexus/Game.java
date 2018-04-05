
package nexus;

import java.util.ArrayList;
import java.util.Collections;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;


public class Game extends BasicGame
{
    private static AppGameContainer app;
    private static ArrayList<Scene> sences ;
    
    public Game (String gamename)
    {
        super(gamename);
        sences = new ArrayList<>();
    }
    
    // Add a scene to the list and call the init method
    public static void addScene (Scene sence)
    {
        sences.add(sence);
        try 
        {
            sence.init(app);
        } 
        catch (SlickException e) 
        {
            System.out.println("ERROR DE ESCENA " + e.toString());
        }
        Collections.sort(sences);
    }

    // Removes a scene
    public static void removeSence ( Scene sence )
    {
        for( int i = 0 ; i < sences.size() ; i++ )
        {
            if( sences.get(i).equals(sence) )
            {
                sences.remove(i);
            }
        }
    }

    @Override
    public void init(GameContainer gc) throws SlickException 
    {
        
        for( int i = 0 ; i < sences.size() ; i++ )
        {
            sences.get(i).init(gc);
        }
    }

    @Override
    public void update(GameContainer gc, int c) throws SlickException 
    {
        c *= 0.2;
        for( int i = 0 ; i < sences.size() ; i++ )
        {
            sences.get(i).update(gc,c);
        }
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException
    {
        for( int i = 0 ; i < sences.size() ; i++ )
        {
            sences.get(i).render(gc, g);
        }
    }

    public static void main(String[] args) throws SlickException
    {
        app = new AppGameContainer(new Game ("neXus"));
        app.setDisplayMode(app.getScreenWidth(),app.getScreenHeight()-100, false);
        app.setShowFPS(false);
        app.setTargetFrameRate(45);
        app.start();
    }
}