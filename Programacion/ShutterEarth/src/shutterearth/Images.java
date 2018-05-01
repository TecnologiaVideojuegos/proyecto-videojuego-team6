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
    private final HashMap <Integer,Image> images;
    private final HashMap <Integer,Animation> sprites;
    public static int MENU = 0;
    public static int BASE_DER = 1;
    public static int BASE_IZQ = 2;
    public static int HERO0 = 3;
    public static int HERO1 = 4;
    public static int HERO2 = 5;
    public static int HERO3 = 6;
    public static int HERO4 = 7;
    
    public Images ()
    {
        sprites = new HashMap <>();
        images = new HashMap <>();

        try
        {
            sprites.put(Images.BASE_DER, new Animation (new SpriteSheet("./images/BASE_DER.png",281,300),180));
            sprites.put(Images.BASE_IZQ, new Animation (new SpriteSheet("./images/BASE_IZQ.png",281,300),180));
            images.put(Images.MENU, new Image("./images/MENU.png"));
        }
        catch (SlickException e)
        {
            System.out.println("ERROR INLOADING IMAGES");
        }
    }
    
    public Animation getSprit (int n)
    {
        return sprites.get(n);
    }
    public Image getImage (int n)
    {
        return images.get(n);
    }
}
