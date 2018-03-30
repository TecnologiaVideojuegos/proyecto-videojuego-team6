/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AliensDriveMeCrazy;

import java.util.ArrayList;
import org.newdawn.slick.Image;

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
        this.wearponCurrent = 0;
    }
    
    public void addWearpon (Wearpon wearpon)
    {
        this.wearpon.add(wearpon);
    }
    
    public int getCurrent ()
    {
        return wearponCurrent;
    }
    
    public Image getWearpon ()
    {
        return wearpon.get(wearponCurrent).getImage();
    }
    
    public Image getBullets ()
    {
        return wearpon.get(wearponCurrent).getBullets();
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

    @Override
    public int getAmount()
    {
        return wearpon.get(wearponCurrent).getAmount();
    }

    @Override
    public void refill()
    {
        wearpon.get(wearponCurrent).refill();
    }

    @Override
    public void setAmountMax(int amount)
    {
        wearpon.get(wearponCurrent).setAmountMax(amount);
    }
    
    @Override
    public int getShotSpeed ()
    {
        return wearpon.get(wearponCurrent).getShotSpeed();
    }
}
