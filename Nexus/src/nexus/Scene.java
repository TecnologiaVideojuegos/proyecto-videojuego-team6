
package nexus;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public abstract class Scene implements Comparable<Scene>
{
    // The states a scene can be in
    public enum STATE { ON , FREEZE , INVISIBLE };
    // The current state is saved in this variable
    private STATE state;
    // The render priority - We will need this to decide whitch scene is renderd first
    protected int prio = 0;            
    // An Image "Buffer" to hold the last active frame when our scene get freezed
    private Image sence;
    
    private Boolean freezed;

    public Scene (int x, int y)
    {
        // Default state is on
        state = STATE.ON;
        try 
        {
                // Change to your resolution
                // Init the buffer
                sence = new Image(x,y);
        } 
        catch (SlickException e) 
        {
            System.out.println("ERROR DE ESCENA " + e.toString());
        }
        freezed = false;
    }


    // These methods will be used by the "real" scenes that inherit from this scene
    public abstract void Render(GameContainer gc, Graphics g) throws SlickException;
    public abstract void Update(GameContainer gc, int t) throws SlickException;
    public abstract void init(GameContainer gc) throws SlickException;


    // This render method get called by the Scene-Manager
    // It handles if the scene is visible/frozen/on
    // This method calls our "CustomRender"
    public void render(GameContainer gc, Graphics g) throws SlickException {
        if( state != STATE.INVISIBLE )
        {
            if( state == STATE.ON) 
            {
                Render(gc, g );
            }
            if( state == STATE.FREEZE)
            {
                sence.getGraphics().clear();
                Render(gc, sence.getGraphics());
                freezed = true;
            }
            if(freezed)
            {
                g.drawImage(sence, 0, 0);
            }
        }
    }

    // Update method that is called by the Scene-Manager
    // This method calls our "CustomUpdate"
    public void update(GameContainer gc, int t) throws SlickException {
        if(state == STATE.ON)
        {
            Update(gc, t);
        }
    }

    // Set the render priority
    public void setPriority ( int p )
    {
        prio = p;
    }

    // Returns the render priority
    public int getPriority ()
    {
        return prio;
    }

    // Used to compare two objects in an Collection
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
	
    // Set the current state of the scene
    public void setState( STATE s )
    {
        state = s;
        if (state != STATE.FREEZE)
            freezed = false;
    }
    
    public Boolean isFreezed ()
    {
        return freezed;
    }
}
