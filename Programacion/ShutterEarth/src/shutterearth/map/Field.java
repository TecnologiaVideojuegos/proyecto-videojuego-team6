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
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.command.BasicCommand;
import org.newdawn.slick.command.Command;
import org.newdawn.slick.command.Control;
import org.newdawn.slick.command.InputProvider;
import org.newdawn.slick.command.InputProviderListener;
import org.newdawn.slick.command.KeyControl;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import shutterearth.Game;
import shutterearth.Media;
import shutterearth.characters.BadGuy;
import shutterearth.characters.Hero;
import shutterearth.screens.Scene;
import shutterearth.screens.StartMenu;
import shutterearth.characters.Charact;
import shutterearth.characters.Enemy;
import shutterearth.characters.SavedHero;
import shutterearth.characters.Ship;
import shutterearth.map.randomGenerator.Juego;

/**
 *
 * @author mr.blissfulgrin
 */
public class Field extends Scene implements InputProviderListener
{
    private InputProvider provider;
    private final Command CONTROL = new BasicCommand("CONTROL");
    private final Command UP = new BasicCommand("UP");
    private final Command DOWN = new BasicCommand("DOWN");
    private final Command LEFT = new BasicCommand("LEFT");
    private final Command RIGHT = new BasicCommand("RIGHT");
    private final Command I_LEFT = new BasicCommand("I_LEFT");
    private final Command I_RIGHT = new BasicCommand("I_RIGHT");
    private final Command SHOT = new BasicCommand("SHOT");
    
    private final Control back = new KeyControl(Input.KEY_BACK);
    private final Control esc = new KeyControl(Input.KEY_ESCAPE);
    private final Control up = new KeyControl(Input.KEY_UP);
    private final Control down = new KeyControl(Input.KEY_DOWN);
    private final Control right = new KeyControl(Input.KEY_RIGHT);
    private final Control left = new KeyControl(Input.KEY_LEFT);
    private final Control e = new KeyControl(Input.KEY_E);
    private final Control w = new KeyControl(Input.KEY_W);
    private final Control s = new KeyControl(Input.KEY_S);
    private final Control d = new KeyControl(Input.KEY_D);
    private final Control a = new KeyControl(Input.KEY_A);
    private final Control q = new KeyControl(Input.KEY_Q);
    private final Control space = new KeyControl(Input.KEY_SPACE);
    
    private final HUD hud;
    private final Hero hero;
    private final int stage;
    private boolean battle;
    private final ArrayList <Charact> enemy;
    private final ArrayList <Enemy> en;
    private final ArrayList <Ship> sh;
    private int shipCounter;
    private float counter;
    private boolean relisable;
    
    private float radix;
    private final Circle animation;
    private boolean animationStarted;
    private final int diagonal;
    
    private final int xt;
    private final int yt;
    
    private  Map map;
    private BadGuy badGuy;
    private float [][]spots;
    private int n;
    
    private final BB bb;
    private Rectangle futureBB;
    private Rectangle futurePoweUp2;
    private final PowerUp powerUp2;
    private Rectangle futurePoweUp3;
    private final PowerUp powerUp3;
    private boolean done;
    private boolean dialoged;
    private boolean gameEnded;
    
    private boolean texted;
    private int tcount;
    private boolean endDone;
    
    
    public Field (Hero hero, int stage, HUD hud, int lessHealth)
    {
        this.xt = 10;
        if (hero.getHealthMax()<=100)
            this.yt = 15 + Game.getX()/60 + Game.getX()/600;
        else if (hero.getHealthMax()<=200)
            this.yt = 15 + Game.getX()/30 + Game.getX()/300;
        else
            this.yt = 15 + Game.getX()/15 + Game.getX()/150;
        this.stage = stage;
        this.hero = hero;
        this.hud = hud;
        this.battle = false;
        radix = 0;
        animation = new Circle (Game.getX()/2,Game.getY()/2,radix);
        animationStarted = false;
        diagonal = (Game.getX()^2+Game.getY()^2)^(1/2);
        
        relisable = false;
        enemy = new ArrayList <>();
        en = new ArrayList <>();
        sh = new ArrayList <>();
        hero.getDamage(lessHealth);
        gameEnded = false;
        done = false;
        bb = new BB();
        powerUp2 = new PowerUp(2);
        powerUp3 = new PowerUp(3);
        tcount = 3;
        texted = false;
        dialoged = false;
        endDone = false;
    }
    
    @Override
    public void Render(GameContainer gc, Graphics g) throws SlickException
    {
        if (battle)
            Game.getMedia().getImage(Media.IMAGE.BATTLE).draw(0,0,Game.getX(),Game.getY());
        else
            Game.getMedia().getImage(Media.IMAGE.GAME).draw(0,0,Game.getX(),Game.getY());
        if (animationStarted)
        {
            g.setColor(Color.black);
            g.fill(animation);
            g.setColor(Color.yellow);
        }
        if (battle)
        {
            g.drawString("Stage: "+stage, xt, yt);
        }
        else
        {
            g.setColor(Color.black);
            g.drawString("Stage: "+stage, xt, yt);
            g.setColor(Color.yellow);
        }
    }

    @Override
    public void Update(GameContainer gc, float t) throws SlickException
    {
        if (powerUp2.available() && hero.getBox().intersects(powerUp2.getRect()))
        {
            hero.setPowerUp(powerUp2.getPower());
            powerUp2.exit();
        }
        if (powerUp3.available() && hero.getBox().intersects(powerUp3.getRect()))
        {
            hero.setPowerUp(powerUp3.getPower());
            powerUp3.exit();
        }
        if (texted) // HA MOSTRADO EL TEXTO INICIAL
        {
            if (!gameEnded) //HAS SUPERADO EL NIVEL FINAL
            {
                if (relisable) // CONTADOR PARA LANZAR LAS NAVES
                {
                    if ((counter < shipCounter))
                    {
                        counter += 1*t;
                        if (deadAliens())
                            counter = shipCounter;
                    }
                    else
                    {
                        releaseShips();
                        relisable = false;
                    }
                }
                if (animationStarted) //ANIMACIÓN DE CIERRE, EL JUEGO HA ACABADO
                {
                    radix = animation.getRadius() + 5f*t;
                    animation.setRadius(radix);
                    animation.setCenterX(Game.getX()/2);
                    animation.setCenterY(Game.getY()/2);
                    if (radix > diagonal)
                    {
                        if (enemy.isEmpty() && hero.isAlive())
                        {
                            this.loadNew();
                        }
                        else
                        {
                            this.exit();
                        }
                    }
                }
                else if (enemy.isEmpty() && hero.isAlive()) //HAS GANADO
                {
                    if (done) //HAS DERROTADO AL BOSS FALSO
                    {
                        Game.addScene(new TextDisplayer(this,18,0));
                    }
                    else
                    {
                        switch (stage) //INICIA LOS BOSS
                        {
                            case 1:
                                if (!dialoged)
                                {
                                    bb.setRect(futureBB);
                                    Game.addScene(new TextDisplayer(this,11,3));
                                    dialoged = true;
                                }
                                if (hero.getBox().intersects(bb.getRect()))
                                {
                                    bb.exit();
                                    releaseBadGuy();
                                    Game.addScene(new TextDisplayer(this,12,3));
                                    done = true;
                                }
                                break;
                            case 5:
                                if (!dialoged)
                                {
                                    bb.setRect(futureBB);
                                    Game.addScene(new TextDisplayer(this,13,3));
                                    dialoged = true;
                                }
                                if (hero.getBox().intersects(bb.getRect()))
                                {
                                    bb.exit();
                                    releaseBadGuy();
                                    Game.addScene(new TextDisplayer(this,14,3));
                                    done = true;
                                }
                                break;
                            case 10:
                                releaseBadGuy();
                                Game.addScene(new TextDisplayer(this,15,3));
                                break;
                            default:
                                this.startAnimation();
                                break; 
                        }
                    }
                }
            }
            else
            {
                if (!endDone) //LIBERA LA HIJA
                {
                    Game.addScene(new TextDisplayer(this,17,3));
                    bb.setRect(futureBB);
                    endDone = true;
                }
                if (hero.getBox().intersects(bb.getRect()))
                {
                    Game.addScene(new TextDisplayer(this,16,4));
                }
                if (!hero.isAlive())
                {
                    exit();
                }
            }
        }
        else    //REALIZA LA ANIMACIÓN
        {
            if (tcount > 0)
            {
                tcount --;
            }
            else
            {
                Game.addScene(new TextDisplayer(this,stage-1,3));
                texted = true;
            }
        }
    }

    @Override
    public void init(GameContainer gc) throws SlickException
    {
        provider = new InputProvider(gc.getInput());
        provider.addListener(this);
        
        for (int x = 0; x<2; x++)
        {
            provider.bindCommand(back, CONTROL);
            provider.bindCommand(esc, CONTROL);
            provider.bindCommand(up, UP);
            provider.bindCommand(down, DOWN);
            provider.bindCommand(right, RIGHT);
            provider.bindCommand(left, LEFT);
            provider.bindCommand(e, I_RIGHT);
            provider.bindCommand(w, UP);
            provider.bindCommand(s, DOWN);
            provider.bindCommand(d, RIGHT);
            provider.bindCommand(a, LEFT);
            provider.bindCommand(q, I_LEFT);
            provider.bindCommand(space, SHOT);
        }
    }

    @Override
    public void controlPressed(Command command)
    {
        if (!this.isFreezed())
        {
            if (command.equals(CONTROL))
            {
                this.pause();
                Game.addScene(new Pause(this));
            }
            else if (command.equals(UP))
            {
                hero.goUp();
            }
            else if (command.equals(DOWN))
            {
                hero.goDown();
            }
            else if (command.equals(RIGHT))
            {
                hero.goRight();
            }
            else if (command.equals(LEFT))
            {
                hero.goLeft();
            }
            else if (command.equals(I_RIGHT))
            {
                hero.inventoryRight();
            }
            else if (command.equals(I_LEFT))
            {
                hero.inventroyLeft();
            }
            else if (command.equals(SHOT))
            {
                hero.shot();
            }
        }
    }

    @Override
    public void controlReleased(Command cmnd) {}
 
    /**
     * PARA EL JUEGO
     */
    public void pause ()
    {
        this.setState(STATE.FREEZE);
        hud.pause();
        map.pause();
        enemy.forEach((enem)->
        {
            enem.pause();
        });
        sh.forEach((ship)->
        {
            ship.pause();
        });
        en.forEach((enm)->
        {
            enm.pause();
        });
        if (badGuy!=null)
            badGuy.pause();
    }
    
    /**
     * SACA AL JUEGO DE PARADO
     */
    public void wake ()
    {
        if (battle)
            Game.getMedia().getMusic(Media.MUSIC.BATTLE_SONG).loop();
        else
            Game.getMedia().getMusic(Media.MUSIC.CANCION_GAME).loop();
        this.setState(STATE.ON);
        hud.wake();
        map.wake();
        enemy.forEach((enem)->
        {
            enem.wake();
        });
        sh.forEach((ship)->
        {
            ship.wake();
        });
        en.forEach((enm)->
        {
            enm.wake();
        });
        if (badGuy!=null)
            badGuy.wake();
    }
    
    /**
     * CIERRA Y SALVA EL JUEGO
     */
    public void exit ()
    {
        hero.save();
        provider.unbindCommand(back);
        provider.unbindCommand(esc);
        provider.unbindCommand(up);
        provider.unbindCommand(down);
        provider.unbindCommand(right);
        provider.unbindCommand(left);
        provider.unbindCommand(w);
        provider.unbindCommand(a);
        provider.unbindCommand(s);
        provider.unbindCommand(d);
        provider.unbindCommand(q);
        provider.unbindCommand(e);
        provider.unbindCommand(space);
        provider.removeListener(this);
        
        Game.removeSence(this);
        hud.end();
        map.end();
        bb.exit();
        powerUp2.exit();
        powerUp3.exit();
        enemy.forEach((enem)->
        {
            enem.end();
        });
        if (gameEnded)
        {
            Game.addScene(new StartMenu(hero.save())); 
            Game.getMedia().getMusic(Media.MUSIC.CANCION_MENU).loop();
            System.out.println("END");
        }
        else
        {
            Game.addScene(new StartMenu(hero.save())); 
            Game.getMedia().getMusic(Media.MUSIC.CANCION_MENU).loop();
        }
    }
    
    /**
     * CARGA UN JUEGO NUEVO
     */
    public void start ()
    {        
        switch(stage)
        {
            case 1:
                en.add(new Enemy(1,stage,hero,this));
                en.add(new Enemy(1,stage,hero,this));
                en.add(new Enemy(1,stage,hero,this));
                en.add(new Enemy(1,stage,hero,this));
                en.add(new Enemy(1,stage,hero,this));
                en.add(new Enemy(1,stage,hero,this));
                en.add(new Enemy(1,stage,hero,this));
                setMap(new Juego (Game.getX()/9,hero.getH()*2));
                break;
            case 2:
                en.add(new Enemy(1,stage,hero,this));
                en.add(new Enemy(1,stage,hero,this));
                en.add(new Enemy(1,stage,hero,this));
                en.add(new Enemy(1,stage,hero,this));
                en.add(new Enemy(1,stage,hero,this));
                en.add(new Enemy(1,stage,hero,this));
                en.add(new Enemy(1,stage,hero,this));
                en.add(new Enemy(2,stage,hero,this));
                en.add(new Enemy(2,stage,hero,this));
                en.add(new Enemy(2,stage,hero,this));
                setMap(new Juego (Game.getX()/9,hero.getH()*2));
                break;
            case 3:
                en.add(new Enemy(1,stage,hero,this));
                en.add(new Enemy(1,stage,hero,this));
                en.add(new Enemy(1,stage,hero,this));
                en.add(new Enemy(1,stage,hero,this));
                en.add(new Enemy(1,stage,hero,this));
                en.add(new Enemy(2,stage,hero,this));
                en.add(new Enemy(2,stage,hero,this));
                en.add(new Enemy(2,stage,hero,this));
                sh.add(new Ship(1,stage,hero,this));
                sh.add(new Ship(1,stage,hero,this));
                this.shipCounter = 5000;
                setMap(new Juego (Game.getX()/9,hero.getH()*2));
                break;
            case 4:
                en.add(new Enemy(1,stage,hero,this));
                en.add(new Enemy(1,stage,hero,this));
                en.add(new Enemy(1,stage,hero,this));
                en.add(new Enemy(1,stage,hero,this));
                en.add(new Enemy(1,stage,hero,this));
                en.add(new Enemy(2,stage,hero,this));
                en.add(new Enemy(2,stage,hero,this));
                en.add(new Enemy(2,stage,hero,this));
                en.add(new Enemy(2,stage,hero,this));
                sh.add(new Ship(1,stage,hero,this));
                sh.add(new Ship(1,stage,hero,this));
                sh.add(new Ship(1,stage,hero,this));
                this.shipCounter = 4500;
                setMap(new Juego (Game.getX()/9,hero.getH()*2));
                break;
            case 5:
                en.add(new Enemy(1,stage,hero,this));
                en.add(new Enemy(1,stage,hero,this));
                en.add(new Enemy(1,stage,hero,this));
                en.add(new Enemy(1,stage,hero,this));
                en.add(new Enemy(2,stage,hero,this));
                en.add(new Enemy(2,stage,hero,this));
                en.add(new Enemy(2,stage,hero,this));
                en.add(new Enemy(2,stage,hero,this));
                en.add(new Enemy(2,stage,hero,this));
                en.add(new Enemy(2,stage,hero,this));
                en.add(new Enemy(2,stage,hero,this));
                en.add(new Enemy(2,stage,hero,this));
                sh.add(new Ship(1,stage,hero,this));
                sh.add(new Ship(1,stage,hero,this));
                sh.add(new Ship(1,stage,hero,this));
                this.shipCounter = 4000;
                setMap(new Juego (Game.getX()/9,hero.getH()*2));
                break;
            case 6:
                en.add(new Enemy(1,stage,hero,this));
                en.add(new Enemy(1,stage,hero,this));
                en.add(new Enemy(1,stage,hero,this));
                en.add(new Enemy(2,stage,hero,this));
                en.add(new Enemy(2,stage,hero,this));
                en.add(new Enemy(2,stage,hero,this));
                en.add(new Enemy(2,stage,hero,this));
                en.add(new Enemy(2,stage,hero,this));
                en.add(new Enemy(2,stage,hero,this));
                en.add(new Enemy(2,stage,hero,this));
                sh.add(new Ship(1,stage,hero,this));
                sh.add(new Ship(1,stage,hero,this));
                sh.add(new Ship(2,stage,hero,this));
                this.shipCounter = 3500;
                setMap(new Juego (Game.getX()/9,hero.getH()*2));
                break;
            case 7:
                en.add(new Enemy(1,stage,hero,this));
                en.add(new Enemy(1,stage,hero,this));
                en.add(new Enemy(2,stage,hero,this));
                en.add(new Enemy(2,stage,hero,this));
                en.add(new Enemy(2,stage,hero,this));
                en.add(new Enemy(2,stage,hero,this));
                en.add(new Enemy(2,stage,hero,this));
                en.add(new Enemy(2,stage,hero,this));
                en.add(new Enemy(2,stage,hero,this));
                en.add(new Enemy(2,stage,hero,this));
                sh.add(new Ship(1,stage,hero,this));
                sh.add(new Ship(1,stage,hero,this));
                sh.add(new Ship(1,stage,hero,this));
                sh.add(new Ship(1,stage,hero,this));
                sh.add(new Ship(2,stage,hero,this));
                this.shipCounter = 3500;
                setMap(new Juego (Game.getX()/9,hero.getH()*2));
                break;
            case 8:
                en.add(new Enemy(1,stage,hero,this));
                en.add(new Enemy(1,stage,hero,this));
                en.add(new Enemy(2,stage,hero,this));
                en.add(new Enemy(2,stage,hero,this));
                en.add(new Enemy(2,stage,hero,this));
                en.add(new Enemy(2,stage,hero,this));
                en.add(new Enemy(2,stage,hero,this));
                en.add(new Enemy(2,stage,hero,this));
                en.add(new Enemy(2,stage,hero,this));
                en.add(new Enemy(2,stage,hero,this));
                sh.add(new Ship(1,stage,hero,this));
                sh.add(new Ship(2,stage,hero,this));
                sh.add(new Ship(2,stage,hero,this));
                this.shipCounter = 3000;
                setMap(new Juego (Game.getX()/9,hero.getH()*2));
                break;
            case 9:
                en.add(new Enemy(2,stage,hero,this));
                en.add(new Enemy(2,stage,hero,this));
                en.add(new Enemy(2,stage,hero,this));
                en.add(new Enemy(2,stage,hero,this));
                en.add(new Enemy(2,stage,hero,this));
                en.add(new Enemy(2,stage,hero,this));
                en.add(new Enemy(2,stage,hero,this));
                en.add(new Enemy(2,stage,hero,this));
                sh.add(new Ship(1,stage,hero,this));
                sh.add(new Ship(1,stage,hero,this));
                sh.add(new Ship(1,stage,hero,this));
                sh.add(new Ship(1,stage,hero,this));
                sh.add(new Ship(2,stage,hero,this));
                sh.add(new Ship(2,stage,hero,this));
                sh.add(new Ship(2,stage,hero,this));
                this.shipCounter = 2000;
                setMap(new Juego (Game.getX()/9,hero.getH()*2));
                break;
            case 10:
                en.add(new Enemy(1,stage,hero,this));
                en.add(new Enemy(1,stage,hero,this));
                en.add(new Enemy(1,stage,hero,this));
                en.add(new Enemy(1,stage,hero,this));
                en.add(new Enemy(2,stage,hero,this));
                en.add(new Enemy(2,stage,hero,this));
                en.add(new Enemy(2,stage,hero,this));
                en.add(new Enemy(2,stage,hero,this));
                en.add(new Enemy(2,stage,hero,this));
                sh.add(new Ship(1,stage,hero,this));
                sh.add(new Ship(1,stage,hero,this));
                sh.add(new Ship(1,stage,hero,this));
                sh.add(new Ship(2,stage,hero,this));
                sh.add(new Ship(2,stage,hero,this));
                this.shipCounter = 2500;
                setMap(new Juego (Game.getX()/9,hero.getH()*2));
                break;
        }
    }
    /**
     * PONE UN ALIEN EN EL HUD
     * @param enemy
     * @param lastLive 
     */
    public void setHudAlien (Charact enemy, int lastLive)
    {
        hud.addBadGuy(enemy, lastLive);
    }
    /**
     * HEROE MUERE
     */
    public void heroDied ()
    {
        Game.addScene(new TextDisplayer(this,10,0));
    }
    /**
     * ENEMIGO MUERE
     * @param enemy 
     */
    public void enemyDied (Charact enemy)
    {
        this.enemy.remove(enemy);
    }
    /**
     * INICIA LA ANIMACION
     */
    public void startAnimation ()
    {
        Game.getMedia().getSound(Media.SOUND.ALIEN1).play();
        this.animationStarted = true;
    }
    /**
     * INICIA EL ESTADO DE BATALLA
     */
    public void startBattle ()
    {
        Game.getMedia().getMusic(Media.MUSIC.BATTLE_SONG).loop();
        this.battle = true;
    }
    
    @Override
    public String toString()
    {
        return "Field "+this.hero.toString()+" "+this.stage;
    }
    /**
     * OBTIENE SPAWNS
     * @param room
     * @param x
     * @param up
     * @param w
     * @param hero
     * @return 
     */
    public float [] getNewBownds(int room,float x,boolean up, float w, boolean hero)
    {
        float [] result = map.getNextRoom(room, x, up, w, hero);
        if (result != null)
            return map.getNextRoom(room, x, up, w, hero);
        else
        {
            this.exit();
            return new float []{0,0,0,0,0,0,0,0,0,0,0,0,0};
        }
    }
    /**
     * CAMBIA DE TIPO DE MAPA
     * @param map 
     */
    private void setMap(Map map)
    {
        if (this.map != null)
        {
            this.map.end();
        }
        this.map = map;
        setup();
    }
    /**
     * LIBERA LAS NAVES
     */
    private void releaseShips ()
    {
        sh.forEach((ship)->
        {
            ship.activate();
        });
        startBattle ();
        powerUp2.setRect(this.futurePoweUp2);
        if (stage > 5)
            powerUp3.setRect(this.futurePoweUp3);
    }
    /**
     * APLICA SPAWNS
     */
    private void setup ()
    {
        relisable = !sh.isEmpty();
        n = sh.size() + en.size() +5;
        float [][] result = map.getSpots(n);
        if (result != null)
            spots = result;
        else
        {
            this.exit();
            result = new float [n+1][10];
            for (float[] result1 : result)
            {
                for (int j = 0; j < result[0].length; j++)
                {
                    result1[j] = 0;
                }
            }
            spots = result;
        }
        hero.place(spots[0][0], spots[0][1], spots[0][2], spots[0][3], spots[0][4], (int)spots[0][5], (int)spots[0][6]);
        futureBB = new Rectangle(spots[n-2][0], spots[n-2][1] + Game.step() - hero.getH()/2 - 6,hero.getW(),hero.getH()/2);
        futurePoweUp2 = new Rectangle(spots[n-1][0], spots[n-1][1] + Game.step() - hero.getH()/2 - 6,hero.getW(),hero.getH()/2);
        futurePoweUp3 = new Rectangle(spots[n-3][0], spots[n-3][1] + Game.step() - hero.getH()/2 - 6,hero.getW(),hero.getH()/2);
        sh.forEach((ship) ->
        {
            if (!enemy.contains(ship))
                enemy.add(ship);
        });

        for (int j = 0; j < en.size(); j++)
        {
            if (!enemy.contains(en.get(j))) 
                enemy.add(en.get(j));
            en.get(j).place(spots[j+1][0],spots[j+1][1],spots[j+1][2], spots[j+1][3], spots[j+1][4],(int)spots[j+1][5],(int)spots[j+1][6]);
        }
        go();
    }
    /**
     * INICIA EL RENDERIZADO DE LOS PERSONAJES
     */
    private void go()
    {
        Game.addScene(this);
        hero.addEnemys(enemy);
        map.start();
        hud.start();
        hero.setField(this);
        enemy.forEach((enem)->
        {
            enem.start();
        });
        hero.start();
        enemy.forEach((enem)->
        {
            enem.startI();
        });
        hero.startI();
    }
    /**
     * LIBERA AL BOSS
     */
    private void releaseBadGuy()
    {
        this.badGuy = new BadGuy(stage,hero,this);
        badGuy.place(spots[n-2][0], spots[n-2][1], spots[n-2][2], spots[n-2][3], spots[n-2][4], (int)spots[n-2][5], (int)spots[n-2][6]);
        enemy.add(badGuy);
        badGuy.start();
        badGuy.startI();
        startBattle();
    }
    /**
     * DICE SI LOS ALIENS HAN MUERTO
     * @return 
     */
    private boolean deadAliens()
    {
        boolean result = false;
        
        result = en.stream().map((c) -> c.isAlive()).reduce(result, (accumulator, alien) -> accumulator | alien);
        
        return !result;
    }
    /**
     * CARAGA EL SIGUIENTE NIVEL DE LA PARTIDA
     */
    private void loadNew()
    {
        hero.save();
        provider.unbindCommand(back);
        provider.unbindCommand(esc);
        provider.unbindCommand(up);
        provider.unbindCommand(down);
        provider.unbindCommand(right);
        provider.unbindCommand(left);
        provider.unbindCommand(w);
        provider.unbindCommand(a);
        provider.unbindCommand(s);
        provider.unbindCommand(d);
        provider.unbindCommand(q);
        provider.unbindCommand(e);
        provider.unbindCommand(space);
        provider.removeListener(this);
        hero.setStage((stage+1)>10?10:stage+1);
        SavedHero hs = hero.save();
        hs.reInventory();
        Hero h = new Hero (hs);
        HUD hudn = new HUD(h);
        Field field = new Field(h,((stage+1)>10?10:stage+1),hudn,hero.getHealthMax()-hero.getHealthCurrent());
        Game.removeSence(this);
        hud.end();
        map.end();
        enemy.forEach((enem)->
        {
            enem.end();
        });
        field.start();
    }
    /**
     * MALO MUERE
     */
    public void badDead ()
    {
        enemy.clear();
        if (stage < 10)
        {
            badGuy.revive();
        }
        else
        {
            gameEnded = true;
        }
        Game.getMedia().getSound(Media.SOUND.ALIEN1).play();
    }
    /**
     * ALICA ESTADOS A LAS BALAS
     * @param x
     * @param y
     * @return 
     */
    public float[] bulletControl(float x,float y)
    {
        return map.bulletControl(x, y);
    }
    /**
     * MALO HA MUERTO EN STAGE 10
     */
    public void setGameEnded ()
    {
        gameEnded = true;
    }
}
