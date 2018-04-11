/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AliensDriveMeCrazy.Guns;

import java.io.Serializable;


/**
 *
 * @author mr.blissfulgrin
 */
public class SavedBullets implements Serializable
{
    private final int amountMax;
    private final String img;
    private final int damage;
    private final int shotSpeed;
    private final int MAX_BULLETS;
    
    public SavedBullets (int amountMax, String img, int damage, int shotSpeed, int MAX_BULLETS)
    {
        this.amountMax = amountMax;
        this.img = img;
        this.damage = damage;
        this.shotSpeed = shotSpeed;
        this.MAX_BULLETS = MAX_BULLETS;
    }
    
    public int getAmountMax()
    {
        return amountMax;
    }
    public int getDamage()
    {
        return damage;
    }
    public int getShotSpeed()
    {
        return shotSpeed;
    }
    public String getImg()
    {
        return img;
    }
    public int getMAX_BULLETS()
    {
        return MAX_BULLETS;
    }
}
