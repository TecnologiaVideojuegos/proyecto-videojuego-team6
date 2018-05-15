
package shutterearth.map.randomGenerator;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import shutterearth.Game;
import shutterearth.map.Map;


public class Juego extends Map
{

    private Nivel nivel;

    public Juego (float x, float y)
    {
        super (x,y);
    }
    
    @Override
    public void init(GameContainer container) throws SlickException {}
    
    @Override
    public void Render(GameContainer gc, Graphics g) throws SlickException
    {
        nivel.render(g);
    }

    @Override
    public void Update(GameContainer gc, float t) throws SlickException
    {
    }

    @Override
    public String toString()
    {
        return "RANDOM GENERATOR";
    }

    @Override
    public float[][] getSpots(int n)
    {
        return nivel.getSpots(n);
    }

    @Override
    public float[] getNextRoom(int room, float x, boolean up, float w)
    {
        return nivel.getNextRoom(room, x, up, w);
    }

    @Override
    public void start()
    {
        try
        {
            nivel = new Nivel(Game.getContainer());
            Game.addScene(this);
        }
        catch (SlickException e)
        {
            System.out.println("ERROR LOADING RANDOM MAP... " +e.toString());
        }
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
