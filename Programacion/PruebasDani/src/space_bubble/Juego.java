
package space_bubble;

import java.util.ArrayList;
import java.util.Date;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;


public class Juego extends BasicGame{
    private AppGameContainer g;
    //private Rectangle f;
    private Nave f;
    private Input entrada;
    private ArrayList<Bola> bolas;
    private int nBolas = 200;
    private int kBolas = nBolas-1;
    
    private static int NONE = 0;
    private static final int DOWN = 1;
    private static final int UP = 2;
    private static final int RIGHT = 3;
    private static final int LEFT = 4;
    
    
    public Juego(String t) throws SlickException{
        super(t);
        this.g = new AppGameContainer(this);
        this.bolas = new ArrayList<Bola>();
        g.setDisplayMode(800, 600, false);
        g.start();
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        //f = new Rectangle(290, 190, 10, 10);
        f = new Nave(250,150);
        for(int i=0; i<nBolas; i++) bolas.add(new Bola(((float)(0.1+Math.random()*7.8))*100f,
                ((float)(0.1+Math.random()*5.8))*100f,
                g.getWidth(), g.getHeight(), i));
        for(int i=0; i<bolas.size(); i++) bolas.get(i).setLista(bolas);
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        int aux;
        for(int i=0; i<bolas.size(); i++) bolas.get(i).mover(delta);
        
        aux = (int) new Date().getTime();
        if(((aux%2)==0)&&(!bolas.isEmpty())){
            bolas.get(aux%bolas.size()).kill();
        }
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        //f.dibujar(g);
        for(int i=0;i<bolas.size();i++) bolas.get(i).dibujar(g);
    }
    
    public boolean colision(Rectangle obstaculo, Nave f, int direccion){
        boolean colisiona = false;
                
        switch(direccion){
            case DOWN:
                if((f.getCenterX()<=(obstaculo.getCenterX()+(obstaculo.getWidth()/2)))&&
                       (f.getCenterX()>=(obstaculo.getCenterX()-(obstaculo.getWidth()/2)))&&
                        (f.getCenterY()<=(obstaculo.getCenterY()-(obstaculo.getHeight()/2)))&&
                        (f.getCenterY()>=(obstaculo.getCenterY()-(obstaculo.getHeight()/2)-(f.getHeight()/2)))){
                    colisiona = true;
                }
                break;
            case UP:
                if((f.getCenterX()<=(obstaculo.getCenterX()+(obstaculo.getWidth()/2)))&&
                       (f.getCenterX()>=(obstaculo.getCenterX()-(obstaculo.getWidth()/2)))&&
                        (f.getCenterY()>=(obstaculo.getCenterY()+(obstaculo.getHeight()/2)))&&
                        (f.getCenterY()<=(obstaculo.getCenterY()+(obstaculo.getHeight()/2)+(f.getHeight()/2)))){
                    colisiona = true;
                }
                break;
            case RIGHT:
                if((f.getCenterY()<=(obstaculo.getCenterY()+(obstaculo.getHeight()/2)))&&
                       (f.getCenterY()>=(obstaculo.getCenterY()-(obstaculo.getHeight()/2)))&&
                        (f.getCenterX()<=(obstaculo.getCenterX()-(obstaculo.getWidth()/2)))&&
                        (f.getCenterX()>=(obstaculo.getCenterX()-(obstaculo.getWidth()/2)-(f.getHeight()/2)))){
                    colisiona = true;
                }
                break;
            case LEFT:
                if((f.getCenterY()<=(obstaculo.getCenterY()+(obstaculo.getHeight()/2)))&&
                       (f.getCenterY()>=(obstaculo.getCenterY()-(obstaculo.getHeight()/2)))&&
                        (f.getCenterX()>=(obstaculo.getCenterX()+(obstaculo.getWidth()/2)))&&
                        (f.getCenterX()<=(obstaculo.getCenterX()+(obstaculo.getWidth()/2)+(f.getHeight()/2)))){
                    colisiona = true;
                }
                break;
        }
                
        return colisiona;
    }
}
