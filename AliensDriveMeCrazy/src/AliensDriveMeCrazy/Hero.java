/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AliensDriveMeCrazy;

/**
 *
 * @author mr.blissfulgrin
 */
public class Hero extends Character
{

    
    public Hero(Inventory inventory)
    {
        super(new String [] {"./src/img/CHARACTER.png"}, inventory, 1, 100,100);
    }

    @Override
    public void move(int control)
    {
        x += xVel*control;
        y += (yVel - gravity)*control;
    }

    @Override
    public void shot(int control)
    {
        
    }
    
    public void UP ()
    {
        yVel = -10;
    }
    public void DOWN ()
    {
        yVel = -4;
        jump = true;
    }
    public void LEFT ()
    {
        xVel = -1;
    }
    public void RIGHT ()
    {
        xVel = 1;
    }
    
    public void SHOT()
    {
        inventory.shot();
    }
}
