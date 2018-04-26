
package cubo;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Transform;

public class Juego extends BasicGame{
    private AppGameContainer g;
    private Cubo cubo;
    private Cubo c[] = new Cubo[4];
    
    private Polygon r1, r2;
    private Line l1, l2, l3, l4;
    
    private Ellipse e;
    private float ePto[];
    private int indice[] = new int[4];
    private int rot = 0;
    
    private int contador = 1000/32; //Rota 4 veces por segundo
    
    
    public Juego(String t) throws SlickException{
        super(t);
        this.g = new AppGameContainer(this);
        System.out.print(g.getScreenWidth()+"  -  ");
        System.out.print(g.getScreenHeight()+"\n\n");
        g.setDisplayMode(800, 600, false);
        
        g.start();
        
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        cubo = new Cubo(400,300,50);
        
        c[0] = new Cubo(100,100,10);
        c[1] = new Cubo(156.5685424949f,100,10);
        c[2] = new Cubo(100,140,10);
        c[3] = new Cubo(156.5685424949f,140,10);
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        if(contador<=0){
            cubo.rotar();
            for(Cubo ci : c) ci.rotar();
            
            contador = 1000/32;
        } else contador-=delta;
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException{
        cubo.dibujar(g);
        //for(Cubo ci : c) ci.dibujar(g);
        g.drawString("Cube", 380, 550);
    }
    
    public void rotacion(int n){
        for(int i=0;i<4;i++){
            r1.transform(Transform.createRotateTransform(7));
            
        }
    }
}
