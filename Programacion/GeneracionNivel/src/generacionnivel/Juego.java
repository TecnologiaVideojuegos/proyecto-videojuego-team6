
package generacionnivel;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;


public class Juego extends BasicGame{
    //CONSTANTES Y PROPORCIONES
    private final AppGameContainer g = new AppGameContainer(this);
    public final int gH = g.getScreenHeight();
    public final int gW = g.getScreenWidth();
    public final float hubH = gH*(3/35f);
    public final float chH = gH*(1/14f);//alto del personaje
    public final float chHALFW = gW*(1/80f);//mitad del ancho del personaje
    public final float ceTHIRDH = gH*(4/105f);//un tercio del alto de una habitacion
    public final float ceWI = gW*(1/10f);//ancho de una celda. Las celdas forman habitaciones
    
    
    private Rectangle personaje = new Rectangle(10,10,chHALFW*2, chH);
    
    private Input entrada;
    private Celda[][] casillas = new Celda[8][8];
    private Color[][] colores = new Color[8][8];
    private Color[] diccionario = {Color.blue,Color.orange,Color.green,Color.magenta,Color.cyan,Color.yellow,Color.pink};
    
    public Juego(String t) throws SlickException{
        super(t);
        g.setDisplayMode(gW, gH, true);
        g.start();
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        entrada = new Input(700);
        resetCasillas();
        resetColores();
        generacion();
    }
    
    private void resetCasillas(){
        for(int i=0;i<casillas.length;i++)
            for(int j=0;j<casillas[i].length;j++)
                casillas[i][j] = new Celda(((i%8)*ceWI)+ceWI,((j%8)*(ceTHIRDH*3))+hubH,ceWI,ceTHIRDH*3);
    }
    private void resetColores(){
        for(int i=0;i<colores.length;i++)
            for(int j=0;j<colores[i].length;j++)
                colores[i][j]=Color.black;
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        if(entrada.isKeyDown(Input.KEY_W)) generacion();
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        for(int i=0;i<casillas.length;i++){
            for(int j=0;j<casillas[i].length;j++){
                //if(colorear[i][j]) g.fill(casillas[i][j]);
                //else g.draw(casillas[i][j]);
                g.setColor(colores[i][j]);
                g.fill(casillas[i][j]);
            }
        }
        g.fill(personaje);
    }
    
    public void generacion(){
        int cellCount = 1;
        int cellNum = 32;
        int compiCount = 1;
        int r = 0;
        int c = 7;
        int rand;
        int hab = 0;
        
        resetCasillas();
        casillas[r][c].setVisited(true);
        resetColores();
        colores[r][c] = diccionario[hab];
        do{
            if(compiCount==1) rand = ((int)(Math.random()*400))%2;//restringido a izquierda o derecha
            else if(compiCount==4) rand = (((int)(Math.random()*400))%2)+2;//restringido a arriba o abajo
            else rand = ((int)(Math.random()*400))%4;
            try{
                switch(rand){
                    case 0:
                        if(!casillas[r+1][c].isVisited()){//derecha
                            cellCount++;
                            casillas[r+1][c].setVisited(true);
                            if(colores[r+1][c].equals(Color.black)) colores[r+1][c] = diccionario[hab];
                        }
                        r = r+1;
                        c = c;
                        compiCount++;
                        break;
                    case 1:
                        if(!casillas[r-1][c].isVisited()){//izquierda
                            cellCount++;
                            casillas[r-1][c].setVisited(true);
                            if(colores[r-1][c].equals(Color.black)) colores[r-1][c] = diccionario[hab];
                        }
                        r = r-1;
                        c = c;
                        compiCount++;
                        break;
                    case 2:
                        if(!casillas[r][c-1].isVisited()){//arriba
                            hab = (hab+1)%diccionario.length;
                            cellCount++;
                            casillas[r][c-1].setVisited(true);
                            if(colores[r][c-1].equals(Color.black)) colores[r][c-1] = diccionario[hab];
                        }
                        r = r;
                        c = c-1;
                        compiCount = 1;
                        break;
                    case 3:
                        if(!casillas[r][c+1].isVisited()){//abajo
                            hab = (hab+1)%diccionario.length;
                            cellCount++;
                            casillas[r][c+1].setVisited(true);
                            if(colores[r][c+1].equals(Color.black)) colores[r][c+1] = diccionario[hab];
                        }
                        r = r;
                        c = c+1;
                        compiCount = 1;
                        break;
                }
            } catch (Exception e){}//Captura el IndexOutOfBoundsException y lo vuelve a intentar
        }while((cellCount<cellNum)||(compiCount==1));
    }
    
    
}
