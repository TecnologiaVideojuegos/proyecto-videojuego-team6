/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AliensDriveMeCrazy.Characters;

import AliensDriveMeCrazy.Guns.SavedInventory;
import java.io.Serializable;

/**
 *
 * @author mr.blissfulgrin
 */
public class SavedHero implements Serializable
{
    private final int kills;
    private final int stage;
    private final String[] img;
    private final SavedInventory inventory;
    private final int healthMax;
    private final int money;
    
    public SavedHero (int kills, int stage, int money, int healthMax, SavedInventory inventory, String[] img)
    {
        this.kills = kills;
        this.stage = stage;
        this.img = img;
        this.inventory = inventory;
        this.healthMax = healthMax;
        this.money = money;
    }
    
    public int getKills ()
    {
        return kills;
    }
    public int getStage ()
    {
        return stage;
    }
    public int getHeathMax ()
    {
        return healthMax;
    }
    public int getMoney ()
    {
        return money;
    }
    public String[] getImg ()
    {
        return img;
    }
    public SavedInventory getSavedInventory ()
    {
        return inventory;
    }
}
