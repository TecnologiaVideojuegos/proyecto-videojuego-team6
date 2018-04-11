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
    
    public void refill(int wearpon)
    {
        for (int x = 0; x <  this.wearpon.size(); x++)
        {
            if (this.wearpon.get(x).getIdentifer() == wearpon)
                this.wearpon.get(x).refill();
        }
        this.goBest();
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
    
    public void goLeft ()
    {
        if ((wearponCurrent + 1) < wearpon.size())
            wearponCurrent ++;
    }
    public void goRight ()
    {
        if ((wearponCurrent - 1) >= 0)
            wearponCurrent --;
    }
    
    public void goBest ()
    {
        wearponCurrent = 0;
        boolean error = false;
        while (!((wearpon.get(wearponCurrent).getAmount() > 0) || (wearpon.get(wearponCurrent).getBulletsMax() == 0) || error))
            if ((wearponCurrent + 1) < wearpon.size())
                wearponCurrent ++;
            else
                error = true;
    }
    
    @Override
    public int getMAX_BULLETS ()
    {
        return wearpon.get(wearponCurrent).getMAX_BULLETS();
    }
    
    public int numberWearpons ()
    {
        return wearpon.size();
    }
    
    public Image showWearpon (int x)
    {
        return wearpon.get(x).getImage();
    }
    public Image showBullets (int x)
    {
        return wearpon.get(x).getBullets();
    }
}
