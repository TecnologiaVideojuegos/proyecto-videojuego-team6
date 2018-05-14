
package generacionnivel;

import java.util.ArrayList;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class Habitacion extends Rectangle{
    private ArrayList <Salida> salidasSup = new ArrayList<>();
    private ArrayList <Salida> salidasInf = new ArrayList<>();
    private int cellCount;
    
    private AppGameContainer g;
    private Color color;
    private String id;
    
    public Habitacion(AppGameContainer g, Rectangle celda, String id){
        super(celda.getLocation().getX(), celda.getLocation().getY(), celda.getWidth(), celda.getHeight());
        this.g = g;
        cellCount = 1;
        
        Color[] diccionario = {Color.blue,Color.orange,Color.green,Color.magenta,Color.cyan,Color.yellow,Color.pink};
        color = diccionario[((int)(Math.random()*700))%diccionario.length];
        
        this.id = id;
    }
    
    public void addCelda(Celda c){
        //Si esta a la derecha
        if(getX()<c.getX()) setBounds(getX(), getY(), getWidth()+c.getWidth(), getHeight());
        //Si esta a la izquierda
        else setBounds(getX()-c.getWidth(), getY(), getWidth()+c.getWidth(), getHeight());
        
        cellCount++;
    }
    
    /**
     * @param c Celda que pertenece a esta hab. Donde se va a poner la salida
     * @param h Nueva habitacion a la que conduce la salida
     */
    public void addSalidaSup(Celda c, Habitacion h){
        salidasSup.add(new Salida(h, c.getX()+(Prop.chHALFW*g.getScreenWidth()),
                c.getY(), (Prop.saWI*g.getScreenWidth()),
                (Prop.ceTHIRDH*g.getScreenHeight())));
    }
    
    /**
     * @param c Celda que pertenece a esta hab. Donde se va a poner la salida
     * @param h Nueva habitacion a la que conduce la salida 
     */
    public void addSalidaInf(Celda c, Habitacion h){
        salidasInf.add(new Salida(h, c.getX()+(Prop.chHALFW*g.getScreenWidth()),
                c.getY()+(2*(Prop.ceTHIRDH*g.getScreenHeight())), (Prop.saWI*g.getScreenWidth()),
                (Prop.ceTHIRDH*g.getScreenHeight())));
    }
    
    public void render(Graphics g){
        g.setColor(color);
        g.fill(this);
        g.setColor(Color.white);
        g.drawString(toString(), getCenterX(), getCenterY());
        for(Salida s : salidasInf){
            g.draw(s);
            g.drawString(s.getNext().toString(), s.getCenterX(), s.getCenterY());
        }
        for(Salida s : salidasSup){
            g.draw(s);
            g.drawString(s.getNext().toString(), s.getCenterX(), s.getCenterY());
        }
    }
    
    public int getCount(){
        return cellCount;
    }
    
    public void addCount(){
        cellCount++;
    }
    
    public String toString(){
        return id+" - "+cellCount;
    }
}
