
package shutterearth.map.randomGenerator;

import java.util.ArrayList;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

/**
 * Clase rpincipal del Nivel de juego. Contiene entre otras cosas referencias a
 * las Habitaciones vecinas a traves de sus Salidas y toda la informacion necesaria
 * para la interaccion y la colision de los personajes, balas y demas elemntos
 * dentro de ella
 */
public class Habitacion extends Rectangle implements Comparable<Habitacion>
{
    /**Almacena una lista con las Salidas superiores de la Habitacion*/
    private final ArrayList <Salida> salidasSup = new ArrayList<>();
    /**Almacena una lista con las Salidas inferiores de la Habitacion*/
    private final ArrayList <Salida> salidasInf = new ArrayList<>();
    /**Almacena una lista con las los limites internos de una habitacion*/
    private final ArrayList <Rectangle> bulletLimits = new ArrayList<>();
    /**Almacena el numero de Celdas que componen la Habitacion*/
    private int cellCount;
    
    private final AppGameContainer g;
    /**Almacena una lista con las Celdas qeu componen la Habitacion*/
    private final ArrayList<Celda> celdas = new ArrayList<>();
    /**Identificador de la Habitacion*/
    private final int id;
    /**Almacena info sobre si la Habitacion es izquierda, derecha o central*/
    private final boolean[] lado = new boolean[2];//indica si es una hab izq o der
    
    /**Permite renderizar el rectangulo del techo*/
    private  Rectangle up;
    /**Permite renderizar el rectangulo del suelo*/
    private  Rectangle down;
    /**Indica si ya se han instanciado los rectangulos del suelo y del techo*/
    private boolean first;
    
    /**
     * Constructor. Instancia una nueva Habitacion con las dimesiones de la
     * Celda inicial y un identificador
     * @param g
     * @param celda
     * @param id 
     */
    public Habitacion(AppGameContainer g, Celda celda, int id)
    {
        super(celda.getLocation().getX(), celda.getLocation().getY(), celda.getWidth(), celda.getHeight());
        this.g = g;
        cellCount = 1;
        celdas.add(celda);        
        this.id = id;
        
        this.first = true;
        
        lado[0]=true;
        lado[1]=true;
    }
    
    /**
     * Establece si la Habitacion esta sola respecto a su lado izquierdo
     * (no tiene vecinas a la izquierda) o su lado derecho
     * @param i 1=false 2=true
     * @param d 1=false 2=true
     */
    public void setLado(int i, int d){
        if(i==1) lado[0] = false;
        else if(i==2) lado[0] = true;
        if(d==1) lado[1] = false;
        else if(d==2) lado[1] = true;
    }
    
    /**
     * Devuelve codificado para que el resto del programa lo entienda si se trata
     * una Habitacion en el lado izquierdo, derecho o una Habitacion central con
     * o sin vecinos a ambos lados
     * @return 
     */
    public int getLado(){
        int aux;
        if(lado[0]&&lado[1]) aux = 2; //abierto a ambos lados
        else if(!lado[0]&&!lado[1]) aux = 3; //central
        else if(lado[0]) aux = 0;//izquierda
        else aux = 1;//derecha
        return aux;
    }
    
    /**
     * Permite añadir una nueva Celda a la Habitacion. Las diensiones de esta se
     * suman e incrementan las de la Habitacion. Modifica además el contador de Celdas
     * @param c 
     */
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
     * Permite crear una nueva Salida Superior para esta Habitacion en la posicion de la
     * Celda proporcionada como parametro. Dicha Celda ya debe formar parte de la
     * Habitacion. El segundo parametro proporciona la referencia de la Habitacion
     * a la que conducira la Salida.
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
     * Permite crear una nueva Salida Inferior para esta Habitacion en la posicion de la
     * Celda proporcionada como parametro. Dicha Celda ya debe formar parte de la
     * Habitacion. El segundo parametro proporciona la referencia de la Habitacion
     * a la que conducira la Salida.
     * @param c Celda que pertenece a esta hab. Donde se va a poner la salida
     * @param h Nueva habitacion a la que conduce la salida 
     */
    public void addSalidaInf(Celda c, Habitacion h)
    {
        salidasInf.add(new Salida(h, c.getX()+(Prop.chHALFW*g.getScreenWidth()),
                c.getY()+(2*(Prop.ceTHIRDH*g.getScreenHeight())), (Prop.saWI*g.getScreenWidth()),
                (Prop.ceTHIRDH*g.getScreenHeight())));
    }
    
    /**
     * Devuelve la lista de las Salidas superiores de la Habitacion
     * @return 
     */
    public ArrayList<Salida> getSalSup()
    {
        return salidasSup;
    }
    
    /**
     * Devuelve la lista de las Salidas inferiores de la Habitacion
     * @return 
     */
    public ArrayList<Salida> getSalInf()
    {
        return salidasInf;
    }
    
    /**
     * Permite añadir una Salida superior ya exixtente a la Habitacion. Es recomendable
     * que la Celda sobre la que se construyo ya forme parte de la Habitacion
     * @param s 
     */
    public void addSalidaSup(Salida s)
    {
        salidasSup.add(s);
    }
    
    /**
     * Permite añadir una Salida inferior ya exixtente a la Habitacion. Es recomendable
     * que la Celda sobre la que se construyo ya forme parte de la Habitacion
     * @param s 
     */
    public void addSalidaInf(Salida s)
    {
        salidasInf.add(s);
    }
    
    /**
     * Permite añadir una pared interior a la Habitacion en la coordenada
     * proporcionada. Normalmente esta corresponde con el limite izquierdo o 
     * derecho de la Habitacion.
     * @param x 
     */
    public void addBulletLimits(float x)
    {
        if(x>this.getX()) bulletLimits.add(new Rectangle(x-10,this.getY(),10,this.getHeight()));
        else bulletLimits.add(new Rectangle(x,this.getY(),10,this.getHeight()));
    }
    
    /**
     * Devuelve la lista de paredes interiores de la Habitacion
     * @return 
     */
    public ArrayList<Rectangle> getBulletLimits()
    {
        return bulletLimits;
    }
    
    /**
     * Crea un array n parejas formadas por el identificador de la Habitacion
     * y una coordenada x dentro de los limites de esta
     * @param n Num de parejas solicitadas
     * @return 
     */
    public float[][] spawn(int n)
    {
        float[][] aux = new float[n][2];
        //Seleccionamos una x dentro de la habitacion como pto de spawn,
        //el resto de la info esta contenida en la propia Habitacion
        float j;
        for(int i=0;i<n;i++){
            j = (((float)(Math.random()*width-40))+getX());
            aux[i][0] = id;
            aux[i][1] = j;
        }        
        return aux;
    }
    
    /**
     * <u>Primera fase de renderizado: </u>Permite la rederizacion de los fondos
     * de la Habitacion e instancia los rectangulos del suelo y el techo si nos
     * se ha hecho ya.
     * @param g 
     */
    public void renderBack(Graphics g){
        g.setColor(Color.white);
        if (first)
        {
            this.down = new Rectangle(this.x,this.maxY-10,this.width,10);
            this.up =new Rectangle(this.x,this.y,this.width,5);
            first = false;
        }
        //Primero dibujamos los fondos
        celdas.forEach((c) ->
        {
            c.render(g);
        });
    }
    
    /**
     * <u>Segunda fase de renderizado: </u>Permite la renderizacion del suelo
     * de la Habitacion asi como el borde de esta
     * @param g 
     */
    public void renderFloor(Graphics g){
        //BORDES
        g.setColor(Color.black);
        g.draw(this);
        
        g.setColor(Color.darkGray);//SUELO
        g.fill(down);
        //g.drawString(toString(), getCenterX(), getCenterY()); //for testing
        g.setColor(Color.white);
    }
    
    /**
     * <u>Tercera fase de renderizado: </u>Permite el renderizado de las imagenes
     * de las salidas
     * @param g 
     */
    public void renderExits(Graphics g){
        //Despues dibujamos las marcas de las salidas
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
    }
    
    /**
     * <u>Cuarta fase de renderizado: </u>Permite el renderizado del techo de la
     * Habiatacion y de las paredes interiores
     * de las salidas
     * @param g 
     */
    public void renderWalls(Graphics g){
        //TECHO
        g.setColor(Color.black);
        g.fill(up);
        
        g.setColor(Color.darkGray);
        bulletLimits.forEach((r) ->
        {
            g.fill(r);
        });
        
        g.setColor(Color.white);
    }
    
    /**
     * Devuelve el nuemero de Celdas que forman parte de la Habitacion
     * @return 
     */
    public int getCount()
    {
        return cellCount;
    }
    
    /**
     * Incrementa en 1 el contador de Celdas que forman parte de la Habitacion
     */
    public void addCount()
    {
        cellCount++;
    }
    
    /**
     * Devuelve el identificador de la Habitacion
     * @return 
     */
    public int getId(){
        return id;
    }
    
    /**
     * Devuelve un String con el identificador de la Habitacion y el contador
     * de Celdas. Util para las pruebas
     * @return 
     */
    @Override
    public String toString()
    {
        return id+" - "+cellCount;
    }
    
    /**
     * Metodo que permite la comparacion y la ordenacion de las Habitaciones. Seran
     * menores las situadas más arriba en la pantalla (una y menor) y, a igualdad
     * en la coordenada y, seran menores si estan situadas mas a la izquierda en
     * la pantalla (una x menor)
     * <ul> -1 Si la Habitacion es menor que la proporcionada por parametro
     * <p> 0 Si son iguales
     * <p> 1 Si la Habitacion es mayor que la proporcionada por parametro</ul>
     * @param h
     * @return 
     */
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
