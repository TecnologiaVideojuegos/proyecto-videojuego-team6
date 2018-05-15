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
       
    public static enum IMAGE {MENU,BULLET_R,BULLET_L,GUN1,GUN0,HERO_DER,HERO_IZQ,GUN2,GUN3,GUN4,FULL_LIVE,TQUARTERS_LIVE,HALF_LIVE,QUARTER_LIVE,LOG_IN,EXIT,NEW,REGISTER,BACK,PLAY,STORE,FORWARD,UPGRADE,RESUME,END_GAME,GREY,GAME,BATTLE,FIRE_L,FIRE_R,BB,SHIP_RIGHT,SHIP_LEFT,GRAVE,SHIP_F_DER,SHIP_F_IZQ,BLOOD,EXPLOSION,SHIP_SHOT,BACKGROUND_0,BACKGROUND_1,BACKGROUND_2,BACKGROUND_3,BACKGROUND_4,BACKGROUND_5,BACKGROUND_6,HERO_HEAD,BOLA_MALO,SHOT_ALIEN_RIGHT,SHOT_ALIEN_LEFT,BAD_GUY};    
    public static enum SPRITE {BASE_DER,BASE_IZQ,BASE_SDE,BASE_SIZ,FUERTE_DER,FUERTE_IZQ,FUERTE_SDE,FUERTE_SIZ,HERO_1_DER,HERO_1_IZQ,HERO_1_SDE,HERO_1_SIZ,HERO_2_DER,HERO_2_IZQ,HERO_2_SDE,HERO_2_SIZ,HERO_3_DER,HERO_3_IZQ,HERO_3_SDE,HERO_3_SIZ,HERO_4_DER,HERO_4_IZQ,HERO_4_SDE,HERO_4_SIZ,HERO_5_DER,HERO_5_IZQ,HERO_5_SDE,HERO_5_SIZ,MALO_DER,MALO_IZQ,MALO_SDE,MALO_SIZ};
    public static enum MUSIC {CANCION_MENU,CANCION_GAME,CANCION_FONDO,BATTLE_SONG,END_SONG};
    public static enum SOUND {SHOT,ALIEN1,ALIEN2,SHIP_SONG,SHIP_SONG2,CASH,BAD,HITED,HITED_ALIEN,FIRE_ALIEN};
    
    private Image background;
    private Rectangle black;
    private Rectangle yellow;
    private int amount;
    private int where;
    private int maxW;
    private int next;
    private boolean oneIn;
    
    private String source;
    
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
        this.source = "";
    }
    

    @Override
    public void Render(GameContainer gc, Graphics g) throws SlickException
    {
        background.draw(0,0,Game.getX(),Game.getY());
        g.setColor(Color.black);
        g.fill(black);
        g.setColor(Color.yellow);
        g.fill(yellow);
        g.drawString(source, black.getX(), black.getY()+black.getHeight()+20);
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

                    images.put(img, new Image("./media/"+img.name()+".png"));
                    where ++;
                    oneIn = true;
                    source = "./media/"+img.name()+"/.png";
                    break;
                }
            }
            
            for (SPRITE spt : SPRITE.values())
            {
                if (!sprites.containsKey(spt) && !oneIn)
                {
                    if (!spt.name().contains("HERO")&&!spt.name().contains("MALO"))
                        sprites.put(spt, new Animation (new SpriteSheet("./media/"+spt.name()+".png",spt.name().charAt(spt.name().length()-3)=='S'?300:281,spt.name().charAt(spt.name().length()-3)=='S'?320:300),180));
                    else
                    {
                        switch (spt.name())
                        {
                            case "MALO_DER":
                            case "MALO_IZQ":
                                sprites.put(spt, new Animation(new SpriteSheet("./media/"+spt.name()+".png",536,385),180));
                                break;
                            case "MALO_SDE":
                            case "MALO_SIZ":
                                sprites.put(spt, new Animation(new SpriteSheet("./media/"+spt.name()+".png",522,400),180));
                                break;
                            case "HERO_1_DER":
                            case "HERO_1_IZQ":
                                sprites.put(spt, new Animation(new SpriteSheet("./media/"+spt.name()+".png",312,408),180));
                                break;
                            case "HERO_1_SDE":
                            case "HERO_1_SIZ":
                                sprites.put(spt, new Animation(new SpriteSheet("./media/"+spt.name()+".png",265,424),180));
                                break;
                            case "HERO_2_DER":
                            case "HERO_2_IZQ":
                                sprites.put(spt, new Animation(new SpriteSheet("./media/"+spt.name()+".png",328,408),180));
                                break;
                            case "HERO_2_SDE":
                            case "HERO_2_SIZ":
                                sprites.put(spt, new Animation(new SpriteSheet("./media/"+spt.name()+".png",280,424),180));
                                break;
                            case "HERO_3_DER":
                            case "HERO_3_IZQ":
                                sprites.put(spt, new Animation(new SpriteSheet("./media/"+spt.name()+".png",368,408),180));
                                break;
                            case "HERO_3_SDE":
                            case "HERO_3_SIZ":
                                sprites.put(spt, new Animation(new SpriteSheet("./media/"+spt.name()+".png",320,424),180));
                                break;
                            case "HERO_4_DER":
                            case "HERO_4_IZQ":
                                sprites.put(spt, new Animation(new SpriteSheet("./media/"+spt.name()+".png",344,410),180));
                                break;
                            case "HERO_4_SDE":
                            case "HERO_4_SIZ":
                                sprites.put(spt, new Animation(new SpriteSheet("./media/"+spt.name()+".png",296,424),180));
                                break;
                            case "HERO_5_DER":
                            case "HERO_5_IZQ":
                                sprites.put(spt, new Animation(new SpriteSheet("./media/"+spt.name()+".png",496,408),180));
                                break;
                            case "HERO_5_SDE":
                            case "HERO_5_SIZ":
                                sprites.put(spt, new Animation(new SpriteSheet("./media/"+spt.name()+".png",449,424),180));
                                break;
                        }
                    }
                    where ++;
                    oneIn = true;
                    source = "./media/"+spt.name()+"/.png";
                    break;
                }
            }
            
            for (MUSIC ms : MUSIC.values())
            {
                if (!music.containsKey(ms) && !oneIn)
                {
                    music.put(ms, new Music("./media/"+ms.name()+".wav", false));
                    where ++;
                    oneIn = true;
                    source = "./media/"+ms.name()+"/.wav";
                    break;
                }
            }
            
            for (SOUND s : SOUND.values())
            {
                if (!sound.containsKey(s) && !oneIn)
                {
                    sound.put(s, new Sound("./media/"+s.name()+".wav"));
                    where ++;
                    oneIn = true;
                    source = "./media/"+s.name()+"/.wav";
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
                return IMAGE.GUN1;
            case 2:
                return IMAGE.GUN2;
            case 3:
                return IMAGE.GUN3;
            case 4:
                return IMAGE.GUN4;
            default:
                return null;
        }
    }
    public static IMAGE getBackGround (int id)
    {
        switch (id)
        {
            case 0:
                return IMAGE.BACKGROUND_0;
            case 1:
                return IMAGE.BACKGROUND_1;
            case 2:
                return IMAGE.BACKGROUND_2;
            case 3:
                return IMAGE.BACKGROUND_3;
            case 4:
                return IMAGE.BACKGROUND_4;
            case 5:
                return IMAGE.BACKGROUND_5;
            case 6:
                return IMAGE.BACKGROUND_6;
            default:
                return null;
        }
    }
    
    @Override
    public String toString()
    {
        return "Media";
    }
}
