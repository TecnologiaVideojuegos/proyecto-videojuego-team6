/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AliensDriveMeCrazy;

import java.util.ArrayList;

/**
 *
 * @author mr.blissfulgrin
 */
public class Inventory implements WearponInterface
{
    private final ArrayList <Wearpon> wearpon;
    private int wearponCurrent;
    
    public Inventory()
    {
        this.wearpon = new ArrayList <>();
    }
    
    public void addWearpon (Wearpon wearpon)
    {
        this.wearpon.add(wearpon);
    }

    @Override
    public int shot()
    {
        return wearpon.get(wearponCurrent).shot();
    }

    @Override
    public boolean isShotable()
    {
        boolean state = wearpon.get(wearponCurrent).isShotable();
        wearponCurrent++;
        return state;
    }
}
