/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shutterearth.map;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import shutterearth.Game;
import shutterearth.Media;
import shutterearth.characters.Hero;
import shutterearth.screens.Scene;
import shutterearth.characters.Charact;

/**
 *
 * @author mr.blissfulgrin
 */
public class HUD extends Scene
{
    private final Hero hero;
    private Charact bad;
    private final LiveDisplayer heroHealth;
    private final LiveDisplayer badHealth;
    private final int gx;
    private final int gy;
    private final int gw;
    private final int gh;
    private final int bx;
    private final int by;
    private final int bw;
    private final Rectangle rect;
    
    private int counter;
    private final int tCounter;
    
    public HUD (Hero hero)
    {
        this.hero = hero;
        this.heroHealth = new LiveDisplayer(10,10,Game.getX()/60,10,hero.getHealthMax());
        
        this.gy = 20;
        this.gw = Game.getX()/15;
        this.gh = gw;
        this.gx = Game.getX() - gw - gy;
        
        this.bw = Game.getX()/20;
        this.bx = Game.getX()/2 - bw - (Game.getX()*15)/70 - (15*Game.getX())/700 + Game.getX()/40;
        this.by = 20;
        
        this.rect = new Rectangle(bx-10,by -10,(Game.getX()*30)/70 + (30*Game.getX())/700 +20 + bw,(Game.getX()*4)/70 + (4*Game.getX())/700 -10 + by);
        
        this.badHealth = new LiveDisplayer(Game.getX()/2 - (Game.getX()*15)/70 - (15*Game.getX())/700 + Game.getX()/40 +10,by,Game.getX()/70,30,0);
    
        counter = 0;
        tCounter = 500;
    }
    
    @Override
    public void Render(GameContainer gc, Graphics g) throws SlickException
    {
        g.setColor(Color.yellow);
        Game.getMedia().getImage(Media.getGun(hero.getInventory().getGunID())).draw(gx,gy,gw,gh);

        g.drawString("Bullets: " + hero.getBullets(), gx, gy+gh+15);
        if (bad != null)
        {
            switch(bad.getInfo())
            {
                case 1:
                    Game.getMedia().getSprit(Media.SPRITE.BASE_DER).draw(bx,by,bw,bw);
                    break;
                case 2:
                    Game.getMedia().getSprit(Media.SPRITE.FUERTE_DER).draw(bx,by,bw,bw);
                    break;
                case 3:
                    Game.getMedia().getImage(Media.IMAGE.SHIP_RIGHT).draw(bx,by,bw,bw);
                    break;
                case 4:
                    Game.getMedia().getImage(Media.IMAGE.SHIP_F_DER).draw(bx,by,bw,bw);
                    break;
                case 5:
                    Game.getMedia().getSprit(Media.SPRITE.MALO_DER).draw(bx,by,bw,bw);
                    break;
            }
            g.draw(rect);
            Game.getMedia().getImage(Media.IMAGE.GREY).draw(rect.getX(),rect.getY(),rect.getWidth(),rect.getHeight());
        }
    }

    @Override
    public void Update(GameContainer gc, float t) throws SlickException
    {
        heroHealth.setHealth(hero.getHealthCurrent(), true);
        if (bad != null)
        {
            badHealth.setHealth(bad.getCurrentHealth(), true);
            if (counter > tCounter)
            {    
                bad = null;
                counter = 0;
            }
            else
                counter ++;
            badHealth.setDisplay(true);
        }
        else
        {
            badHealth.setHealth(0, false);
            badHealth.setDisplay(false);
        }
    }

    @Override
    public void init(GameContainer gc) throws SlickException
    {
        Game.addScene(heroHealth);
        Game.addScene(badHealth);
    }
    /**
     * NUEVO ALIEN A MOSTRAR
     * @param bad
     * @param lastLive 
     */
    public void addBadGuy (Charact bad,int lastLive)
    {
        badHealth.setHealth(lastLive, false);
        this.bad = bad;
    }
    /**
     * FINALIZA EL HUD
     */
    public void end ()
    {
        Game.removeSence(this);
        Game.removeSence(heroHealth);
        Game.removeSence(badHealth);
        hero.end();
    }
    /**
     * INICIA EL HUD
     */
    public void start ()
    {
        Game.addScene(this);
    }
    /**
     * PARA EL HUD
     */
    public void pause ()
    {
        this.setState(STATE.FREEZE);
        heroHealth.setState(Scene.STATE.FREEZE);
        badHealth.setState(Scene.STATE.FREEZE);
        hero.pause();
    }
    /**
     * DESPIERTA AL HUD
     */
    public void wake ()
    {
        this.setState(STATE.ON);
        heroHealth.setState(Scene.STATE.ON);
        badHealth.setState(Scene.STATE.ON);
        hero.wake();
    }
    
    @Override
    public String toString()
    {
        return "HUD "+this.hero.toString();
    }
}
