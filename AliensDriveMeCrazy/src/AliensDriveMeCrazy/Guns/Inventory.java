/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AliensDriveMeCrazy.Guns;

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
    public Inventory (SavedInventory inventory)
    {
        this.wearpon = new ArrayList <>();
        inventory.getWearpon().forEach((w) ->
        {
            this.wearpon.add(new Wearpon(w));
        });
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
        boolean shotable = wearpon.get(wearponCurrent).isShotable();
        if (!shotable)
            wearponCurrent++;
        return shotable;
    }

    @Override
    public int getAmount()
    {
        return wearpon.get(wearponCurrent).getAmount();
    }
    
    @Override
    public int getBulletsMax()
    {
        return wearpon.get(wearponCurrent).getBulletsMax();
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
    
    @Override
    public int getDamage()
    {
        return wearpon.get(wearponCurrent).getDamage();
    }
    
    public SavedInventory save ()
    {
        ArrayList <SavedWearpon> wearponSaved = new ArrayList <>();
        this.wearpon.forEach((w) ->
        {
            wearponSaved.add(w.save());
        });
        return new SavedInventory(wearponSaved);
    }
}
