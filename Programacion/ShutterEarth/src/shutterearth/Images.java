/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shutterearth;

import java.util.HashMap;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author mr.blissfulgrin
 */
public class Images
{
    private final String [] spriteSources;
    private final String [] imageSources;
    private final HashMap <Integer,Image> images;
    private final HashMap <Integer,Animation> sprites;
    
    public Images ()
    {
        sprites = new HashMap <>();
        images = new HashMap <>();
        imageSources = new String [0]; 
        spriteSources = new String [] 
        {
            "BASE_DER","BASE_IZQ"
        };
        
        int x = 0;
        for (String source : spriteSources)
        {
            try
            {
                sprites.put(x, new Animation (new SpriteSheet("./images/"+source+".png",281,300),180));
            }
            catch (SlickException e)
            {
                System.out.println("ERROR IN " + source);
                sprites.put(x, null);
            }
            x++;
        }
    }
    
    public Animation getSprit (int n)
    {
        return sprites.get(n);
    }
}
