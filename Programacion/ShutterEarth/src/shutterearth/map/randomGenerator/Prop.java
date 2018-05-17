
package shutterearth.map.randomGenerator;

/**
 * Clase que contiene variables static con los factores de proporcionalidad
 * utilizados para la creacion de las celdas y demas elementos geometricos en la
 * generacion del nivel
 */
public class Prop 
{
    /**Alto del espacio del HUB*/
    public static float hubH = (3/35f);
    /**Alto del personaje*/
    public static float chH = (1/14f);
    /**Mitad del ancho del personaje*/
    public static float chHALFW = (1/80f);
    /**Un tercio del alto de una habitacion*/
    public static float ceTHIRDH = (4/105f);
    /**Ancho de una celda. Las celdas forman habitaciones*/
    public static float ceWI = (1/10f);
    /**Ancho de una salida*/
    public static float saWI = (3/40f);
}
