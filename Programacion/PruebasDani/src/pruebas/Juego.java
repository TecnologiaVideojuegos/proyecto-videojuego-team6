
package pruebas;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;


public class Juego extends BasicGame{
    private AppGameContainer g;
    //private Rectangle f;
    private Nave f;
    private float centroF[];
    private Rectangle boxes[];
    private float centros[][];
    private Input entrada;
    
    private static int NONE = 0;
    private static final int DOWN = 1;
    private static final int UP = 2;
    private static final int RIGHT = 3;
    private static final int LEFT = 4;
    
    private Rectangle ring;
    
    public Juego(String t) throws SlickException{
        super(t);
        this.centroF = new float[2];
        this.boxes = new Rectangle[4];
        this.centros = new float[4][2];
        this.g = new AppGameContainer(this);
        g.setDisplayMode(600, 400, false);
        g.start();
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        //f = new Rectangle(290, 190, 10, 10);
        f = new Nave(250,150);
        //f.rotar();
        System.out.println(f.getCenterX()+"//"+f.getCenterY());
        System.out.println(f.getX()+"//"+f.getY());
        boxes[0] = new Rectangle(0,300,200,10);
        boxes[1] = new Rectangle(400,300,200,10);
        boxes[2] = new Rectangle(0,200,100,10);
        boxes[3] = new Rectangle(500,200,100,10);
        
        ring = new Rectangle(250,150,100,100);
        
        //Calculo de centros
        centroF[0] = f.getX()+(f.getWidth()/2);
        centroF[1] = f.getY()+(f.getHeight()/2);
        
        for(int i=0;i<4;i++){
            centros[i][0]=boxes[i].getX()+(boxes[i].getWidth()/2);
            centros[i][1]=boxes[i].getY()+(boxes[i].getHeight()/2);
        }
        
        entrada = new Input(400);
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        //Desplazamiento
        for(int i=0;i<4;i++){
            if(entrada.isKeyDown(Input.KEY_DOWN)){
                if(colision(boxes[i],f,DOWN)){
                    f.setY(-(delta/10f));
                    continue;
                }
                else f.setY(+(delta/100f));
                if(colision(boxes[i],f,DOWN)) System.out.println("true down");
            }
            else if(entrada.isKeyDown(Input.KEY_UP)){
                if(colision(boxes[i],f,UP)){
                    f.setY(+(delta/10f));
                    continue;
                }
                else f.setY(-(delta/100f));
                if(colision(boxes[i],f,UP)) System.out.println("true up");
            }
            else if(entrada.isKeyDown(Input.KEY_RIGHT)){
                if(colision(boxes[i],f,RIGHT)){
                    f.setX(-(delta/10f));
                    continue;
                }
                else f.setX(+(delta/100f));
                if(colision(boxes[i],f,RIGHT)) System.out.println("true right");
            }
            else if(entrada.isKeyDown(Input.KEY_LEFT)){
                if(colision(boxes[i],f,LEFT)){
                    f.setX(+(delta/10f));
                    continue;
                }
                else f.setX(-(delta/100f));
                if(colision(boxes[i],f,LEFT)) System.out.println("true left");
            }
            else{
                if((f.getCenterY()<395)){
                    if(colision(boxes[i],f,DOWN)){
                    f.setY(-(delta/10f));
                    continue;
                }
                else f.setY(+(delta/100f));
                }
            }
        }
        if(entrada.isKeyDown(Input.KEY_W)){
            f.rotar(delta);
            //System.out.println("ROTATEEEEE");
        }else if(entrada.isKeyDown(Input.KEY_Q)){
            f.abrirAlas();
            //System.out.println("Abrete sesamo");
        }else if(entrada.isKeyDown(Input.KEY_E)){
            f.cerrarAlas();
            //System.out.println("me sierro");
        }
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        //g.fillOval(50, 50, 100, 150);
        for(int i=0;i<4;i++) g.draw(boxes[i]);
        f.dibujar(g);
        //g.draw(ring);
        //g.draw(n);
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
