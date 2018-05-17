
package shutterearth.map.randomGenerator;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import shutterearth.Game;
import shutterearth.map.Map;

/**
 * Clase intermedia. Instancia un nivel y proporciona los métodos necesarios
 * para que el resto del programa obtenga de él la información necesaria. 
 * Permite también incorporar el nivel al game loop.
 */
public class Juego extends Map
{
    /**Almacena el nivel instanciado*/
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

    /**
     * Permite invocar el metodo getSpots() de Nivel con los parametros adecuados
     * para su integracion con el resto del progrma.
     * <p> Consultar Nivel.getSpots() javadoc
     * @param n Num de arrays de coordenadas solicitado
     * @return 
     */
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
    
    /**
     * Permite invocar el metodo bulletControl() de Nivel con los parametros adecuados
     * para su integracion con el resto del progrma.
     * <p> Consultar Nivel.bulletControl() javadoc
     * @param x
     * @param y
     * @return 
     */
    @Override
    public float[] bulletControl (float x, float y)
    {
        return nivel.bulletControl(x, y);
    }

    /**
     * Permite invocar el metodo getNextRoom() de Nivel con los parametros adecuados
     * para su integracion con el resto del progrma.
     * <p> Consultar Nivel.getNextRoom() javadoc
     * @param room
     * @param x
     * @param up
     * @param w
     * @param hero
     * @return 
     */
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
