/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shutterearth;

import java.util.HashMap;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import shutterearth.screens.Access;
import shutterearth.screens.Scene;

/**
 *
 * @author mr.blissfulgrin
 */
public class Media extends Scene
{
    private final HashMap <IMAGE,Image> images;
    private final HashMap <SPRITE,Animation> sprites;
    private final HashMap <MUSIC,Music> music;
    private final HashMap <SOUND,Sound> sound;
       
    public static enum IMAGE {MENU,BULLET_R,BULLET_L,GUN1,GUN0,HERO_DER,HERO_IZQ,GUN2,GUN3,GUN4,FULL_LIVE,TQUARTERS_LIVE,HALF_LIVE,QUARTER_LIVE,LOG_IN,EXIT,NEW,REGISTER,BACK,PLAY,STORE,FORWARD,UPGRADE,RESUME,END_GAME,GREY,GAME,BATTLE,FIRE_L,FIRE_R,BB,SHIP_RIGHT,SHIP_LEFT};    
    public static enum SPRITE {BASE_DER,BASE_IZQ,BASE_SDE,BASE_SIZ,FUERTE_DER,FUERTE_IZQ,FUERTE_SDE,FUERTE_SIZ};
    public static enum MUSIC {CANCION_MENU,CANCION_GAME,CANCION_FONDO,BATTLE_SONG,END_SONG};
    public static enum SOUND {SHOT,ALIEN1,ALIEN2,SHIP_SONG,SHIP_SONG2,CASH,BAD};
    
    private Image background;
    private Rectangle black;
    private Rectangle yellow;
    private int amount;
    private int where;
    private int maxW;
    private int next;
    private boolean oneIn;
    
    public Media ()
    {
        sprites = new HashMap <>();
        images = new HashMap <>();
        music = new HashMap <>();
        sound = new HashMap <>();
        
        this.amount = IMAGE.values().length + SPRITE.values().length + MUSIC.values().length + SOUND.values().length;
        this.where = 0;
        this.maxW = (Game.getX()*8)/10-40;
        this.black = new Rectangle (Game.getX()/10, Game.getY()/3, (Game.getX()*8)/10, Game.getY()/3);
        this.yellow = new Rectangle (Game.getX()/10+20, Game.getY()/3+20, 0, Game.getY()/3-40);
        try
        {
            this.background = new Image("./media/MENU.png");
        } catch (SlickException ex)
        {
            System.out.println("ERROR LOADING MEDIA");
        }
    }
    

    @Override
    public void Render(GameContainer gc, Graphics g) throws SlickException
    {
        background.draw(0,0,Game.getX(),Game.getY());
        g.setColor(Color.black);
        g.fill(black);
        g.setColor(Color.yellow);
        g.fill(yellow);
        g.drawString("Loading...", black.getX(), black.getY()+black.getHeight()+20);
    }

    @Override
    public void Update(GameContainer gc, float t) throws SlickException
    {
        oneIn = false;
        try
        {
            for (IMAGE img : IMAGE.values())
            {
                if (!images.containsKey(img) && !oneIn)
                {
                    if (img.name().equals("GUN0")||img.name().equals("GUN1")||img.name().equals("GUN2")||img.name().equals("GUN3")|img.name().equals("GUN4"))
                        images.put(img, new Image("./media/BULLET_R.png"));
                    else
                        images.put(img, new Image("./media/"+img.name()+".png"));
                    where ++;
                    oneIn = true;
                    break;
                }
            }
            
            for (SPRITE spt : SPRITE.values())
            {
                if (!sprites.containsKey(spt) && !oneIn)
                {
                    sprites.put(spt, new Animation (new SpriteSheet("./media/"+spt.name()+".png",spt.name().charAt(spt.name().length()-3)=='S'?300:281,spt.name().charAt(spt.name().length()-3)=='S'?320:300),180));
                    where ++;
                    oneIn = true;
                    break;
                }
            }
            
            for (MUSIC ms : MUSIC.values())
            {
                if (!music.containsKey(ms) && !oneIn)
                {
                    music.put(ms, new Music("./media/"+ms.name()+".ogg", false));
                    where ++;
                    oneIn = true;
                    break;
                }
            }
            
            for (SOUND s : SOUND.values())
            {
                if (!sound.containsKey(s) && !oneIn)
                {
                    if (!s.name().equals("BAD"))
                        sound.put(s, new Sound("./media/"+s.name()+".ogg"));
                    else
                        sound.put(s, new Sound("./media/"+s.name()+".wav"));
                    where ++;
                    oneIn = true;
                    break;
                }
            }
        }
        catch (SlickException e)
        {
            System.out.println("ERROR LOADING MEDIA");
        }
        next = (maxW*where)/amount;
        yellow.setWidth(next);
                
        if (where >= amount)
        {
            Game.removeSence(this);
            Game.addScene(new Access());
            Game.getMedia().getMusic(Media.MUSIC.CANCION_MENU).loop();
        }
    }

    @Override
    public void init(GameContainer gc) throws SlickException{}

    public Animation getSprit (SPRITE n)
    {
        return sprites.get(n);
    }
    public Image getImage (IMAGE n)
    {
        return images.get(n);
    }
    public Music getMusic (MUSIC n)
    {
        return music.get(n);
    }
    public Sound getSound (SOUND n)
    {
        return sound.get(n);
    }
    public static IMAGE getGun (int id)
    {
        switch (id)
        {
            case 0:
                return IMAGE.GUN0;
            case 1:
                return IMAGE.GUN0;
            case 2:
                return IMAGE.GUN0;
            case 3:
                return IMAGE.GUN0;
            case 4:
                return IMAGE.GUN0;
            default:
                return IMAGE.BULLET_R;
        }
    }
}
