
package shutterearth.map.randomGenerator;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import shutterearth.Game;
import shutterearth.map.Map;


public class Juego extends Map
{

    private Nivel nivel;
    private int errorCount;

    public Juego (float x, float y)
    {
        super (x,y);
        try
        {
            nivel = new Nivel(Game.getContainer());
            
        }
        catch (SlickException e)
        {
            System.out.println("ERROR LOADING RANDOM MAP... " +e.toString());
        }
        errorCount = 5;
    }
    
    @Override
    public void init(GameContainer container) throws SlickException {}
    
    @Override
    public void Render(GameContainer gc, Graphics g) throws SlickException
    {
        nivel.render(g);
    }

    @Override
    public void Update(GameContainer gc, float t) throws SlickException{}

    @Override
    public String toString()
    {
        return "RANDOM GENERATOR";
    }

    @Override
    public float[][] getSpots(int n)
    {
        while (errorCount > 0)
        {
            try
            {
                return nivel.getSpots(n);
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
                errorCount --;
            }
        }
        return null;
    }

    @Override
    public float[] getNextRoom(int room, float x, boolean up, float w, boolean hero)
    {
        while (errorCount > 0)
        {
            try
            {
                return nivel.getNextRoom(room, x, up, w, hero);
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
                errorCount --;
            }
        }
        return null;
    }

    @Override
    public void start()
    {
        Game.addScene(this);
    }

    @Override
    public void pause(){}

    @Override
    public void wake(){}

    @Override
    public void end()
    {
        Game.removeSence(this);
    }
}
