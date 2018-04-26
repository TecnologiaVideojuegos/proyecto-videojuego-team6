/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space_bubble;

import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.*;

/**
 *
 * @author Daniel
 */
public class Bola{
    private int radio = 7;
    private Circle bola;
    private int height;
    private int width;
    private Vector2f veloc;
    private GradientFill color;
    
    private ArrayList<Bola> lista;
    private int misma;
    
    private boolean is_dead;
    private int contador;
    private Line dead[];
    private int ptos;
    
    
    public Bola(float xB, float yB, int width, int height, int misma){
        this.bola = new Circle(xB, yB, radio);
        this.width = width;
        this.height = height;
        this.veloc = new Vector2f(((int)Math.pow(-1, (int)xB))*((float)Math.random()),((int)Math.pow(-1, (int)yB))*((float)Math.random()));
        this.misma = misma;
        
        this.is_dead = false;
        this.contador = 1000;
        this.dead = new Line[8];
        
        
        Color c;
        switch((int)(Math.random()*7)){
            case(0):
                c = Color.cyan;
                ptos = 10;
                break;
            case(1):
                c = Color.blue;
                ptos = 25;
                break;
            case(2):
                c = Color.green;
                ptos = 50;
                break;
            case(3):
                c = Color.yellow;
                ptos = 100;
                break;
            case(4):
                c = Color.orange;
                ptos = 250;
                break;
            case(5):
                c = Color.red;
                ptos = 500;
                break;
            case(6):
                c = Color.magenta;
                ptos = 1000;
                break;
            default:
                c = Color.pink;                
                ptos = 2000;
        }
        this.color = new GradientFill(0, 0, c, width, height, c);
    }
    
    public void mover(int delta){
        int indice;
        
        if(!is_dead){
            if((bola.getCenterX()<radio)||(bola.getCenterX()>width-radio))
                veloc.set(-1*veloc.getX(), veloc.getY());
            if((bola.getCenterY()<radio)||(bola.getCenterY()>height-radio))
                veloc.set(veloc.getX(), -1*veloc.getY());

            if((indice=colision())>=0) swap(indice);

            bola.setCenterX(bola.getCenterX()+(veloc.getX()*delta*100)/500f);
            bola.setCenterY(bola.getCenterY()+(veloc.getY()*delta*100)/500f);
            
        } else{
            /*for (Line dead1 : dead) {
                dead1.setCenterX(bola.getCenterX());
                dead1.setCenterY(bola.getCenterY());
            }*/
            
            contador -= delta;//Duracion estimada postMorten = 2 seg
        }
    }
    
    public void dibujar(Graphics g){
        if(!is_dead) g.fill(bola, color);
        else{
            if(contador>0){
                g.drawString(String.valueOf(ptos), bola.getCenterX(), bola.getCenterY());
                for(Line deadl : dead) g.draw(deadl, color);
            } else lista.remove(this);
        }
    }
    
    public void setLista(ArrayList<Bola> lista){
        this.lista = lista;
    }
    
    public Circle getBola(){
        return bola;
    }
    
    public Vector2f getVeloc(){
        return veloc;
    }
    
    public void setVeloc(Vector2f veloc){
        this.veloc = veloc;
    }
    
    private int colision(){
        int indice = -1;
        for(int i=0;i<lista.size();i++){
            if(i!=misma){
                if(bola.intersects(lista.get(i).getBola())) indice = i;
            }
        }
        
        return indice;
    }
    
    private void swap(int indice){
        Vector2f aux;
        aux = lista.get(indice).getVeloc();
        lista.get(indice).setVeloc(veloc);
        veloc = aux;
    }
    
    public void kill(){
        is_dead = true;
        
        this.dead[0] = new Line(bola.getCenterX(), bola.getCenterY()+5f, 0, 5, true);
        this.dead[1] = new Line(bola.getCenterX()+2.5f, bola.getCenterY()+2.5f, 5, 5, true);
        this.dead[2] = new Line(bola.getCenterX()+5f, bola.getCenterY(), 5, 0, true);
        this.dead[3] = new Line(bola.getCenterX()+2.5f, bola.getCenterY()-2.5f, 5, -5, true);
        this.dead[4] = new Line(bola.getCenterX(), bola.getCenterY()-5f, 0, -5, true);
        this.dead[5] = new Line(bola.getCenterX()-2.5f, bola.getCenterY()-2.5f, -5, -5, true);
        this.dead[6] = new Line(bola.getCenterX()-5f, bola.getCenterY(), -5, 0, true);
        this.dead[7] = new Line(bola.getCenterX()-2.5f, bola.getCenterY()+2.5f, -5, 5, true);
    }
}
