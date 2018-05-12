
package generacionnivel;

import java.util.ArrayList;
import org.newdawn.slick.geom.Rectangle;

public class Habitacion extends Rectangle{
    private ArrayList <Salida> salidasSup;
    private ArrayList <Salida> salidasInf;
    private float maxI;
    private float maxD;
    
    public Habitacion(Rectangle celda){
        super(celda.getLocation().getX(), celda.getLocation().getY(), celda.getWidth(), celda.getHeight());
    }
    
    public void addCelda(Celda c){
        //Si esta a la derecha
        if(this.getX()>c.getX()) this.setBounds(this.getX(), this.getY(), this.getWidth()+c.getWidth(), this.getHeight());
        //Si esta a la izquierda
        else this.setBounds(this.getX()-c.getWidth(), this.getY(), this.getWidth()+c.getWidth(), this.getHeight());
    }
    
}
