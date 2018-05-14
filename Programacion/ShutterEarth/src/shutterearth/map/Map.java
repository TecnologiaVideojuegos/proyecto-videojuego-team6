/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shutterearth.map;

/**
 *
 * @author mr.blissfulgrin
 */
public interface Map //Los que implemente Map deben extender de Scene tb
{
    //Dimensiones del mapa
    public void draw (float x, float y, float w, float h); 
    //Da los spawns de los personajes, (x,y,)npc (w,h,floor)habitación
    //Primero el héroe y el reto son los npc
    public int [] getSpots (int n);
    //Da la sigiente habitación si se puede ir a ella o la misma si no
    public void getNextRoom (boolean up);
    //Control de Scena, deben tenerlos pero no tienen por qué contener nada si no se necesita
    //Solo si el mapa tiene animaciones
    public void start ();
    public void pause ();
    public void wake ();
    public void end ();
}
