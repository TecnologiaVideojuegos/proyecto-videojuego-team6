/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AliensDriveMeCrazy;

import AliensDriveMeCrazy.Characters.Hero;
import AliensDriveMeCrazy.Characters.SavedHero;
import AliensDriveMeCrazy.Guns.Bullets;
import AliensDriveMeCrazy.Guns.Inventory;
import AliensDriveMeCrazy.Guns.Wearpon;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author mr.blissfulgrin
 */
public class SavingStation implements Serializable
{    
    //No interesan guardar el objeto entero, solo sus metadatos, Image de Slik no es Serializable
    public static Hero load ()
    { 
        Hero hero;
        SavedHero heroSaved;
        try
        {
            FileInputStream fis;
            ObjectInputStream ois;
            fis = new FileInputStream("./src/saves/Hero.dat");
            ois = new ObjectInputStream(fis);
            heroSaved = (SavedHero) ois.readObject();
            hero = new Hero(heroSaved);
            ois.close(); 
            fis.close();
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("NEW HERO CREATED");
            Bullets bullets = new Bullets(10,"./src/img/BULLET.jpg",1, 80);
            Bullets bullets2 = new Bullets(0,"./src/img/BULLET.jpg",1, 150);
            Wearpon wearpon = new Wearpon("./src/img/WEARPON.jpg",bullets,0);
            Wearpon wearpon2 = new Wearpon("./src/img/WEARPON.jpg",bullets2,1);
            Inventory inventory = new Inventory();
            inventory.addWearpon(wearpon);
            inventory.addWearpon(wearpon2);
            hero = new Hero (inventory);
        } 
        catch (IOException | ClassNotFoundException ex)
        {
            System.out.println("ERROR LOADING" + ex.toString());
            Bullets bullets = new Bullets(10,"./src/img/BULLET.jpg",1, 80);
            Bullets bullets2 = new Bullets(0,"./src/img/BULLET.jpg",1, 150);
            Wearpon wearpon = new Wearpon("./src/img/WEARPON.jpg",bullets,0);
            Wearpon wearpon2 = new Wearpon("./src/img/WEARPON.jpg",bullets2,1);
            Inventory inventory = new Inventory();
            inventory.addWearpon(wearpon);
            inventory.addWearpon(wearpon2);
            hero = new Hero (inventory);
        }
        return hero;
    }
    
    public static void save (Hero hero)
    {
        try
        {
            FileOutputStream fos;
            ObjectOutputStream oos;
            fos = new FileOutputStream("./src/saves/Hero.dat");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(hero.save());
            oos.close();
            fos.close();
            System.out.println("HERO SAVED");
        } 
        catch (FileNotFoundException ex)
        {
            System.out.println("ERROR SAVING1");
        } 
        catch (IOException ex)
        {
            System.out.println("ERROR SAVING2");
        }
    }
}
