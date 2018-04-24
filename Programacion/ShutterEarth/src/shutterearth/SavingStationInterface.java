/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shutterearth;

import shutterearth.characters.Hero;
import shutterearth.characters.SavedHero;

/**
 *
 * @author mr.blissfulgrin
 */
public interface SavingStationInterface
{
    public SavedHero load (String user, String pswd);
    public boolean add (Hero hero);
    public void save ();
}
