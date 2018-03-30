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
        super(new String [] {"./src/img/CHARACTER.png"}, inventory, 3, 100,100);
    }
}
