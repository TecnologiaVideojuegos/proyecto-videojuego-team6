/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shutterearth.map;

import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author mr.blissfulgrin
 */
public interface Deployable
{
    /**
     * DA UN SPAWN AL DEPLOYABLE
     * @param rect 
     */
    public void setRect (Rectangle rect);
    /**
     * DEVUELDE EL DEPLOYABLE PARA COMPROBAR COLISIONES
     * @return 
     */
    public Rectangle getRect ();
    /**
     * FINALIZA EL DEPLOYABLE
     */
    public void exit ();
}
