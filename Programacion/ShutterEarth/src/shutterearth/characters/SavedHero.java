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
public class SavedHero
{
    private final String user;
    private final String pswd;
    private final Boolean permission;
    
    public SavedHero(String user, String pswd, Boolean permission)
    {
        this.user = user;
        this.pswd = pswd;
        this.permission = permission;
    }
    public SavedHero(Hero hero)
    {
        this.user = hero.getUser();
        this.pswd = hero.getPswd();
        this.permission = hero.getPermission();
    }
    
    public String getUser ()
    {
        return user;
    }
    
    public String getPswd ()
    {
        return pswd;
    }
    
    public Boolean getPermission ()
    {
        return permission;
    }
}
