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
    public static int HERO_DER = 3;
    public static int HERO_IZQ = 4;
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
    public static int LOG_IN = 19;
    public static int EXIT = 20;
    public static int NEW = 21;
    public static int REGISTER = 22;
    public static int BACK = 23;
    public static int PLAY = 24;
    public static int STORE = 25;
    public static int FORWARD = 26;
    public static int UPGRADE = 27;
    public static int GREY = 28;
    public static int RESUME = 29;
    public static int END_GAME = 30;
    public static int GAME = 31;
    public static int BATTLE = 32;
    
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
            images.put(Media.HERO_DER, new Image("./media/HERO_DER.png"));
            images.put(Media.HERO_IZQ, new Image("./media/HERO_IZQ.png"));
            images.put(Media.GUN2, new Image("./media/BULLET.png"));
            images.put(Media.GUN3, new Image("./media/BULLET.png"));
            images.put(Media.GUN4, new Image("./media/BULLET.png"));
            images.put(Media.BULLETS, new Image("./media/BULLET.png"));
            images.put(Media.FULL_LIVE, new Image("./media/FULL_LIVE.png"));
            images.put(Media.TQUARTERS_LIVE, new Image("./media/TQUARTERS_LIVE.png"));
            images.put(Media.HALF_LIVE, new Image("./media/HALF_LIVE.png"));
            images.put(Media.QUARTER_LIVE, new Image("./media/QUARTER_LIVE.png"));
            images.put(Media.LOG_IN, new Image("./media/LOG_IN.png"));
            images.put(Media.EXIT, new Image("./media/EXIT.png"));
            images.put(Media.NEW, new Image("./media/NEW.png"));
            images.put(Media.REGISTER, new Image("./media/REGISTER.png"));
            images.put(Media.BACK, new Image("./media/BACK.png"));
            images.put(Media.PLAY, new Image("./media/PLAY.png"));
            images.put(Media.STORE, new Image("./media/STORE.png"));
            images.put(Media.FORWARD, new Image("./media/FORWARD.png"));
            images.put(Media.UPGRADE, new Image("./media/UPGRADE.png"));
            images.put(Media.RESUME, new Image("./media/RESUME.png"));
            images.put(Media.END_GAME, new Image("./media/END_GAME.png"));
            images.put(Media.GREY, new Image("./media/GREY.png"));
            images.put(Media.GAME, new Image("./media/GAME.png"));
            images.put(Media.BATTLE, new Image("./media/BATTLE.png"));
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
}
