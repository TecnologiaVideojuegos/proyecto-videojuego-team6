/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AliensDriveMeCrazy.Guns;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author mr.blissfulgrin
 */
public class Wearpon implements WearponInterface
{
    private final int identifer;
    private Image img;
    private final Bullets bullets;

    public Wearpon (String source, Bullets bullets, int identifer)
    {
        this.identifer = identifer;
        this.bullets = bullets;

        try
        {
            this.img = new Image(source);
        } 
        catch (SlickException e)
        {
            System.out.println("ERROR WEARPON LOADING IMG");
        }
    }
    public Wearpon (SavedWearpon wearpon)
    {
        this.identifer = wearpon.getIdentifer();
        this.bullets = new Bullets (wearpon.getBullets());

        try
        {
            this.img = new Image(wearpon.getImg());
        } 
        catch (SlickException e)
        {
            System.out.println("ERROR WEARPON LOADING IMG");
        }
    }
    
    public Image getImage ()
    {
        return img;
    }
    public Image getBullets ()
    {
        return bullets.getImage();
    }
    
    @Override
    public boolean isShotable()
    {
        return bullets.isShotable();
    }
    
    @Override
    public int shot()
    {
        return bullets.shot();
    }

    @Override
    public int getAmount()
    {
        return bullets.getAmount();
    }

    @Override
    public void refill()
    {
        bullets.refill();
    }

    @Override
    public void setAmountMax(int amount)
    {
        bullets.setAmountMax(amount);
    }
    
    @Override
    public int getShotSpeed()
    {
        return bullets.getShotSpeed();
    }
    
    @Override
    public int getBulletsMax()
    {
        return bullets.getBulletsMax();
    }
    
    @Override
    public int getDamage()
    {
        return bullets.getDamage();
    }
    
    public SavedWearpon save ()
    {
        return new SavedWearpon (identifer, img.getResourceReference(), bullets.save());
    }
}
