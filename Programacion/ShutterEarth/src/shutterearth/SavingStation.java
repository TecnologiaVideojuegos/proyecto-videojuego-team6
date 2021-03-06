/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shutterearth;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import shutterearth.characters.Hero;
import shutterearth.characters.SavedHero;

/**
 *
 * @author mr.blissfulgrin
 */
public class SavingStation
{
    private HashMap <String,SavedHero> saves;
    
    public SavingStation ()
    {
        try
        {
            FileInputStream fis;
            ObjectInputStream ois;
            fis = new FileInputStream("./saves/saves.dat");
            ois = new ObjectInputStream(fis);
            saves = (HashMap <String,SavedHero>) ois.readObject();
            ois.close(); 
            fis.close();
            System.out.println("DATA LOADED");
        }
        catch (IOException | ClassNotFoundException ex)
        {   
            System.out.println("CREATING BASE DATA");
            ArrayList <SavedHero> base = new ArrayList <> ();
            SavedHero Juan = new SavedHero ("GS7", "qwerty77", true);
            SavedHero UndMe = new SavedHero ("UndMe", "qwerty", true);
            base.add(Juan);
            base.add(UndMe);
            
            saves = new HashMap <> ();
            base.forEach((h) ->
            {
                saves.put(h.getUser(), h);
            });
        }
    }
    /**
     * CARGA UN HEROE
     * @param user
     * @param pswd
     * @return 
     */
    public SavedHero load (String user, String pswd)
    { 
        SavedHero hero = null;
        
        if (saves.containsKey(user) && saves.get(user).getPswd().equals(pswd))
        {
            hero = saves.get(user);
        }
        
        return hero;
    }
    /**
     * DEBUGG SOLO DESARROLLADORES
     * @param user
     * @return 
     */
    public SavedHero load (String user)
    { 
        SavedHero hero = null;
        
        if (saves.containsKey(user))
        {
            hero = saves.get(user);
        }
        
        return hero;
    }
    /**
     * AÑADE UN NUEVO HEROE
     * @param hero
     * @return FALSO SI NO SE PUDO AÑADIR
     */
    public boolean add (SavedHero hero)
    {
        if (!saves.containsKey(hero.getUser()))
        {
            saves.put(hero.getUser(), hero);
            this.save();
            return true;
        }
        return false;
    }
    
    /**
     * GUARDA UN HEROE
     * @param hero 
     */
    public void save (Hero hero)
    {
        saves.remove(hero.getUser());
        saves.put(hero.getUser(), hero.save());
        this.save();
    }
    /**
     * GUARDA UN HEROE
     * @param hero 
     */
    public void save (SavedHero hero)
    {
        saves.remove(hero.getUser());
        saves.put(hero.getUser(), hero);
        this.save();
    }
    /**
     * GUARDA TODOS LOS REGISTROS
     */
    public void save ()
    {
        try
        {
            FileOutputStream fos;
            ObjectOutputStream oos;
            fos = new FileOutputStream("./saves/saves.dat");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(saves);
            oos.close();
            fos.close();
            //System.out.println("DATA SAVED");
        } 
        catch (IOException ex)
        {
            System.out.println("ERROR SAVING");
        }
    }
}
