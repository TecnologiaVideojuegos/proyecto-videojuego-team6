
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
    private int[] filasCount = new int[8];
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
        for(int i=0;i<filasCount.length;i++) filasCount[i] = celdas.length;
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
        /*for(int i=0;i<celdas.length;i++){
            for(int j=0;j<celdas[i].length;j++){
                //if(colorear[i][j]) g.fill(celdas[i][j]);
                //else g.draw(celdas[i][j]);
                g.setColor(colores[i][j]);
                g.fill(celdas[i][j]);
            }
        }*/
        for(Habitacion h : nivel) h.render(g);
        g.fill(personaje);
    }
    
    public void generacion(){
        int cellCount = 1;
        int cellNum = 32;
        int r = 0;
        int c = 7;
        int rand;
        
        
        resetCeldas();
        nivel.removeAll(nivel);//reset del nivel
        celdas[r][c].setVisited(true);
        Habitacion hab = new Habitacion(g,celdas[r][c]);
        celdas[r][c].setHab(hab);
        this.nivel.add(hab);
        filasCount[c]--;
        do{
            if((hab.getCount()<2)&&(filasCount[c]>1)) rand = ((int)(Math.random()*400))%2;//restringido a izquierda o derecha
            else if(hab.getCount()==4) rand = (((int)(Math.random()*400))%2)+2;//restringido a arriba o abajo
            else rand = ((int)(Math.random()*400))%4;
            try{
                switch(rand){
                    case 0:
                        if(!celdas[r+1][c].isVisited()){//derecha
                            cellCount++;
                            celdas[r+1][c].setVisited(true);
                            celdas[r+1][c].setHab(hab);
                            hab.addCelda(celdas[r+1][c]);
                            hab.addCount();
                            filasCount[c]--;
                        }
                        r = r+1;
                        c = c;
                        break;
                    case 1:
                        if(!celdas[r-1][c].isVisited()){//izquierda
                            cellCount++;
                            celdas[r-1][c].setVisited(true);
                            celdas[r-1][c].setHab(hab);
                            hab.addCelda(celdas[r-1][c]);
                            hab.addCount();
                            filasCount[c]--;
                        }
                        r = r-1;
                        c = c;
                        break;
                    case 2:
                        if(!((!celdas[r][c-1].isVisited())&&(filasCount[c-1]<=1))){
                            if(!celdas[r][c-1].isVisited()){//arriba
                                cellCount++;
                                celdas[r][c-1].setVisited(true);
                                hab = new Habitacion(g, celdas[r][c-1]);
                                nivel.add(hab);
                                celdas[r][c-1].setHab(hab);
                                celdas[r][c].getHab().addSalidaSup(celdas[r][c], hab);
                                celdas[r][c-1].getHab().addSalidaInf(celdas[r][c-1], celdas[r][c].getHab());
                                filasCount[c-1]--;
                            } else{
                                hab = celdas[r][c-1].getHab();
                            }
                            r = r;
                            c = c-1;
                        }
                        break;
                    case 3:
                        if(!((!celdas[r][c+1].isVisited())&&(filasCount[c+1]<=1))){
                            if(!celdas[r][c+1].isVisited()){//abajo
                                cellCount++;
                                celdas[r][c+1].setVisited(true);
                                hab = new Habitacion(g, celdas[r][c+1]);
                                nivel.add(hab);
                                celdas[r][c+1].setHab(hab);
                                celdas[r][c].getHab().addSalidaInf(celdas[r][c], hab);
                                celdas[r][c+1].getHab().addSalidaSup(celdas[r][c+1],celdas[r][c].getHab());
                                filasCount[c+1]--;
                            } else{
                                hab = celdas[r][c+1].getHab();
                            }
                            r = r;
                            c = c+1;
                        }
                        break;
                }
            } catch (IndexOutOfBoundsException e){
                for(int i=0;i<e.getStackTrace().length;i++){
                    if(i!=0) System.out.print("   ");
                    System.out.println(e.getStackTrace()[i].getClassName()+" "+e.getStackTrace()[i].getMethodName()+" "+e.getStackTrace()[i].getLineNumber());
                }                    
            }//Captura el IndexOutOfBoundsException y lo vuelve a intentar
        }while((cellCount<cellNum)||(hab.getCount()==1));
    }
    
    
}
