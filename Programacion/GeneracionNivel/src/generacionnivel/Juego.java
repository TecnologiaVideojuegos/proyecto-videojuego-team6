
package generacionnivel;

import java.util.ArrayList;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;


public class Juego extends BasicGame{
    //CONSTANTES Y PROPORCIONES
    private final AppGameContainer g = new AppGameContainer(this);
    private final int gW = g.getScreenWidth();
    private final int gH = g.getScreenHeight();
    
    private Rectangle personaje = new Rectangle(10,10,Prop.chHALFW*2, Prop.chH);
    
    private Input entrada;
    private Celda[][] celdas = new Celda[8][8];
    private Color[][] colores = new Color[8][8];
    private Color[] diccionario = {Color.blue,Color.orange,Color.green,Color.magenta,Color.cyan,Color.yellow,Color.pink};
    
    private ArrayList<Habitacion> nivel = new ArrayList<>();
    
    public Juego(String t) throws SlickException{
        super(t);
        g.setDisplayMode(gW, gH, true);
        g.start();
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        entrada = new Input(700);
        resetCeldas();
        resetColores();
        generacion();
    }
    
    private void resetCeldas(){
        for(int i=0;i<celdas.length;i++)
            for(int j=0;j<celdas[i].length;j++)
                celdas[i][j] = new Celda(((i%8)*Prop.ceWI*gW)+Prop.ceWI*gW,((j%8)*(Prop.ceTHIRDH*gH*3))+Prop.hubH*gH,Prop.ceWI*gW,Prop.ceTHIRDH*gH*3);
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
        for(int i=0;i<celdas.length;i++){
            for(int j=0;j<celdas[i].length;j++){
                //if(colorear[i][j]) g.fill(celdas[i][j]);
                //else g.draw(celdas[i][j]);
                g.setColor(colores[i][j]);
                g.fill(celdas[i][j]);
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
        
        
        resetCeldas();
        nivel.removeAll(nivel);//reset del nivel
        celdas[r][c].setVisited(true);
        Habitacion hab = new Habitacion(g,celdas[r][c]);
        this.nivel.add(hab);
        do{
            if(compiCount<2) rand = ((int)(Math.random()*400))%2;//restringido a izquierda o derecha
            else if(compiCount==4) rand = (((int)(Math.random()*400))%2)+2;//restringido a arriba o abajo
            else rand = ((int)(Math.random()*400))%4;
            try{
                switch(rand){
                    case 0:
                        if(!celdas[r+1][c].isVisited()){//derecha
                            cellCount++;
                            celdas[r+1][c].setVisited(true);
                            celdas[r+1][c].setHab(hab);
                            hab.addCelda(celdas[r+1][c]);
                        }
                        r = r+1;
                        c = c;
                        compiCount++;
                        break;
                    case 1:
                        if(!celdas[r-1][c].isVisited()){//izquierda
                            cellCount++;
                            celdas[r-1][c].setVisited(true);
                            celdas[r-1][c].setHab(hab);
                            hab.addCelda(celdas[r-1][c]);
                        }
                        r = r-1;
                        c = c;
                        compiCount++;
                        break;
                    case 2:
                        if(!celdas[r][c-1].isVisited()){//arriba
                            cellCount++;
                            celdas[r][c-1].setVisited(true);
                        }
                        r = r;
                        c = c-1;
                        compiCount = 1;
                        break;
                    case 3:
                        if(!celdas[r][c+1].isVisited()){//abajo
                            cellCount++;
                            celdas[r][c+1].setVisited(true);
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
