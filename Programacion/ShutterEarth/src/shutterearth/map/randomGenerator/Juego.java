
package shutterearth.map.randomGenerator;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;
import shutterearth.screens.Scene;
import shutterearth.Game;


public class Juego extends Scene{
    
    private Rectangle personaje = new Rectangle(10,10,Prop.chHALFW*2*Game.getX(), Prop.chH*Game.getY());
    
    private Input entrada;
    
    private Nivel nivel;

    @Override
    public void init(GameContainer container) throws SlickException {
        entrada = new Input(700);
        nivel = new Nivel(Game.getContainer());
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

    @Override
    public void Render(GameContainer gc, Graphics g) throws SlickException
    {
        nivel.render(g);
    }

    @Override
    public void Update(GameContainer gc, float t) throws SlickException
    {
    }

    @Override
    public String toString()
    {
        return "RANDOM GENERATOR";
    }
}
