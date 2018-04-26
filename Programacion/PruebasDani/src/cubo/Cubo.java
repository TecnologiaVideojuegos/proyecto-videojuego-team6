
package cubo;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Polygon;


public class Cubo extends Polygon {
    
    private Polygon base;
    private Ellipse elip;
    private Line l[] = new Line[4];
    private int indice;
    private int rot;
    private float size;
    
    public Cubo(float cx, float cy, float size){
        super();
        this.size = size;
        this.elip = new Ellipse(cx, cy, 4*size, 2*size,200);
        this.indice = elip.getPointCount()/4;
        this.rot = 0;
        
        for(int i=0;i<4;i++){
            this.addPoint(elip.getPoints()[i*indice*2], elip.getPoints()[i*indice*2+1]);
            System.out.println(elip.getPoints()[i*indice*2]+" - "+elip.getPoints()[i*indice*2+1]+"\n");
            this.l[i] = new Line(elip.getPoints()[i*indice*2], elip.getPoints()[i*indice*2+1]-(2*size),
                                elip.getPoints()[i*indice*2], elip.getPoints()[i*indice*2+1]+(2*size));
        }
        this.setCenterY(this.getCenterY()-(2*size));
        base = new Polygon(this.getPoints());
        base.setCenterY(this.getCenterY()+(4*size));
        
        
    }
    
    public void dibujar(Graphics g){
        //g.draw(new Ellipse(elip.getCenterX(),elip.getCenterY()+2*size,4*size, 2*size,200));
        g.draw(this);
        g.draw(base);
        for(int i=0;i<4;i++){
            g.draw(l[i]);
        }
    }
    
    public void rotar(){
        float x,y;
        for(int i=0;i<8;i+=2){
            x = elip.getPoints()[((i/2)*indice*2+rot*2)%elip.getPoints().length];
            y = elip.getPoints()[((i/2)*indice*2+rot*2+1)%elip.getPoints().length];
            this.points[i] = x;
            this.points[i+1] = y;
            l[i/2].setCenterX(x);
            l[i/2].setCenterY(y);
        }
        
        this.setCenterY(this.getCenterY()-(2*size));
        base = new Polygon(this.getPoints());
        base.setCenterY(base.getCenterY()+(4*size));
        
        rot++;
        rot%=elip.getPointCount();
    }
}
