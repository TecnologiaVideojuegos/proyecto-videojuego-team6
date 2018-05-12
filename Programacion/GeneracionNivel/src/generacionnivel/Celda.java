
package generacionnivel;

import org.newdawn.slick.geom.Rectangle;

public class Celda extends Rectangle{
    private boolean visited;
    private Habitacion hab = null;

    public Celda(float x, float y, float width, float height) {
        super(x, y, width, height);
        this.visited = false;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Habitacion getHab() {
        return hab;
    }

    public void setHab(Habitacion hab) {
        this.hab = hab;
    }    
}
