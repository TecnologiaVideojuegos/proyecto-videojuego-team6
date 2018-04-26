
package generacionnivel;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;


public class Juego extends BasicGame{
    private AppGameContainer g;
    
    private Rectangle hab;
    private SpriteSheet s;
    private Animation ani[] = new Animation[31];
    private float x=0;
    
    public Juego(String t) throws SlickException{
        super(t);
        this.g = new AppGameContainer(this);
        g.setDisplayMode(1245, 700, false);
        g.start();
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        
        hab = new Rectangle(250,150,300,80);
        s = new SpriteSheet("imagenes/BOSSJUNTO.png",300,322);
        for(int j=0;j<31;j++){
            ani[j] = new Animation();
            for(int i=0;i<4;i++) ani[j].addFrame(s.getSprite(i, 0), 150);
        }        
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        x+=((float)delta*0.1);
        x%=1245;
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        g.draw(hab);
        //g.draw(borde);
        for(int j=0;j<31;j++) ani[j].draw((x+(float)Math.random()*50+(40*j))%1245,100,40,50);
        for(int j=0;j<31;j++) ani[j].draw((x+(float)Math.random()*50+(40*j))%1245,150,40,50);
        for(int j=0;j<31;j++) ani[j].draw((x+(float)Math.random()*50+(40*j))%1245,200,40,50);
        for(int j=0;j<31;j++) ani[j].draw((x+(float)Math.random()*50+(40*j))%1245,250,40,50);
        for(int j=0;j<31;j++) ani[j].draw((x+(float)Math.random()*50+(40*j))%1245,300,40,50);
        for(int j=0;j<31;j++) ani[j].draw((x+(float)Math.random()*50+(40*j))%1245,350,40,50);
        for(int j=0;j<31;j++) ani[j].draw((x+(float)Math.random()*50+(40*j))%1245,400,40,50);
        for(int j=0;j<31;j++) ani[j].draw((x+(float)Math.random()*50+(40*j))%1245,450,40,50);
    }
}
