
package shutterearth.map.randomGenerator;

import java.util.ArrayList;
import javafx.util.Pair;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

public class Habitacion extends Rectangle implements Comparable<Habitacion>{
    private ArrayList <Salida> salidasSup = new ArrayList<>();
    private ArrayList <Salida> salidasInf = new ArrayList<>();
    private ArrayList <Rectangle> bulletLimits = new ArrayList<>();
    private int cellCount;
    
    private AppGameContainer g;
    private ArrayList<Celda> celdas = new ArrayList<>();
    private String id;
    
    public Habitacion(AppGameContainer g, Celda celda, String id){
        super(celda.getLocation().getX(), celda.getLocation().getY(), celda.getWidth(), celda.getHeight());
        this.g = g;
        cellCount = 1;
        celdas.add(celda);        
        this.id = id;
    }
    
    public void addCelda(Celda c){
        //Si esta a la derecha
        if(getX()<c.getX()) setBounds(getX(), getY(), getWidth()+c.getWidth(), getHeight());
        //Si esta a la izquierda
        else setBounds(getX()-c.getWidth(), getY(), getWidth()+c.getWidth(), getHeight());
        
        celdas.add(c);
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
    
    public ArrayList<Salida> getSalSup(){
        return salidasSup;
    }
    public ArrayList<Salida> getSalInf(){
        return salidasInf;
    }
    public void addSalidaSup(Salida s){
        salidasSup.add(s);
    }
    public void addSalidaInf(Salida s){
        salidasInf.add(s);
    }
    
    public void addBulletLimits(float x){
        if(x>this.getX()) bulletLimits.add(new Rectangle(x-5,this.getY(),5,this.getHeight()));
        else bulletLimits.add(new Rectangle(x,this.getY(),5,this.getHeight()));
    }
    
    public ArrayList<Rectangle> getBulletLimits(){
        return bulletLimits;
    }
    
    public ArrayList<Pair> spawn(int n){
        ArrayList<Pair> aux = new ArrayList<>();
        //Seleccionamos una x dentro de la habitacion como pto de spawn,
        //el resto de la info esta contenida en la propia Habitacion
        float x = ((float)(Math.random()*(getMaxX()-getX()))+getX());
        for(int i=0;i<n;i++) aux.add(new Pair(this, x));
        
        return aux;
    }
    
    public void render(Graphics g){
        //Primero dibujamos los fondos
        for(Celda c : celdas) c.render(g);
        
        //A continuacion dibujamos el contorno de la habitacion
        //g.drawString(toString(), getCenterX(), getCenterY()); //for testing
        g.setColor(Color.black);
        g.draw(this);
        
        //Despues dibujamos las marcas de las salidas y de las paredes internas
        for(Salida s : salidasInf){
            //g.draw(s); //for testing
            //g.drawString(s.getNext().toString(), s.getCenterX(), s.getCenterY());
            s.render(g, false);
        }
        for(Salida s : salidasSup){
            //g.draw(s); //for testing
            //g.drawString(s.getNext().toString(), s.getCenterX(), s.getCenterY());
            s.render(g, true);
        }
        g.setColor(Color.darkGray);
        for(Rectangle r : bulletLimits) g.fill(r);
        g.setColor(Color.white);
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
    
    @Override
    public int compareTo(Habitacion h){
        int comp = 0;
        if(this.getY()<h.getY()) comp = -1;
        else if(this.getY()>h.getY()) comp = 1;
        else{
            if(this.getX()<h.getX()) comp = -1;
            else if(this.getX()>h.getX()) comp = 1;
        }
        return comp;
    }
}
