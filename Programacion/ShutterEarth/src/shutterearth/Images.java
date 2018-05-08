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
    public static int GUN0 = 8;
    public static int GUN1 = 9;
    public static int GUN2 = 10;
    public static int GUN3 = 11;
    public static int GUN4 = 12;
    public static int BULLETS = 13;
    
    public Images ()
    {
        sprites = new HashMap <>();
        images = new HashMap <>();

        try
        {
            sprites.put(Images.BASE_DER, new Animation (new SpriteSheet("./images/BASE_DER.png",281,300),180));
            sprites.put(Images.BASE_IZQ, new Animation (new SpriteSheet("./images/BASE_IZQ.png",281,300),180));
            images.put(Images.MENU, new Image("./images/MENU.png"));
            images.put(Images.GUN0, new Image("./images/BULLET.png"));
            images.put(Images.GUN1, new Image("./images/BULLET.png"));
            images.put(Images.GUN2, new Image("./images/BULLET.png"));
            images.put(Images.GUN3, new Image("./images/BULLET.png"));
            images.put(Images.GUN4, new Image("./images/BULLET.png"));
            images.put(Images.BULLETS, new Image("./images/BULLET.png"));
        }
        catch (SlickException e)
        {
            System.out.println("ERROR LOADING IMAGES");
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
    public static int getGun (int id)
    {
        switch (id)
        {
            case 0:
                return GUN0;
            case 1:
                return GUN0;
            case 2:
                return GUN0;
            case 3:
                return GUN0;
            case 4:
                return GUN0;
            default:
                return BULLETS;
        }
    }
}
