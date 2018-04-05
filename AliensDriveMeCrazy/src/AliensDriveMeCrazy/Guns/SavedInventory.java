/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AliensDriveMeCrazy.Guns;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author mr.blissfulgrin
 */
public class SavedInventory implements Serializable
{
    private final ArrayList <SavedWearpon> wearpon;
    
    public SavedInventory (ArrayList <SavedWearpon> wearpon)
    {
        this.wearpon = wearpon;
    }
    
    public ArrayList <SavedWearpon> getWearpon ()
    {
        return wearpon;
    }
}
