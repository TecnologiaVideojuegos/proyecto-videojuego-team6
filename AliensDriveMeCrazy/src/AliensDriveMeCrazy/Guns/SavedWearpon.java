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
public class SavedWearpon implements Serializable
{
    private final int identifer;
    private final String img;
    private final SavedBullets bullets;
    
    public SavedWearpon (int identifer, String img, SavedBullets bullets)
    {
        this.identifer = identifer;
        this.img = img;
        this.bullets = bullets;
    }
    
    public int getIdentifer ()
    {
        return identifer;
    }
    public String getImg ()
    {
        return img;
    }
    public SavedBullets getBullets ()
    {
        return bullets;
    }
}
