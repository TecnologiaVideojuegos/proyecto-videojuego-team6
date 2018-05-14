
package generacionnivel;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Celda extends Rectangle{
    private boolean visited;
    private Habitacion hab = null;
    private Image img;

    public Celda(float x, float y, float width, float height) throws SlickException{
        super(x, y, width, height);
        this.visited = false;
        int rand = ((int)(Math.random()*70))%7;
        this.img = new Image("image/"+rand+".png");
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
    
    public void render(Graphics g){
        g.texture(this, img, true);
    }
}
