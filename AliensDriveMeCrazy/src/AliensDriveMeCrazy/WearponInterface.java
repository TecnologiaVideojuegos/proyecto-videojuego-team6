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
public interface WearponInterface
{
    public boolean isShotable();
    public int shot();
    public int getAmount();
    public void refill ();
    public void setAmountMax (int amount);
}
