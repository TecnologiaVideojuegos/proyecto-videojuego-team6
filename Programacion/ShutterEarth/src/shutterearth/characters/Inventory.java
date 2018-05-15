/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shutterearth.characters;

import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import shutterearth.Game;
import shutterearth.Media;
import shutterearth.screens.Scene;

/**
 *
 * @author mr.blissfulgrin
 */
public class Inventory extends Scene
{
    private final int maxGun;
    private final ArrayList <Gun> inventory;
    private int index;
    private final ArrayList <Shot> shots;
    private final ArrayList <Shot> count;
    private final ArrayList <Shot> toRemoveS;
    private final ArrayList <Label> label;
    private final ArrayList <Label> toRemoveL;
    private final Charact hero;
    private int delay;
    private int counter;
    private final int from;
    
    public Inventory (ArrayList <int[]> guns, Charact hero)
    {
        this.hero = hero;
        this.shots = new ArrayList <>();
        this.toRemoveS = new ArrayList <>();
        this.label = new ArrayList <>();
        this.count = new ArrayList <>();
        this.toRemoveL = new ArrayList <>();
        maxGun = guns.size();
        index = 0;
        this.inventory = new ArrayList<>();
        guns.forEach((gun) ->
        {
            if (gun[1]>0)
                inventory.add(new Gun(gun[0],gun[1],0));
        });
        this.delay = inventory.get(0).getSpeed();
        this.counter = 0;
        this.from = 0;
    }
    
    public Inventory (int[] gun, Charact hero, int from)
    {
        this.hero = hero;
        this.shots = new ArrayList <>();
        this.toRemoveS = new ArrayList <>();
        this.label = new ArrayList <>();
        this.count = new ArrayList <>();
        this.toRemoveL = new ArrayList <>();
        maxGun = 1;
        index = 0;
        this.inventory = new ArrayList<>();
        inventory.add(new Gun(gun[0],gun[1],1));
        this.delay = inventory.get(0).getSpeed();
        this.counter = 0;
        this.from = from;
    }
    
    public void rightGun()
    {
        if (index < inventory.size()-1)
        {
            index++;
            this.delay = inventory.get(index).getSpeed();
        }
    }
    public void lefttGun()
    {
        if (index > 0)
        {
            this.delay = inventory.get(index).getSpeed();
            index--;
        }
    }
    public int getGunID()
    {
        return inventory.get(index).getID();
    }
    public int getCost()
    {
        return inventory.get(index).getConsume();
    }
    
    public void shot(int n)
    {
        if (hero.isAlive())
        {
            if (counter <= 0)
            {
                int offset = (int)(hero.getH()/(n+1));
                for (int j = 0; j < n; j++)
                {
                    shots.add(new Shot (inventory.get(index),hero,offset+offset*j));
                }
                counter = delay;
            }
        }
    }
    
    public void end ()
    {
        Game.removeSence(this);
    }

    @Override
    public void Render(GameContainer gc, Graphics g) throws SlickException
    {
        shots.forEach((s) ->
        {
            if (s.isDwable())
            {
                switch (from)
                {
                    case 0:
                        Game.getMedia().getImage(s.getFace()?Media.IMAGE.BULLET_R:Media.IMAGE.BULLET_L).draw(s.getX(),s.getY(),s.getW(),s.getH());
                        break;
                    case 1:
                        Game.getMedia().getImage(s.getFace()?Media.IMAGE.FIRE_R:Media.IMAGE.FIRE_L).draw(s.getX(),s.getY(),s.getW(),s.getH());
                        break;
                    case 2:
                        Game.getMedia().getImage(s.getFace()?Media.IMAGE.SHIP_SHOT:Media.IMAGE.SHIP_SHOT).draw(s.getX(),s.getY(),s.getW(),s.getH());
                        break;
                    case 3:
                        Game.getMedia().getImage(s.getFace()?Media.IMAGE.BOLA_MALO:Media.IMAGE.BOLA_MALO).draw(s.getX(),s.getY(),s.getW(),s.getH());
                        break;
                }
            }
        });
        label.forEach((lab)->
        {
            if (lab.isAlive())
                g.drawString(lab.getLabel(), lab.getX(), lab.getY());
        });
        count.forEach((s)->
        {
            switch (s.getHited())
            {
                case 0:
                case 1:
                case 2:
                    Game.getMedia().getImage(Media.IMAGE.BLOOD).draw(s.getX(),s.getY(),s.getW(),s.getH());
                    break;
                case 3:
                case 4:
                    Game.getMedia().getImage(Media.IMAGE.EXPLOSION).draw(s.getX(),s.getY(),s.getW(),s.getH());
                    break;
            }
        });
    }

    @Override
    public void Update(GameContainer gc, float t) throws SlickException
    {
        shots.forEach((s) ->
        {
            s.update(t);
            if (s.isDwable()&&s.ended())
            {
                toRemoveS.add(s);
            }
            else if(!hero.isAlive() && !s.isDwable())
            {
                toRemoveS.add(s);
            }
                    
        });
        toRemoveS.forEach((s)->
        {
            shots.remove(s);
        });
        toRemoveS.clear();
        
        label.forEach((lab)->
        {
            if (!lab.isAlive())
                toRemoveL.add(lab);
            else
                lab.update(t);
        });
        toRemoveL.forEach((lab)->
        {
            label.remove(lab);
        });
        toRemoveL.clear();
        
        shots.forEach((s) ->
        {
            if (s.isDwable())
            {
                hero.getEnemys().forEach((e)->
                {
                    if (e.isAlive())
                    {
                        if (s.getBox().intersects(e.getBox()))
                        {
                            e.getDamage(s.getDamage());
                            s.setHited(e.getInfo());
                            count.add(s);
                            toRemoveS.add(s);
                            if (e.getInfo() == 0)
                            {
                                Game.getMedia().getSound(Media.SOUND.HITED).play();
                            }
                            else
                            {
                                Game.getMedia().getSound(Media.SOUND.HITED_ALIEN).play();
                                if (hero.getInfo()==0)
                                {
                                    hero.setHudAlien(e, e.getCurrentHealth()+s.getDamage());
                                }
                            }
                            if(hero.getInfo()==0 && !e.isAlive() && hero.isAlive())
                            {
                                label.add(new Label(e.getBox().getCenterX(),e.getBox().getCenterY(),e.getFace(),e.getHealthMax()*Game.getReward()));
                                hero.hasKilled(e.getHealthMax()*Game.getReward());
                            }
                        }
                    }
                });
            }
        });
        toRemoveS.forEach((s)->
        {
            shots.remove(s);
        });
        toRemoveS.clear();
        count.forEach((s)->
        {
            if (s.remove())
                toRemoveS.add(s);
            else
                s.count(t);
        });
        toRemoveS.forEach((s)->
        {
            count.remove(s);
        });

        if (counter >= 0)
            counter -= 1*t;
        
        if (!hero.isAlive() && shots.isEmpty())
        {
            Game.removeSence(this);
        }
    }

    @Override
    public void init(GameContainer gc) throws SlickException{}
    
    public ArrayList<int[]> save()
    {
        ArrayList <int[]> data = new ArrayList<>();
        int correction = 0;
        for (int j = 0; j<maxGun; j++)
        {
            if ((inventory.size()>j-correction)&&(inventory.get(j-correction).getID()==j))
            {
                data.add(new int[]{inventory.get(j-correction).getID(),inventory.get(j-correction).getLevel()});
            }
            else
            {
                data.add(new int[]{j,0});
                correction++;
            }
        }
        return data;
    }
    public int getNumberOfGuns()
    {
        return maxGun;
    }
    public void die()
    {
        Game.removeSence(this);
    }
    
    @Override
    public String toString()
    {
        return "Inventory "+this.hero.toString()+" "+this.inventory.size()+" "+this.shots.size();
    }
}
