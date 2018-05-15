
package shutterearth.map.randomGenerator;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class Salida extends Rectangle
{
    private Habitacion next;

    public Salida(Habitacion next, float x, float y, float width, float height) {
        super(x, y, width, height);
        this.next = next;
    }

    public Habitacion getNext() {
        return next;
    }
    
    public void setHab(Habitacion h){
        next = h;
    }
    
    public void render(Graphics g, boolean b)
    {
        g.setColor(Color.blue);
        g.setLineWidth(9);
        if(b) g.drawLine(this.getX(), this.getY(), this.getMaxX(), this.getY());
        else g.drawLine(this.getX(), this.getMaxY(), this.getMaxX(), this.getMaxY());
        g.setColor(Color.white);
        g.setLineWidth(1);
    }
}
