/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shutterearth;

import java.util.HashMap;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author mr.blissfulgrin
 */
public class Media
{
    private final HashMap <Integer,Image> images;
    private final HashMap <Integer,Animation> sprites;
    private final HashMap <Integer,Music> music;
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
    public static int FULL_LIVE = 14;
    public static int TQUARTERS_LIVE = 15;
    public static int HALF_LIVE = 16;
    public static int QUARTER_LIVE = 17;
    public static int CANCION_MENU = 18;
    
    public Media ()
    {
        sprites = new HashMap <>();
        images = new HashMap <>();
        music = new HashMap <>();

        try
        {
            sprites.put(Media.BASE_DER, new Animation (new SpriteSheet("./media/BASE_DER.png",281,300),180));
            sprites.put(Media.BASE_IZQ, new Animation (new SpriteSheet("./media/BASE_IZQ.png",281,300),180));
            images.put(Media.MENU, new Image("./media/MENU.png"));
            images.put(Media.GUN0, new Image("./media/BULLET.png"));
            images.put(Media.GUN1, new Image("./media/BULLET.png"));
            images.put(Media.GUN2, new Image("./media/BULLET.png"));
            images.put(Media.GUN3, new Image("./media/BULLET.png"));
            images.put(Media.GUN4, new Image("./media/BULLET.png"));
            images.put(Media.BULLETS, new Image("./media/BULLET.png"));
            images.put(Media.FULL_LIVE, new Image("./media/FULL_LIVE.png"));
            images.put(Media.TQUARTERS_LIVE, new Image("./media/TQUARTERS_LIVE.png"));
            images.put(Media.HALF_LIVE, new Image("./media/HALF_LIVE.png"));
            images.put(Media.QUARTER_LIVE, new Image("./media/QUARTER_LIVE.png"));
            music.put(Media.CANCION_MENU, new Music("./media/CANCION_MENU.ogg", false));
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
    public Music getMusic (int n)
    {
        return music.get(n);
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
    public static int getLive (int id)
    {
        switch (id)
        {
            case 0:
                return FULL_LIVE;
            case 1:
                return TQUARTERS_LIVE;
            case 2:
                return HALF_LIVE;
            case 3:
                return QUARTER_LIVE;
            default:
                return BULLETS;
        }
    }
}
