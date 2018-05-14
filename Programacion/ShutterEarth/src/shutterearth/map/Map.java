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
    
    public Map (float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    //Da los spawns de los personajes, (x,y,)npc (w,h,floor)habitación
    //Primero el héroe y el reto son los npc
    public abstract float [][] getSpots (int n);
    //Da la sigiente habitación si se puede ir a ella o la misma si no
    public abstract float[] getNextRoom (int room, boolean up);
    //Control de Scena, deben tenerlos pero no tienen por qué contener nada si no se necesita
    //Solo si el mapa tiene animaciones
    public abstract void start ();
    public abstract void pause ();
    public abstract void wake ();
    public abstract void end ();
}
