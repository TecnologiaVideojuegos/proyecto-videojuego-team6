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

        while (control.get())
        {
            try
            {
                System.out.print("Hello "+hero.getUser()+" what's your will? ");
                preData = input.readLine();
                if(hero.getPermission())
                {
                    if ((preData != null))
                    {
                        data = preData.split(" ");
                        switch (data [0].toUpperCase())
                        {
                            case "DEBUG":
                                    Game.setDebug(data[1].trim().equals("1"));
                                break;
                            case "BULLETS":
                                if (Integer.parseInt(data[1].trim())>=0)
                                    hero.setBullets(Integer.parseInt(data[1].trim()));
                                break;
                            case "HEALTH":
                                if (Integer.parseInt(data[1].trim())>=0)
                                    hero.setHealth(Integer.parseInt(data[1]));
                                break;
                            case "STAGE":
                                if (Integer.parseInt(data[1].trim())>=0)
                                {
                                    for (int x = hero.getStage(); x < ((Integer.parseInt(data[1].trim())<10?Integer.parseInt(data[1].trim()):9)/2)+1; x++)
                                    {
                                        if (hero.getInventory().get(x)[1]<1)
                                            hero.getInventory().get(x)[1] = 1;
                                    }
                                    hero.setStage(Integer.parseInt(data[1].trim())<10?Integer.parseInt(data[1].trim()):9);
                                }
                                break;
                            case "GUN":
                                if (Integer.parseInt(data[1].trim())>=0 && Integer.parseInt(data[2].trim())>=0)
                                    hero.getInventory().get(Integer.parseInt(data[1].trim())<5?Integer.parseInt(data[1].trim()):4)[1] = Integer.parseInt(data[2].trim())<5?Integer.parseInt(data[2].trim()):4;
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
                                if (h!=null)
                                {
                                    System.out.println("------------------------------");
                                    System.out.println("Hero: "+h.getUser());
                                    System.out.println("Kills: "+h.getKills());
                                    System.out.println("BULLETS: "+h.getBullets());
                                    System.out.println("HEALTH: "+h.getHealthMax());
                                    System.out.println("GUNS: "+h.getNumberOfGuns());
                                    System.out.println("STAGE: "+h.getStage());
                                    System.out.println("INVENTORY:");
                                    for (int j = 0; j<h.getInventory().size(); j++)
                                    {
                                        for (int i = 0; i<h.getInventory().get(j).length; i++)
                                        {
                                            System.out.print(h.getInventory().get(j)[i]+ "  ");
                                        }
                                        System.out.println("");
                                    }
                                    System.out.println("------------------------------");
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
            catch (IOException | NumberFormatException | ArrayIndexOutOfBoundsException | NullPointerException e)
            {
                System.out.println("INPUT ERROR");
                System.out.println(e.toString());
            }
        }

    }
    
    public void setHero(SavedHero hero)
    {
        this.hero = hero;
    }
}
