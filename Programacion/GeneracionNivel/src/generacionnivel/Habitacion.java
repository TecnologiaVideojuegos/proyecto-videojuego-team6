
package generacionnivel;

import java.util.ArrayList;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class Habitacion extends Rectangle{
    private ArrayList <Salida> salidasSup;
    private ArrayList <Salida> salidasInf;
    private float maxI;
    private float maxD;
    
    private AppGameContainer g;
    private Color color;
    
    public Habitacion(AppGameContainer g, Rectangle celda){
        super(celda.getLocation().getX(), celda.getLocation().getY(), celda.getWidth(), celda.getHeight());
        this.g = g;
        
        Color[] diccionario = {Color.blue,Color.orange,Color.green,Color.magenta,Color.cyan,Color.yellow,Color.pink};
        color = diccionario[((int)Math.random()*700)%diccionario.length];
    }
    
    public void addCelda(Celda c){
        //Si esta a la derecha
        if(this.getX()>c.getX()) this.setBounds(this.getX(), this.getY(), this.getWidth()+c.getWidth(), this.getHeight());
        //Si esta a la izquierda
        else this.setBounds(this.getX()-c.getWidth(), this.getY(), this.getWidth()+c.getWidth(), this.getHeight());
    }
    
    public void addSalidaSup(Celda c, Habitacion h){
        salidasSup.add(new Salida(h, c.getX()+(Prop.chHALFW*g.getScreenWidth()),
                c.getY(), (Prop.saWI*g.getScreenWidth()),
                (Prop.ceTHIRDH*g.getScreenHeight())));
    }
    
    public void addSalidaInf(Celda c, Habitacion h){
        salidasInf.add(new Salida(h, c.getX()+(Prop.chHALFW*g.getScreenWidth()),
                c.getY()+(2*(Prop.ceTHIRDH*g.getScreenHeight())), (Prop.saWI*g.getScreenWidth()),
                (Prop.ceTHIRDH*g.getScreenHeight())));
    }
    
    public void render(Graphics g){
        g.setColor(color);
        g.fill(this);
    }
}
