
package generacionnivel;

import java.util.ArrayList;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;


public class Juego extends BasicGame{
    //CONSTANTES Y PROPORCIONES
    private final AppGameContainer g = new AppGameContainer(this);
    private final int gW = g.getScreenWidth();
    private final int gH = g.getScreenHeight();
    
    private Rectangle personaje = new Rectangle(10,10,Prop.chHALFW*2*g.getScreenWidth(), Prop.chH*g.getScreenHeight());
    
    private Input entrada;
    private Celda[][] celdas = new Celda[8][8];
    private int[] filasCount = new int[8];
    
    private ArrayList<Habitacion> nivel = new ArrayList<>();
    
    private int genId = 0;
    
    public Juego(String t) throws SlickException{
        super(t);
        g.setDisplayMode(gW, gH, false);
        g.start();
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        entrada = new Input(700);
        resetCeldas();
        generacion();
        //nivel1();
    }
    
    private void resetCeldas(){
        for(int i=0;i<celdas.length;i++)
            for(int j=0;j<celdas[i].length;j++)
                celdas[i][j] = new Celda(((i%8)*Prop.ceWI*gW)+Prop.ceWI*gW,((j%8)*(Prop.ceTHIRDH*gH*3))+Prop.hubH*gH,Prop.ceWI*gW,Prop.ceTHIRDH*gH*3);
        for(int i=0;i<filasCount.length;i++) filasCount[i] = celdas.length;
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        if(entrada.isKeyDown(Input.KEY_W)) generacion();
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        for(Habitacion h : nivel) h.render(g);
        g.fill(personaje);
    }
    
    public String geneId(){
        genId++;
        return ""+genId;
    }
    
    public void generacion(){
        int cellCount = 1;
        int cellNum = 32;
        int r = 0;
        int c = 7;
        int rand;
        genId=0;
        
        resetCeldas();
        nivel = new ArrayList<>();//reset del nivel
        celdas[r][c].setVisited(true);
        Habitacion hab = new Habitacion(g,celdas[r][c],geneId());
        celdas[r][c].setHab(hab);
        this.nivel.add(hab);
        filasCount[c]--;
        do{
            if((celdas[r][c].getHab().getCount()<3)&&(filasCount[c]>0)) rand = ((int)(Math.random()*400))%2;//restringido a izquierda o derecha
            else if(celdas[r][c].getHab().getCount()==4) rand = (((int)(Math.random()*400))%2)+2;//restringido a arriba o abajo
            else rand = ((int)(Math.random()*400))%4;
            try{
                switch(rand){
                    case 0:
                        if(!celdas[r+1][c].isVisited()){//derecha
                            cellCount++;
                            celdas[r+1][c].setVisited(true);
                            celdas[r+1][c].setHab(celdas[r][c].getHab());
                            celdas[r][c].getHab().addCelda(celdas[r+1][c]);
                            filasCount[c]--;
                        }
                        r = r+1;
                        c = c;
                        break;
                    case 1:
                        if(!celdas[r-1][c].isVisited()){//izquierda
                            cellCount++;
                            celdas[r-1][c].setVisited(true);
                            celdas[r-1][c].setHab(celdas[r][c].getHab());
                            celdas[r][c].getHab().addCelda(celdas[r-1][c]);
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
                                hab = new Habitacion(g, celdas[r][c-1],geneId());
                                nivel.add(hab);
                                celdas[r][c-1].setHab(hab);
                                celdas[r][c].getHab().addSalidaSup(celdas[r][c], celdas[r][c-1].getHab());
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
                                hab = new Habitacion(g, celdas[r][c+1],geneId());
                                nivel.add(hab);
                                celdas[r][c+1].setHab(hab);
                                celdas[r][c].getHab().addSalidaInf(celdas[r][c], celdas[r][c+1].getHab());
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
            } catch (IndexOutOfBoundsException e){}//Captura el IndexOutOfBoundsException y lo vuelve a intentar
        }while((cellCount<cellNum)||(celdas[r][c].getHab().getCount()==1));
        
        //Eliminamos las habitaciones de una celda
        for(int i=0;i<celdas.length;i++){
            for(int j=0;j<celdas[i].length;j++){
                while(celdas[i][j].getHab().getCount()==1){
                    try{
                        
                    } catch(IndexOutOfBoundsException e){}
                }
            }
        }
    }
    
    public void nivel1(){
        Habitacion hab = new Habitacion(g,celdas[0][7],geneId());
        System.out.println(hab.getX()+" - "+hab.getY()+"  =  "+hab.getWidth()+" - "+hab.getHeight());
        hab.addCelda(celdas[1][7]);
        System.out.println(hab.getX()+" - "+hab.getY()+"  =  "+hab.getWidth()+" - "+hab.getHeight());
        nivel.add(hab);
        hab= new Habitacion(g, celdas[1][6],geneId());
        nivel.add(hab);
        nivel.get(0).addSalidaSup(celdas[1][7], nivel.get(1));
        nivel.get(1).addSalidaInf(celdas[1][6], nivel.get(0));
    }
}
