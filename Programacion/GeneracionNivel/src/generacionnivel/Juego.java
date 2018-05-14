
package generacionnivel;

import java.util.ArrayList;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;


public class Juego extends BasicGame{
    //CONSTANTES Y PROPORCIONES
    private final AppGameContainer g = new AppGameContainer(this);
    private final int gW = g.getScreenWidth();
    private final int gH = g.getScreenHeight();
    
    private Rectangle personaje = new Rectangle(10,10,Prop.chHALFW*2*g.getScreenWidth(), Prop.chH*g.getScreenHeight());
    
    private Input entrada;
    
    private Nivel nivel;
    
    public Juego(String t) throws SlickException{
        super(t);
        g.setDisplayMode(gW, gH, false);
        g.start();
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        entrada = new Input(700);
        nivel = new Nivel(g);
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        g.setBackground(Color.yellow);
        nivel.render(g);
        g.fill(personaje);
    }
    
    /*public void nivel1(){
        Habitacion hab = new Habitacion(g,celdas[0][7],geneId());
        System.out.println(hab.getX()+" - "+hab.getY()+"  =  "+hab.getWidth()+" - "+hab.getHeight());
        hab.addCelda(celdas[1][7]);
        System.out.println(hab.getX()+" - "+hab.getY()+"  =  "+hab.getWidth()+" - "+hab.getHeight());
        nivel.add(hab);
        hab= new Habitacion(g, celdas[1][6],geneId());
        nivel.add(hab);
        nivel.get(0).addSalidaSup(celdas[1][7], nivel.get(1));
        nivel.get(1).addSalidaInf(celdas[1][6], nivel.get(0));
    }*/
}
