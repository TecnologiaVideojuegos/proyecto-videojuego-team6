/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas2;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Transform;

/**
 *
 * @author Daniel
 */
public class Nave{
    private float centerX;
    private float centerY;
    private float size = 15;
    private Transform transform;
    private boolean abiertas = true;
    
    private Polygon forma;
    private Polygon alas;
    private Polygon alasC;
    private GradientFill relleno;
    
    public Nave(float x, float y){
        super();
        //pto 1
        this.centerX = x;
        this.centerY = y;
        //ptos 2 a 10
        forma = new Polygon();
        forma.addPoint(0f*size+x, 3f*size+y);
        forma.addPoint(0.5f*size+x, 2f*size+y);
        forma.addPoint(2f*size+x, 0f*size+y);
        forma.addPoint(1f*size+x, -2f*size+y);
        forma.addPoint(0.5f*size+x, -1f*size+y);
        forma.addPoint(-0.5f*size+x, -1f*size+y);
        forma.addPoint(-1f*size+x, -2f*size+y);
        forma.addPoint(-2f*size+x, 0f*size+y);
        forma.addPoint(-0.5f*size+x, 2f*size+y);
        
        alas = new Polygon();
        alas.addPoint(1f*size+x, 2f*size+y);
        alas.addPoint(5f*size+x, 0f*size+y);
        alas.addPoint(2f*size+x, 0f*size+y);
        alas.addPoint(0.5f*size+x, -1f*size+y);
        alas.addPoint(-0.5f*size+x, -1f*size+y);
        alas.addPoint(-2f*size+x, 0f*size+y);
        alas.addPoint(-5f*size+x, 0f*size+y);
        alas.addPoint(-1f*size+x, 2f*size+y);
        
        alasC = new Polygon();
        alasC.addPoint(1f*size+x, 2f*size+y);
        alasC.addPoint(3f*size+x, 0f*size+y);
        alasC.addPoint(2f*size+x, 0f*size+y);
        alasC.addPoint(0.5f*size+x, -1f*size+y);
        alasC.addPoint(-0.5f*size+x, -1f*size+y);
        alasC.addPoint(-2f*size+x, 0f*size+y);
        alasC.addPoint(-3f*size+x, 0f*size+y);
        alasC.addPoint(-1f*size+x, 2f*size+y);
        
        relleno = new GradientFill(-5f*size+x, 0f*size+y, Color.green, 5f*size+x, 0f*size+y, Color.blue);
    }
    
    public void rotar(int delta){
        this.transform=Transform.createRotateTransform(delta/1000f,forma.getCenterX(),forma.getCenterY());
        this.forma =(Polygon) forma.transform(transform);
        this.alas =(Polygon) alas.transform(transform);
        this.alasC =(Polygon) alasC.transform(transform);
    }
    
    public void dibujar(Graphics g){
        g.fill(alas, relleno);
        g.fill(forma, relleno);
    }
    
    public float getX(){
        return forma.getX();
    }
    
    public float getY(){
        return forma.getY();
    }
    
    public void setX(float x){
        forma.setX(forma.getX()+x);
        alas.setX(alas.getX()+x);
        alasC.setX(alasC.getX()+x);
    }
    
    public void setY(float y){
        forma.setY(forma.getY()+y);
        alas.setY(alas.getY()+y);
        alasC.setY(alasC.getY()+y);
    }
    
    public float getHeight(){
        return forma.getHeight();
    }
    
    public float getWidth(){
        return forma.getWidth();
    }
    
    public float getCenterX(){
        return forma.getCenterX();
    }
    
    public float getCenterY(){
        return forma.getCenterY();
    }
    
    public void abrirAlas(){
        /*if(!abiertas){
            System.out.println("MAX X: "+alas.getMaxX()+"//MAX Y: "+alas.getMaxY()+
                    "//MIN X: "+alas.getMinX()+"//MIN Y: "+alas.getMinY());
            System.out.println("CENTRO: "+alas.getCenterX()+" - "+alas.getCenterY());
            
            transform = Transform.createScaleTransform(2f, 1);
            this.alas =(Polygon) alas.transform(transform);
            
            System.out.println("MAX X: "+alas.getMaxX()+"//MAX Y: "+alas.getMaxY()+
                    "//MIN X: "+alas.getMinX()+"//MIN Y: "+alas.getMinY());
            System.out.println("CENTRO: "+alas.getCenterX()+" - "+alas.getCenterY());
            
            alas.setCenterX(alas.getCenterX()/2);
            
            System.out.println("MAX X: "+alas.getMaxX()+"//MAX Y: "+alas.getMaxY()+
                    "//MIN X: "+alas.getMinX()+"//MIN Y: "+alas.getMinY());
            System.out.println("CENTRO: "+alas.getCenterX()+" - "+alas.getCenterY());
            
            abiertas = true;
        }*/
        if(!abiertas){
            //Swap
            Polygon aux;
            aux = alasC;
            alasC = alas;
            alas = aux;
            
            abiertas = true;
        }
    }
    
    public void cerrarAlas(){
        /*if(abiertas){
            System.out.println("MAX X: "+alas.getMaxX()+"//MAX Y: "+alas.getMaxY()+
                    "//MIN X: "+alas.getMinX()+"//MIN Y: "+alas.getMinY());
            System.out.println("CENTRO: "+alas.getCenterX()+" - "+alas.getCenterY());
            
            transform = Transform.createScaleTransform(0.5f, 1);
            this.alas =(Polygon) alas.transform(transform);
            
            System.out.println("MAX X: "+alas.getMaxX()+"//MAX Y: "+alas.getMaxY()+
                    "//MIN X: "+alas.getMinX()+"//MIN Y: "+alas.getMinY());
            System.out.println("CENTRO: "+alas.getCenterX()+" - "+alas.getCenterY());
            
            alas.setCenterX(alas.getCenterX()*2);
            
            System.out.println("MAX X: "+alas.getMaxX()+"//MAX Y: "+alas.getMaxY()+
                    "//MIN X: "+alas.getMinX()+"//MIN Y: "+alas.getMinY());
            System.out.println("CENTRO: "+alas.getCenterX()+" - "+alas.getCenterY());
            
            abiertas = false;
        }*/
        if(abiertas){
            //Swap
            Polygon aux;
            aux = alasC;
            alasC = alas;
            alas = aux;
                        
            abiertas = false;
        }
    }
}
