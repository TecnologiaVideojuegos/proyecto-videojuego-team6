/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shutterearth.map;

import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import shutterearth.Game;
import shutterearth.Media;

/**
 *
 * @author mr.blissfulgrin
 */
public class BattleMap extends Map
{
    private final ArrayList <Rectangle> room;
    private final ArrayList <Circle> animation;
    
    public BattleMap (float x, float y)
    {
        super (x,y);
        this.room = new ArrayList <>();
        this.animation = new ArrayList <>();
        
        float actualFloor = Game.getY();
        for (int j = 0; j<8; j++)
        {
            actualFloor -= Game.step();
            room.add(new Rectangle (x,actualFloor,Game.getX()-(x)*2,Game.step()));
        }
        
    }
    
    @Override
    public void Render(GameContainer gc, Graphics g) throws SlickException
    {
        g.setColor(Color.black);
        room.forEach((rm)->
        {
            Game.getMedia().getImage(Media.IMAGE.GREY).draw(rm.getX(),rm.getY(),rm.getWidth(),rm.getHeight());
            g.fill(new Rectangle(rm.getX(),rm.getMaxY()-10,rm.getWidth(),10));
        });
        g.setColor(Color.yellow);
    }

    @Override
    public void Update(GameContainer gc, float t) throws SlickException
    {
        
    }

    @Override
    public void init(GameContainer gc) throws SlickException{}

    @Override
    public String toString()
    {
        return "Battle Map "+x+" "+y;
    }

    @Override
    public float[][] getSpots(int n)
    {
        float [][] spots = new float [n][6];
        int rm;
        spots[0] = new float[]{room.get(0).getX()+10,room.get(0).getY()-10,room.get(0).getMaxY(),x,Game.getX()-x,2,0};
        
        for (int j = 1; j < n; j++)
        {
            rm = (int)(Math.random()*(room.size()-1)+1);
            spots[j] = new float[]{(float)((Math.random()*((Game.getX()-((x)*2)-10)))+x+10),room.get(rm).getY()-10,room.get(rm).getMaxY(),x,Game.getX()-x,2,rm};
        }
        
        return spots;
    }

    @Override
    public float[] getNextRoom(int room,float x, boolean up, float w, boolean hero)
    {
        if (up && (room < (this.room.size()-1)))
        {
            return new float[]{this.room.get(room+1).getMaxY(),this.x,Game.getX()-this.x,2,room+1};
        }
        else if (!up && (room > 0))
        {
            return new float[]{this.room.get(room-1).getMaxY(),this.x,Game.getX()-this.x,2,room-1};
        }
        else
        {
            return new float[]{this.room.get(room).getMaxY(),this.x,Game.getX()-this.x,2,room};
        }
    }

    @Override
    public void start()
    {
        Game.addScene(this);
    }

    @Override
    public void pause()
    {
        this.setState(STATE.FREEZE);
    }

    @Override
    public void wake()
    {
        this.setState(STATE.ON);
    }

    @Override
    public void end()
    {
        Game.removeSence(this);
    }

    @Override
    public float[] bulletControl(float x, float y)
    {
        return new float[]{0,Game.getX()};
    }
    
}
