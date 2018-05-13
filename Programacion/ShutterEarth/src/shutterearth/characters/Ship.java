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

/**
 *
 * @author mr.blissfulgrin
 */
public class Ship extends CharactX
{    
    private int state;
    private int side;
    private boolean first;
    private float target;
    private final Hero hero;
    private float count;
    private int gess;
    private int wait;
    
    public Ship (int type,int stage, Hero hero)
    {
        this.hero = hero;
        this.state = 0;
        this.side = 0;
        w = Game.getX()/9;
        h = Game.getY()/9;
        animationTime = 20;
        inventory = new Inventory(new int[]{type+2,stage/2},this,500-stage*20);

        xPos = (int)(Math.random()*(Game.getX()+1000) - 500);
        yPos = -(int)(Math.random()*1000+h);
        wait = (int)(Math.random()*200);
        floor = 0;
        
        line = new Rectangle (0,yPos,Game.getX(),h);
        colum = new Rectangle (0,0,Game.getX(),Game.getY());
        box = new Rectangle (xPos,yPos,w,h);
        first = true;
        count = 0;
    }

    @Override
    public void Render(GameContainer gc, Graphics g) throws SlickException
    {
        if (active)
        {
                Game.getMedia().getImage(this.getFace()?Media.IMAGE.SHIP_RIGHT:Media.IMAGE.SHIP_LEFT).draw(xPos,yPos,w,h);
                if (animation)
                {
                    Game.getMedia().getImage(this.getFace()?Media.IMAGE.FIRE_R:Media.IMAGE.FIRE_L).draw(xPos,yPos,w,h);
                }
        }
        if (Game.debug())
        {
            for (Rectangle rect : this.debug())
            {
                Game.getMedia().getImage(Media.IMAGE.GREY).draw(rect.getX(),rect.getY(),rect.getWidth(),rect.getHeight());
            }
        } 
    }

    @Override
    public void Update(GameContainer gc, float t) throws SlickException
    {
        if (active)
        {
            if (wait < 0)
            {
                switch (state)
                {
                    case 0: //change side
                        state = (int)(Math.random()*2 + 1);
                        break;
                    case 1: //GO RIGTH                  
                        if (yPos < 0)
                        {
                            xVel = 0;
                            this.goDown();
                        }
                        else if (yPos > 15)
                        {
                            xVel = 0;
                            this.goUp();
                        }
                        else if (xPos+w>Game.getX())
                        {
                            yVel = 0;
                            this.goLeft();
                        }
                        else if (xPos+w<Game.getX()-15)
                        {
                            yVel = 0;
                            this.goRight();
                        }
                        else
                        {
                            state = 3;
                            xVel = 0;
                            side = 1;
                        }
                        break;
                    case 2: //GO LEFT
                        if (yPos < 0)
                        {
                            xVel = 0;
                            this.goDown();
                        }
                        else if (yPos > 15)
                        {
                            xVel = 0;
                            this.goUp();
                        }
                        else if (xPos < 0)
                        {
                            yVel = 0;
                            this.goRight();
                        }
                        else if (xPos > 15)
                        {
                            yVel = 0;
                            this.goLeft();
                        }
                        else
                        {
                            state = 3;
                            side = 2;
                        }
                        break;
                    case 3: //SHOT
                        if (first)
                        {
                            target = hero.getBox().getCenterY();
                            gess = (int)(Math.random()*4);
                            if (gess==0)
                            {
                                target += Math.random()*Game.getY()/10;
                            }
                            else if (gess==1)
                            {
                                target -= Math.random()*Game.getY()/10;
                            }
                            first = false;
                        }
                        if (box.getCenterY() < target-10)
                        {
                            xVel = 0;
                            this.goDown();
                        }
                        else if (box.getCenterY() > target+10)
                        {
                            xVel = 0;
                            this.goUp();
                        }
                        else
                        {
                            xVel = 0;
                            this.shot();
                            first = true;
                            if (count > 100)
                            {
                                count = 0;
                                state = 0;
                            }
                            else
                            {
                                count += 1*t;
                            }
                        }
                        break;
                    default:
                            state = 0;
                            break;
                }
                this.setX(xPos + xVel*t);
                this.setY(yPos + yVel*t);
            
                counterAnimation -= 1*t;
                if (counterAnimation < 0)
                {
                    animation = false;
                    counterAnimation = this.animationTime;
                }
            }
            else
                wait -= 1*t;
        }
        
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
    }

    @Override
    public void init(GameContainer gc) throws SlickException{}

    @Override
    public void goUp()
    {
        yVel = - Game.getxVel();
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
        yVel = Game.getxVel();
    }

    @Override
    public void shot()
    {
        inventory.shot();
    }
    
    @Override
    public boolean getFace()
    {
        if (xVel != 0)
            return xVel > 0;
        else
            return side==2;
    }
    
    @Override
    public Rectangle[] debug()
    {
        return new Rectangle[] {line,box};
    }

    @Override
    public void place(float floor, int left, int right){}

    @Override
    public void setBounds(float left, float right, float floor){}
}
