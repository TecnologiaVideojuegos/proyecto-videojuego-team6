
package generacionnivel;

import org.newdawn.slick.geom.Rectangle;

public class Salida extends Rectangle{
    private Habitacion next;

    public Salida(Habitacion next, float x, float y, float width, float height) {
        super(x, y, width, height);
        this.next = next;
    }

    public Habitacion getNext() {
        return next;
    }
}
