package shutterearth.map.randomGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import shutterearth.Game;

/**
 * Clase central de la generacion procedural de niveles. En su constructor se
 * encarga de llamar a los metodos con los algoritmos necesarios para generar un
 * nuevo nivel de juego
 */
public class Nivel 
{
    /**Atributo que almacena las haitaciones que componen el nivel*/
    private ArrayList<Habitacion> nivel;
    /**Almacena la que sera la habitacion segura donde spawneara el heroe*/
    private final Habitacion heroSpawn;
    /**HashMap utilizado para la traduccion de id a Habitaciones*/
    private HashMap<Integer, Habitacion> traductor;
    
    /**Variables necesarias para el escalado del juego*/
    private final AppGameContainer g;
    private final int gW;
    private final int gH;
    
    /**
     * Constructor. Crea un nuevo nivel de juego, almacenando en el arraylist
     * las habitaciones generadas
     * @param g
     * @throws SlickException 
     */
    public Nivel(AppGameContainer g) throws SlickException
    {
        this.g = g;
        this.gW = Game.getX();
        this.gH = Game.getY();
        
        generacion();
        heroSpawn = nivel.get(0);
        //ArrayList<Habitacion> aux = new ArrayList<>(nivel);
        nivel.sort(null);
        paredes();
        //nivel = new ArrayList<>(aux);
    }
    
    /**
     * Devuelve la lista de habitaciones que conforman el nivel
     * @return 
     */
    public ArrayList<Habitacion> getNv()
    {
        return nivel;
    }
    
    /**
     * Devuelve la habitacion segura designada para que spawnee el heroe
     * @return Habitacion
     */
    public Habitacion getHeroSpawn()
    {
        return heroSpawn;
    }
    
    /**
     * Devuelve los rectangulos generados como paredes internas para calcular
     * la colision de las balas en estas
     * @return 
     */
    public ArrayList<Rectangle> getBulleytLimits()
    {
        ArrayList<Rectangle> aux = new ArrayList<>();
        nivel.forEach((h) ->
        {
            aux.addAll(h.getBulletLimits());
        });
        return aux;
    }
    
    /**
     * Retorna un ArrayList con parejas float[] formadas por un id de una
     * Habitacion y una coordenada x en su interior donde podrá spawnear un
     * personaje. Para un numero de puntos solicitados, devuelve tantas parejas
     * de spawn como indique dicho numero repartidas aleatoriamente entre todas
     * las habitaciones excepto la del heroe.
     * @param numExactoMonstruos Num de puntos solicitados.
     * @return 
     */
    private ArrayList<float[]> getSpawns(int numExactoMonstruos)
    {
        ArrayList<float[]> lista = new ArrayList<>();
        float[][] aux;
        int count = 0;
        while(lista.size()<numExactoMonstruos){
            if(!nivel.get(count).equals(heroSpawn)){
                if(lista.size()<=(numExactoMonstruos-2)){
                    aux = nivel.get(count).spawn(((int)(Math.random()*100))%3);
                } else{
                    aux = nivel.get(count).spawn(numExactoMonstruos-lista.size());
                }
                for (int i=0;i<aux.length;i++) lista.add(aux[i]);
            }
            count = (count+1)%nivel.size();
        }
        return lista;
    }
    
    /**
     * Metodo traductor entre el nivel generado y el resto del programa. Devuelve
     * el numero solicitado de coordenadas de spawn incluyendo las del heroe mediante
     * un doble array float[][]. El primer elemento float[] seran dichas coordenadas
     * del heroe
     * @param num Num de arrays de coordenadas solicitado
     * @return 
     */
    public float[][] getSpots(int num){
        ArrayList<float[]> aux = getSpawns(num-1);
        float[][] spawn = new float[aux.size()+1][7];
        spawn[0][0]=heroSpawn.getX()+20;//x spawn
        spawn[0][1]=heroSpawn.getY();//y spawn
        spawn[0][2]=heroSpawn.getMaxY();//y floor
        spawn[0][3]=heroSpawn.getX();//x izq
        spawn[0][4]=heroSpawn.getMaxX();//x der
        spawn[0][5]=heroSpawn.getLado();//num pared
        spawn[0][6]=heroSpawn.getId();//id hab
        
        for(int i=0;i<aux.size();i++){
            spawn[i+1][0]=aux.get(i)[1];//x spawn
            spawn[i+1][1]=traductor.get((int)aux.get(i)[0]).getY();//y spawn
            spawn[i+1][2]=traductor.get((int)aux.get(i)[0]).getMaxY();//y floor
            spawn[i+1][3]=traductor.get((int)aux.get(i)[0]).getX();//x izq
            spawn[i+1][4]=traductor.get((int)aux.get(i)[0]).getMaxX();//x der
            spawn[i+1][5]=traductor.get((int)aux.get(i)[0]).getLado();//num pared
            spawn[i+1][6]=traductor.get((int)aux.get(i)[0]).getId();//id hab
        }
        
        return spawn;
    }
    
    /**
     * Devuelve al personaje que lo solicita los parametros de la nueva Habitacion
     * a la que se dirije siempre que este colisionando con la salida adecuada. Si no, 
     * retorna los aprametros iniciales, con lo que el personaje no cambia de Habitacion
     * <p> La colision se calcula mediante la intersecccion de rectangulos a partir
     * de las coordenadas del personaje y el rectangulo Salida concreto
     * @param id Id de la Habitacion actual del personaje
     * @param x Posicion del personaje
     * @param up Indica si el personaje quiere dirigirse arriba o abajo
     * @param charW Ancho del personaje
     * @param hero Indica si el personaje es o no el heroe
     * @return 
     */
    public float[] getNextRoom(int id, float x, boolean up, float charW, boolean hero){
        float[] aux = new float[5];
        
        //calculamos si puede saltar o no
        Habitacion hab = traductor.get(id);
        Rectangle per = new Rectangle(x, hab.getY(), charW, hab.getMaxY());//colision
        if(up){
            for(Salida s : hab.getSalSup()){
                if(s.intersects(per)) hab = s.getNext();
            }
        }
        else{
            for(Salida s : hab.getSalInf()){
                if((s.intersects(per))&&!((s.getNext().equals(heroSpawn))&&(!hero))) hab = s.getNext();
            }
        }
        
        
        aux[0] = hab.getMaxY();//y floor
        aux[1] = hab.getX();//x ixq
        aux[2] = hab.getMaxX();//x der
        aux[3] = hab.getLado();//num pared (si es hab izq, der, central, etc)
        aux[4] = hab.getId();//id hab
        
        return aux;
    }
    
    /**
     * Devuelve, dadas las posiciones x e y de una bala, los margenes o limites
     * izquierdo o derecho de la Habitacion con la que esta alineada. en caso de
     * la habitacion tenga alguna pared abierta al exterior, los margenes corespondientes
     * seran 0 en el caso de la izquierda y la x máxima de la pantalla en el caso de la derecha
     * @param x Posicion x de la bala
     * @param y Posicion y de la bala
     * @return 
     */
    public float[] bulletControl(float x, float y){
        float[] aux = null;
        for(Habitacion h : nivel){
            if(h.contains(x, y)){
                aux = new float[2];
                switch(h.getLado()){
                    case 0://izquierda
                        aux[0] = 0; //Pared izq
                        aux[1] = h.getMaxX(); //Pared derecha
                        break;
                    case 1://derecha
                        aux[0] = h.getX(); //Pared izq
                        aux[1] = Game.getX(); //Pared derecha
                        break;
                    case 2://abierto a ambos lados
                        aux[0] = 0; //Pared izq
                        aux[1] = Game.getX(); //Pared derecha
                        break;
                    case 3://central
                        aux[0] = h.getX(); //Pared izq
                        aux[1] = h.getMaxX(); //Pared derecha
                        break;
                }
            }
        }
        return aux;
    }
    
    /**
     * Metodo de renderizado por fases del nivel. renderiza en orden cada una de
     * las partes de cada una de las habitaciones. De esta forma el orden de las
     * capas es el adecuado y todo se visualiza correctamente
     * @param g Controlador grafico. Se encarga del renderizado
     */
    public void render(Graphics g)
    {
        nivel.forEach((h) ->
        {
            h.renderBack(g);
        });
        nivel.forEach((h) ->
        {
            h.renderFloor(g);
        });
        nivel.forEach((h) ->
        {
            h.renderExits(g);
        });
        nivel.forEach((h) ->
        {
            h.renderWalls(g);
        });
    }
    
    /**
     * Metodo que instancia un array doble de celdas, una "cuadricula"
     * @param celdas Array que se quiere inicializar
     * @param filasCount Array de enteros que controla el num de Habitaciones visitadas por piso
     * @throws SlickException 
     */
    private void resetCeldas(Celda[][] celdas, int[] filasCount) throws SlickException
    {
        for(int i=0;i<celdas.length;i++)
            for(int j=0;j<celdas[i].length;j++)
                celdas[i][j] = new Celda(((i%8)*Prop.ceWI*gW)+Prop.ceWI*gW,((j%8)*(Prop.ceTHIRDH*gH*3))+Prop.hubH*gH,Prop.ceWI*gW,Prop.ceTHIRDH*gH*3);
        for(int i=0;i<filasCount.length;i++) filasCount[i] = celdas.length;
    }
    
    /**
     * Metodo principal de la generacion procedural. El algoritmo se compone
     * internamente de tres fases
     * <p>Fase 1: Se inicializan las variables y las estructuras necesarias. Se
     * marca como celda inicial la inferior izquierda, la Celda[0][7] y se crea
     * con ella la primera Habitacion
     * <p>Fase 2: Dentro del bucle do-while distinguimos dos zonas. En la
     * primera se decide aleatoriamente si el algoritmo irá a la derecha, a la
     * izquierda, hacia arriba o hacia abajo dentro de la tabla de Celdas. Esta
     * decisión puede ser totalmente aleatoria o estar limitada al movimiento
     * horizontal o vertical solamente en función del número de celdas que
     * formen ya parte de la habitación y el número de celdas ya visitadas en
     * ese “piso” del edificio.
     * <p>En la segunda zona, tenemos un switch, el cual contiene las acciones
     * a realizar en función del movimiento que finalmente se ha decidido hacer.
     * <ul>En movimientos horizontales, la nueva celda que se visita se añade a
     * la habitación de la celda anterior.
     * <p>Los movimientos verticales en este algoritmo implican la creación de
     * una nueva Habitación si la celda siguiente no ha sido visitada y se añaden
     * a esta y a la Habitacion de la que venimos las correspondientes salidas
     * superior e inferior con las referencias a su vecina.</ul>

     * <p>Fase 3: Se toma el doble array de Celdas y se recorre de arriba a abajo
     * y de izquierda a derecha, , intentando preferentemente combinar las
     * Habitaciones de una Celda vecinas. Si la Habitación en cuestión no tiene
     * ninguna vecina con la misma casuística, se combina aleatoriamente con la
     * de su izquierda o su derecha.
     * <p>Por último que se efectúa también un cambio de todas las referencias
     * de las Salidas implicadas con esta Habitacion unitaria que desaparece y
     * se combina con la otra.
     * @throws SlickException 
     */
    private void generacion() throws SlickException
    {
        boolean repeat = true;
        Celda[][] celdas = null;
        int count;
        while(repeat){
            try{
                count = 0;
                int cellCount=1, cellNum = 32;
                int r=0, c=7;
                int rand;
                int id=0;

                celdas = new Celda[8][8];
                int[] filasCount = new int[8];
                resetCeldas(celdas, filasCount);

                nivel = new ArrayList<>();//reset del nivel
                traductor = new HashMap<>();

                celdas[r][c].setVisited(true);
                Habitacion hab = new Habitacion(g,celdas[r][c],id);
                celdas[r][c].setHab(hab);
                this.nivel.add(hab);
                this.traductor.put(id, hab);
                id++;
                filasCount[c]--;

                do
                {
                    if((celdas[r][c].getHab().getCount()<3)&&(filasCount[c]>0)) rand = ((int)(Math.random()*400))%2;//restringido a izquierda o derecha
                    else if(celdas[r][c].getHab().getCount()==4) rand = (((int)(Math.random()*400))%2)+2;//restringido a arriba o abajo
                    else rand = ((int)(Math.random()*400))%4;
                    try
                    {
                        switch(rand)
                        {
                            case 0:
                                if(!celdas[r+1][c].isVisited())
                                {//derecha
                                    cellCount++;
                                    celdas[r+1][c].setVisited(true);
                                    celdas[r+1][c].setHab(celdas[r][c].getHab());
                                    celdas[r][c].getHab().addCelda(celdas[r+1][c]);
                                    filasCount[c]--;
                                }
                                r = r+1;
                                break;
                            case 1:
                                if(!celdas[r-1][c].isVisited())
                                {//izquierda
                                    cellCount++;
                                    celdas[r-1][c].setVisited(true);
                                    celdas[r-1][c].setHab(celdas[r][c].getHab());
                                    celdas[r][c].getHab().addCelda(celdas[r-1][c]);
                                    filasCount[c]--;
                                }
                                r = r-1;
                                break;
                            case 2:
                                if(!((!celdas[r][c-1].isVisited())&&(filasCount[c-1]<=1)))
                                {
                                    if(!celdas[r][c-1].isVisited())
                                    {//arriba
                                        cellCount++;
                                        celdas[r][c-1].setVisited(true);
                                        hab = new Habitacion(g, celdas[r][c-1],id);
                                        nivel.add(hab);
                                        traductor.put(id, hab);
                                        id++;
                                        celdas[r][c-1].setHab(hab);
                                        celdas[r][c].getHab().addSalidaSup(celdas[r][c], celdas[r][c-1].getHab());
                                        celdas[r][c-1].getHab().addSalidaInf(celdas[r][c-1], celdas[r][c].getHab());
                                        filasCount[c-1]--;
                                    }
                                    c = c-1;
                                }
                                break;
                            case 3:
                                if(!((!celdas[r][c+1].isVisited())&&(filasCount[c+1]<=1)))
                                {
                                    if(!celdas[r][c+1].isVisited())
                                    {//abajo
                                        cellCount++;
                                        celdas[r][c+1].setVisited(true);
                                        hab = new Habitacion(g, celdas[r][c+1],id);
                                        nivel.add(hab);
                                        traductor.put(id, hab);
                                        id++;
                                        celdas[r][c+1].setHab(hab);
                                        celdas[r][c].getHab().addSalidaInf(celdas[r][c], celdas[r][c+1].getHab());
                                        celdas[r][c+1].getHab().addSalidaSup(celdas[r][c+1],celdas[r][c].getHab());
                                        filasCount[c+1]--;
                                    }
                                    c = c+1;
                                }
                                break;
                        }
                    } 
                    catch (IndexOutOfBoundsException e){}//Captura el IndexOutOfBoundsException y lo vuelve a intentar
                    count++;
                    if(count>999) throw new Exception();
                }
                while((cellCount<cellNum)||(celdas[r][c].getHab().getCount()==1));
                repeat = false;
            } catch(Exception e){}//Si por un error no converge, comienza de nuevo
        }
        
        
        //Eliminamos las habitaciones de una celda
        int aux = 2;
        for(int i=0;i<celdas.length;i++)
        {
            for(int j=0;j<celdas[i].length;j++)
            {
                if((celdas[i][j].isVisited())&&(celdas[i][j].getHab().getCount()==1))
                {
                    try
                    { 
                        if(celdas[i-1][j].getHab().getCount()<=2) aux = 1;
                    }
                    catch(Exception e)
                    { 
                        aux = 3;
                    }
                    try
                    { 
                        if(celdas[i+1][j].getHab().getCount()<=2) aux = 0;
                    }
                    catch(Exception e)
                    { 
                        aux = 4;
                    }
                    switch(aux)
                    {
                        case 2:
                            aux = ((int)(Math.random()*32))%2;
                            break;
                        case 3:
                            aux = 0;
                            break;
                        case 4:
                            aux = 1;
                            break;
                    }

                    switch(aux)
                    {
                        case 0:
                            //revisa la celda derecha
                            nivel.remove(celdas[i][j].getHab());
                            celdas[i+1][j].getHab().addCelda(celdas[i][j]);
                            for(Salida s : celdas[i][j].getHab().getSalSup())
                            {
                                celdas[i+1][j].getHab().addSalidaSup(s);
                                for(Salida y :s.getNext().getSalInf())
                                    if(y.getNext().equals(celdas[i][j].getHab()))
                                        y.setHab(celdas[i+1][j].getHab());
                            }
                            for(Salida s : celdas[i][j].getHab().getSalInf())
                            {
                                celdas[i+1][j].getHab().addSalidaInf(s);
                                for(Salida y :s.getNext().getSalSup())
                                    if(y.getNext().equals(celdas[i][j].getHab()))
                                        y.setHab(celdas[i+1][j].getHab());
                            }
                            celdas[i][j].setHab(celdas[i+1][j].getHab());
                            break;
                        case 1:
                            //revisa la celda izquierda
                            nivel.remove(celdas[i][j].getHab());
                            celdas[i-1][j].getHab().addCelda(celdas[i][j]);
                            for(Salida s : celdas[i][j].getHab().getSalSup())
                            {
                                celdas[i-1][j].getHab().addSalidaSup(s);
                                for(Salida y :s.getNext().getSalInf())
                                    if(y.getNext().equals(celdas[i][j].getHab()))
                                        y.setHab(celdas[i-1][j].getHab());
                            }
                            for(Salida s : celdas[i][j].getHab().getSalInf())
                            {
                                celdas[i-1][j].getHab().addSalidaInf(s);
                                for(Salida y :s.getNext().getSalSup())
                                    if(y.getNext().equals(celdas[i][j].getHab()))
                                        y.setHab(celdas[i-1][j].getHab());
                            }
                            celdas[i][j].setHab(celdas[i-1][j].getHab());
                            break;
                    }
                }
            }
        }
    }
    
    /**
     * Genera las paredes interiores de las Habitaciones del nivel y determina
     * si estas estan a la izquierda, a la derecha o en el centro con o sin vecinas
     * a los lados.
     */
    private void paredes()
    {
        for(int i=0;i<nivel.size();i++)
        {
            if(i!=0)
                if(nivel.get(i-1).getY()==nivel.get(i).getY()){
                    nivel.get(i).addBulletLimits(nivel.get(i).getX());//tiene compi a la izq
                    nivel.get(i).setLado(1, 0);
                }
            if(i<(nivel.size()-1))
                if(nivel.get(i+1).getY()==nivel.get(i).getY()){
                    nivel.get(i).addBulletLimits(nivel.get(i).getMaxX());//tiene compi a la derecha
                    nivel.get(i).setLado(0, 1);
                }
        }
    }
}
