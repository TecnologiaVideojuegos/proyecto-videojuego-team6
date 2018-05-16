
package shutterearth.map.randomGenerator;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import shutterearth.Game;
import shutterearth.Media;

public class Salida extends Rectangle
{
    private Habitacion next;
    private Rectangle salida;
    private Image imagen;

    public Salida(Habitacion next, float x, float y, float width, float height) {
        super(x, y, width, height);
        this.next = next;
        this.imagen = Game.getMedia().getImage(Media.IMAGE.ESCALERA);
        salida = new Rectangle(getX(), next.getY()-23f, getWidth(), (next.getHeight())+13f);//Solo se usa en salidas INF
    }

    public Habitacion getNext() {
        return next;
    }
    
    public void setHab(Habitacion h){
        next = h;
    }
    
    public void render(Graphics g, boolean b)
    {
        if(!b){
            g.texture(salida, imagen, true);
        }
    }
}
