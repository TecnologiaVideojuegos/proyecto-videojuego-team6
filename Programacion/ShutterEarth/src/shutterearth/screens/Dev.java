/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shutterearth.screens;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import shutterearth.Game;
import shutterearth.characters.SavedHero;

/**
 *
 * @author mr.blissfulgrin
 */
public class Dev extends Thread
{
    private final DataInputStream input;
    private final AtomicBoolean control;
    private SavedHero hero;
    
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
        try
        {
            while (control.get())
            {
                System.out.print("Hello "+hero.getUser()+" what's your will? ");
                preData = input.readLine();
                if(hero.getPermission())
                {
                    if ((preData != null))
                    {
                        data = preData.split(" ");
                        switch (data [0])
                        {
                            case "BULLETS":
                                hero.setBullets(Integer.parseInt(data[1]));
                                break;
                            case "HEALTH":
                                hero.setHealth(Integer.parseInt(data[1]));
                                break;
                            case "STAGE":
                                hero.setStage(Integer.parseInt(data[1])<10?Integer.parseInt(data[1]):9);
                                break;
                            case "GUN0":
                                hero.getInventory().get(0)[1] = Integer.parseInt(data[1])<5?Integer.parseInt(data[1]):4;
                                break;
                            case "GUN1":
                                hero.getInventory().get(1)[1] = Integer.parseInt(data[1])<5?Integer.parseInt(data[1]):4;
                                break;
                            case "GUN2":
                                hero.getInventory().get(2)[1] = Integer.parseInt(data[1])<5?Integer.parseInt(data[1]):4;
                                break;
                            case "GUN3":
                                hero.getInventory().get(3)[1] = Integer.parseInt(data[1])<5?Integer.parseInt(data[1]):4;
                                break;
                            case "GUN4":
                                hero.getInventory().get(4)[1] = Integer.parseInt(data[1])<5?Integer.parseInt(data[1]):4;
                                break;
                            case "SCOPE":
                                SavedHero h = null;
                                if (!"this".equals(data[1]))
                                {
                                    h = Game.load(data[1]);
                                }
                                if (h == null)
                                {
                                    h = this.hero;
                                }
                                System.out.println("Hero: "+h.getUser());
                                System.out.println("Kills: "+h.getKills());
                                System.out.println("BULLETS: "+h.getBullets());
                                System.out.println("HEALTH: "+h.getHealthMax());
                                System.out.println("GUNS: "+h.getNumberOfGuns());
                                System.out.println("STAGE: "+h.getStage());
                                for (int j = 0; j<h.getInventory().size(); j++)
                                {
                                    for (int i = 0; i<h.getInventory().get(j).length; i++)
                                    {
                                        System.out.print(h.getInventory().get(j)[i]+ "  ");
                                    }
                                    System.out.println("");
                                }

                                break;
                            default:
                                System.out.println("NON RECOGNIZED ACTION");
                                break;
                        }
                    }
                }
                else
                    System.out.println("YOU HAVE NOT PERMISION");
            }
        }
        catch (IOException | ArrayIndexOutOfBoundsException e)
        {
            System.out.println("INPUT ERROR");
            System.out.println(e.toString());
        }
    }
    
    public void setHero(SavedHero hero)
    {
        this.hero = hero;
    }
}
