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
import org.newdawn.slick.geom.Rectangle;
import shutterearth.Game;
import shutterearth.Media;
import shutterearth.map.Field;

/**
 *
 * @author mr.blissfulgrin
 */
public class BadGuy extends Charact
{
    private final Hero hero;
    private final int stage;
    private boolean revived;
    
    public BadGuy (int stage, Hero hero, Field field)
    {
        if (stage < 1)
            this.stage = 1;
        else if (stage > 10)
            this.stage = 1;
        else
            this.stage = stage;
        this.field = field;
        this.inventory = new Inventory(new int[]{4,((stage-1)/2)},this,3);
        
        this.healthMax = 50 + stage*100;
        this.healthCurrent = 50 + stage*100;
        
        this.h = Game.getY()/11;
        this.w = h;
        this.jumpUp = false;
        this.jumpDown = false;
        this.over = false;
        this.xPos = Game.getxVel();
        animationTime = 40;
        counterAnimation = 0;
        animation = false;
        revived = false;
        this.goRight();
        
        this.hero = hero;
        
        this.enemy = new ArrayList <>();
        this.enemy.add(hero);
    }
    
    @Override
    public void goUp()
    {
        if (!jumping())
        {
            this.boundSetter(this.field.getNewBownds(room,xPos, true, w));
            yVel = Game.getyVelUp();
            this.setY(yPos-1);
            jumpUp = true;
        }
    }
    @Override
    public void goLeft()
    {
        xVel = -Game.getxVel();
    }
    @Override
    public void goRight()
    {
        xVel = Game.getxVel();
    }
    @Override
    public void goDown()
    {
        if (!jumping())
        {
            this.boundSetter(this.field.getNewBownds(room,xPos, false, w));
            yVel = Game.getyVelDown();
            this.setY(yPos-1);
            jumpDown = true;
        }
    }

    @Override
    public void shot()
    {
        inventory.shot(((stage/2) > 0?(stage/2):1));
    }
    
    public void revive ()
    {
        revived = true;
    }
    
    @Override
    public void Render(GameContainer gc, Graphics g) throws SlickException
    {        
        if (Game.debug())
        {
            for (Rectangle rect : this.debug())
            {
                Game.getMedia().getImage(Media.IMAGE.GREY).draw(rect.getX(),rect.getY(),rect.getWidth(),rect.getHeight());
            }
        } 
        if (this.isAlive())
        {
            if (!jumping())
            {
                Game.getMedia().getSprit(xVel > 0? Media.SPRITE.MALO_DER : Media.SPRITE.MALO_IZQ).draw(xPos,yPos,w,h);
            }
            else
            {
                Game.getMedia().getSprit(xVel > 0? Media.SPRITE.MALO_SDE: Media.SPRITE.MALO_SIZ).draw(xPos,yPos,w,h);
            }
            
            if (animation)
            {
                Game.getMedia().getImage(this.getFace()?Media.IMAGE.FIRE_R:Media.IMAGE.FIRE_L).draw(xPos+(this.getFace()?-10:0),yPos,w+10,h);
            }
        }
        else
        {
            if (!revived)
                Game.getMedia().getImage(Media.IMAGE.GRAVE).draw(xPos,yPos,w,h);
            else
            {
                if (h<Game.getY()/6)
                {
                    h++;
                    w = h;
                }
                Game.getMedia().getSprit(Media.SPRITE.MALO_DER).draw(xPos,yPos,w,h);
            }
        }
    }

    @Override
    public void Update(GameContainer gc, float t) throws SlickException
    {
        if (this.isAlive())
        {
            if (this.yPos < (hero.getY()-hero.getH()))
            {
                switch((int)(Math.random()*50))
                {
                    case 0:
                        this.goDown();
                        break;
                }
            }
            else if (this.box.getMaxY() > hero.getY())
            {
                switch((int)(Math.random()*50))
                {
                    case 0:
                        this.goUp();
                        break;
                }
            }
            else
            {
                switch((int)(Math.random()*350))
                {
                    case 0:
                        this.goDown();
                        break;
                    case 1:
                        this.goUp();
                        break;
                }
            }
            if (this.xPos > (hero.getX()+hero.getW()))
            {
                switch((int)(Math.random()*35))
                {
                    case 0:
                        this.goLeft();
                        break;
                }
            }
            else if (this.box.getMaxX() < hero.getX())
            {
                switch((int)(Math.random()*35))
                {
                    case 0:
                        this.goRight();
                        break;
                }
            }
            else
            {
                switch((int)(Math.random()*300))
                {
                    case 0:
                        this.goRight();
                        break;
                    case 1:
                        this.goLeft();
                        break;
                }
            }
            if (this.isInRoom(hero.box))
            {
                this.shot();
            }
            
            this.setX(this.xPos + this.xVel*t);
            if (yVel < Game.getGravityMax())
                yVel += Game.getGravity()*t;
            this.setY(this.yPos + this.yVel*t);

            if (jumpUp)
            { 
                if (over)
                {
                    if (yPos >= floor)
                    {
                        jumpUp = false;
                        over = false;
                        yPos = floor;
                    }  
                }
                else
                {
                    over = (yPos < floor);
                }
            }
            else if(jumpDown)
            {
                if (yPos >= floor)
                {
                    jumpDown = false;
                    yPos = floor;
                }
            }
            else if (yPos >= floor)
            {
                this.setY(floor);
            }

            if (xPos <= colum.getX())
            {
                this.goRight();
            }
            if (xPos+w >= colum.getX()+colum.getWidth())
            {
                this.goLeft();
            }

            if (animation)
            {
                if (counterAnimation > animationTime)
                {
                    animation = false;
                    counterAnimation = 0;
                }
                counterAnimation+=1*t;
            }
        }
    }

    @Override
    public void init(GameContainer gc) throws SlickException{}
    
    @Override
    public void place (float x, float y, float floor, float left, float right, int borderRoom, int room)
    {
        this.room = room;
        this.xPos = x;
        this.yPos = y;
        this.floor = floor - h - 5;
        line = new Rectangle (0,yPos,Game.getX(),floor-yPos);
        colum = new Rectangle (left,0,right-left,Game.getY());
        box = new Rectangle (xPos,yPos,w,h);
        this.borderRoom = borderRoom;
    }
    @Override
    public void setBounds (float floor, float left, float right, int borderRoom, int room)
    {
        this.room = room;
        colum.setX(left);
        colum.setWidth(right-left);
        this.floor = floor - h - 5;
        this.borderRoom = borderRoom;
    }
    
    @Override
    protected void setX(float x)
    {
        this.xPos = x;
        box.setX(x);
    }
    @Override
    protected void setY(float y)
    {
        this.yPos = y;
        box.setY(y);
        line.setY(y);
        line.setHeight(floor+h+5-y);
    }
    
    @Override
    public int getInfo ()
    {
        return 5;
    }
    
    @Override
    public void doShotAnimation()
    {
        Game.getMedia().getSound(Media.SOUND.SHOT).play();
        animation = true;
    }
    
    @Override
    public String toString()
    {
        return "BadGuy "+this.healthCurrent+" "+this.xPos+" "+this.xPos;
    }
}