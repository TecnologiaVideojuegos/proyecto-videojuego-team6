/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AliensDriveMeCrazy.Guns;

import java.io.Serializable;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author mr.blissfulgrin
 */
public class Bullets implements WearponInterface, Serializable
{
    private int amountMax;
    private int amountCurrent;
    private Image img;
    private final int damage;
    private final int shotSpeed;
    
    public Bullets (int amountMax, String source, int damage, int shotSpeed)
    {
        this.amountMax = amountMax;
        this.amountCurrent = amountMax;
        this.damage = damage;
        this.shotSpeed = shotSpeed;
        
        try
        {
            this.img = new Image(source);
        } 
        catch (SlickException ex)
        {
            System.out.println("ERROR DE IMAGEN BULLETS");
        }
    }
    
    @Override
    public int getAmount ()
    {
        return amountCurrent;
    }
    
    @Override
    public void setAmountMax (int amount)
    {
        amountMax = amount;
        amountCurrent = amount;
    }
    
    @Override
    public int getBulletsMax()
    {
        return amountMax;
    }
      
    @Override
    public void refill ()
    {
        amountCurrent = amountMax;
    }
    
    public Image getImage ()
    {
        return img;
    }
    
    @Override
    public boolean isShotable ()
    {
        return (amountMax == 0)? true:amountCurrent > 0;
    }
    
    @Override
    public int shot ()
    {
        amountCurrent --;
        return damage;
    }
    
    @Override
    public int getShotSpeed()
    {
        return shotSpeed;
    }
    
    @Override
    public int getDamage()
    {
        return damage;
    }
}
