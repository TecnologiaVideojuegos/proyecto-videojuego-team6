/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shutterearth.map;

import shutterearth.screens.Scene;

/**
 *
 * @author mr.blissfulgrin
 */
public abstract class Map extends Scene
{
    protected final float x;
    protected final float y;
    
    //NOS PERMITE APLICAR CUALQUIER MAPA AL JUEGO SIN IMPORTAR SU NATURALEZA
    //LAS COLISIONES BALA-MAPA SE HACEN A MANO POR ESO
    public Map (float x, float y)
    {
        this.x = x;
        this.y = y;
    }
    
    /**
     * CONTROL DE COLISIONES DE BALAS CON EL MAPA
     * @param x
     * @param y
     * @return 
     */
    public abstract float [] bulletControl (float x,float y);
    
    /**
     * Da los spawns de los personajes, (x,y,)npc (w,h,floor)habitación
     * Primero el héroe y el reto son los npc
     * @param n
     * @return 
     */
    public abstract float [][] getSpots (int n);
    /**
     * Da la sigiente habitación si se puede ir a ella o la misma si no
     * @param room
     * @param x
     * @param up
     * @param w
     * @param hero
     * @return 
     */
    public abstract float[] getNextRoom (int room,float x, boolean up, float w, boolean hero);
    //Control de Scena
    public abstract void start ();
    public abstract void pause ();
    public abstract void wake ();
    public abstract void end ();
}
