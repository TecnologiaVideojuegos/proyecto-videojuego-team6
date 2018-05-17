
package shutterearth.map.randomGenerator;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import shutterearth.Game;
import shutterearth.Media;

/**
 * Clase esencial en la generacion procedural del Nivel. Unidad minima de
 * informacion en este proceso que almacena si ha sido visitada o no y la
 * Habitacion a la que ha sido asignada. Contiene además su tesela con la que
 * contribuira al fondo de su Habitacion
 */
public class Celda extends Rectangle
{
    /**Indica si la habitacion ha sido visitada o no en el algoritmo de generacion*/
    private boolean visited;
    /**Referencia a la Habitacion a la que ha sido asignada*/
    private Habitacion hab = null;
    /**Tesela de fondo asignada a esta Celda*/
    private final Image img;

    /**
     * Constructor. Instancia una Celda con unas coordenadas y proporciones concretas
     * y se le asigna aleatoriamente una tesela de fondo
     * @param x
     * @param y
     * @param width
     * @param height
     * @throws SlickException 
     */
    public Celda(float x, float y, float width, float height) throws SlickException
    {
        super(x, y, width, height);
        this.visited = false;
        int rand = ((int)(Math.random()*70))%7;
        this.img = Game.getMedia().getImage(Media.getBackGround(rand));
    }

    /**
     * Devuleve si la Celda ha sido visitada o no
     * @return 
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     * Establece si la Celda ha sido visitada o no
     * @param visited 
     */
    public void setVisited(boolean visited) 
    {
        this.visited = visited;
    }

    /**
     * Devuelve la Habitacion asignada a esta Celda
     * @return 
     */
    public Habitacion getHab() {
        return hab;
    }

    /**
     * Modifica la Habitacion asignada a esta Celda
     * @param hab 
     */
    public void setHab(Habitacion hab) 
    {
        this.hab = hab;
    }
    
    /**
     * Permite renderizar la tesela de esta Celda
     * @param g 
     */
    public void render(Graphics g)
    {
        g.texture(this, img, true);
    }
}
