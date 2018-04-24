/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shutterearth.characters;

/**
 *
 * @author mr.blissfulgrin
 */
public class Hero
{
    private final String user;
    private final String pswd;
    
    public Hero(SavedHero hero)
    {
        this.user = hero.getUser();
        this.pswd = hero.getPswd();
    }
    
    public String getUser ()
    {
        return user;
    }
    
    public String getPswd ()
    {
        return pswd;
    }
    
    public SavedHero save()
    {
        return new SavedHero(this);
    }
}
