
package shutterearth.map.randomGenerator;

import java.util.ArrayList;
import javafx.util.Pair;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class Habitacion extends Rectangle implements Comparable<Habitacion>
{
    private final ArrayList <Salida> salidasSup = new ArrayList<>();
    private final ArrayList <Salida> salidasInf = new ArrayList<>();
    private final ArrayList <Rectangle> bulletLimits = new ArrayList<>();
    private int cellCount;
    
    private final AppGameContainer g;
    private final ArrayList<Celda> celdas = new ArrayList<>();
    private final int id;
    private boolean[] lado = new boolean[2];//indica si es una hab izq o der
    
    private  Rectangle up;
    private  Rectangle down;
    private boolean first;
    
    public Habitacion(AppGameContainer g, Celda celda, int id)
    {
        super(celda.getLocation().getX(), celda.getLocation().getY(), celda.getWidth(), celda.getHeight());
        this.g = g;
        cellCount = 1;
        celdas.add(celda);        
        this.id = id;
        this.first = true;
    }
    
    public void setLado(int i, int d){
        if(i==1) lado[0] = false;
        else if(i==2) lado[0] = true;
        if(d==1) lado[1] = false;
        else if(d==2) lado[1] = true;
    }
    
    public int getLado(){
        
    }
    
    public void addCelda(Celda c)
    {
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
    public void addSalidaSup(Celda c, Habitacion h)
    {
        salidasSup.add(new Salida(h, c.getX()+(Prop.chHALFW*g.getScreenWidth()),
                c.getY(), (Prop.saWI*g.getScreenWidth()),
                (Prop.ceTHIRDH*g.getScreenHeight())));
    }
    
    /**
     * @param c Celda que pertenece a esta hab. Donde se va a poner la salida
     * @param h Nueva habitacion a la que conduce la salida 
     */
    public void addSalidaInf(Celda c, Habitacion h)
    {
        salidasInf.add(new Salida(h, c.getX()+(Prop.chHALFW*g.getScreenWidth()),
                c.getY()+(2*(Prop.ceTHIRDH*g.getScreenHeight())), (Prop.saWI*g.getScreenWidth()),
                (Prop.ceTHIRDH*g.getScreenHeight())));
    }
    
    public ArrayList<Salida> getSalSup()
    {
        return salidasSup;
    }
    public ArrayList<Salida> getSalInf()
    {
        return salidasInf;
    }
    public void addSalidaSup(Salida s)
    {
        salidasSup.add(s);
    }
    public void addSalidaInf(Salida s)
    {
        salidasInf.add(s);
    }
    
    public void addBulletLimits(float x)
    {
        if(x>this.getX()) bulletLimits.add(new Rectangle(x-10,this.getY(),10,this.getHeight()));
        else bulletLimits.add(new Rectangle(x,this.getY(),10,this.getHeight()));
    }
    
    public ArrayList<Rectangle> getBulletLimits()
    {
        return bulletLimits;
    }
    
    public ArrayList<Pair> spawn(int n)
    {
        ArrayList<Pair> aux = new ArrayList<>();
        //Seleccionamos una x dentro de la habitacion como pto de spawn,
        //el resto de la info esta contenida en la propia Habitacion
        float j = ((float)(Math.random()*(getMaxX()-getX()))+getX());
        for(int i=0;i<n;i++) aux.add(new Pair(this, j));
        
        return aux;
    }
    
    public void render(Graphics g)
    {
        if (first)
        {
            this.down = new Rectangle(this.x,this.maxY-10,this.width,10);
            this.up =new Rectangle(this.x,this.y,this.width,10);
            first = false;
        }
        //Primero dibujamos los fondos
        celdas.forEach((c) ->
        {
            c.render(g);
        });
        
        //BORDES
        g.setColor(Color.darkGray);
        g.fill(down);
        g.fill(up);
        //A continuacion dibujamos el contorno de la habitacion
        //g.drawString(toString(), getCenterX(), getCenterY()); //for testing
        g.setColor(Color.black);
        g.draw(this);
        
        //Despues dibujamos las marcas de las salidas y de las paredes internas
        salidasInf.forEach((s) ->
        {
            //g.draw(s); //for testing
            //g.drawString(s.getNext().toString(), s.getCenterX(), s.getCenterY());
            s.render(g, false);
        });
        salidasSup.forEach((s) ->
        {
            //g.draw(s); //for testing
            //g.drawString(s.getNext().toString(), s.getCenterX(), s.getCenterY());
            s.render(g, true);
        });
        g.setColor(Color.darkGray);
        bulletLimits.forEach((r) ->
        {
            g.fill(r);
        });
        
        g.setColor(Color.white);
    }
    
    public int getCount()
    {
        return cellCount;
    }
    
    public void addCount()
    {
        cellCount++;
    }
    
    public int getId(){
        return id;
    }
    
    @Override
    public String toString()
    {
        return id+" - "+cellCount;
    }
    
    @Override
    public int compareTo(Habitacion h)
    {
        int comp = 0;
        if(this.getY()<h.getY()) comp = -1;
        else if(this.getY()>h.getY()) comp = 1;
        else
        {
            if(this.getX()<h.getX()) comp = -1;
            else if(this.getX()>h.getX()) comp = 1;
        }
        return comp;
    }
}
