/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shutterearth.characters;

import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import shutterearth.Game;
import shutterearth.map.Field;
import shutterearth.screens.Scene;

/**
 *
 * @author mr.blissfulgrin
 */
public abstract class Charact extends Scene
{
    protected int healthCurrent;
    protected float w;
    protected float h;
    protected float xVel;
    protected float yVel;
    protected float xPos;
    protected float yPos;
    protected Inventory inventory;
    protected int healthMax;
    
    protected float floor;
    protected boolean over;
    protected boolean jumpUp;
    protected boolean jumpDown;
    
    protected Rectangle line;
    protected Rectangle colum;
    protected Rectangle box;
    protected boolean animation;
    protected int counterAnimation;
    protected int animationTime;
    
    protected boolean active;
    protected Field field;
    ArrayList<Charact> enemy;
    
    protected boolean called;
    protected int borderRoom;
    protected int room;
    
    public Charact ()
    {
        counterAnimation = 0;
        animation = false;
        active = false;
        called = false;
    }

    @Override
    public abstract void Render(GameContainer gc, Graphics g) throws SlickException;

    @Override
    public abstract void Update(GameContainer gc, float t) throws SlickException;

    @Override
    public abstract void init(GameContainer gc) throws SlickException;

    /**
     * SALTO HACIA ARRIBA
     */
    public abstract void goUp();

    /**
     * IR A LA IZQUIERDA
     */
    public abstract void goLeft();

    /**
     * IR A LA DERECHA
     */
    public abstract void goRight();

    /**
     * SALTO HACIA ABAJO
     */
    public abstract void goDown();

    /**
     * DISPARAR
     */
    public abstract void shot();

    /**
     * Dota al personaje de spawn y activa sus parámetros de colisiones y posición
     * @param x
     * @param y
     * @param floor
     * @param left
     * @param right
     * @param borderRoom
     * @param room 
     */
    public abstract void place(float x, float y, float floor, float left, float right, int borderRoom, int room);

    /**
     * Lo usa el personaje para saber las dimensiones de la nueva sala
     * @param floor
     * @param left
     * @param right
     * @param borderRoom
     * @param room 
     */
    public abstract void setBounds (float floor, float left, float right, int borderRoom, int room);
    
    /**
     * Método auxiliar que carga los limites de movimiento en el personaje
     * @param bounds 
     */
    protected void boundSetter(float[] bounds)
    {
        setBounds(bounds[0],bounds[1],bounds[2],(int)bounds[3],(int)bounds[4]);
    }
        
    protected abstract void setX(float x);
    protected abstract void setY(float y);
    
    public abstract void doShotAnimation();
    
    /**
     * SALUD ACTUAL
     * @return 
     */
    public int getCurrentHealth()
    {
        return this.healthCurrent;
    }
    
    /**
     * RECIBE DAÑO
     * @param damage 
     */
    public void getDamage(int damage)
    {
        this.healthCurrent -= damage;
    }
    
    /**
     * DICE SI EL PERSOANJE SIGUE VIVO
     * @return 
     */
    public boolean isAlive()
    {
        return healthCurrent > 0;
    }
    
    /**
     * FILA EN LA QUE ESTÄ EL PERSONAJE DE SU CABEZA AL SUELO
     * @return 
     */
    public Rectangle getLine()
    {
        return line;
    }

    /**
     * COLUMNA QUE PROYECTA LA SALA EN LA QUE ESTÁ EL PERSONAJE
     * @return 
     */
    public Rectangle getColum()
    {
        return colum;
    }

    /**
     * HITBOX DEL PERSONAJE
     * @return 
     */
    public Rectangle getBox()
    {
        return box;
    }

    /**
     * DICE SI ESTÁ EN LA MISMA FILA
     * @param rect
     * @return 
     */
    public boolean isInLine(Rectangle rect)
    {
        return line.intersects(rect);
    }

    /**
     * DICE SI ESTÁ EN LA MISMA SALA
     * @param rect
     * @return 
     */
    public boolean isInRoom(Rectangle rect)
    {
        return line.intersects(rect) && colum.intersects(rect);
    }

    /**
     * DICE SI LE HA GOLPEADO
     * @param rect
     * @return 
     */
    public boolean isHited(Rectangle rect)
    {
        return box.intersects(rect);
    }

    /**
     * altura
     * @return 
     */
    public float getH()
    {
        return h;
    }

    /**
     * ancho
     * @return 
     */
    public float getW()
    {
        return w;
    }

    /**
     * DEBUGG SOLO DESARROLLO
     * usado para mostrar por pantalla los petadatos de la posición del personaje
     * @return 
     */
    public Rectangle[] debug()
    {
        return new Rectangle[] {line,colum,box};
    }

    /**
     * Inventario
     * @return 
     */
    public Inventory getInventory()
    {
        return inventory;
    }

    /**
     * posicion y
     * @return 
     */
    public float getY()
    {
        return yPos;
    }

    /**
     * posicion x
     * @return 
     */
    public float getX()
    {
        return xPos;
    }

    /**
     * Dice hacia donde mira el personaje
     * @return TRUE = DERECHA
     */
    public boolean getFace()
    {
        return xVel > 0;
    }

    /**
     * INICIA el mostrado del personaje 
     */
    public void start()
    {
        Game.addScene(this);
    }
    
    /**
     * INICIA el mostrado deinventario personaje 
     */
    public void startI()
    {
        Game.addScene(inventory);
    }

    /**
     * TERMINA el mostrado del personaje y de su inventario
     */
    public void end()
    {
        Game.removeSence(this);
        Game.removeSence(inventory);
    }

    /**
     * PARALIZA al personaje y su inventario, deja de actualizarlos
     */
    public void pause()
    {
        this.setState(Scene.STATE.FREEZE);
        inventory.setState(Scene.STATE.FREEZE);
    }
    /**
     * DESPIERTA al personaje y su inventario, continúa actualizándolos
     */
    public void wake()
    {
        this.setState(Scene.STATE.ON);
        inventory.setState(Scene.STATE.ON);
    }

    /**
     * PONE LOS ENEMIGOS DEL PERSONAJE
     * @param enemy 
     */
    public void addEnemys(ArrayList<Charact> enemy)
    {
        this.enemy = enemy;
    }
    
    /**
     * OBTIENE LOS ENEMIGOS DEL PERSONAJE
     * @return 
     */
    public ArrayList<Charact> getEnemys()
    {
        return enemy;
    }
    
    /**
     * SUBE EL FLAG DE ACTIVIDAD
     */
    public void activate()
    {
        active = true;
    }
    
    /**
     * DA LA SALUD MÁXIMA
     * @return 
     */
    public int getHealthMax ()
    {
        return healthMax;
    }
    public void hasKilled(int money){}
    
    public abstract int getInfo();
    
    public void setHudAlien (Charact enemy, int LastLive){}
    
    /**
     * DICE SI SALTA
     * @return 
     */
    public boolean jumping ()
    {
        return jumpUp || jumpDown;
    }
    
    public int getBorder()
    {
        return borderRoom;
    }
    /**
     * LLAMADA PARA OBTENER LIMITES DE BALAS
     * @param x
     * @param y
     * @return 
     */
    public float[] bulletControl (float x,float y)
    {
        return field.bulletControl(x, y);
    }
}
