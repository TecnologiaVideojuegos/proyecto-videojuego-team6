
package shutterearth.map.randomGenerator;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import shutterearth.Game;
import shutterearth.Media;

/**
 * Clase que guarda una referencia de la Habitacion a la que conduce desde la
 * Habitacion en la que se encuentra. Permite evaluar mediante interseccion o
 * colision si el personaje se encuentra en una salida
 */
public class Salida extends Rectangle
{
    /**Referencia a la Habitacion a la que conduce la Salida desde la Habitacion en la que se encuentra*/
    private Habitacion next;
    /**Estos dos atributos permiten renderizar la imagen de la salida en el nivel: las escaleras*/
    private final Rectangle salida;
    /**Estos dos atributos permiten renderizar la imagen de la salida en el nivel: las escaleras*/
    private final Image imagen;

    /**
     * Constructor. Genera una salida en la posicion adecuada que conduce o guarda
     * como referencia la Habitacion next
     * @param next
     * @param x
     * @param y
     * @param width
     * @param height 
     */
    public Salida(Habitacion next, float x, float y, float width, float height) {
        super(x, y, width, height);
        this.next = next;
        this.imagen = Game.getMedia().getImage(Media.IMAGE.ESCALERA);
        salida = new Rectangle(getX(), next.getY()-Game.getY()/32, getWidth(), (Game.step())+Game.getY()/41);//Solo se usa en salidas INF
    }

    /**
     * Devuelve la Habitacion a la que conduce la Salida
     * @return 
     */
    public Habitacion getNext() {
        return next;
    }
    
    /**
     * Modifica la referencia de la Habitacion a la que conduce la Salida
     * @param h 
     */
    public void setHab(Habitacion h){
        next = h;
    }
    
    /**
     * Permite renderizar la Salida
     * @param g
     * @param b 
     */
    public void render(Graphics g, boolean b)
    {
        if(!b){
            g.texture(salida, imagen, true);
        }
    }
}
