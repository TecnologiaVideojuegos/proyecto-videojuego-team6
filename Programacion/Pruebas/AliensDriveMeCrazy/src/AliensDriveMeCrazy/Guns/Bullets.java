/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AliensDriveMeCrazy.Guns;
import java.util.ArrayList;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author mr.blissfulgrin
 */
public class Bullets implements WearponInterface
{
    private int amountMax;
    private int amountCurrent;
    private Image img;
    private final int damage;
    private final int shotSpeed;
    private final int MAX_BULLETS;
    private ArrayList <Integer> cost;
    private int level;
    
    public Bullets (int amountMax, String source, int damage, int shotSpeed, int MAX_BULLETS)
    {
        this.amountMax = amountMax>0? amountMax:0;
        this.amountCurrent = this.amountMax;
        this.damage = damage>0? damage:0;
        this.shotSpeed = shotSpeed>0? shotSpeed:0;
        this.MAX_BULLETS = MAX_BULLETS;
        
        try
        {
            this.img = new Image(source);
        } 
        catch (SlickException ex)
        {
            System.out.println("ERROR DE IMAGEN BULLETS");
        }
    }
    public Bullets (int amountMax, String source, int damage, int shotSpeed)
    {
        this.amountMax = amountMax>0? amountMax:0;
        this.amountCurrent = this.amountMax;
        this.damage = damage>0? damage:0;
        this.shotSpeed = shotSpeed>0? shotSpeed:0;
        this.MAX_BULLETS = 0;
        
        try
        {
            this.img = new Image(source);
        } 
        catch (SlickException ex)
        {
            System.out.println("ERROR DE IMAGEN BULLETS");
        }
    }
    public Bullets (SavedBullets bullets)
    {
        this.amountMax = bullets.getAmountMax();
        this.amountCurrent = bullets.getAmountMax();
        this.damage = bullets.getDamage();
        this.shotSpeed = bullets.getShotSpeed();
        this.MAX_BULLETS = bullets.getMAX_BULLETS();
        try
        {
            this.img = new Image(bullets.getImg());
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
    
    public SavedBullets save()
    {
        return new SavedBullets(amountMax, img.getResourceReference(), damage, shotSpeed, MAX_BULLETS);
    }

    @Override
    public int getMAX_BULLETS ()
    {
        return MAX_BULLETS;
    }
}
