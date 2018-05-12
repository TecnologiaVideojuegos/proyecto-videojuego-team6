
package generacionnivel;

import org.newdawn.slick.geom.Rectangle;

public class Salida extends Rectangle{
    private Habitacion next;

    public Salida(Habitacion next, Celda c) {
        super(c.getX(), c.getY(), c., height);
        this.next = next;
    }

    public Habitacion getNext() {
        return next;
    }
}
