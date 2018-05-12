/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shutterearth.characters;

import java.util.ArrayList;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author mr.blissfulgrin
 */
public interface Charact
{
    public int getCurrentHealth ();
    public void goUp();
    public void goLeft();
    public void goRight();
    public void goDown();
    public void shot();
    public void getDamage (int damage);
    public boolean isAlive ();
    public Rectangle getLine ();
    public void place (float floor, int left, int right);
    public void setBounds (float left, float right, float floor);
    public Rectangle getColum ();
    public Rectangle getBox ();
    public boolean isInLine (Rectangle rect);
    public boolean isInRoom (Rectangle rect);
    public boolean isHited (Rectangle rect);
    public float getH ();
    public float getW ();
    public Rectangle[] debug ();
    public Inventory getInventory ();
    public float getY();
    public float getX();
    public void doShotAnimation();
    public boolean getFace();
    public void start ();
    public void end ();
    public void pause ();
    public void wake ();
    public void addEnemys (ArrayList <Charact> enemy);
    public void startI();
}
