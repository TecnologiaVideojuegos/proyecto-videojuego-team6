
package shutterearth.screens;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;


public abstract class Scene implements Comparable<Scene>
{
    // ESTADOS DE LA ESCENA
    public enum STATE { ON , FREEZE };
    private STATE state;
    private int prio = 0;            

    /**
     * CAPA DE ABSTRACCIÓN POR ENCIMA DE SLICK
     */
    public Scene ()
    {
        state = STATE.ON;
    }


    public abstract void Render(GameContainer gc, Graphics g) throws SlickException;
    public abstract void Update(GameContainer gc, float t) throws SlickException;
    public abstract void init(GameContainer gc) throws SlickException;


    public void render(GameContainer gc, Graphics g) throws SlickException 
    {
        Render(gc, g);
    }

    public void update(GameContainer gc, float t) throws SlickException {
        if(state == STATE.ON)
        {
            Update(gc, t);
        }
    }

    /**
     * APLICA PRIORIDA DE MOSTRADO
     * @param p 
     */
    public void setPriority ( int p )
    {
        prio = p;
    }

    public int getPriority ()
    {
        return prio;
    }

    /**
     * ORDENA ESCENAS
     * @param compareObject
     * @return 
     */
    @Override
    public int compareTo(Scene compareObject)
    {
        if (getPriority() < compareObject.getPriority())
            return -1;
        else if (getPriority() == compareObject.getPriority())
            return 0;
        else
            return 1;
    }
	
    /**
     * CAMBIA EL ESTADO DE LA SCENA
     * @param s 
     */
    public void setState( STATE s )
    {
        state = s;
    }
    
    public Boolean isFreezed ()
    {
        return state == STATE.FREEZE;
    }
    
    @Override
    public abstract String toString ();
}
