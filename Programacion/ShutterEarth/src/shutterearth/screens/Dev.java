/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shutterearth.screens;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import shutterearth.characters.SavedHero;

/**
 *
 * @author mr.blissfulgrin
 */
public class Dev extends Thread
{
    private final DataInputStream input;
    private final AtomicBoolean control;
    private final SavedHero hero;
    
    public Dev (SavedHero hero)
    {
        input = new DataInputStream(System.in);
        control = new AtomicBoolean(true);
        this.hero = hero;
    }
    
    public void end ()
    {
        control.set(false);
        try
        {
            input.close();
            System.out.println("CLOSED CORRECTLY");
        }
        catch (IOException e)
        {
            System.out.println("ERROR CLOSING");
        }
    }
    
    @Override
    public void run()
    {
        String preData;
        String [] data;
        int amount;
        while (control.get())
        {
            System.out.print("Hello "+hero.getUser()+" what's your will? ");
            try
            {
                preData = input.readLine();
                if ((preData != null))
                {
                    data = preData.split(" ");
                    amount = Integer.parseInt(data[1]);
                    switch (data [0])
                    {
                        case "BULLETS":
                            hero.setBullets(amount);
                            break;
                        case "HEALTH":
                            hero.setHealth(amount);
                            break;
                        case "STAGE":
                            hero.setStage(amount<10?amount:9);
                            break;
                        case "GUN0":
                            hero.getInventory().get(0)[1] = amount<5?amount:4;
                            break;
                        case "GUN1":
                            hero.getInventory().get(1)[1] = amount<5?amount:4;
                            break;
                        case "GUN2":
                            hero.getInventory().get(2)[1] = amount<5?amount:4;
                            break;
                        case "GUN3":
                            hero.getInventory().get(3)[1] = amount<5?amount:4;
                            break;
                        case "GUN4":
                            hero.getInventory().get(4)[1] = amount<5?amount:4;
                            break;
                        default:
                            System.out.println("NON RECOGNIZED ACTION");
                            break;
                    }
                }
            }
            catch (IOException e)
            {
                System.out.println("INPUT ERROR");
            }
        }
    }
}
