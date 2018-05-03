
package generacionnivel;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;


public class Juego extends BasicGame{
    private AppGameContainer g;
    private Input entrada;
    
    private Rectangle hab;
    private Rectangle[][] casillas = new Rectangle[8][8];
    private boolean[][] colorear = new boolean[8][8];
    
    public Juego(String t) throws SlickException{
        super(t);
        this.g = new AppGameContainer(this);
        g.setDisplayMode(1280, 700, false);
        g.start();
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        entrada = new Input(700);
        
        hab = new Rectangle(250,150,300,80);
        for(int i=0;i<casillas.length;i++)
            for(int j=0;j<casillas[i].length;j++)
                casillas[i][j] = new Rectangle(((i%8)*160),((j%8)*80),160,80);
        generacion();
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        if(entrada.isKeyDown(Input.KEY_W)) generacion();
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        for(int i=0;i<casillas.length;i++){
            for(int j=0;j<casillas[i].length;j++){
                if(colorear[i][j]) g.fill(casillas[i][j]);
                else g.draw(casillas[i][j]);
            }
        }        
    }
    
    /**
     * Nada de esto soluciona el problema todavia. Solo estoy probando
     */
    public void generacion(){
        //Aprox 1: pesos.
        /*int peso=10;
        colorear[0]=true;
        for(int i=1;i<colorear.length;i++){
            if((((int)(Math.random()*100))%peso)!=0) colorear[i] = true;
            else colorear[i]=false;
            
            if(colorear[i-1]) if(peso>2) peso--;
            else  peso++;
        }*/
        
        //Aprox 2: decidir la celda superior tambien
        /*colorear = new boolean[colorear.length];
        colorear[0]=true;
        for(int i=1;i<colorear.length;i++){
            if(!colorear[i]){
                if((((int)(Math.random()*100))%2)!=0) colorear[i] = true;
                if((((int)(Math.random()*100))%2)!=0) if((i+8)<colorear.length) colorear[i+8] = true;
            }
        }*/
        
        //Aprox 3: decidir la celda superior tambien
        int contador=1;
        //colorear = new boolean[colorear.length];
        //colorear[0]=true;
        //if((contador==1))
        for(int i=0;i<colorear.length;i++) colorear[i][0]=true;
    }
}
