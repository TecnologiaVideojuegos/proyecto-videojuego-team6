
package pruebas;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.*;


public class Main {

    public static void main(String[] args) {
        try {
            Juego game = new Juego("Prueba");
        } catch (SlickException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
