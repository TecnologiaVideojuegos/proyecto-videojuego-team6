/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shutterearth.characters;

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
public class Enemy extends CharactX
{
    private final int type;
    private final Hero hero;
    
    public Enemy (int type, int stage,Hero hero,Field field)
    {
        h = Game.getY()/12;
        w = (h/10)*9;
        
        animationTime = 40;
        counterAnimation = 0;
        animation = false;
        
        this.jumpUp = false;
        this.jumpDown = false;
        this.over = false;
        
        this.type = type;
        this.hero = hero;
        
        inventory = new Inventory(new int[]{type,stage/2},this,100-stage*2+type*1,1);
        this.field = field;
        
        if ((Math.random()*2)==0)
        {
            this.goRight();
        }
        else
        {
            this.goLeft();
        }
        yVel = 0;
        
        this.healthCurrent = 20+10*stage*type;
        this.healthMax = 20+10*stage*type;
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
            if (type == 1)
            {
                if (jumping())
                    Game.getMedia().getSprit(this.getFace()?Media.SPRITE.BASE_SDE:Media.SPRITE.BASE_SIZ).draw(xPos,yPos,w,h);
                else
                    Game.getMedia().getSprit(this.getFace()?Media.SPRITE.BASE_DER:Media.SPRITE.BASE_IZQ).draw(xPos,yPos,w,h);
            }
            else
            {
                if (jumping())
                    Game.getMedia().getSprit(this.getFace()?Media.SPRITE.FUERTE_SDE:Media.SPRITE.FUERTE_SIZ).draw(xPos,yPos,w,h);
                else
                    Game.getMedia().getSprit(this.getFace()?Media.SPRITE.FUERTE_DER:Media.SPRITE.FUERTE_IZQ).draw(xPos,yPos,w,h);
            }
                
            if (animation)
            {
                Game.getMedia().getImage(this.getFace()?Media.IMAGE.FIRE_R:Media.IMAGE.FIRE_L).draw(xPos+(this.getFace()?-10:0),yPos,w+10,h);
            }
        }
    }

    @Override
    public void Update(GameContainer gc, float t) throws SlickException
    {
        if (this.isAlive())
        {
            if (this.isInRoom(hero.getBox()))
            {
                this.shot();
                if (this.box.getCenterX()>hero.box.getCenterX())
                {
                    this.goLeft();
                }
                else
                {
                    this.goRight();
                }
            }
            else
            {
                switch ((int)(Math.random()*50))
                {
                    case 0:
                        this.goRight();
                        break;
                    case 1:
                        this.goLeft();
                        break;
                }
                switch ((int)(Math.random()*400))
                {
                    case 0:
                        //field.getNewBownds(true);
                        this.goUp();
                        break;
                    case 1:
                        //field.getNewBownds(false);
                        this.goDown();
                        break;
                }
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
        else
        {

            if (!called)
            {
                Game.removeSence(this);
                field.enemyDied(this);
                called = true;
            }
        }
    }

    @Override
    public void init(GameContainer gc) throws SlickException{}

    @Override
    public void goUp()
    {
        if (!jumping())
        {
            this.boundSetter(this.field.getNewBownds(room,xPos, true));
            yVel = Game.getyVelUp();
            this.setY(yPos-1);
            jumpUp = true;
        }
    }

    @Override
    public void goLeft()
    {
        xVel = -Game.getxVel() +(float)Math.random()*1;
    }

    @Override
    public void goRight()
    {
        xVel = Game.getxVel() - (float)Math.random()*1;
    }

    @Override
    public void goDown()
    {
        if (!jumping())
        {
            this.boundSetter(this.field.getNewBownds(room,xPos, false));
            yVel = Game.getyVelDown();
            this.setY(yPos-1);
            jumpDown = true;
        }
    }

    @Override
    public void shot()
    {
        if (!jumping())
        {
            inventory.shot(1);
        }
    }

    @Override
    public void place(float x, float y, float floor, float left, float right, int borderRoom, int room)
    {
        this.room = room;
        this.xPos = x;
        this.yPos = y;
        this.floor = floor - h - 5;
        line = new Rectangle (0,yPos,Game.getX(),floor+h-yPos);
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
        this.floor = floor - h -5;
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
        line.setHeight(floor+h-y +5);
    }

    @Override
    public int getInfo()
    {
        return type;
    }
    
    @Override
    public void doShotAnimation()
    {
        Game.getMedia().getSound(Media.SOUND.FIRE_ALIEN).play();
        animation = true;
    }
    
    @Override
    public String toString()
    {
        return "Enemy "+this.healthCurrent+" "+this.xPos+" "+this.xPos;
    }
}
