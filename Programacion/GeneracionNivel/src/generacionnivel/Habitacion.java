
package generacionnivel;

import java.util.ArrayList;
import org.newdawn.slick.geom.Rectangle;

/**
 * @author Daniel
 */
public class Habitacion {
    private Rectangle hab;//opcional
    private ArrayList <Rectangle> salidas;
    private float maxI;
    private float maxD;
    
    public Habitacion(boolean[] salidas, float posX, float posY){
        for(int i=0;i<salidas.length;i++){
            //120=ancho de la salida menos la mitad del del personaje para evitar falsos positivos
            //if(salidas[i]) this.salidas.add(new Rectangle(,,120,80));
        }
    }
    
}
